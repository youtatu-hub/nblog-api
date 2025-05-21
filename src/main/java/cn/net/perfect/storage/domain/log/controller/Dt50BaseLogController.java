package cn.net.perfect.storage.domain.log.controller;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.service.Dt50BaseLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChangLee
 */
@RestController
@RequestMapping("/terminal/base/log")
public class Dt50BaseLogController {

    @Autowired
    private Dt50BaseLogService dt50BaseLogServive;

    @PostMapping("/save")
    public String saveLog() {
        Dt50BaseLog dt50Log = new Dt50BaseLog();
        dt50Log.setLogContent("我是接口测试");
        dt50Log.setInsertTime(System.currentTimeMillis());
        dt50Log.setLogTime(1706499359000L);
        dt50Log.setTid(26850L);
        dt50Log.setGroupId(2);
        dt50Log.setInsertTime(System.currentTimeMillis());
        System.out.println(dt50Log);
        return "ok";
    }

    @PostMapping("/pageList")
    public Page<Dt50BaseLog> pageList(@RequestBody Dt50BaseLogDTO queryDto) {
        return dt50BaseLogServive.getPageList(queryDto);
    }

    @PostMapping("/getDataInterval")
    public List<String> getTerminalDataInterval(@RequestBody Dt50BaseLogDTO queryDto) {
        queryDto.setStartTime(System.currentTimeMillis() - 60 * 60 * 24 * 7 * 1000);
        queryDto.setEndTime(System.currentTimeMillis());
        return dt50BaseLogServive.getTerminalDataInterval(queryDto);
    }


}
