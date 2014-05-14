package com.gopivotal.cf.workshop.cloudfoundry;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * basic bean factory that interrogates the environment to get VCAP_SERVICES and
 * then use to initialize a BasicDataSource object for MySQL based upon a
 * user-provided service provided via a bean property
 * 
 * @author aripka
 *
 */
public class MySQLUPSBasicDataSourceFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(MySQLUPSBasicDataSourceFactory.class);

	private BasicDataSource bds;

	private String upsName;

	@SuppressWarnings({"unchecked","rawtypes"})
	public void initalizeFromEnvironment() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		String vcapServices = System.getenv("VCAP_SERVICES");
		
		if (vcapServices != null) {
			Map<String, List<Map>> svcMap = mapper.readValue(vcapServices,
					Map.class);
			logger.info("ups lookup for ups:" + this.upsName);

			for (Map ups : svcMap.get("user-provided")) {
				logger.info(ups.get("name").toString());
				if (upsName != null && ups.get("name").equals(upsName)) {
					logger.info("ups found");

					bds = new BasicDataSource();
					bds.setDriverClassName("com.mysql.jdbc.Driver");

					bds.setUrl(((Map) ups.get("credentials")).get("URL")
							.toString());
					bds.setUsername(((Map) ups.get("credentials")).get("USER")
							.toString());
					bds.setPassword(((Map) ups.get("credentials"))
							.get("PASSWD").toString());

				}
			}
		}
		if (bds == null) {
			String errMsg = null;
			if (vcapServices == null) {
				throw new BeanInitializationException(
						"Environment variable VCAP_SERVICES must be populated");
			} else if (upsName == null) {
				errMsg = "upsName property not set";
			} else if (upsName != null) {
				errMsg = "ups named " + upsName + "not bound to application";
			} else {
				errMsg = "Error initializing the Basic Data Source from VCAP_SERVICES";
			}
			throw new BeanInitializationException(errMsg);

		}

	}

	public DataSource getDataSource() throws Exception {
		return bds;
	}

	public String getUpsName() {
		return upsName;
	}

	public void setUpsName(String upsName) {
		this.upsName = upsName;
	}

}
