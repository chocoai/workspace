/**
 * @Title: ImpliedFeature.java 
 * @Package com.whty.apkutils.entity 
 * @Description: ApkUtils
 * @author xjin
 * @date 2014-6-26 
 * @version    
 */
package com.whty.ebp.manage.model;

/**
 * @ClassName: ImpliedFeature
 * @author zhujg
 * @date 2015-11-10
 * 
 */
public class ImpliedFeature {
	private String feature;
	private String implied;

	public ImpliedFeature() {
	}

	public ImpliedFeature(String feature, String implied) {
		this.feature = feature;
		this.implied = implied;
	}

	public String getFeature() {
		return this.feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getImplied() {
		return this.implied;
	}

	public void setImplied(String implied) {
		this.implied = implied;
	}

	public String toString() {
		return "Feature [feature=" + this.feature + ", implied=" + this.implied
				+ "]";
	}
}
