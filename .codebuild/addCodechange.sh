#! /usr/bin/env bash

if [ $(git config --get remote.origin.url)"" == "git@github.com:diffblue/CoreBanking.git" ]; then
	branchName=dcover-demo-$(date +'%d/%m-%H%M%S')
	git checkout -b $branchName
	cp .codebuild/codechange/Account.java src/main/java/io/diffblue/corebanking/account/Account.java
	cp .codebuild/codechange/BankTransaction.java src/main/java/io/diffblue/corebanking/transaction/BankTransaction.java
	cp .codebuild/codechange/CashTransaction.java src/main/java/io/diffblue/corebanking/transaction/CashTransaction.java
	cp .codebuild/codechange/CoreBanking.java src/main/java/io/diffblue/corebanking/CoreBanking.java
	cp .codebuild/codechange/ReadFromDB.java src/main/java/io/diffblue/corebanking/datamanagement/ReadFromDB.java
	cp .codebuild/codechange/Transaction.java src/main/java/io/diffblue/corebanking/transaction/Transaction.java

	vim -es '+:%s/currentBalance += amount/currentBalance -= amount/g' '+:wq!' src/main/java/io/diffblue/corebanking/account/Account.java
	git add src/main/java
	git commit -m "Add overdraft account"
	git push origin "$branchName"
	google-chrome https://github.com/diffblue/CoreBanking/compare/dcover-demo-master...$branchName?expand=1
else
	echo "you are not using the correct repo"
fi
