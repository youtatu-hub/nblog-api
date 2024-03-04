package cn.net.perfect.storage.domain.log.mapper;

import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChangLee
 */
@Mapper
public interface Dt50BaseLogMapper extends BaseMapper<Dt50BaseLog> {

    @Insert("INSERT INTO log_t${tid}_${groupId} USING log TAGS (${tid}, ${groupId}) VALUES (#{insertTime}, #{logTime}, #{logContent})")
    int insertOne(Dt50BaseLog log);

}