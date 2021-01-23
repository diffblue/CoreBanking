package io.diffblue.corebanking.communication;

import java.util.HashMap;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;

public class WorkerThread extends Thread {

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
        System.out.printf("WorkerThread: topic '%s': receiving...%n", inChannel.getTopic());
        Message message = inChannel.receive();
        System.out.printf("WorkerThread: topic '%s': received '%s'%n", inChannel.getTopic(),
            message.getMessageId());

        try {
          processMessage(message);
          inChannel.acknowledge(message);
          System.out.println("WorkerThread: message acknowledged");
        } catch (PulsarClientException e) {
          inChannel.negativeAcknowledge(message);
          System.out.println("WorkerThread: message negatively acknowledged");
        }
      }
    } catch (Exception e) {
      if (e.getCause() instanceof InterruptedException) {
        System.out.println("WorkerThread: interrupted, terminating now");
        return;
      }
      System.out.printf("WorkerThread: error: %s%n", e.getMessage());
      e.printStackTrace();
    }
  }

  private void processMessage(Message message) throws PulsarClientException {
    String outChannel = messageProcessor.computeOutChannel(message);
    forwardMessage(outChannel, message);
  }

  private void forwardMessage(String outChannel, Message message) throws PulsarClientException {
    System.out.printf("WorkerThread: forwarding message '%s' to topic '%s'...%n",
        message.getMessageId(), outChannel);
    MessageId id = outChannels.get(outChannel).newMessage()
        .key(message.getKey())
        .value(message.getData())
        .properties(message.getProperties())
        .send();
    System.out.printf("WorkerThread: sent, id: '%s'%n", id);
  }
}
