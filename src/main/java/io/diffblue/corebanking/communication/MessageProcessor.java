package io.diffblue.corebanking.communication;

import org.apache.pulsar.client.api.Message;

public class MessageProcessor {

  public String computeOutChannel(Message message) {
    if (message.getKey().equals(MessageRouter.KEY_TRANSACTION)) {
      if (message.hasProperty(MessageRouter.PROPERTY_URGENT) &&
          message.getProperty(MessageRouter.PROPERTY_URGENT).equals(Boolean.TRUE.toString())) {
        return MessageRouter.OUT_URGENT;
      } else {
        return MessageRouter.OUT_BATCH;
      }
    } else {
      return MessageRouter.OUT_MANUAL;
    }
  }
}
