package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class TransactionExceptionDiffblueTest {
  /**
   * Method under test: {@link TransactionException#TransactionException(String)}
   */
  @Test
  void testNewTransactionException() {
    // Arrange and Act
    TransactionException actualTransactionException = new TransactionException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualTransactionException.getLocalizedMessage());
    assertEquals("An error occurred", actualTransactionException.getMessage());
    assertNull(actualTransactionException.getCause());
    assertEquals(0, actualTransactionException.getSuppressed().length);
  }
}
