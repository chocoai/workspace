package com.smart.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class DBHelper {

	public DBHelper() {

	}

	public static Connection getConnection()
			throws IOException, ClassNotFoundException, SQLException {

		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("config.properties");
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);

		String jdbcDriver = properties.getProperty("dataSource.driverClass");
		String jdbcUrl = properties.getProperty("dataSource.url");
		String userName = properties.getProperty("dataSource.user");
		String password = properties.getProperty("dataSource.password");

		Class.forName(jdbcDriver);
		Connection conn = DriverManager.getConnection(jdbcUrl, userName,
				password);
		return conn;
	}

	public static void closeConnection(ResultSet rs, PreparedStatement ps,
			Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			Connection conn = DBHelper.getConnection();
			if (conn != null) {
				System.out.println("connect database success");
			} else {
				System.out.println("connect database failed");
			}
			
			closeConnection(null, null, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
