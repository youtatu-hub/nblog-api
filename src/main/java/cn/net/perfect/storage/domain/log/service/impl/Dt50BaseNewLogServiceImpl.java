package cn.net.perfect.storage.domain.log.service.impl;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.mapper.Dt50BaseLogMapper;
import cn.net.perfect.storage.domain.log.mapper.Dt50BaseNewLogMapper;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.po.Dt50BaseNewLog;
import cn.net.perfect.storage.domain.log.service.Dt50BaseNewLogService;
import cn.net.perfect.storage.util.IdGenerator;
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
public class Dt50BaseNewLogServiceImpl implements Dt50BaseNewLogService {

    @Autowired
    private Dt50BaseNewLogMapper dt50BaseNewLogMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private static volatile IdGenerator idGenerator;

    public IdGenerator getIdGenerator() {
        if (idGenerator == null) {
            synchronized (Dt50BaseNewLogServiceImpl.class) {
                if (idGenerator == null) {
                    Long initialId = dt50BaseNewLogMapper.selectMaxSequNo();
                    initialId = (initialId == null) ? 0L : initialId;
                    idGenerator = new IdGenerator(initialId);
                }
            }
        }
        return idGenerator;
    }


    @Override
    public boolean saveLog(Dt50BaseNewLog baseLog) {
        baseLog.setSequNo(getIdGenerator().getNextId());
        int insertCount = dt50BaseNewLogMapper.insertOne(baseLog);
        return insertCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Page<Dt50BaseNewLog> getPageList(Dt50BaseLogDTO dto) {
        Page<Dt50BaseNewLog> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        return dt50BaseNewLogMapper.selectPage(page, buildQueryWrapper(dto));
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

    private LambdaQueryWrapper<Dt50BaseNewLog> buildQueryWrapper(Dt50BaseLogDTO dto) {
        LambdaQueryWrapper<Dt50BaseNewLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(dto.getTid() != null, Dt50BaseNewLog::getTid, dto.getTid());
        lqw.gt(null != dto.getStartTime() , Dt50BaseNewLog::getLogTime, dto.getStartTime());
        lqw.le(null != dto.getEndTime() , Dt50BaseNewLog::getLogTime, dto.getEndTime());
        lqw.like(StringUtils.isNotBlank(dto.getContent()), Dt50BaseNewLog::getLogContent, dto.getContent());
        lqw.orderByDesc(Dt50BaseNewLog::getInsertTime);
        return lqw;
    }

}
