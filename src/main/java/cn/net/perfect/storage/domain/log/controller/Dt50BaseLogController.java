package cn.net.perfect.storage.domain.log.controller;

import cn.net.perfect.storage.domain.log.DTO.Dt50BaseLogDTO;
import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.service.Dt50BaseLogServive;
import cn.net.perfect.storage.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChangLee
 */
@RestController
@RequestMapping("/dt50/base/log")
public class Dt50BaseLogController {

    @Autowired
    private Dt50BaseLogServive dt50BaseLogServive;

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
    public List<Dt50BaseLog> pageList(@RequestBody Dt50BaseLogDTO queryDto) {
        return dt50BaseLogServive.getList(queryDto);
    }


}
