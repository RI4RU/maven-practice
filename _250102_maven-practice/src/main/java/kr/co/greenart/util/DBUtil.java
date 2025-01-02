package kr.co.greenart.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import kr.co.greenart.db.DBTimeMapper;

public class DBUtil {
	private static DataSource dataSource;
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		initDataSource();
		initSqlSessionFactory();
	}

	
	private static void initDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/my_db");
		ds.setUsername("root");
		ds.setPassword("root");
		
		ds.setInitialSize(0); // 앱 시작시 0개의 커넥션 생성
		ds.setMaxTotal(8); // 최대 8개의 커넥션을 유지
		ds.setMaxIdle(8); // 유휴상태의 최대 커넥션 갯수
		ds.setMinIdle(0); // 유휴상태의 최소 커넥션 갯수
		
		dataSource = ds;
	}
	
	private static void initSqlSessionFactory() {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMappers("kr.co.greenart.db");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}
	
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	public static void driverLoad() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
