package com.tlopes.azure;

import com.microsoft.azure.sdk.iot.service.messaging.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.messaging.MessagingClient;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class MessageClientWrapper {

  private static final String IOT_HUB_CONN_STRING = "";

  private final MessagingClient messagingClient;

  public MessageClientWrapper() {
    this.messagingClient = new MessagingClient(IOT_HUB_CONN_STRING, IotHubServiceClientProtocol.AMQPS);
  }

  void onStart(@Observes StartupEvent ev) throws Exception {
    System.out.println("Opening real iot hub connection");
    messagingClient.open();
  }

  void onStop(@Observes ShutdownEvent ev) throws InterruptedException {
    messagingClient.close();
  }

  String sendMessage() {
    return "Not a mock";
  }
}
