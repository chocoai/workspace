package com.whty.ebp.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.whty.common.util.ThreadPoolUtils;
import com.whty.ebp.api.model.NoteData;
import com.whty.ebp.api.model.OutSideNoteData;
import com.whty.ebp.api.service.NoteDataService;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.service.FlatModelService;

@Controller
@RequestMapping("/api/data")
public class DataController extends BaseController {

	@Autowired
	private NoteDataService noteDataService;

	@Autowired
	private FlatModelService flatModelService;

	@RequestMapping(value = "/qqq", method = RequestMethod.POST)
	@ResponseBody
	public void bbb(HttpServletRequest request, HttpServletResponse response, Model model) {
		String modelCodeStr = "S2W";
		flatModelService.valModel(modelCodeStr);//
	}

	@RequestMapping(value = "/outSideNoteDataUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void outSideNoteDataUpdate(@RequestParam(value = "fileData", required = false) final MultipartFile file,
			final HttpServletRequest request, final HttpServletResponse response, Model model) {

		// ThreadPoolUtils.execute(new Runnable() {
		//
		// @Override
		// public void run() {
		OutSideNoteData outSideNoteData = new OutSideNoteData();
		outSideNoteData.setUserId(request.getParameter("userId"));
		outSideNoteData.setUserName(request.getParameter("userName"));
		outSideNoteData.setPlatformCode(request.getParameter("platformCode"));
		outSideNoteData.setLoginPlatformCode(request.getParameter("loginPlatformCode"));
		outSideNoteData.setOrgId(request.getParameter("orgId"));
		outSideNoteData.setOrgName(request.getParameter("orgName"));

		noteDataService.saveOutSideNoteDateFile(outSideNoteData, file);

		// }

		// });

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "000000");
		printJson(response, result);

	}

	@RequestMapping(value = "/noteDataUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void noteDataUpdate(@RequestParam(value = "fileData", required = false) final MultipartFile file,
			final HttpServletRequest request, final HttpServletResponse response, Model model) {

		// ThreadPoolUtils.execute(new Runnable() {
		//
		// @Override
		// public void run() {

		NoteData ebpNoteData = new NoteData();

		ebpNoteData.setUserId(request.getParameter("userId"));
		ebpNoteData.setPlatformCode(request.getParameter("platformCode"));
		ebpNoteData.setClassId(request.getParameter("classId"));
		ebpNoteData.setClassName(request.getParameter("className"));
		ebpNoteData.setSubjectId(request.getParameter("subjectId"));
		ebpNoteData.setSubjectName(request.getParameter("subjectName"));
		ebpNoteData.setOrgId(request.getParameter("orgId"));
		ebpNoteData.setOrgName(request.getParameter("orgName"));
		ebpNoteData.setTeacherId(request.getParameter("teacherId"));
		ebpNoteData.setTeacherName(request.getParameter("teacherName"));
		ebpNoteData.setChapterId(request.getParameter("chapterId"));
		ebpNoteData.setChapterName(request.getParameter("chapterName"));
		ebpNoteData.setInteractiveId(request.getParameter("interactiveId"));
		noteDataService.saveNoteDateFile(ebpNoteData, file);

		// }

		// });

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "000000");
		printJson(response, result);
	}

}
