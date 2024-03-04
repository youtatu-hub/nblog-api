package cn.net.perfect.storage.domain.log.DTO;

import cn.net.perfect.storage.DTO.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author ChangLee
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Dt50BaseLogDTO extends BaseEntity {

    private Long tid;

    private String content;

    private Long startTime;

    private Long endTime;

}
