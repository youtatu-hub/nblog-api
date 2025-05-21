package cn.net.perfect.storage.domain.engine.service.impl;

import cn.net.perfect.storage.domain.engine.DTO.Dt50MonitorFileDTO;
import cn.net.perfect.storage.domain.engine.mapper.Dt50MonitorFileMapper;
import cn.net.perfect.storage.domain.engine.po.Dt50MonitorFilePO;
import cn.net.perfect.storage.domain.engine.service.Dt50MonitorFileService;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
import cn.net.perfect.storage.util.DateUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author youta
 * @version v1.0
 * @project dt50-storage-tdengine-main
 * @package cn.net.perfect.storage.domain.engine.service.impl
 * @company 北交信飞
 * @date 2024/11/13 16:32
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Dt50MonitorFileServiceImpl implements Dt50MonitorFileService {
    private final Dt50MonitorFileMapper dt50MonitorFileMapper;

    @Override
    public boolean saveMonitorFiles(List<Dt50MonitorFilePO> monitorFileList) {
        return dt50MonitorFileMapper.insertMonitorFile(monitorFileList);
    }

    @Override
    public boolean updateMonitorFiles(Dt50MonitorFilePO monitorFile) {
        return dt50MonitorFileMapper.update(monitorFile);
    }

    @Override
    public Page<Dt50MonitorFilePO> queryMonitorFiles(Dt50MonitorFileDTO dto) {
        Page<Dt50MonitorFilePO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<Dt50MonitorFilePO> records = dt50MonitorFileMapper.selectMonitorFile(dto, page);
        page.setRecords(records);
        return page;
    }
}
