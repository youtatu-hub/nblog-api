package cn.net.perfect.storage.mq;

import cn.net.perfect.storage.domain.onlinehistory.po.Dt50OnlineHistory;
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
public class Dt50OnlineLogBatch {

    private static final String msgType = "dt50OnlineStatusChange";

    @JmsListener(destination = msgType, containerFactory = "jmsQueueListenerContainerFactory")
    public void receiveTopic(String text) {
        //tid:timestamp  # 设备号 ASCII_3A分隔 时间戳
        String[] values = text.split(",");
        String tid = values[0];
        String timestamp = values[1];
        Dt50OnlineHistory onlineHistory = new Dt50OnlineHistory();
        onlineHistory.setTid(Long.parseLong(tid));
        onlineHistory.setOnlineStatus(1);
        onlineHistory.setLogTime(Long.parseLong(timestamp));
        SpringUtils.context().publishEvent(onlineHistory);
    }

}
