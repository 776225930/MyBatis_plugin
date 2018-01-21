package com.example.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.example.mybatis.dao.EmployeeMapper;
public class MyBatisTest {
	public SqlSessionFactory getSessionFactory() throws IOException{
		String resource="mybatis-config.xml";
		InputStream in=Resources.getResourceAsStream(resource);
	    return new SqlSessionFactoryBuilder().build(in);
	   
	}
	/**
	 * 插件原理
	 * 在四大对象创建时
	 * 1.每个创建出来的对象不是直接返回的,而是调用
	 *       interceptorChain.pluginAll(parameterHandler)返回
	 * 2.获取到所有的interceptor（拦截器）（插件需要实现的接口）
	 *      调用interceptor.plugin(target);返回target包装后的对象
	 * 3.插件机制，可以使用插件为目标创建一个代理对象：AOP（面向切面）
	 *          代理对象就可以拦截四大对象的每一个执行
	 * public Object pluginAll(Object target) {
    		for (Interceptor interceptor : interceptors) {
      			target = interceptor.plugin(target);
    			}
    			return target;
  			}
	 * @throws IOException
	 */
	/**
	 * 插件编写
	 * 1.编写Interceptor的实现类
	 * 2.使用@Intercepts注解完成插件签名
	 * 3.将写好的插件注册
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		SqlSession session=getSessionFactory().openSession();
		EmployeeMapper mapper=session.getMapper(EmployeeMapper.class);
		System.out.println(mapper.getEmpById(3));
	}

}
