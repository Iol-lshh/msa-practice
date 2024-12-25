package lshh.pollservice.common.bean;

import lshh.core.lib.util.ClockManager;
import lshh.core.lib.util.LocalClockManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {
    @Bean
    public ClockManager clockManager() {
        return new LocalClockManager();
    }
}
