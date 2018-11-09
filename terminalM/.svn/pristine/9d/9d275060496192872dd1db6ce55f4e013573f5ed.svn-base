package com.whty.assis.manage.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whty.common.excel.FileVo;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.DelFile;
import com.whty.common.util.GUIDGenerator;

public class ImportEbook2Oracle {

	private static String url_oracle = "jdbc:oracle:thin:@10.8.9.160:1521:orcl";
	private static String username_oracle = "teacherassistant";
	private static String password_oracle = "teacherassistant";
	// private static String url_oracle_e =
	// "jdbc:oracle:thin:@127.0.0.142:1521:edu";
	// private static String username_oracle_e = "teacherassistant";
	// private static String password_oracle_e = "teacherassistant";
	private static String url_oracle_e = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 127.0.0.55)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 127.0.0.56)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED) (SERVICE_NAME = edu)))";
	private static String username_oracle_e = "teacherassistant";
	private static String password_oracle_e = "pass4teacher";

	private static String resFileFileName = "12月11日入库清单.xlsx";
	private static String filePath = "E:/天喻通讯/资料/应用/教师助手/数字教材/20151202/";
	private static String file_path = "teachassistantfiles/ebook/";
	private static int sort_num = 126;

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		int count = 0;
		try {
			// 读取excel数据
			FileVo resfile = new FileVo();
			resfile.setFile(new File(filePath + resFileFileName));
			resfile.setFileFileName(resFileFileName);
			List reslist = PoiUtil.readerExcelSheet(resfile, 1);
			count = reslist.size();
			Map map = new HashMap();
			// Calendar calendar = Calendar.getInstance();
			String year_month_day = "2015-12-11";// calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
			System.out.println("有：" + count + "条");
			int k = 1;
			for (int i = 0; i < count; i++) {
				String[] res = (String[]) reslist.get(i);
				if (res != null && res[0] != null && (res[4] == null || res[4].equals(""))) {
					// 插入数据
					// if((i+1)==461){
					// System.out.println("-----------------开始第" + (i+1) +
					// "条---------------------------");
					map = queryTextbook(res[0]);
					if (null == map || map.isEmpty()) {
						System.out.println(
								"-----------------失败：找不到对应的教材。" + res[0] + res[1] + "---------------------------");
					} else {
						insertEvent(res, map, sort_num + i, year_month_day);
						System.out.println(
								"-----------------教材：" + res[0] + res[1] + "---------------------------" + k + res[4]);
						k++;
					}

					// if(res[4] != null && !res[4].equals("")){
					// File file = new
					// File(filePath+File.separator+"8-12月3日入库图片"+File.separator+"book_"+res[0]+".jpg");
					// file.delete();
					// System.out.println("-----------------删除教材封面：" + res[0]+
					// res[1]+"---------------------------"+k);
					// k++;
					// }
					// }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("-----------------------------------------全部结束------------------------------------------ ");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map queryTextbook(String textbook_id) {
		Map map = new HashMap();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			StringBuffer sql = new StringBuffer(
					"select textbook_id,textbook_name,period_id,subject_id,edition_id,volume_id from t_textbook ");
			sql.append("where textbook_id='").append(textbook_id).append("'");
			conn = DriverManager.getConnection(url_oracle, username_oracle, password_oracle);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				map = new HashMap();
				map.put("textbook_id", rs.getObject("textbook_id"));
				map.put("textbook_name", rs.getObject("textbook_name"));
				map.put("period_id", rs.getObject("period_id"));
				map.put("subject_id", rs.getObject("subject_id"));
				map.put("edition_id", rs.getObject("edition_id"));
				map.put("volume_id", rs.getObject("volume_id"));
			}
			return map;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void insertEvent(String[] res, Map map, int sort_num, String year_month_day) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			StringBuffer sql = new StringBuffer("insert into T_EBOOK (");
			sql.append(
					"ebook_id, ebook_name, intro, period_id, subject_id, edition_id, volume_id, creator_id, sort_num, file_path, image_path, textbook_id, textbook_name, file_size");
			sql.append(") values (");
			sql.append("'" + GUIDGenerator.getGUID() + "',");
			sql.append("'" + map.get("textbook_name") + "',");
			sql.append("'" + map.get("textbook_name") + "',");
			sql.append("'" + map.get("period_id") + "',");
			sql.append("'" + map.get("subject_id") + "',");
			sql.append("'" + map.get("edition_id") + "',");
			sql.append("'" + map.get("volume_id") + "',");
			sql.append("'1',").append(sort_num + ",");
			sql.append("'" + file_path + year_month_day + "/" + res[0] + ".zip',");
			sql.append("'" + file_path + year_month_day + "/" + "book_" + res[0] + ".jpg',");
			sql.append("'" + map.get("textbook_id") + "',");
			sql.append("'" + map.get("textbook_name") + "',");
			File file = new File(filePath + res[0] + ".zip");
			int file_size = (int) file.length();
			sql.append("" + file_size + ")");
			// System.out.println(sql.toString() + ";");

			conn = DriverManager.getConnection(url_oracle_e, username_oracle_e, password_oracle_e);
			stmt = conn.createStatement();
			stmt.addBatch(sql.toString());
			stmt.executeBatch();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
