package cn.net.perfect.storage.mq;

import cn.net.perfect.storage.domain.log.po.Dt50BaseLog;
import cn.net.perfect.storage.util.JsonUtils;
import cn.net.perfect.storage.util.spring.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author ChangLee
 */
@Slf4j
@AllArgsConstructor
@Component
public class Dt50BaseLogBatch {

    private static final String msgType = "dt50BaseLogQueue";

    @JmsListener(destination = msgType, containerFactory = "jmsQueueListenerContainerFactory")
    public void receiveTopic(String text) {
        Dt50BaseLog baseLog = JsonUtils.parseObject(text, Dt50BaseLog.class);
        SpringUtils.context().publishEvent(baseLog);
    }

}
