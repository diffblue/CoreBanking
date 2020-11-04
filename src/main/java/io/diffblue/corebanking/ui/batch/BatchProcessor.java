package io.diffblue.corebanking.ui.batch;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.compliance.CheckCompliance;
import io.diffblue.corebanking.transaction.BankTransaction;
import io.diffblue.corebanking.transaction.CashTransaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BatchProcessor {
  public static void main(String[] args) {
    if (args.length != 1) {
      return;
    }

    try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      BatchProcessor batchProcessor = new BatchProcessor();
      reader.lines().forEach(batchProcessor::processLine);
      batchProcessor.check();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private Map<String, Client> clients = new HashMap<>();
  private Map<Long, Account> accounts = new HashMap<>();

  private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  private void processLine(String line) {
    String[] command = line.split("\\|");
    if (command.length == 0) {
      throw new IllegalArgumentException("Command expected");
    }
    switch(command[0]) {
      case "CLIENT": {
        if (command.length != 2) {
          throw new IllegalArgumentException("CLIENT expects one argument (client name)");
        }
        clients.put(command[1], new Client(command[1]));
        break;
      }
      case "ACCOUNT": {
        if (command.length != 4) {
          throw new IllegalArgumentException(
              "ACCOUNT expects three arguments (account number, client name, initial balance)");
        }
        Long accountNumber = Long.parseLong(command[1]);
        accounts.put(accountNumber, new Account(
            accountNumber, clients.get(command[2]), Long.parseLong(command[3])));
        break;
      }
      case "CASH": {
        if (command.length != 4) {
          throw new IllegalArgumentException(
              "CASH expects three arguments (amount, date, account number)");
        }
        try {
          new CashTransaction(
              Long.parseLong(command[1]),
              dateFormat.parse(command[2]),
              accounts.get(Long.parseLong(command[3])))
              .executeTransaction();
        } catch (Exception e) {
          throw new RuntimeException(e.getMessage());
        }
        break;
      }
      case "BANK": {
        if (command.length != 5) {
          throw new IllegalArgumentException(
              "BANK expects four arguments (amount, date, source and target account number)");
        }
        try {
          new BankTransaction(
              Long.parseLong(command[1]),
              dateFormat.parse(command[2]),
              accounts.get(Long.parseLong(command[3])),
              accounts.get(Long.parseLong(command[4])))
              .executeTransaction();
        } catch (Exception e) {
          throw new RuntimeException(e.getMessage());
        }
        break;
      }
      default:
        throw new IllegalArgumentException("Unexpected command: " + command[0]);
    }
  }

  private void check() {
    CheckCompliance.checkAccountCompliance(new ArrayList<>(accounts.values()));
    CheckCompliance.viewComplianceCheckResults();
  }
}
