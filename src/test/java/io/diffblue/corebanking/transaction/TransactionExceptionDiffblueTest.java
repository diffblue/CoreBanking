package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class TransactionExceptionDiffblueTest {
  @Test
  public void constructorTest() {
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

