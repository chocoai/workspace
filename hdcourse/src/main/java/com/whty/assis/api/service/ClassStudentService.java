package com.whty.assis.api.service;

import java.util.Map;

import com.whty.assis.api.model.EbpClassStudent;

public interface ClassStudentService {

	EbpClassStudent findEduClassStudent(Map para);

	EbpClassStudent findSelfClassStudent(Map para);

	void updateClassStudent(Map para);

}
