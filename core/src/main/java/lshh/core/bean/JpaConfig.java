package lshh.core.bean;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import lshh.core.lib.component.persistence.HibernateSessionFactoryInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
	private final JpaProperties jpaProperties;

	@Value("${spring.datasource.main.package}")
	private String packageToScan;

	public JpaConfig(JpaProperties jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
		DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
		properties.put("hibernate.session_factory.interceptor", new HibernateSessionFactoryInterceptor());
		properties.put("hibernate.session_factory.statement_inspector", new HibernateSessionFactoryInterceptor());

		return builder
			.dataSource(dataSource)
			.properties(properties)
			.packages(packageToScan)
			.build();
	}
}
