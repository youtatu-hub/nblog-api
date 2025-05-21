package cn.net.perfect.storage.domain.log.service.impl;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.mapper.Dt50BaseLogMapper;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.service.Dt50BaseLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChangLee
 */
@Slf4j
@Service
public class Dt50BaseLogServiceImpl implements Dt50BaseLogService {

    @Autowired
    private Dt50BaseLogMapper dt50BaseLogMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public boolean saveLog(Dt50BaseLog baseLog) {
        int insertCount = dt50BaseLogMapper.insertOne(baseLog);
        return insertCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Page<Dt50BaseLog> getPageList(Dt50BaseLogDTO dto) {
        Page<Dt50BaseLog> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        return dt50BaseLogMapper.selectPage(page, buildQueryWrapper(dto));
    }

    /**
     * 数据间隔分割 15分钟
     */
    final int intervalSec = 60 * 15 * 1000;

    @Override
    public List<String> getTerminalDataInterval(Dt50BaseLogDTO dto) {
        List<String> resultList = new ArrayList<>();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Dt50BaseLogMapper logMapper = session.getMapper(Dt50BaseLogMapper.class);
            try (Cursor<Dt50BaseLog> cursor = logMapper.streamingQueryContinuousInfo(dto)) {
                Long beginTime = null;
                Long lastTime = null;
                String str = null;
                for (Dt50BaseLog log : cursor) {
                    if(null == beginTime) {
                        beginTime = log.getLogTime();
                        lastTime = log.getLogTime();
                        str = beginTime + "_";
                        continue;
                    }
                    if(log.getLogTime() >= lastTime) {
                        if(lastTime + intervalSec < log.getLogTime()) {
                            resultList.add(str + lastTime);
                            beginTime = log.getLogTime();
                            str = beginTime +"_";
                        }
                    } else {
                        resultList.add(str + lastTime);
                    }
                    lastTime = log.getLogTime();
                }
                if(null != str && null != lastTime) {
                    resultList.add(str + lastTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private LambdaQueryWrapper<Dt50BaseLog> buildQueryWrapper(Dt50BaseLogDTO dto) {
        LambdaQueryWrapper<Dt50BaseLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(dto.getTid() != null, Dt50BaseLog::getTid, dto.getTid());
        lqw.gt(null != dto.getStartTime() , Dt50BaseLog::getInsertTime, dto.getStartTime());
        lqw.le(null != dto.getEndTime() , Dt50BaseLog::getInsertTime, dto.getEndTime());
        lqw.like(StringUtils.isNotBlank(dto.getContent()), Dt50BaseLog::getLogContent, dto.getContent());
        lqw.orderByDesc(Dt50BaseLog::getInsertTime);
        return lqw;
    }


    public boolean dt50BaseLogStorage(Dt50BaseLog baseLog) {
        return saveLog(baseLog);
    }

}
