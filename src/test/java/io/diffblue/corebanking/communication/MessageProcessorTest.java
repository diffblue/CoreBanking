package io.diffblue.corebanking.communication;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import org.apache.pulsar.client.api.Message;
import org.junit.Test;

public class MessageProcessorTest {

  @Test
  public void testUrgent() {
    // arrange
    MessageProcessor messageProcessor = new MessageProcessor();
    Message message = mock(Message.class);
    when(message.getKey()).thenReturn(MessageRouter.KEY_TRANSACTION);
    when(message.hasProperty(eq(MessageRouter.PROPERTY_URGENT))).thenReturn(true);
    when(message.getProperty(eq(MessageRouter.PROPERTY_URGENT))).thenReturn(Boolean.TRUE.toString());
    HashMap<String, String> properties = new HashMap<>();
    properties.put(MessageRouter.PROPERTY_URGENT, Boolean.TRUE.toString());
    when(message.getProperties()).thenReturn(properties);
    byte[] payload = "some message".getBytes();
    when(message.getData()).thenReturn(payload);

    // act and assert
    assertEquals(MessageRouter.OUT_URGENT, messageProcessor.computeOutChannel(message));
  }

}
