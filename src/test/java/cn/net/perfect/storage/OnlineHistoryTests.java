package cn.net.perfect.storage;

import cn.net.perfect.storage.domain.onlinehistory.dto.Dt50OnlineHistoryQuery;
import cn.net.perfect.storage.domain.onlinehistory.mapper.Dt50OnlineHistoryMapper;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
import cn.net.perfect.storage.domain.onlinehistory.service.TerminalOnlineHistoryService;
import cn.net.perfect.storage.util.DateUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OnlineHistoryTests {

    @Autowired
    private Dt50OnlineHistoryMapper onlineHistoryMapper;

    @Autowired
    private TerminalOnlineHistoryService terminalOnlineHistoryService;


    @Test
    public void test1() {
        Date date = new Date();
        Dt50OnlineHistory onlineHistory = new Dt50OnlineHistory();
        onlineHistory.setOnlineStatus(1);
        onlineHistory.setTid(26507L);
        onlineHistory.setLogDay(DateUtils.formatDateTime(date, DateUtils.YEAR_MONTH_DAY_TIME_00_00_00_FORMAT).getTime());
        onlineHistory.setInsertTime(date.getTime());
        onlineHistory.setLogTime(date.getTime());
        onlineHistoryMapper.insertOne(onlineHistory);
    }

    @Test
    public void test2() {
        Dt50OnlineHistoryQuery query = new Dt50OnlineHistoryQuery();
        query.setPage(1);
        query.setPageSize(1);
        query.setTids("26507");
        List<Dt50OnlineHistory> list = terminalOnlineHistoryService.getList(query);
        System.out.println(list);
    }



}
