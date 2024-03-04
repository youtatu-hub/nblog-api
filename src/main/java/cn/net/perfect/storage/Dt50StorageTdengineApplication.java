package cn.net.perfect.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.net.perfect.storage.domain.**.mapper")
public class Dt50StorageTdengineApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dt50StorageTdengineApplication.class, args);
    }

}
