package com.tlopes.azure;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class MessagePublisherTest {

  @Inject
  MessagePublisher messagePublisher;

  /**
   * While the mock is being properly injected, MessageClientWrapper is still being instantiated, which is creating an
   * actual connection to IoT Hub, requiring valid connection string.
   * This is not ideal since anyone may forget to inject the mock and end up messing an iot hub instance.
   */
  @InjectMock
  MessageClientWrapper messageClientWrapper;

  @Test
  void publishMessage() {
    Mockito.when(messageClientWrapper.sendMessage()).thenReturn("A mock");

    messagePublisher.publishMessage();
  }
}
