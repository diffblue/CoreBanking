package io.diffblue.corebanking.ui.service;

import io.diffblue.corebanking.communication.MessageRouter;

public class MessageRouterService {
  public static final String MESSAGE_BROKER_URL = "pulsar://localhost:6650";

  public static void main(String[] args) {

    try {
      System.out.println("MessageRouterService: starting... (type Ctrl+C to quit)");
      MessageRouter messageRouter = new MessageRouter(MESSAGE_BROKER_URL);
      Runtime.getRuntime().addShutdownHook(new Thread(messageRouter::close));
      messageRouter.start();

    } catch (Exception e) {
      System.err.printf("MessageRouterService: error: %s%n", e);
    }
    System.err.println("MessageRouterService: main thread exits now");
  }
}
