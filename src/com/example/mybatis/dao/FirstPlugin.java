package com.example.mybatis.dao;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
/**
 * 完成插件签名
 * 		告诉mybatis插件拦截那个对象的哪个方法
 * @author JHao
 *
 */
@Intercepts({@Signature(args = java.sql.Statement.class, method ="parameterize", type = StatementHandler.class)})
public class FirstPlugin implements Interceptor{
    /**
     * 拦截目标对象的方法的执行
     */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("FirstPlugin-->intercept: "+invocation.getMethod());
		 //动态改变sql运行参数,把查询1号改为查询3号
		Object target = invocation.getTarget();
		System.out.println("当前拦截到的目标对象: "+target);
		//拿到StatementHandler==>ParameterHandler==>parameterObject
		//拿到元数据
		 MetaObject metaObject = SystemMetaObject.forObject(target);
		 Object value = metaObject.getValue("parameterHandler.parameterObject");
		 System.out.println("sql语句用的参数 : "+value);
		 //修改sql语句的参数
		 metaObject.setValue("parameterHandler.parameterObject", 11);
		 //执行目标方法 
		Object proceed = invocation.proceed();
		//返回执行后的返回值
		return proceed;
	}
   /**
    * plugin:
    *    包装目标对象,为目标对象创建一个代理对象
    */
	@Override
	public Object plugin(Object target) {
		System.out.println("FirstPlugin-->plugin: mybatis将要包装的对象"+target );
		//借助Plugin类的wrap方法来使用当前Interceptor包装我们的目标对象
		Object wrap=Plugin.wrap(target, this);
		//返回为当前target包装好的代理对象
		return wrap;
	}
    /**
     * 将插件注册是的property属性设置进来
     */
	@Override
	public void setProperties(Properties properties) {
		     System.out.println("插件配置信息: "+properties);
	}

}
