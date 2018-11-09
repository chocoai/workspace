package com.yhcrt.iHealthCloud.util;

/**
 * 字典帮助类
 * 
 */
public class Item {

	public Item() {
	}

	public Item(String key, String value) {
		setKey(key);
		setValue(value);
	}

	private String key;
	private String value;
	private boolean checked = false;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
