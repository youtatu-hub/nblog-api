package cn.net.perfect.storage.domain.log.service;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * @author ChangLee
 */
public interface Dt50BaseLogService {

    boolean saveLog(Dt50BaseLog baseLog);

    Page<Dt50BaseLog> getPageList(Dt50BaseLogDTO dto);

    /**
     * 获得终端数据间隔的时间信息
     */
    List<String> getTerminalDataInterval(Dt50BaseLogDTO dto);

}
