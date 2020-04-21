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
        methodName = line.split("(")[0]
        location = line.split("(")[1].split(")")[0] 
        print("- " + methodName +  "() from " + location + "\\n", end = "")
        print("Link: https://github.com/diffblue/CoreBanking/blob/dcover-demo-master/src/test/java/" + location.replace(".", "/") + ".java\\n", end = "")
