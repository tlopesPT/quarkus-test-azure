package com.tlopes.azure;

import com.microsoft.azure.sdk.iot.service.messaging.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.messaging.MessagingClient;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class MessageClientWrapper implements IMessageClientWrapper {

  private static final String IOT_HUB_CONN_STRING = "HostName=some-iot-hub.net;SharedAccessKeyName=service;SharedAccessKey=XYZ";

  private final MessagingClient messagingClient;

  public MessageClientWrapper() {
    this.messagingClient = new MessagingClient(IOT_HUB_CONN_STRING, IotHubServiceClientProtocol.AMQPS);
  }

  @Override
  @PostConstruct // Do not use @Observes StartupEvent ev. Any bean (injected or not, alternative or not) can observe and react to an event
  public void onStart() {
    System.out.println("----------------  Opening real iot hub connection  ----------------");
    try {
      // can use "test".equals(ProfileManager.getActiveProfile()) to avoid opening connecting, but this is messy
      messagingClient.open();
    } catch (Exception ignored) {
      System.err.println("Failed to open IoT Hub connection, probably invalid connection string, ignoring error.");
    }
  }

  @Override
  @PreDestroy
  public void onStop() throws InterruptedException {
    System.out.println("----------------  Closing real iot hub connection  ----------------");
    messagingClient.close(50);
  }

  @Override
  public String sendMessage() {
    System.out.println("----------------  Sending real message  ----------------");
    return "Returned actual client wrapper";
  }
}
