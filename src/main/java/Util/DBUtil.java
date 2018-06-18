package Util;

import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;



/**
 * ���ݿ����ӹ���
 */

public class DBUtil {
	//���ݿ����ӳ�
	private static BasicDataSource ds;
	
	
	static {
		try {
			//����config.properties
			Properties prop=new Properties();
			/*
			 * ���DBUtil�������
			 * ���������getResourceAsStream������������·������.properties�ļ�
			 * Ȼ�󷵻�һ��InputStream
			 */
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			//��ȡ��Ϣ����ʼ������
			String drivername=prop.getProperty("drivername");
			String url=prop.getProperty("url");
			String username=prop.getProperty("username");
			String password=prop.getProperty("password");
			int maxActive=Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait=Integer.parseInt(prop.getProperty("maxwait"));
			
			//��ʼ�����ӳ�
			/*
			 * <util:properties id="jdbc" location="classpath:config.properties"/>
			 *
			 * <bean id="ds" class="org.apache.commons.dbcp.BasicDataSource">
			 * 	<property name="driverClassName" value="#{jdbc.driver}"/>
			 * 
			 * 
			 * </bean>
			 */
			ds=new BasicDataSource();
			//DriverManager.getConnection()
			ds.setDriverClassName(drivername);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			
			//���������
			ds.setMaxActive(maxActive);
			//���ȴ�ʱ��
			ds.setMaxWait(maxWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * ���ݿ�����
	 */
	public static Connection getConnection() throws Exception {
		try {
			/*
			 * ���ӳ��ṩ�˻�ȡ���ӵķ���
			 * Connection getConnection()
			 * �÷����Ὣ���ӳ��еĿ������ӷ��أ�
			 * ����ǰ���ӳ���û�п������ӣ���ô
			 * �÷���������������ʱ����maxwaitָ��ֵ����
			 * �������ڼ����п������ӿ���������̷��ظ����ӣ�
			 * ����ʱ����û�п������ӣ���ô�÷������׳��쳣��
			 */
			return ds.getConnection();
		} catch (Exception e) {
			throw e;
		}
	}
}
