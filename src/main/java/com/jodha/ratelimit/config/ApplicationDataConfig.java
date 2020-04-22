package com.jodha.ratelimit.config;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.conversion.BasicRelationalConverter;
import org.springframework.data.relational.core.conversion.RelationalConverter;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@EnableJdbcRepositories(basePackages = "com.jodha.ratelimit.dao")
public class ApplicationDataConfig {
	
/*	@Bean
	@Autowired
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	@Autowired
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public JdbcCustomConversions jdbcCustomConversions() {
		return new JdbcCustomConversions();
	}
	
	@Bean
	public RelationalConverter getRelationalConverter(JdbcCustomConversions customConversions,RelationalMappingContext mappingContext) {
		return new BasicRelationalConverter(mappingContext,customConversions);
	}
	
	@Bean
	public RelationalMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy,
			JdbcCustomConversions customConversions) {

		RelationalMappingContext mappingContext = new RelationalMappingContext(namingStrategy.orElse(NamingStrategy.INSTANCE));
		mappingContext.setSimpleTypeHolder(customConversions.getSimpleTypeHolder());

		return mappingContext;
	}*/
	


}
