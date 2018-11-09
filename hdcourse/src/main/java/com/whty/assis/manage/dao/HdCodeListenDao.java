package com.whty.assis.manage.dao;

import com.whty.assis.manage.model.HdCode;
import com.whty.assis.manage.model.HdCodeListen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HdCodeListenDao {

	List<HdCodeListen> getList(String ListenCode);

	void insert(@Param("licenceCode") String licenceCode, @Param("codeIds") String[] codeIds);

	void delete(String licenceCode);
}
