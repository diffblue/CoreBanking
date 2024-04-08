package io.diffblue.corebanking.datamanagement;

import static org.junit.jupiter.api.Assertions.*;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WriteToDBDiffblueTest {
  @Test
  public void testConstructor() throws Exception {
    WriteToDB writeDB = new WriteToDB();
    assertNotNull(writeDB);
  }
}
