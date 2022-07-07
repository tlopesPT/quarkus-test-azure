package com.tlopes.azure;

import io.quarkus.arc.DefaultBean;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class AlternativeMessageClientWrapper implements IMessageClientWrapper {

  @Override
  @PostConstruct
  public void onStart() {
    System.out.println("----------------  Does nothing on start ----------------");
  }

  @Override
  @PreDestroy
  public void onStop() throws InterruptedException {
    System.out.println("----------------  Does nothing on close ----------------");
  }

  @Override
  public String sendMessage() {
    System.out.println("----------------  Sends nothing to nowhere ----------------");
    return "Returned alternative client wrapper";
  }
}
