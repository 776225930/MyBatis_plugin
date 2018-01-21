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
 * ��ɲ��ǩ��
 * 		����mybatis��������Ǹ�������ĸ�����
 * @author JHao
 *
 */
@Intercepts({@Signature(args = java.sql.Statement.class, method ="parameterize", type = StatementHandler.class)})
public class FirstPlugin implements Interceptor{
    /**
     * ����Ŀ�����ķ�����ִ��
     */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("FirstPlugin-->intercept: "+invocation.getMethod());
		 //��̬�ı�sql���в���,�Ѳ�ѯ1�Ÿ�Ϊ��ѯ3��
		Object target = invocation.getTarget();
		System.out.println("��ǰ���ص���Ŀ�����: "+target);
		//�õ�StatementHandler==>ParameterHandler==>parameterObject
		//�õ�Ԫ����
		 MetaObject metaObject = SystemMetaObject.forObject(target);
		 Object value = metaObject.getValue("parameterHandler.parameterObject");
		 System.out.println("sql����õĲ��� : "+value);
		 //�޸�sql���Ĳ���
		 metaObject.setValue("parameterHandler.parameterObject", 11);
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
		System.out.println("FirstPlugin-->plugin: mybatis��Ҫ��װ�Ķ���"+target );
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
