#! /usr/bin/env bash

masterBranch="dcover-dogfooding-newPr"

if [ $(git config --get remote.origin.url)"" == "git@github.com:diffblue/CoreBanking.git" ]; then
	if [ $(git name-rev --name-only HEAD)"" == "$masterBranch" ]; then
		if [ -f "src/main/java/io/diffblue/corebanking/account/Account.java" ]; then
			#Get branch
			branchName=dcover-demo-$(date +'%d/%m-%H%M%S')
			git checkout -b $branchName

			#Add new feature
			cp .codebuild/codechange/Account.java src/main/java/io/diffblue/corebanking/account/Account.java
			cp .codebuild/codechange/BankTransaction.java src/main/java/io/diffblue/corebanking/transaction/BankTransaction.java
			cp .codebuild/codechange/CashTransaction.java src/main/java/io/diffblue/corebanking/transaction/CashTransaction.java
			cp .codebuild/codechange/CoreBanking.java src/main/java/io/diffblue/corebanking/CoreBanking.java
			cp .codebuild/codechange/ReadFromDB.java src/main/java/io/diffblue/corebanking/datamanagement/ReadFromDB.java
			cp .codebuild/codechange/Transaction.java src/main/java/io/diffblue/corebanking/transaction/Transaction.java

			#Add regression
			vim -es '+:%s/currentBalance += amount/currentBalance -= amount/g' '+:wq!' src/main/java/io/diffblue/corebanking/account/Account.java

			#Commit and push changes
			git add src/main/java
			git commit -m "Add overdrafts to Corebanking back end"
			git push origin "$branchName"
			git checkout "$masterBranch"
			google-chrome "https://github.com/diffblue/CoreBanking/compare/$masterBranch...$branchName?expand=1"
		else
			echo "Cannot find file 'src/main/java/io/diffblue/corebanking/account/Account.java'. Are you sure you are at the root of the project"
		fi
	else
		echo "You are not on the correct branch you should be on '$masterBranch'"
	fi
else
	echo "Please make sure you are inside the correct repo"
fi
