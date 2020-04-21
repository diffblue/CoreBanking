import sys

foundResults = False
foundTestsRun = False

for line in sys.argv[1].split("\n"):
    if "Results :" == line and not foundTestsRun:
        foundResults = True
    if "Tests run:" in line and "Time elapsed:" not in line:
        foundTestsRun = True
        foundResults = False
        print(line)
    if foundResults and "expected" in line:
        if "Failed tests:   " in line:
            print("Failed Tests:")
        line = line.replace("Failed tests: ", "")
        #print(line.split("(")[0] +  "() from " + line.split("(")[1].split(")")[0] + "")
