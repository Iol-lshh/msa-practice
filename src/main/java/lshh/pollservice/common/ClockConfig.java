package lshh.pollservice.common;

import lshh.pollservice.common.lib.ClockManager;
import lshh.pollservice.common.lib.LocalClockManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {
    @Bean
    public ClockManager clockManager() {
        return new LocalClockManager();
    }
}
