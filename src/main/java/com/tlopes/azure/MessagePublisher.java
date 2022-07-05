package com.tlopes.azure;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessagePublisher {

  private final MessageClientWrapper messageClientWrapper;

  public MessagePublisher(final MessageClientWrapper messageClientWrapper) {
    this.messageClientWrapper = messageClientWrapper;
  }

  public void publishMessage() {
    System.out.println(messageClientWrapper.sendMessage());
  }
}
