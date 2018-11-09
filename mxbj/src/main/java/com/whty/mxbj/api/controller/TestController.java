package com.whty.mxbj.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.whty.mxbj.api.service.ArchiveNoteService;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private ArchiveNoteService archiveNoteService;

	@RequestMapping(value = "test2", method = RequestMethod.POST)
	public void test2(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		archiveNoteService.tes2();
	}

	@RequestMapping("/test")
	public void test() {
		archiveNoteService.updatePageUrl();
	}
}
