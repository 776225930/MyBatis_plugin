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
	 * ���ԭ��
	 * ���Ĵ���󴴽�ʱ
	 * 1.ÿ�����������Ķ�����ֱ�ӷ��ص�,���ǵ���
	 *       interceptorChain.pluginAll(parameterHandler)����
	 * 2.��ȡ�����е�interceptor�����������������Ҫʵ�ֵĽӿڣ�
	 *      ����interceptor.plugin(target);����target��װ��Ķ���
	 * 3.������ƣ�����ʹ�ò��ΪĿ�괴��һ���������AOP���������棩
	 *          �������Ϳ��������Ĵ�����ÿһ��ִ��
	 * public Object pluginAll(Object target) {
    		for (Interceptor interceptor : interceptors) {
      			target = interceptor.plugin(target);
    			}
    			return target;
  			}
	 * @throws IOException
	 */
	/**
	 * �����д
	 * 1.��дInterceptor��ʵ����
	 * 2.ʹ��@Interceptsע����ɲ��ǩ��
	 * 3.��д�õĲ��ע��
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		SqlSession session=getSessionFactory().openSession();
		EmployeeMapper mapper=session.getMapper(EmployeeMapper.class);
		System.out.println(mapper.getEmpById(3));
	}

}
