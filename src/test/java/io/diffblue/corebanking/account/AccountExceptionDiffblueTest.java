package io.diffblue.corebanking.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class AccountExceptionDiffblueTest {
  /**
  * Method under test: {@link AccountException#AccountException(String)}
  */
  @Test
  public void testConstructor() {
    // Arrange and Act
    AccountException actualAccountException = new AccountException("An error occurred");

    // Assert
    assertNull(actualAccountException.getCause());
    assertEquals(0, actualAccountException.getSuppressed().length);
    assertEquals("An error occurred", actualAccountException.getMessage());
    assertEquals("An error occurred", actualAccountException.getLocalizedMessage());
  }
}

