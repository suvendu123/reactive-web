package com.suv.reactive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Session;

@Component
class MySessionRefresh {

  @Autowired
  Environment env;

  @Autowired
  Session session;

  @EventListener
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public void handle(RefreshScopeRefreshedEvent event) {

    String keyspace = env.getProperty("cassandra.keyspace");
    session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspace + " WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 };");
    session.execute("USE " + keyspace + " ;");

  }
}