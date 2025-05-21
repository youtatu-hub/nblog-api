package cn.net.perfect.storage.domain.log.mapper;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.po.Dt50BaseNewLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;

/**
 * @author ChangLee
 */
@Mapper
public interface Dt50BaseNewLogMapper extends BaseMapper<Dt50BaseNewLog> {

    @Insert("INSERT INTO dt50_log_t${tid}_${groupId} USING dt50_log TAGS (${tid}, ${groupId}) VALUES (#{insertTime}, #{sequNo}, #{logTime}, #{logContent})")
    int insertOne(Dt50BaseNewLog log);

    Long selectMaxSequNo();

    Cursor<Dt50BaseNewLog> streamingQueryContinuousInfo(Dt50BaseLogDTO dto);


}