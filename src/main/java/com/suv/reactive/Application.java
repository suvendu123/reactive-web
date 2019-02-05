package com.suv.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suv.reactive.client.Client;

@SpringBootApplication
public class Application {

  public static void main(String args[]) {
    SpringApplication.run(Application.class);
    Client client = new Client();
    client.doStuff();
  }
}
