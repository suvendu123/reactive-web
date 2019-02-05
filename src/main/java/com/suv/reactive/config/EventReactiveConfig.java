package com.suv.reactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories("com.suv.reactive.repository")
public class EventReactiveConfig extends AbstractReactiveCassandraConfiguration {

	  @Value("${cassandra.contactpoints}")
	  private String contactPoints;

	  @Value("${cassandra.port}")
	  private int port;

	  @Value("${cassandra.keyspace}")
	  private String keySpace;

	  @Value("${cassandra.basepackages}")
	  private String basePackages;

	  @Override
	  protected String getKeyspaceName() {
	    return keySpace;
	  }

	  @Override
	  protected String getContactPoints() {
	    return contactPoints;
	  }

	  @Override
	  protected int getPort() {
	    return port;
	  }

	  @Override
	  public SchemaAction getSchemaAction() {
	    return SchemaAction.CREATE_IF_NOT_EXISTS;
	  }

	  @Override
	  public String[] getEntityBasePackages() {
	    return new String[] {basePackages};
	  }
}