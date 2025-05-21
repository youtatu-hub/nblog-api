package cn.net.perfect.storage.mq;

import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.domain.log.po.Dt50BaseNewLog;
import cn.net.perfect.storage.domain.log.service.Dt50BaseLogService;
import cn.net.perfect.storage.domain.log.service.Dt50BaseNewLogService;
import cn.net.perfect.storage.util.JsonUtils;
import cn.net.perfect.storage.util.UniqueTimestamp;
import com.alibaba.fastjson.JSONObject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author ChangLee
 */
@Slf4j
@Component
public class Dt50BaseLogBatch implements MessageListener {

    private static final String msgType = "dt50BaseLogQueue";

    int errorCount = 0;

    @Autowired
    private Dt50BaseLogService dt50BaseLogServive;

    @Autowired
    private Dt50BaseNewLogService dt50BaseNewLogService;


    @SuppressWarnings("all")
    @JmsListener(destination = msgType, containerFactory = "jmsQueueListenerContainerFactory")
    @Override
    public void onMessage(Message message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        try {
            TextMessage testMessage = (TextMessage) message;
            String msg = testMessage.getText();
            //log.info("原始数据包::"+ msg);
            Dt50BaseLog baseLog = JsonUtils.parseObject(msg, Dt50BaseLog.class);
            baseLog.setInsertTime(UniqueTimestamp.getUniqueTimestamp(baseLog.getTid()));

            Dt50BaseNewLog baseNewLog = JsonUtils.parseObject(msg, Dt50BaseNewLog.class);
            baseNewLog.setInsertTime(System.currentTimeMillis());
            // 支持复合主键的版本
            // && dt50BaseNewLogService.saveLog(baseNewLog)
            if(dt50BaseLogServive.saveLog(baseLog)) {
                message.acknowledge();
                errorCount = 0;
            } else {
                throw new RuntimeException("插入失败.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCount++;
            if(errorCount == 6) {
                log.info("第六次插入失败:::" + e.getMessage());
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException ex) {
            }
        }
    }

    public static void main(String[] args) {
        Dt50BaseLog log = new Dt50BaseLog();
        log.setLogContent("1234");
        log.setLogTime(System.currentTimeMillis());
        log.setGroupId(1);
        log.setTid(25003L);
        System.out.println(JSONObject.toJSONString(log));
    }

}
