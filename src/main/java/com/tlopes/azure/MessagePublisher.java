package com.tlopes.azure;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessagePublisher {

  private final IMessageClientWrapper messageClientWrapper;

  public MessagePublisher(final IMessageClientWrapper messageClientWrapper) {
    this.messageClientWrapper = messageClientWrapper;
  }

  public void publishMessage() {
    System.out.println(messageClientWrapper.sendMessage());
  }
}
