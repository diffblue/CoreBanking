package io.diffblue.corebanking.ui.service;

import io.diffblue.corebanking.communication.MessageRouter;
import org.apache.pulsar.client.api.PulsarClientException;

public class MessageRouterService {
  public static final String MESSAGE_BROKER_URL = "pulsar://localhost:6650";

  public static void main(String[] args) {
    try {
      MessageRouter messageRouter = new MessageRouter(MESSAGE_BROKER_URL);
      Runtime.getRuntime().addShutdownHook(new Thread(messageRouter::close));
      messageRouter.start();
      while (true) {
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      System.err.printf("Error: " + e.toString());
    }
  }
}
