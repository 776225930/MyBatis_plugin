package com.example.mybatis.dao;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
/**
 * ��ɲ��ǩ��
 * 		����mybatis��������Ǹ�������ĸ�����
 * @author JHao
 *
 */
@Intercepts({@Signature(args = java.sql.Statement.class, method ="parameterize", type = StatementHandler.class)})
public class SecondPlugin implements Interceptor{
    /**
     * ����Ŀ�����ķ�����ִ��
     */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("SecondPlugin-->intercept: "+invocation.getMethod());
		//ִ��Ŀ�귽��
		Object proceed = invocation.proceed();
		//����ִ�к�ķ���ֵ
		return proceed;
	}
   /**
    * plugin:
    *    ��װĿ�����,ΪĿ����󴴽�һ���������
    */
	@Override
	public Object plugin(Object target) {
		System.out.println("SecondPlugin-->plugin: mybatis��Ҫ��װ�Ķ���"+target );
        //����Plugin���wrap������ʹ�õ�ǰInterceptor��װ���ǵ�Ŀ�����
		Object wrap=Plugin.wrap(target, this);
		//����Ϊ��ǰtarget��װ�õĴ������
		return wrap;
	}
    /**
     * �����ע���ǵ�property�������ý���
     */
	@Override
	public void setProperties(Properties properties) {
		     System.out.println("���������Ϣ: "+properties);
	}

}
