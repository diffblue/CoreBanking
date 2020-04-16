package io.diffblue.corebanking.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class AccountExceptionDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange and Act
    AccountException actualAccountException = new AccountException("An error occurred");

    // Assert
    assertEquals("io.diffblue.corebanking.account.AccountException:" + " An error occurred",
        actualAccountException.toString());
    assertEquals("An error occurred", actualAccountException.getLocalizedMessage());
    assertNull(actualAccountException.getCause());
    assertEquals("An error occurred", actualAccountException.getMessage());
    assertEquals(0, actualAccountException.getSuppressed().length);
  }
}

