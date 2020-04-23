import sys

foundResults = False
foundTestsRun = False

for line in sys.argv[1].split("\n"):
    if "Results :" == line and not foundTestsRun:
        foundResults = True
    if "Tests run:" in line and "Time elapsed:" not in line:
        foundTestsRun = True
        foundResults = False
        print("\\n" + line)
    if foundResults and "expected" in line:
        if "Failed tests:   " in line:
            print("Failed Tests:\\n", end = "")
        line = line.replace("Failed tests: ", "")
        methodName = line.split("(")[0].replace("  ", "")
        location = line.split("(")[1].split(")")[0]
        failMessage = line.split(":", 1)[1].replace("\n", "").replace(":", "").replace(".", "").replace("|", "").replace("\t", "")
        #failMessage = "expectedount10  Balance20   Transactionstatbutwasount10 Balance0    Transactionstat"
        methodLine = ""
        try:
            file = open("src/test/java/" + location.replace(".", "/") + ".java")
            currentLine = 1
            for line in file:
                if "public void " + methodName + "()" in line:
                    methodLine = "#L" + str(currentLine)
                currentLine += 1
        except:
            print("")  # Need a better solution for this
        print("- [" + methodName +  "()](https://github.com/diffblue/CoreBanking/blob/dcover-demo-master/src/test/java/" + location.replace(".", "/") + ".java" + methodLine + ") from " + location + "\\n" + failMessage + "\\n", end = "")
