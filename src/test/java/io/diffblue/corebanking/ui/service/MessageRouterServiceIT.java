package io.diffblue.corebanking.ui.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.diffblue.corebanking.communication.MessageRouter;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageRouterServiceIT {
  private static final String APACHE_PULSAR_DIR = "apache-pulsar-2.6.2";
  private static final String APACHE_PULSAR_FILE = "apache-pulsar-2.6.2-bin.tar.gz";
  private static final String APACHE_PULSAR_DOWNLOAD_URL =
      "https://archive.apache.org/dist/pulsar/pulsar-2.6.2/apache-pulsar-2.6.2-bin.tar.gz";
  private static final String APACHE_PULSAR_BINARY = "bin/pulsar";

  private static Process brokerProcess;
  private static Process serviceProcess;

  @BeforeClass
  public static void downloadAndStart() throws Exception {
    if (!Paths.get(APACHE_PULSAR_DIR).toFile().exists()) {
      System.out.println(APACHE_PULSAR_DIR + " not available");
      System.out.println("Downloading...");
      ProcessBuilder downloadProcessBuilder =
          new ProcessBuilder("wget", APACHE_PULSAR_DOWNLOAD_URL)
            .inheritIO();
      Process downloadProcess = downloadProcessBuilder.start();
      assertEquals(0, downloadProcess.waitFor());

      System.out.println("Unpacking...");
      ProcessBuilder unpackProcessBuilder =
          new ProcessBuilder("tar", "xvfz", APACHE_PULSAR_FILE)
            .inheritIO();
      Process unpackProcess = unpackProcessBuilder.start();
      assertEquals(0, unpackProcess.waitFor());
    } else {
      System.out.println(APACHE_PULSAR_DIR + " already available");
    }

    System.out.println("Starting broker...");
    ProcessBuilder brokerProcessBuilder =
        new ProcessBuilder(APACHE_PULSAR_BINARY, "standalone")
        .directory(new File(APACHE_PULSAR_DIR))
        .inheritIO();
    brokerProcess = brokerProcessBuilder.start();
    System.out.println("Broker started.");

    System.out.println("Starting service...");
    List<String> command = new ArrayList<>();
    command.add("java");
    command.add("-cp");
    command.add("CoreBanking-1.0.0-jar-with-dependencies.jar");
    if (System.getProperty("cover.agent") != null) {
      command.add("-javaagent:" + System.getProperty("cover.agent"));
    }
    command.add("io.diffblue.corebanking.ui.service.MessageRouterService");
    System.out.println("Running command: " + command.stream().collect(Collectors.joining(" ")));
    ProcessBuilder serviceProcessBuilder =
        new ProcessBuilder(command.toArray(new String[0]))
            .directory(new File("target"))
            .inheritIO();
    serviceProcess = serviceProcessBuilder.start();
    System.out.println("Service started.");
  }

  @AfterClass
  public static void shutdown() throws Exception {
    System.out.println("Stopping service...");
    serviceProcess.destroy();
    assertEquals(143, serviceProcess.waitFor());
    System.out.println("Service stopped.");

    System.out.println("Stopping broker...");
    brokerProcess.destroy();
    assertEquals(143, brokerProcess.waitFor());
    System.out.println("Broker stopped.");
  }

  @Test
  public void testUrgent() throws Exception {
    try(PulsarClient client = PulsarClient.builder()
        .serviceUrl(MessageRouterService.MESSAGE_BROKER_URL)
        .build()) {

      // Send a message
      byte[] payload = "BANK|150000|2020-01-10|9876|1234".getBytes();
      try(Producer<byte[]> producer = client.newProducer()
          .topic(MessageRouter.IN_ALL)
          .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
          .sendTimeout(10, TimeUnit.SECONDS)
          .blockIfQueueFull(true)
          .create()) {

        producer.newMessage()
            .key(MessageRouter.KEY_TRANSACTION)
            .value(payload)
            .property(MessageRouter.PROPERTY_URGENT, Boolean.TRUE.toString())
            .send();
      }

      // Check message has been correctly routed
      try(Consumer consumer = client.newConsumer()
          .topic(MessageRouter.OUT_URGENT)
          .subscriptionName(MessageRouter.OUT_URGENT)
          .subscribe()) {

        Message msg = consumer.receive();
        consumer.acknowledge(msg);

        assertEquals(MessageRouter.KEY_TRANSACTION, msg.getKey());
        assertTrue(msg.hasProperty(MessageRouter.PROPERTY_URGENT));
        assertEquals(Boolean.TRUE.toString(), msg.getProperty(MessageRouter.PROPERTY_URGENT));
        assertArrayEquals(payload, msg.getData());
      }
    }
  }

  @Test
  public void testBatch() throws Exception {
    try(PulsarClient client = PulsarClient.builder()
        .serviceUrl(MessageRouterService.MESSAGE_BROKER_URL)
        .build()) {

      // Send a message
      byte[] payload = "BANK|100|2020-03-05|1234|9876".getBytes();
      try(Producer<byte[]> producer = client.newProducer()
          .topic(MessageRouter.IN_ALL)
          .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
          .sendTimeout(10, TimeUnit.SECONDS)
          .blockIfQueueFull(true)
          .create()) {

        producer.newMessage()
            .key(MessageRouter.KEY_TRANSACTION)
            .value(payload)
            .send();
      }

      // Check message has been correctly routed
      try(Consumer consumer = client.newConsumer()
          .topic(MessageRouter.OUT_BATCH)
          .subscriptionName(MessageRouter.OUT_BATCH)
          .subscribe()) {

        Message msg = consumer.receive();
        consumer.acknowledge(msg);

        assertEquals(MessageRouter.KEY_TRANSACTION, msg.getKey());
        assertFalse(msg.hasProperty(MessageRouter.PROPERTY_URGENT));
        assertArrayEquals(payload, msg.getData());
      }
    }
  }

  @Test
  public void testManual() throws Exception {
    try(PulsarClient client = PulsarClient.builder()
        .serviceUrl(MessageRouterService.MESSAGE_BROKER_URL)
        .build()) {

      // Send a message
      byte[] payload = "blablabla".getBytes();
      try(Producer<byte[]> producer = client.newProducer()
          .topic(MessageRouter.IN_ALL)
          .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
          .sendTimeout(10, TimeUnit.SECONDS)
          .blockIfQueueFull(true)
          .create()) {

        producer.newMessage()
            .key("blablabla")
            .value(payload)
            .send();
      }

      // Check message has been correctly routed
      try(Consumer consumer = client.newConsumer()
          .topic(MessageRouter.OUT_MANUAL)
          .subscriptionName(MessageRouter.OUT_MANUAL)
          .subscribe()) {

        Message msg = consumer.receive();
        consumer.acknowledge(msg);

        assertFalse(MessageRouter.KEY_TRANSACTION.equals(msg.getKey()));
        assertArrayEquals(payload, msg.getData());
      }
    }
  }
}
