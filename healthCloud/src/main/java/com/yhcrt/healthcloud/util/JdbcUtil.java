package com.yhcrt.healthcloud.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JDBC工具类
 */
public class JdbcUtil {

	/**
	 * 定义数据库的链接
	 */
	private Connection conn;
	/**
	 * 定义sql语句的执行对象
	 */
	private PreparedStatement pstmt;
	/**
	 * 定义查询返回的结果集合
	 */
	private ResultSet rs;

	/**
	 * 初始化
	 * 
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 */
	public JdbcUtil(String driver, String url, String username, String password) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByParams(String sql, List<?> params) throws SQLException {
		// 影响行数
		int result = -1;
		pstmt = conn.prepareStatement(sql);
		int index = 1;
		// 填充sql语句中的占位符
		if (null != params && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		return result > 0 ? true : false;
	}

	/**
	 * 查询多条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<Object, Object>> selectByParams(String sql, List<Object> params) throws SQLException {
		List<Map<Object, Object>> list = new ArrayList<>();
		int index = 1;
		pstmt = conn.prepareStatement(sql);
		if (null != params && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int colsLen = metaData.getColumnCount();
		while (rs.next()) {
			Map<Object, Object> map = new HashMap<>();
			for (int i = 0; i < colsLen; i++) {
				String colsName = metaData.getColumnName(i + 1);
				Object colsValue = rs.getObject(colsName);
				if (null == colsValue) {
					colsValue = "";
				}
				map.put(colsName, colsValue);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 释放连接
	 */
	public void release() {
		try {
			if (null != rs) {
				rs.close();
			}

			if (null != pstmt) {
				pstmt.close();
			}

			if (null != conn) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("释放数据库连接");
	}

}
