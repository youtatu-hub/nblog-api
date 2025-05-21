package cn.net.perfect.storage.domain.engine.controller;

import cn.net.perfect.storage.domain.engine.DTO.Dt50MonitorFileDTO;
import cn.net.perfect.storage.domain.engine.po.Dt50MonitorFilePO;
import cn.net.perfect.storage.domain.engine.service.Dt50MonitorFileService;
import cn.net.perfect.storage.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youta
 * @version v1.0
 * @project dt50-storage-tdengine-main
 * @package cn.net.perfect.storage.domain.log.controller
 * @company 北交信飞
 * @date 2024/11/13 16:29
 */
@Slf4j
@RestController
@RequestMapping("/engine/monitor/file")
@RequiredArgsConstructor
public class Dt50MonitorFileController {
    private final Dt50MonitorFileService dt50MonitorFileService;

    @PostMapping("/save")
    public R<String> saveMonitorFile(@RequestBody List<Dt50MonitorFilePO> monitorFileList) {
        try {
            boolean isSaved = dt50MonitorFileService.saveMonitorFiles(monitorFileList);
            return isSaved ? R.success("数据保存成功") : R.error("数据保存失败");
        } catch (Exception e) {
            log.error("保存数据时发生异常", e);
            return R.error("发生异常：" + e.getMessage());
        }
    }

    @PostMapping("/update")
    public R<String> updateMonitorFile(@RequestBody Dt50MonitorFilePO monitorFile) {
        try {
            boolean isUpdate = dt50MonitorFileService.updateMonitorFiles(monitorFile);
            return isUpdate ? R.success("数据更新成功") : R.error("数据更新失败");
        } catch (Exception e) {
            log.error("更新数据时发生异常", e);
            return R.error("发生异常：" + e.getMessage());
        }
    }

    @PostMapping("/query")
    public Page<Dt50MonitorFilePO> queryMonitorFile(@RequestBody Dt50MonitorFileDTO dto) {
        return dt50MonitorFileService.queryMonitorFiles(dto);
    }
}
