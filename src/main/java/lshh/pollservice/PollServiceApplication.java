package lshh.pollservice;

import lshh.pollservice.common.InitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PollServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollServiceApplication.class, args);
        InitConfig.getInstance().initConfig();
    }

}
