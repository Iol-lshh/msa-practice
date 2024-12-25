package lshh.pollservice;

import lshh.pollservice.common.bean.InitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "lshh.pollservice",
        "lshh.core.bean",
})
public class PollServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollServiceApplication.class, args);
        InitConfig.getInstance().initConfig();
    }

}
