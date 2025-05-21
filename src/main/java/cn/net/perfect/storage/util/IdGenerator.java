package cn.net.perfect.storage.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ChangLee
 */
public class IdGenerator {
    private final AtomicLong idGenerator;

    public IdGenerator(long initialId) {
        this.idGenerator = new AtomicLong(initialId);
    }

    public long getNextId() {
        return idGenerator.incrementAndGet();
    }
}
