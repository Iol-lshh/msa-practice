package lshh.core.bean;

import javax.sql.DataSource;

import lshh.core.lib.component.persistence.MyBatisInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, MyBatisInterceptor myBatisInterceptor) throws Throwable {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setPlugins(myBatisInterceptor);
		return factoryBean.getObject();
	}
}
