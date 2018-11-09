package com.yhcrt.iHealthCloud.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import com.yhcrt.iHealthCloud.entity.SysDictionary;



/**
 * 字典帮助类
 *
 */
public class DictSet {

	private static DictSet ss = null;

	private static Map<String, List<Item>> dicmap = new HashMap<String, List<Item>>();

	public DictSet(List<SysDictionary> dictList, ServletContext application) {
		for (SysDictionary dict : dictList) {
			Item item = new Item(dict.getDictValue(), dict.getDictKey());
			List<Item> list = dicmap.get(dict.getDictEnName());
			if (list != null && list.size() > 0) {
				list.add(item);
			} else {
				List<Item> tempList = new ArrayList<Item>();
				tempList.add(item);
				dicmap.put(dict.getDictEnName(), tempList);
			}
		}
		for (String key : dicmap.keySet()) {
			application.setAttribute(key + "_", dicmap.get(key));
			List<Item> list_ = new ArrayList<Item>();
			Item item = new Item("", "请选择");
			list_.add(item);
			list_.addAll(dicmap.get(key));
			application.setAttribute(key + "__", list_);
		}
	}

	public static List<Item> get(String type) {
		List<Item> list = dicmap.get(type);
		List<Item> tempList = new ArrayList<Item>();
		if (list == null || list.size() == 0)
			return tempList;
		tempList.addAll(list);
		return tempList;
	}

	public static synchronized DictSet getInstanse(List<SysDictionary> dicList, ServletContext application) {
		if (ss == null)
			ss = new DictSet(dicList, application);
		return ss;
	}

	public static synchronized DictSet reload(List<SysDictionary> dicList, ServletContext application) {
		for (Entry<String, List<Item>> entry : dicmap.entrySet()) {
			String key = entry.getKey();
			application.removeAttribute(key + "_");
			application.removeAttribute(key + "__");
		}
		dicmap = new HashMap<String, List<Item>>();
		ss = new DictSet(dicList, application);
		return ss;
	}

}
