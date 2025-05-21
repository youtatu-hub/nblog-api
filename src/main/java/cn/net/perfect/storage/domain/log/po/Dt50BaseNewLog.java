package cn.net.perfect.storage.domain.log.po;

import cn.net.perfect.storage.DTO.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author ChangLee
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("dt50_log")
public class Dt50BaseNewLog extends BaseEntity {

    @TableField("insert_time")
    private Long insertTime;

    @TableField("sequ_no")
    private Long sequNo;

    @TableField("log_time")
    private Long logTime;

    @TableField("log_content")
    private String logContent;

    @TableField("tid")
    private Long tid;

    @TableField("groupId")
    private int groupId;

}
