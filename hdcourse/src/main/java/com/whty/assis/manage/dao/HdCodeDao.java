package com.whty.assis.manage.dao;

import com.whty.assis.manage.model.HdCode;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HdCodeDao {

	List<HdCode> getList();

	List<HdCode> getListTree();

}
