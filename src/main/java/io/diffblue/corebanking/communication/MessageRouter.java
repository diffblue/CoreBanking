package io.diffblue.corebanking.communication;

import java.io.Closeable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

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
  private Thread workerThread;

  public MessageRouter(String messageBrokerUrl) throws PulsarClientException {
    this.client = PulsarClient.builder()
        .serviceUrl(messageBrokerUrl)
        .build();

    this.inChannel = client.newConsumer()
        .topic(IN_ALL)
        .subscriptionName(IN_ALL)
        .subscribe();

    this.outChannels.put(OUT_URGENT,
        client.newProducer()
            .topic(OUT_URGENT)
            .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
            .sendTimeout(10, TimeUnit.SECONDS)
            .blockIfQueueFull(true)
            .create());

    this.outChannels.put(OUT_BATCH,
        client.newProducer()
            .topic(OUT_BATCH)
            .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
            .sendTimeout(10, TimeUnit.SECONDS)
            .blockIfQueueFull(true)
            .create());

    this.outChannels.put(OUT_MANUAL,
        client.newProducer()
            .topic(OUT_MANUAL)
            .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
            .sendTimeout(10, TimeUnit.SECONDS)
            .blockIfQueueFull(true)
            .create());
  }

  public void start() {
    this.workerThread = new Thread(new WorkerThread(inChannel, outChannels));
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
