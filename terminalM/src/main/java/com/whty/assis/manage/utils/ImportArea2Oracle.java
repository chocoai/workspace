package com.whty.assis.manage.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whty.common.excel.FileVo;
import com.whty.common.excel.PoiUtil;

public class ImportArea2Oracle {

	private static String url_oracle_e = "jdbc:oracle:thin:@10.8.9.160:1521:orcl";
	private static String username_oracle_e = "teacherassistant";
	private static String password_oracle_e = "teacherassistant";

	private static String resFileFileName = "a_area.xls";
	private static String filePath = "E:/tmp/";
	private static int sort_num = 1;
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static String ids = "152531,360403,360421,360423,420321,420322,420323,420324,420325,430500,430501,430502,430682,430700,430701,430702,430703,430721,430722,430723,430724,430725,430726,430781,430800,430801,430802,430811,430821,430822,430900,430901,430902,430903,430921,430922,430923,430981,431000,431001,431002,431003,431021,431022,431023,431024,431025,431026,431027,431028,431081,431100,431101,431102,431103,431121,431122,431123,431124,431125,431126,431127,431128,431129,431200,431201,431202,431221,431222,431223,431224,431225,431226,431227,431228,431229,431230,431281,431300,431301,431302,431321,431322,431381,431382,433100,433101,433122,433123,433124,433125,433126,433127,433130,440000,440100,440101,440103,440104,440105,440106,440111,440112,440113,440114,440115,440116,440183,440184,440200,440201,440203,440204,440205,440222,440224,440229,440232,440233,440281,440282,440300,440301,440303,440304,440305,440306,440307,440308,440400,440401,440402,440403,440404,440500,440501,440507,440511,440512,440513,440514,440515,440523,440701,440703,533323,533324,533325,533400,533421,533422,533423,540000,540100,540101,540102,540121,540122,540123,540124,540125,540126,540127,542100,542121,542122,542123,542124,542422,542423,542424,542425,542426,542427,542428,542429,620822,";

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		int count = 0;
		// String ids = "";
		try {
			// 读取excel数据
			FileVo resfile = new FileVo();
			resfile.setFile(new File(filePath + resFileFileName));
			resfile.setFileFileName(resFileFileName);
			List reslist = PoiUtil.readerExcelSheet(resfile, 1);
			count = reslist.size();
			Map map = new HashMap();
			// Calendar calendar = Calendar.getInstance();
			// String year_month_day =
			// calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
			System.out.println("有：" + count + "条");
			for (int i = 0; i < count; i++) {
				String[] res = (String[]) reslist.get(i);
				if (res != null && res[0] != null) {
					// 插入数据
					// if((i+1)==461){
					// System.out.println("-----------------开始第" + (i+1) +
					// "条---------------------------");
					// map = queryTextbook(res);
					if (null == map || map.isEmpty()) {
						// if(ids.contains(res[1]+",")){
						System.out.println("-----------------开始第" + (i + 1) + "条---------------------------");
						// System.out.println("'" + res[1]+"',");
						// ids += "" + res[1]+",";
						insertEvent(res, sort_num + i);
					} else {
						// System.out.println("-----------------已经存在。" + res[1]+
						// res[2]+"---------------------------");
					}
					// }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("有：" + count + "条");
		// System.out.println("ids=" + ids);
		System.out.println("-----------------------------------------全部结束------------------------------------------ ");
		// clear();
	}

	private static void clear() {
		List list = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			StringBuffer sql = new StringBuffer(
					"select a.area_code from (select t.area_code, count(1) co from TA_AREA t group by t.area_code) a where a.co=2 ");
			conn = DriverManager.getConnection(url_oracle_e, username_oracle_e, password_oracle_e);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				list.add(rs.getObject("area_code"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	private static void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(url_oracle_e, username_oracle_e, password_oracle_e);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void end() {
		try {
			stmt.executeBatch();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map queryTextbook(String[] res) {
		Map map = new HashMap();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			StringBuffer sql = new StringBuffer("select area_code, area_name, parent_id from TA_AREA ");
			sql.append("where AREA_CODE='").append(res[1]).append("'");
			conn = DriverManager.getConnection(url_oracle_e, username_oracle_e, password_oracle_e);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				map = new HashMap();
				map.put("area_code", rs.getObject("area_code"));
				map.put("area_name", rs.getObject("area_name"));
				map.put("parent_id", rs.getObject("parent_id"));
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return null;
	}

	private static void insertEvent(String[] res, int sort_num) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			StringBuffer sql = new StringBuffer("insert into TA_AREA (");
			sql.append("id, AREA_CODE, AREA_NAME, PARENT_ID, sort_num, level_id, level_name");
			sql.append(") values (");
			sql.append("'" + res[0] + "',");
			sql.append("'" + res[1] + "',");
			sql.append("'" + res[2] + "',");
			sql.append("'" + res[3] + "',");
			sql.append(sort_num + ",");
			if (res[3].trim().equals("0")) {
				sql.append(1 + ",");
				sql.append("'省级'");
			} else if (res[3].trim().endsWith("0000")) {
				sql.append(2 + ",");
				sql.append("'市级'");
			} else {
				sql.append(3 + ",");
				sql.append("'区县级'");
			}
			sql.append(")");
			System.out.println(sql.toString() + ";");
			conn = DriverManager.getConnection(url_oracle_e, username_oracle_e, password_oracle_e);
			stmt = conn.createStatement();
			stmt.addBatch(sql.toString());
			stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}
