package cn.net.perfect.storage.domain.onlinehistory.service;

import cn.net.perfect.storage.domain.onlinehistory.dto.Dt50OnlineHistoryQuery;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;

import java.util.List;

/**
 * @author ChangLee
 */
public interface TerminalOnlineHistoryService {

    boolean save(Dt50OnlineHistory dt50OnlineHistory);

    /**
     * 获取
     */
    List<Dt50OnlineHistory> getList(Dt50OnlineHistoryQuery onlineHistoryQuery);
}
