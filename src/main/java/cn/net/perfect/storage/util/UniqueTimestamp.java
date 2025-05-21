package cn.net.perfect.storage.util;

import lombok.SneakyThrows;

import java.util.concurrent.ConcurrentHashMap;

/**
 * TDEngine使用
 * 如果业务时间不能保证为唯一的ts时间。使用此方法来获取插入时间。
 * @author ChangLee
 */
public class UniqueTimestamp {
    private static final Object lock = new Object();
    private static final ConcurrentHashMap<Long, Long> deviceTimestamps = new ConcurrentHashMap<>();

    public static Long getUniqueTimestamp(Long deviceId) {
        synchronized (lock) {
            long currentTime = System.currentTimeMillis();
            long currentTimestamp = deviceTimestamps.getOrDefault(deviceId, currentTime);

            if (currentTime > currentTimestamp) {
                currentTimestamp = currentTime;
            } else {
                if((currentTimestamp % 1000) < 999) {
                    currentTimestamp++;
                } else {
                    return null;
                }
            }
            deviceTimestamps.put(deviceId, currentTimestamp);
            return currentTimestamp;
        }
    }

}
