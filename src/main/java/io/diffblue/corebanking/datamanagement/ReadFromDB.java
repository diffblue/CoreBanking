package io.diffblue.corebanking.datamanagement;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.transaction.CashTransaction;
import io.diffblue.corebanking.transaction.TransactionException;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;

import java.util.Date;

public class ReadFromDB {
  public static CoreBanking readFromDB() throws TransactionException {
    CoreBanking coreBanking = new CoreBanking();
    readFromDB(coreBanking);
    return coreBanking;
  }

  public static void readFromDB(CoreBanking coreBanking) throws TransactionException {
    coreBanking.purgeCoreBanking();

    tempStaticData(coreBanking);
  }

  private static void tempStaticData(CoreBanking coreBanking) throws TransactionException {
    // Clients 1
    Client client1 = new Client("John Field");
    Account acc1cli1 = coreBanking.openNewAccount(client1, 100);
    Account acc2cli1 = coreBanking.openNewAccount(client1, 100);
    Account acc3cli1 = coreBanking.openNewAccount(client1, 100);

    // Client 2
    Client client2 = new Client("Jane Robbins");

    Account acc1cli2 = coreBanking.openNewAccount(client2, 100);
    Account acc2cli2 = coreBanking.openNewAccount(client2, 100);

    // Client 3
    Client client3 = new Client("Emily Simmons");

    Account acc1cli3 = coreBanking.openNewAccount(client3, 100);

    // Add clients to coreBanking.
    coreBanking.registerNewClient(client1);
    coreBanking.registerNewClient(client2);
    coreBanking.registerNewClient(client3);

    // Cash Transactions -- Add 100 to each account
    new CashTransaction(100, new Date(2019, 11, 10), acc1cli1).executeTransaction();
    new CashTransaction(100, new Date(2019, 11, 11), acc2cli1).executeTransaction();
    new CashTransaction(100, new Date(2019, 11, 12), acc3cli1).executeTransaction();
    new CashTransaction(100, new Date(2019, 11, 13), acc1cli2).executeTransaction();
    new CashTransaction(100, new Date(2019, 11, 14), acc2cli2).executeTransaction();
    new CashTransaction(100, new Date(2019, 11, 15), acc1cli3).executeTransaction();

    // Cash transactions, invalid
    new CashTransaction(-500, new Date(2019, 11, 13), acc1cli1).executeTransaction();

    // Bank Transactions
    //        new BankTransaction(100, new Date(2019, 11, 15), acc1cli1,
    // acc2cli1).executeTransaction();

  }
}
