package cn.net.perfect.storage.domain.onlinehistory.mapper;

import cn.net.perfect.storage.domain.onlinehistory.dto.Dt50OnlineHistoryQuery;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ChangLee
 */
@Mapper
public interface Dt50OnlineHistoryMapper extends BaseMapper<Dt50OnlineHistory> {

    @Insert("INSERT INTO online_histroy_log_t${tid} USING online_histroy_log TAGS (#{tid}) VALUES (#{logTime}, #{logDay}, #{insertTime}, #{onlineStatus}, #{dataType})")
    int insertOne(Dt50OnlineHistory onlineHistory);

    /**
     * 获取设备历史在线的日期（天）
     */
    Page<Dt50OnlineHistory> getTerminalHistoryOnlineDays(@Param("query") Dt50OnlineHistoryQuery onlineHistoryQuery, IPage<Dt50OnlineHistory> page);


}