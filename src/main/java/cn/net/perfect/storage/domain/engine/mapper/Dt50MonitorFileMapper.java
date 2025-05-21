package cn.net.perfect.storage.domain.engine.mapper;

import cn.net.perfect.storage.domain.engine.DTO.Dt50MonitorFileDTO;
import cn.net.perfect.storage.domain.engine.po.Dt50MonitorFilePO;
import cn.net.perfect.storage.domain.onlinehistory.dto.Dt50OnlineHistoryQuery;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author youta
 * @version v1.0
 * @project dt50-storage-tdengine-main
 * @package cn.net.perfect.storage.domain.engine.mapper
 * @company 北交信飞
 * @date 2024/11/13 16:33
 */
@Mapper
public interface Dt50MonitorFileMapper extends BaseMapper<Dt50MonitorFilePO> {
    boolean insertMonitorFile(List<Dt50MonitorFilePO> monitorFileList);

    boolean update(Dt50MonitorFilePO monitorFile);

    List<Dt50MonitorFilePO> selectMonitorFile(@Param("dto") Dt50MonitorFileDTO dto, @Param("page") Page<Dt50MonitorFilePO> page);
}
