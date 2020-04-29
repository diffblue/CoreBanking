import os

testLoc = "src/dcover/java"

for folderName, subfolders, fileNames in os.walk(testLoc):
    for fileName in fileNames:
        filePath = os.path.join(folderName, fileName)
        if fileName.endswith(".java"):
            
            # Remove all trivial tests
            file = open(filePath, "r")
            newFile = []
            depth = 0
            for line in file:
                if ("tostring" in line.lower() or "constructor" in line.lower() or "equals" in line.lower()) and "public void" in line:
                    newFile.pop()
                    #print(newFile.pop())
                    #print(line.replace("\n", ""))
                    depth = 1
                elif depth > 0:
                    #print(line.replace("\n", ""))
                    for char in line:
                        if char == "{":
                            depth += 1
                        elif char == "}":
                            depth -= 1
                else:
                    newFile.append(line)
            file.close()
            file = open(filePath, "w")
            for line in newFile:
                file.write(line)
            file.close()

            # Remove tostrings in asserts
            file = open(filePath, "r")
            newFile = []
            currentAssert = ""
            multiLine = False
            prevMulti = False
            for line in file:
                if multiLine:
                    currentAssert = currentAssert + line
                    if line.endswith(";\n"):
                        multiLine = False
                        if "toString()" in currentAssert:
                            #print(currentAssert)
                            nothing = 1
                        else:
                            newFile.append(currentAssert)
                        prevMulti = True

                if "assert" in line and not "import" in line:
                    if not line.endswith(";\n"):
                        currentAssert = line
                        multiLine = True
                    else:
                        if "toString()" in line:
                            nothing = 1
                            #print(line)
                        else:
                            newFile.append(line)
                elif not multiLine and not prevMulti:
                    newFile.append(line)
                prevMulti = False
            file.close()
            file = open(filePath, "w")
            for line in newFile:
                file.write(line)
            file.close()

            #remove all tests that have no asserts
            file = open(filePath, "r")
            newFile = []
            depth = 0
            inTest = False
            asserts = 0
            for line in file:
                if "public void" in line:
                    inTest = True
                    depth = 1
                elif depth > 0:
                    #print(line.replace("\n", ""))
                    for char in line:
                        if char == "{":
                            depth += 1
                        elif char == "}":
                            depth -= 1
                    if "assert" in line:
                        asserts += 1
                elif depth == 0 and inTest:
                    inTest = False
                    if asserts == 0:
                        while "@Test" not in newFile.pop():
                            nothing = 0
                    asserts = 0
                newFile.append(line)
            
            file = open(filePath, "w")
            for line in newFile:
                file.write(line)
            file.close()


            # Remove all classes with no tests
            file = open(filePath, "r")
            tests = 0
            for line in file:
                tests += "@Test" in line
            file.close()
            if tests == 0:
                os.system("rm -rf " + filePath) # Yes I know this is bad


