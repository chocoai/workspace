package com.whty.wfd.common.utils;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json转换工具类
 * 
 * @date 2014年11月27日
 */
public class JsonUtils {

	private static ObjectMapper mapper;

	// private static final Logger logger = Logger.getLogger(JsonUtils.class);

	// private static final Logger logger =
	// LoggerFactory.getLogger(JsonUtils.class);

	private static synchronized ObjectMapper getMapperInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		return mapper;
	}

	/**
	 * 将json字符串转换为java对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object jsonToObj(String json, Class<?> clazz) {
		Object obj = null;
		try {
			ObjectMapper objectMapper = getMapperInstance();
			obj = objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			// logger.error(json);
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String objTojson(Object obj) {
		String json = "";
		try {
			ObjectMapper objectMapper = getMapperInstance();
			json = objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 *    json转换map.  <br>
	 * 详细说明  @param jsonStr json字符串  @return  @return Map<String,Object> 集合
	 *  @throws  @author slj
	 */
	// public static Map<String, Object> parseJSON2Map(String jsonStr) {
	// ListOrderedMap map = new ListOrderedMap();
	// // 最外层解析
	// JSONObject json = JSONObject.fromObject((jsonStr == null ||
	// jsonStr.equals("")) ? "{}" : jsonStr);
	// for (Object k : json.keySet()) {
	// Object v = json.get(k);
	// // 如果内层还是数组的话，继续解析
	// if (v instanceof JSONArray) {
	// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// Iterator<JSONObject> it = ((JSONArray) v).iterator();
	// while (it.hasNext()) {
	// JSONObject json2 = it.next();
	// list.add(parseJSON2Map(json2.toString()));
	// }
	// map.put(k.toString(), list);
	// } else {
	// map.put(k.toString(), v);
	// }
	// }
	// return map;
	// }
}
