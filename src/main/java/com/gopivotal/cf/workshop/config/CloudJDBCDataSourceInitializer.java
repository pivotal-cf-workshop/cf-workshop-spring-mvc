package com.gopivotal.cf.workshop.config;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.Assert;

public class CloudJDBCDataSourceInitializer {

	private DataSource dataSource;

	private Resource scriptClasspath;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setScriptClasspath(Resource scriptClasspath) {
		this.scriptClasspath = scriptClasspath;
	}

	/**
	 * this initialize will only initialize if the datasource is a mysql one....
	 */
	public void initialize() {

		Assert.notNull(scriptClasspath);
		Assert.notNull(dataSource);

		String dataSourceClass = dataSource.getClass().getName();

		if (!dataSourceClass.equals("org.h2.jdbcx.JdbcDataSource")) {
			ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(
					scriptClasspath);

			databasePopulator.execute(dataSource);
		}

	}

}
