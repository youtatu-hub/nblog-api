package cn.net.perfect.storage.domain.log.service;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author ChangLee
 */
public interface Dt50BaseLogServive {

    boolean saveLog(Dt50BaseLog baseLog);

    List<Dt50BaseLog> getList(Dt50BaseLogDTO dto);
}
