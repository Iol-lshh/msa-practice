package lshh.core.lib.component.persistence;

import java.sql.Connection;
import java.util.List;

import lshh.core.lib.exception.PersistenceNotAllowedException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "core.sql.mybatis")
@Intercepts({
	@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyBatisInterceptor implements Interceptor {
	private final List<SqlCommandType> allowedCommands;

    public MyBatisInterceptor(List<SqlCommandType> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    @Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		BoundSql sql = statementHandler.getBoundSql();
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        log.debug("Mybatis-From --- {}", mappedStatement.getResource());
        log.debug("Mybatis-Query --- {}", sql.getSql());
		if (!sql.getAdditionalParameters().isEmpty()) {
            log.debug("Mybatis-Parameters --- {}", sql.getAdditionalParameters().toString());
		}
		if (!allowedCommands.contains(mappedStatement.getSqlCommandType())) {
			throw new PersistenceNotAllowedException();
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
}
