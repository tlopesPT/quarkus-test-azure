package com.tlopes.azure;

import com.microsoft.azure.sdk.iot.service.messaging.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.messaging.MessagingClient;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class MessageClientWrapper {

  private static final String IOT_HUB_CONN_STRING = "HostName=some-iot-hub.net;SharedAccessKeyName=service;SharedAccessKey=XYZ";

  private final MessagingClient messagingClient;

  public MessageClientWrapper() {
    this.messagingClient = new MessagingClient(IOT_HUB_CONN_STRING, IotHubServiceClientProtocol.AMQPS);
  }

  void onStart(@Observes StartupEvent ev) {
    System.out.println("----------------  Opening real iot hub connection  ----------------");
    try {
      // can use "test".equals(ProfileManager.getActiveProfile()) to avoid opening connecting, but this is messy
      messagingClient.open();
    } catch (Exception ignored) {
      System.err.println("Failed to open IoT Hub connection, probably invalid connection string, ignoring error.");
    }
  }

  void onStop(@Observes ShutdownEvent ev) throws InterruptedException {
    messagingClient.close(50);
  }

  String sendMessage() {
    return "Returned actual client wrapper";
  }
}
