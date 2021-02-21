package io.diffblue.corebanking.ui.service;

import static io.diffblue.corebanking.communication.MessageRouter.IN_ALL;
import static io.diffblue.corebanking.communication.MessageRouter.OUT_BATCH;
import static io.diffblue.corebanking.communication.MessageRouter.OUT_MANUAL;
import static io.diffblue.corebanking.communication.MessageRouter.OUT_URGENT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.diffblue.corebanking.communication.MessageRouter;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;
import org.junit.Ignore;
import org.junit.Test;

public class MessageRouterServiceIT {

  @Test
  public void testUrgent() throws Exception {
    try (PulsarClient client = PulsarClient.builder()
        .serviceUrl(MessageRouterService.MESSAGE_BROKER_URL)
        .build()) {

      // Send a message
      String message = "BANK|150000|2020-01-10|9876|1234|" + Instant.now();
      System.out.printf("urgent: topic '%s': sending message '%s'...%n", IN_ALL, message);
      try (Producer<byte[]> producer = client.newProducer()
          .topic(IN_ALL)
          .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
          .sendTimeout(10, TimeUnit.SECONDS)
          .blockIfQueueFull(true)
          .create()) {

        MessageId id = producer.newMessage()
            .key(MessageRouter.KEY_TRANSACTION)
            .value(message.getBytes())
            .property(MessageRouter.PROPERTY_URGENT, Boolean.TRUE.toString())
            .send();
        System.out.printf("urgent: topic '%s': sent '%s'%n", IN_ALL, id);
      }

      // Check message has been correctly routed
      try (Consumer consumer = client.newConsumer()
          .topic(OUT_URGENT)
          .subscriptionName("my-urgent-subscription")
          .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
          .subscribe()) {

        System.out.printf("urgent: topic '%s': receiving...%n", OUT_URGENT);
        Message msg = consumer.receive();
        System.out.printf("urgent: topic '%s': received '%s'%n", OUT_URGENT, msg.getMessageId());
        System.out.printf("urgent: topic '%s': acknowledging...%n", OUT_URGENT);
        consumer.acknowledge(msg);
        System.out.printf("urgent: topic '%s': done%n", OUT_URGENT);

        assertEquals(MessageRouter.KEY_TRANSACTION, msg.getKey());
        assertTrue(msg.hasProperty(MessageRouter.PROPERTY_URGENT));
        assertEquals(Boolean.TRUE.toString(), msg.getProperty(MessageRouter.PROPERTY_URGENT));
        assertArrayEquals(message.getBytes(), msg.getData());
      }
    }
  }

  @Test
  public void testBatch() throws Exception {
    try (PulsarClient client = PulsarClient.builder()
        .serviceUrl(MessageRouterService.MESSAGE_BROKER_URL)
        .build()) {

      String message = "BANK|150000|2020-01-10|9876|1234|" + Instant.now();
      System.out.printf("batch: topic '%s': sending message '%s'...%n", IN_ALL, message);
      try (Producer<byte[]> producer = client.newProducer()
          .topic(IN_ALL)
          .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
          .sendTimeout(10, TimeUnit.SECONDS)
          .blockIfQueueFull(true)
          .create()) {

        MessageId id = producer.newMessage()
            .key(MessageRouter.KEY_TRANSACTION)
            .value(message.getBytes())
            .send();
        System.out.printf("batch: topic '%s': sent '%s'%n", IN_ALL, id);
      }

      // Check message has been correctly routed
      System.out.println("batch: receiving message...");
      try (Consumer consumer = client.newConsumer()
          .topic(OUT_BATCH)
          .subscriptionName("my-batch-subscription")
          .subscriptionName(OUT_BATCH)
          .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
          .subscribe()) {

        System.out.printf("batch: topic '%s': receiving...%n", OUT_BATCH);
        Message msg = consumer.receive();
        System.out.printf("batch: topic '%s': received '%s'%n", OUT_BATCH, msg.getMessageId());
        System.out.printf("batch: topic '%s': acknowledging...%n", OUT_BATCH);
        consumer.acknowledge(msg);
        System.out.printf("batch: topic '%s': done%n", OUT_BATCH);

        assertEquals(MessageRouter.KEY_TRANSACTION, msg.getKey());
        assertFalse(msg.hasProperty(MessageRouter.PROPERTY_URGENT));
        assertArrayEquals(message.getBytes(), msg.getData());
      }
    }
  }

  @Test
  public void testManual() throws Exception {
    try (PulsarClient client = PulsarClient.builder()
        .serviceUrl(MessageRouterService.MESSAGE_BROKER_URL)
        .build()) {

      // Send a message
      String message = "blablabla " + Instant.now();
      System.out.printf("manual: topic '%s': sending message '%s'...%n", IN_ALL, message);
      try (Producer<byte[]> producer = client.newProducer()
          .topic(IN_ALL)
          .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
          .sendTimeout(10, TimeUnit.SECONDS)
          .blockIfQueueFull(true)
          .create()) {

        MessageId id = producer.newMessage()
            .key("blablabla")
            .value(message.getBytes())
            .send();
        System.out.printf("manual: topic '%s': sent '%s'%n", IN_ALL, id);
      }

      // Check message has been correctly routed
      try (Consumer consumer = client.newConsumer()
          .topic(MessageRouter.OUT_MANUAL)
          .subscriptionName("my-manual-subscription")
          .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
          .subscriptionName(MessageRouter.OUT_MANUAL)
          .subscribe()) {

        System.out.printf("manual: topic '%s': receiving...%n", OUT_MANUAL);
        Message msg = consumer.receive();
        System.out.printf("manual: topic '%s': received '%s'%n", OUT_MANUAL, msg.getMessageId());
        System.out.printf("manual: topic '%s': acknowledging...%n", OUT_MANUAL);
        consumer.acknowledge(msg);
        System.out.printf("manual: topic '%s': done%n", OUT_MANUAL);

        assertFalse(MessageRouter.KEY_TRANSACTION.equals(msg.getKey()));
        assertArrayEquals(message.getBytes(), msg.getData());
      }
    }
  }
}
