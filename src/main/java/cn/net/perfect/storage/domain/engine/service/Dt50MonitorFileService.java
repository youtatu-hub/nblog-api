package cn.net.perfect.storage.domain.engine.service;

import cn.net.perfect.storage.domain.engine.DTO.Dt50MonitorFileDTO;
import cn.net.perfect.storage.domain.engine.po.Dt50MonitorFilePO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author youta
 * @version v1.0
 * @project dt50-storage-tdengine-main
 * @package cn.net.perfect.storage.domain.engine.service
 * @company 北交信飞
 * @date 2024/11/13 16:32
 */
public interface Dt50MonitorFileService {
    boolean saveMonitorFiles(List<Dt50MonitorFilePO> monitorFileList);

    boolean updateMonitorFiles(Dt50MonitorFilePO monitorFile);

    Page<Dt50MonitorFilePO> queryMonitorFiles(Dt50MonitorFileDTO dto);
}
