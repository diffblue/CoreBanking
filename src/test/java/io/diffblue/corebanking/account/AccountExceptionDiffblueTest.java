package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class AccountExceptionDiffblueTest {
  /**
  * Method under test: {@link AccountException#AccountException(String)}
  */
  @Test
  void testConstructor() {
    // Arrange and Act
    AccountException actualAccountException = new AccountException("An error occurred");

    // Assert
    assertNull(actualAccountException.getCause());
    assertEquals(0, actualAccountException.getSuppressed().length);
    assertEquals("An error occurred", actualAccountException.getMessage());
    assertEquals("An error occurred", actualAccountException.getLocalizedMessage());
  }
}

