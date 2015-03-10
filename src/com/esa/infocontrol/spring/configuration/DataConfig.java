package com.esa.infocontrol.spring.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataConfig {
	
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder(10); 
		return encoder;
	}
	
	@Bean
	public DataSource baseData() {
		Context initCtx;
		DataSource ds = null;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/InfoControl");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return ds;

	}

	/*
	@Bean(name = "dataSource")
	public DataSource baseData() {
		System.out.println("----------------------->");
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost:3306/infocontrol");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("root");
		p.setPassword("escorpion");
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
		datasource.setPoolProperties(p);
		try {
			Connection con = datasource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            int cnt = 1;
            while (rs.next()) {
                System.out.println((cnt++)+". User:"+rs.getString("username")+" Password:"+rs.getString("password"));
            }
            rs.close();
            st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("*+*+*+*+*+*+*+*+*+*+*+*");
		return datasource;

	}
*/
}
