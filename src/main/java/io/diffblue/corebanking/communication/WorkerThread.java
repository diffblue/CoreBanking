package io.diffblue.corebanking.communication;

import java.util.HashMap;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;

public class WorkerThread implements Runnable {

  private Consumer inChannel;
  private HashMap<String, Producer<byte[]>> outChannels;

  WorkerThread(Consumer inChannel, HashMap<String, Producer<byte[]>> outChannels) {
    this.inChannel = inChannel;
    this.outChannels = outChannels;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Message message = inChannel.receive();

        try {
          processMessage(message);
          inChannel.acknowledge(message);
        } catch (PulsarClientException e) {
          inChannel.negativeAcknowledge(message);
        }
      }
    } catch (PulsarClientException e) {
      if (e.getCause() instanceof InterruptedException) {
        return;
      }
    } catch (Exception e) {
      System.err.printf("Failed: " + e.toString());
    }
  }

  private void processMessage(Message message) throws PulsarClientException {
    String outChannel = computeOutChannel(message);
    forwardMessage(outChannel, message);
  }

  private String computeOutChannel(Message message) {
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

  private void forwardMessage(String outChannel, Message message) throws PulsarClientException {
    outChannels.get(outChannel).newMessage()
        .key(message.getKey())
        .value(message.getData())
        .properties(message.getProperties())
        .send();
  }
}
