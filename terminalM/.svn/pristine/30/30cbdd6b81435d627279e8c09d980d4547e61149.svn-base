package com.whty.assis.manage.utils;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.whty.common.excel.PoiUtil;
import com.whty.common.util.CommonFunction;

public class ExportEventid {

	private static String url_oracle = "jdbc:oracle:thin:@127.0.0.142:1521:edu";
	private static String username_oracle = "teacherassistant";
	private static String password_oracle = "teacherassistant";

	public static void main(String[] args) {
		exportExcel();
	}

	private static void exportExcel() {
		List eventList = queryEvent();
		String[] tableHeader = { "模块|modelname", "事件ID|event_id" };
		String fileName = new String(
				"测试环境用户行为统计表" + CommonFunction.getDateSampleString(new Date(), "yyyyMMdd_HHmmss") + ".xls");
		HSSFWorkbook wb = new HSSFWorkbook();

		FileOutputStream fos;
		try {
			PoiUtil.createExcelSheet(wb, tableHeader, eventList);
			fos = new FileOutputStream("F:\\" + fileName);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static List<Map<String, String>> queryEvent() {
		List<Map<String, String>> list = queryTablename();
		StringBuffer sqlStr = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			sqlStr.append("select distinct '" + map.get("model_name") + "' modelname,e.event_id,m.sort_num" + " from "
					+ map.get("table_name")
					+ " e left join B_EVENT be on e.event_id=be.event_id left join B_MODEL m  on be.model_id = m.model_id");
			if (i < list.size() - 1) {
//				System.out.println();
				sqlStr.append(" union all ");
			}
		}
		sqlStr.append(" order by sort_num,event_id");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<Map<String, String>> eventList = new ArrayList<Map<String, String>>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(url_oracle, username_oracle, password_oracle);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlStr.toString());
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("modelname", rs.getString("modelname"));
				map.put("event_id", rs.getString("event_id"));
				eventList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventList;

	}

	private static List<Map<String, String>> queryTablename() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			StringBuffer sql = new StringBuffer(
					"select distinct t.table_name,m.model_name||'（'||m.model_id||'）' model_name,m.sort_num "
							+ "from B_EVENT t " + "left join B_MODEL m on t.model_id=m.model_id "
							+ "where m.status='0' " + "order by m.sort_num");
			conn = DriverManager.getConnection(url_oracle, username_oracle, password_oracle);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("table_name", rs.getString("table_name"));
				map.put("model_name", rs.getString("model_name"));
				map.put("sort_num", rs.getString("sort_num"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
