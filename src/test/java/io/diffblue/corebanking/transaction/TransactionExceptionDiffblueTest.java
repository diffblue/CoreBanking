package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionExceptionDiffblueTest {
  /**
   * Test {@link TransactionException#TransactionException(String)}.
   *
   * <p>Method under test: {@link TransactionException#TransactionException(String)}
   */
  @Test
  @DisplayName("Test new TransactionException(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransactionException.<init>(String)"})
  void testNewTransactionException() {
    // Arrange and Act
    TransactionException actualTransactionException = new TransactionException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualTransactionException.getMessage());
    assertNull(actualTransactionException.getCause());
    assertEquals(0, actualTransactionException.getSuppressed().length);
  }
}
