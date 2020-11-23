package io.diffblue.corebanking.communication;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.TypedMessageBuilder;
import org.junit.Test;

public class WorkerThreadTest {

  @Test
  public void testUrgent() throws Exception {
    Consumer inChannel = mock(Consumer.class);
    Message message = mock(Message.class);
    when(message.getKey()).thenReturn(MessageRouter.KEY_TRANSACTION);
    when(message.hasProperty(eq(MessageRouter.PROPERTY_URGENT))).thenReturn(true);
    when(message.getProperty(eq(MessageRouter.PROPERTY_URGENT))).thenReturn(Boolean.TRUE.toString());
    HashMap<String, String> properties = new HashMap<>();
    properties.put(MessageRouter.PROPERTY_URGENT, Boolean.TRUE.toString());
    when(message.getProperties()).thenReturn(properties);
    byte[] payload = "some message".getBytes();
    when(message.getData()).thenReturn(payload);
    when(inChannel.receive())
        .thenReturn(message)
        .thenThrow(new PulsarClientException(new InterruptedException()));

    HashMap<String, Producer<byte[]>> outChannels = new HashMap<>();
    Producer outUrgent = mock(Producer.class);

    TypedMessageBuilder messageBuilder = mock(TypedMessageBuilder.class);
    when(outUrgent.newMessage()).thenReturn(messageBuilder);
    when(messageBuilder.key(eq(MessageRouter.KEY_TRANSACTION))).thenReturn(messageBuilder);
    when(messageBuilder.properties(eq(properties))).thenReturn(messageBuilder);
    when(messageBuilder.value(eq(payload))).thenReturn(messageBuilder);
    when(messageBuilder.send()).thenReturn(null);

    Producer outBatch = mock(Producer.class);
    Producer outManual = mock(Producer.class);
    outChannels.put(MessageRouter.OUT_URGENT, outUrgent);
    outChannels.put(MessageRouter.OUT_BATCH, outBatch);
    outChannels.put(MessageRouter.OUT_MANUAL, outManual);

    WorkerThread workerThread = new WorkerThread(inChannel, outChannels);
    workerThread.run();

    verify(messageBuilder).send();
  }
}
