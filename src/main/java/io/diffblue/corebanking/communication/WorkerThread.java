package io.diffblue.corebanking.communication;

import java.util.HashMap;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;

public class WorkerThread implements Runnable {

  private Consumer inChannel;
  private HashMap<String, Producer<byte[]>> outChannels;
  private MessageProcessor messageProcessor;

  WorkerThread(
      Consumer inChannel,
      HashMap<String, Producer<byte[]>> outChannels,
      MessageProcessor messageProcessor) {
    this.inChannel = inChannel;
    this.outChannels = outChannels;
    this.messageProcessor = messageProcessor;
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
    String outChannel = messageProcessor.computeOutChannel(message);
    forwardMessage(outChannel, message);
  }

  private void forwardMessage(String outChannel, Message message) throws PulsarClientException {
    outChannels.get(outChannel).newMessage()
        .key(message.getKey())
        .value(message.getData())
        .properties(message.getProperties())
        .send();
  }
}
