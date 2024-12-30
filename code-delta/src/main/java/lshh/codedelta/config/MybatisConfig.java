package lshh.codedelta.config;

import lshh.core.lib.component.persistence.MyBatisInterceptor;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MybatisConfig {

    @Bean
    public MyBatisInterceptor myBatisInterceptor() {
        return new MyBatisInterceptor(List.of(SqlCommandType.SELECT));
    }
}
