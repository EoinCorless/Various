import random

Item = "FSP-9"
TotalRefines = 0
RefineCounter = 0
TestNumber = 1000000
Attempts = 0
SuccessfulAttempts = 0
FailedAttempts = 0
HighestCount = 0
LowestCount = 10000
InfinityGuard = False

while TestNumber > Attempts:
    RefineCounter = 0
    while (Item != "COM-45"):
        #print("Current Item =", Item, "\nRefines performed =", RefineCounter)

        if(Item == "FSP-9"):
            RefineCounter = RefineCounter + 1
            Item = "COM-18"

        elif(Item == "COM-18"):
            RefineCounter = RefineCounter + 1
            Item = "COM-15"

        elif(Item == "COM-15"):
            RefineCounter = RefineCounter + 1
            RandNum = random.randint(1,1000)
            if(RandNum <= 550):
                Item = "FSP-9"
            elif(RandNum <= 845):
                Item = ".44 Revolver"
            elif(RandNum <= 1000):
                Item = "COM-45"

        elif(Item == ".44 Revolver"):
            RefineCounter = RefineCounter + 1
            Item = "Crossvec"

        elif(Item == "Crossvec"):
            RefineCounter = RefineCounter + 1
            Item = "FSP-9"

        elif(Item == "COM-45"):
            print("COM-45 reached, bottom of block")

        elif(RefineCounter == 10000):
            print("RefineCounter hit 10000")
            InfinityGuard = True
            break

        else:
            print("ERROR!")

    if(Item == "COM-45"):
        #print("----->COM-45 reached in",RefineCounter,"refines!<-----")
        Attempts = Attempts + 1
        SuccessfulAttempts = SuccessfulAttempts + 1
        TotalRefines = TotalRefines + RefineCounter
        if(HighestCount < RefineCounter):
            HighestCount = RefineCounter
        if (LowestCount > RefineCounter):
            LowestCount = RefineCounter
        RefineCounter = 0
        Item = "FSP-9"

    elif(InfinityGuard == True):
        print("Infinity Guard tripped.\nFailure at refine", RefineCounter)
        Attempts = Attempts + 1
        FailedAttempts = FailedAttempts + 1
        Item = "FSP-9"


    else:
        print("Failure at refine",RefineCounter)
        Attempts = Attempts + 1
        FailedAttempts = FailedAttempts + 1
        Item = "FSP-9"

AverageRefines = TotalRefines/SuccessfulAttempts
print("---------->Test Completed!\nTotal Attempts =", Attempts, "\nSuccessful Attempts =", SuccessfulAttempts, "\nFailed Attempts =", FailedAttempts)
print("---------->Average Refines per success= ", AverageRefines)
print("---------->Highest Count:", HighestCount)
print("---------->Lowest Count:", LowestCount)