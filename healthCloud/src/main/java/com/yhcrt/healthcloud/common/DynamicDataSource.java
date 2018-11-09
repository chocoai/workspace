package com.yhcrt.healthcloud.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @author rpf
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	public static final String DATA_SOURCE_READ = "dataSourceRead";

	public static final String DATA_SOURCE_WRITE = "dataSourceWrite";

	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		CONTEXT_HOLDER.set(customerType);
	}

	public static String getCustomerType() {
		return CONTEXT_HOLDER.get();
	}

	public static void clearCustomerType() {
		CONTEXT_HOLDER.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getCustomerType();
	}

}
