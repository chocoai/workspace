package com.whty.assis.api.service;

import net.sf.json.JSONObject;

public interface RealClassService {
	void realClass(JSONObject ebpUserClassContent, String userId, String platformCode, JSONObject classJson);
}
