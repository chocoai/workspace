package com.whty.common.datasource;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	public Logger getParentLogger() {

		return null;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextHolder.getDBType();
	}

}
