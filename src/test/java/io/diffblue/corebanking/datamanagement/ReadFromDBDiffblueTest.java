package io.diffblue.corebanking.datamanagement;

import static org.junit.jupiter.api.Assertions.*;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.jupiter.api.Test;

class ReadFromDBDiffblueTest {
  @Test
  void readFromDBTest() throws Exception {
    try {
      CoreBanking coreBanking = ReadFromDB.readFromDB();
      assertNotNull(coreBanking, "CoreBanking object should not be null");
    } catch (TransactionException e) {
      fail("TransactionException was thrown");
    }
  }

  @Test
  void readFromDBTest2() throws Exception {
    try {
      CoreBanking coreBanking = ReadFromDB.readFromDB();
      assertNotNull(coreBanking, "CoreBanking object should not be null");
    } catch (TransactionException e) {
      fail("TransactionException was thrown");
    }
  }

  @Test
  void readFromDBWithCoreBankingTest() throws Exception {
    try {
      CoreBanking coreBanking = new CoreBanking();
      ReadFromDB.readFromDB(coreBanking);
      assertNotNull(coreBanking.getAccounts(), "Accounts list should not be null");
      assertNotNull(coreBanking.getClients(), "Clients list should not be null");
    } catch (TransactionException e) {
      fail("TransactionException was thrown");
    }
  }

  @Test
  void readFromDBWithCoreBankingTest2() throws Exception {
    try {
      CoreBanking coreBanking = new CoreBanking();
      ReadFromDB.readFromDB(coreBanking);
      assertNotNull(coreBanking.getAccounts(), "Accounts list should not be null");
      assertNotNull(coreBanking.getClients(), "Clients list should not be null");
    } catch (TransactionException e) {
      fail("TransactionException was thrown");
    }
  }
}
