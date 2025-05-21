package cn.net.perfect.storage.domain.log.service;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.po.Dt50BaseNewLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * @author ChangLee
 */
public interface Dt50BaseNewLogService {

    boolean saveLog(Dt50BaseNewLog baseLog);

    Page<Dt50BaseNewLog> getPageList(Dt50BaseLogDTO dto);

    /**
     * 获得终端数据间隔的时间信息
     */
    List<String> getTerminalDataInterval(Dt50BaseLogDTO dto);

}
