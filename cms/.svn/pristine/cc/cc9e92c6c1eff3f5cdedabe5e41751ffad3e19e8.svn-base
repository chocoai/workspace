package com.yhcrt.weihu.cms.manager.assist.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.entity.assist.BbsTopic;
import com.yhcrt.weihu.cms.manager.assist.BbsTopicMng;

@Service
@Transactional
public class BbsTopicMngImpl implements BbsTopicMng {

	@Override
	public List<BbsTopic> getList(Integer count) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BbsTopic> list = new ArrayList<BbsTopic>();
		try {
			Properties po = new Properties();
			java.io.InputStream in = BbsTopicMngImpl.class.getResourceAsStream("/bbs.properties");
			po.load(in);
			in.close();

			String db_url = po.getProperty("db_url");
			String db_user = po.getProperty("db_user");
			String db_pwd = po.getProperty("db_pwd");
			String bbs_url = po.getProperty("bbs_url");

			con = DriverManager.getConnection(db_url, db_user, db_pwd);
			String sql = "select id,title,path,suffix from v_topic order by createTime desc limit ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, count);
			rs = ps.executeQuery();
			while (rs.next()) {
				BbsTopic topic = new BbsTopic();
				topic.setId(rs.getInt("id"));
				topic.setTitle(rs.getString("title"));
				if (rs.getString("path") != null && rs.getString("suffix") != null) {
					topic.setUrl(bbs_url + rs.getString("path") + '/' + rs.getInt("id") + rs.getString("suffix"));
				} else {
					topic.setUrl("javascript:void(0);");
				}
				list.add(topic);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
