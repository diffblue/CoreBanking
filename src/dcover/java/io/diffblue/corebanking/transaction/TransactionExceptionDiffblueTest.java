package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TransactionExceptionDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange and Act
    TransactionException actualTransactionException = new TransactionException("An error occurred");

    // Assert
    assertEquals("io.diffblue.corebanking.transaction.TransactionException:" + " An error occurred",
        actualTransactionException.toString());
    assertEquals("An error occurred", actualTransactionException.getLocalizedMessage());
    assertNull(actualTransactionException.getCause());
    assertEquals("An error occurred", actualTransactionException.getMessage());
    assertEquals(0, actualTransactionException.getSuppressed().length);
  }
}

