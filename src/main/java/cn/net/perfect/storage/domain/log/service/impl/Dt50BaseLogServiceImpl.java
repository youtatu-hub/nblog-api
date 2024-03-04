package cn.net.perfect.storage.domain.log.service.impl;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.mapper.Dt50BaseLogMapper;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.service.Dt50BaseLogServive;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChangLee
 */
@Service
public class Dt50BaseLogServiceImpl implements Dt50BaseLogServive {

    @Autowired
    private Dt50BaseLogMapper dt50BaseLogMapper;

    @Override
    public boolean saveLog(Dt50BaseLog baseLog) {
        int insertCount = dt50BaseLogMapper.insertOne(baseLog);
        return insertCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<Dt50BaseLog> getList(Dt50BaseLogDTO dto) {
        Page<Dt50BaseLog> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        Page<Dt50BaseLog> pageResult = dt50BaseLogMapper.selectPage(page, buildQueryWrapper(dto));
        return pageResult.getRecords();
    }

    private LambdaQueryWrapper<Dt50BaseLog> buildQueryWrapper(Dt50BaseLogDTO dto) {
        LambdaQueryWrapper<Dt50BaseLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(dto.getTid() != null, Dt50BaseLog::getTid, dto.getTid());
        lqw.gt(null != dto.getStartTime() , Dt50BaseLog::getLogTime, dto.getStartTime());
        lqw.le(null != dto.getEndTime() , Dt50BaseLog::getLogTime, dto.getEndTime());
        lqw.like(StringUtils.isNotBlank(dto.getContent()), Dt50BaseLog::getLogContent, dto.getContent());
        return lqw;
    }

    @Async
    @EventListener
    public void Dt50BaseLogStorage(Dt50BaseLog baseLog) {
        saveLog(baseLog);
    }

}
