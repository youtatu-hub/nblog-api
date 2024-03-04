package cn.net.perfect.storage.domain.onlinehistory.service.impl;

import cn.net.perfect.storage.domain.onlinehistory.dto.Dt50OnlineHistoryQuery;
import cn.net.perfect.storage.domain.onlinehistory.mapper.Dt50OnlineHistoryMapper;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
import cn.net.perfect.storage.domain.onlinehistory.service.TerminalOnlineHistoryService;
import cn.net.perfect.storage.util.DateUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ChangLee
 */
@Service
public class TerminalOnlineHistoryServiceImpl implements TerminalOnlineHistoryService {

    @Autowired
    private Dt50OnlineHistoryMapper onlineHistoryMapper;

    @Override
    public boolean save(Dt50OnlineHistory onlineHistory) {
        return onlineHistoryMapper.insertOne(onlineHistory) == 1;
    }

    @Override
    public List<Dt50OnlineHistory> getList(Dt50OnlineHistoryQuery onlineHistoryQuery) {
        String[] split = onlineHistoryQuery.getTids().split(",");
        List<Long> terminalId = new ArrayList<>();
        for (String s : split) {
            terminalId.add(Long.parseLong(s));
        }
        onlineHistoryQuery.setTerminalIdList(terminalId);
        IPage<Dt50OnlineHistory> page = new Page<>(onlineHistoryQuery.getPage(), onlineHistoryQuery.getPageSize());
        return onlineHistoryMapper.getTerminalHistoryOnlineDays(onlineHistoryQuery, page).getRecords();
    }

    @Async
    @EventListener
    public void Dt50OnlineHistoryLogStorage(Dt50OnlineHistory onlineHistory) {
        onlineHistory.setLogDay(DateUtils.formatDateTime(new Date(onlineHistory.getLogTime()), DateUtils.YEAR_MONTH_DAY_TIME_00_00_00_FORMAT).getTime());
        onlineHistory.setInsertTime(System.currentTimeMillis());
        onlineHistory.setDataType(1);
        save(onlineHistory);
    }

}
