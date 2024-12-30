package lshh.core.lib.component.persistence;

import org.hibernate.Interceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "core.sql.hibernate")
public class HibernateSessionFactoryInterceptor implements Interceptor, StatementInspector {

	@Override
	public String inspect(String sql) {
		log.debug("Hibernate-Query --- " + sql);
		return sql;
	}
}
