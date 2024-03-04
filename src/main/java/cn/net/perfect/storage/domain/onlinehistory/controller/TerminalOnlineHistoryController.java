package cn.net.perfect.storage.domain.onlinehistory.controller;

import cn.net.perfect.storage.domain.onlinehistory.dto.Dt50OnlineHistoryQuery;
import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
import cn.net.perfect.storage.domain.onlinehistory.service.TerminalOnlineHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChangLee
 */
@RestController
@RequestMapping("/terminal/online/history")
public class TerminalOnlineHistoryController {

    @Autowired
    private TerminalOnlineHistoryService terminalOnlineHistoryService;

    @RequestMapping("/getList")
    public List<Dt50OnlineHistory> getList(@RequestBody Dt50OnlineHistoryQuery onlineHistoryQuery) {
        return terminalOnlineHistoryService.getList(onlineHistoryQuery);
    }

}
