package cn.net.perfect.storage.domain.engine.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author youta
 * @version v1.0
 * @project dt50-storage-tdengine
 * @package cn.net.perfect.storage.domain.engine.DTO
 * @company 北交信飞
 * @date 2024/12/2 15:05
 */
@Data
public class Dt50MonitorFileDTO {
    /**
     * 车型
     */
    private Integer trainTypeId;
    /**
     * 车号
     */
    private String trainId;
    /**
     * 开始时间
     */
    private Date fileStartTime;
    /**
     * 结束时间
     */
    private Date fileEndTime;
    /**
     * 每次请求的当前秒时间戳/5的整数部分
     */
    private Long tm;

    private Integer pageNum;

    private Integer pageSize;
}
