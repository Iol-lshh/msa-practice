package lshh.pollservice.common.bean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
public class DefaultDatabaseConfig {
    private final HibernateProperties hibernateProperties;
    private final JpaProperties jpaProperties;

    public DefaultDatabaseConfig(HibernateProperties hibernateProperties, JpaProperties jpaProperties) {
        this.hibernateProperties = hibernateProperties;
        this.jpaProperties = jpaProperties;
    }

    @Primary
    @Bean(name="defaultDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("defaultDataSource") DataSource dataSource) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings()
        );
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("lshh.pollservice.domain.entity")
                .build();
    }

    @Primary
    @Bean(name="transactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Primary
    @Bean(name="entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder, @Qualifier("defaultDataSource") DataSource dataSource) {
        return Objects.requireNonNull(entityManagerFactory(builder, dataSource).getObject())
                .createEntityManager();
    }
}
