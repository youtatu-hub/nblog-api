package cn.net.perfect.storage.domain.engine.po;

import lombok.Data;

/**
 * @author youta
 * @version v1.0
 * @project dt50-storage-tdengine-main
 * @package cn.net.perfect.storage.domain.engine.po
 * @company 北交信飞
 * @date 2024/11/13 16:38
 */
@Data
public class Dt50MonitorFilePO {
    /**
     * 车型号
     */
    private Integer trainTypeId;

    /**
     * 车号
     */
    private String trainId;

    /**
     * 车次
     */
    private String trainOrder;

    /**
     * 单位ID
     */
    private Long deptId;

    /**
     * 终端号
     */
    private Long terminalId;

    /**
     * 文件开始时间
     */
    private Long fileStartTime;


    /**
     * 文件结束时间
     */
    private Long fileEndTime;

    /**
     * 司机号
     */
    private String driverId;

    /**
     * 副司机号
     */
    private String viceDriverId;

    /**
     * 运输种别
     */
    private String transportCategory;

    /**
     * 文件长度
     */
    private String fileLength;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 总重
     */
    private String totalWeight;

    /**
     * 载重
     */
    private String loadWeight;

    /**
     * 计长
     */
    private String calculatedLength;

    /**
     * 辆数
     */
    private String numberOfVehicles;

    /**
     * 监控装置号
     */
    private String monitoringDeviceNumber;

    /**
     * 开始站号
     */
    private Integer startStationNumber;

    /**
     * 开始交路号
     */
    private Integer startTransferNumber;

    /**
     * 结束站号
     */
    private Integer endStationNumber;

    /**
     * 结束交路号
     */
    private Integer endTransferNumber;

    /**
     * 下载次数
     */
    private Integer downloadTimes;

    /**
     * 里程
     */
    private String mileage;

    /**
     * 文件ID
     */
    private Integer fileId;

    /**
     * 主轮径
     */
    private Float mainWheelDiameter;

    /**
     * 备轮径
     */
    private Float viceWheelDiameter;

    /**
     * 状态
     */
    private String statusValue;

    /**
     * 收到时间
     */
    private Long receiveTime;

    /**
     * 是否错误
     */
    private Integer isError;

    /**
     * 是否有检测项点
     */
    private Integer isCheckItem;

    /**
     * lkj类型
     */
    private String lkjType;

    /**
     * 区间
     */
    private String intervalValue;
    /**
     * 统计状态（0为未统计）
     */
    private Integer statStatus;
    /**
     * (A/Ⅰ)项点数
     */
    private Integer itemNum1;
    /**
     * B/Ⅱ项点数
     */
    private Integer itemNum2;
    /**
     * 文件统计时间
     */
    private Long statTime;

}
