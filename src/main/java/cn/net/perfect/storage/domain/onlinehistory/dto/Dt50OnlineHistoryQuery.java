package cn.net.perfect.storage.domain.onlinehistory.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ChangLee
 */
@Data
public class Dt50OnlineHistoryQuery {

    private String tids;

    private Integer page;

    private Integer pageSize;

    private List<Long> terminalIdList;

}
