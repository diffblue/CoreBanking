package io.diffblue.corebanking.communication;

import java.io.Closeable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;

public class MessageRouter implements Closeable {
  public static final String IN_ALL = "all-incoming";
  public static final String OUT_URGENT = "urgent";
  public static final String OUT_BATCH = "batch";
  public static final String OUT_MANUAL = "manual";

  public static final String KEY_TRANSACTION = "transaction";

  public static final String PROPERTY_URGENT = "urgent";

  private PulsarClient client;
  private Consumer inChannel;
  private final HashMap<String, Producer<byte[]>> outChannels = new HashMap<>();
  private MessageProcessor messageProcessor;
  private Thread workerThread;

  public MessageRouter(String messageBrokerUrl) throws PulsarClientException {
    this.client = PulsarClient.builder()
        .serviceUrl(messageBrokerUrl)
        .build();

    System.out.printf("MessageRouter: subscribing to topic '%s'...%n", IN_ALL);
    this.inChannel = client.newConsumer()
        .topic(IN_ALL)
        .subscriptionName("message-router-consumer")
        .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
        .subscribe();

    System.out.printf("MessageRouter: opening producer on topic '%s'...%n", OUT_URGENT);
    this.outChannels.put(OUT_URGENT,
        client.newProducer()
            .topic(OUT_URGENT)
            .producerName("message-router-urgent-producer")
            .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
            .sendTimeout(10, TimeUnit.SECONDS)
            .blockIfQueueFull(true)
            .create());

    System.out.printf("MessageRouter: opening producer on topic '%s'...%n", OUT_BATCH);
    this.outChannels.put(OUT_BATCH,
        client.newProducer()
            .topic(OUT_BATCH)
            .producerName("message-router-batch-producer")
            .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
            .sendTimeout(10, TimeUnit.SECONDS)
            .blockIfQueueFull(true)
            .create());

    System.out.printf("MessageRouter: opening producer on topic '%s'...%n", OUT_MANUAL);
    this.outChannels.put(OUT_MANUAL,
        client.newProducer()
            .topic(OUT_MANUAL)
            .producerName("message-router-manual-producer")
            .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
            .sendTimeout(10, TimeUnit.SECONDS)
            .blockIfQueueFull(true)
            .create());

    this.messageProcessor = new MessageProcessor();
  }

  public void start() {
    this.workerThread = new WorkerThread(inChannel, outChannels, messageProcessor);
    this.workerThread.start();
  }

  @Override
  public void close() {
    this.workerThread.interrupt();

    try {
      this.inChannel.close();
    } catch (PulsarClientException e) {
      System.err.printf("Error: " + e.toString());
    }
    this.outChannels.values().forEach(outChannel -> {
      try {
        outChannel.close();
      } catch (PulsarClientException e) {
        System.err.printf("Error: " + e.toString());
      }
    });
    try {
      this.client.close();
    } catch (PulsarClientException e) {
      System.err.printf("Error: " + e.toString());
    }
  }
}
