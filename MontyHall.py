import random
DoorA=0
DoorB=0
DoorC=0
RevDoorA=0
RevDoorB=0
RevDoorC=0

GamesSim=int(input("How many Simulations?:"))
ManSim=int(input("How many Manual Games?:"))

#Manual
ManualWins=0
ManualLosses=0
TotalGames=0
GamesSim=0
while(TotalGames<ManSim):
    WinDoor = random.randint(1, 3)
    #print("Win Door set!",WinDoor)
    #DoorChoice=random.randint(1,3)
    DoorChoice=int(input("What door? (Int only 1-3)"))
    #print("DoorChoice set!", DoorChoice)
    RevealedDoor=random.randint(1,3)
    #print("RevealedDoor set!", RevealedDoor)
    if(WinDoor==RevealedDoor or DoorChoice==RevealedDoor):
        NotPrizeDoor=0
        while(NotPrizeDoor!=1):
            #print("Rerolling! WinDoor == RevealedDoor or DoorChoice==RevealedDoor!")
            RevealedDoor = random.randint(1, 3)
            #print("RevealedDoor set!", RevealedDoor)
            if(RevealedDoor != WinDoor and RevealedDoor != DoorChoice):
                #print("RevealedDoor != WinDoor! and RevealedDoor != DoorChoice!")
                #print("Loop broke!")
                NotPrizeDoor=1

    print("Door Choice =",DoorChoice,"\nWinDoor",WinDoor,"\nRevealedDoor",RevealedDoor)
    #if(DoorChoice == 1):
    #    print("DoorChoice is 1!")

    #elif(DoorChoice == 2):
    #    print("DoorChoice is 2!")

    #elif(DoorChoice == 3):
    #    print("DoorChoice is 3!")

    if(RevealedDoor == 1):
        print("RevealedDoor is 1! No prize!")

    elif(RevealedDoor == 2):
        print("RevealedDoor is 2! No prize!")

    elif(RevealedDoor == 3):
        print("RevealedDoor is 3! No prize!")

    Swap=input("Do you want to swap? Y/N?")

    if(Swap=="Y" or Swap=="y"):
        if(DoorChoice==1 and RevealedDoor==2):
            DoorChoice=3
        elif (DoorChoice == 1 and RevealedDoor == 3):
            DoorChoice = 2
        elif (DoorChoice == 2 and RevealedDoor == 1):
            DoorChoice = 3
        elif (DoorChoice == 2 and RevealedDoor == 3):
            DoorChoice = 1
        elif (DoorChoice == 3 and RevealedDoor == 1):
            DoorChoice = 2
        elif (DoorChoice == 3 and RevealedDoor == 2):
            DoorChoice = 1
        print("Door Swapped to",DoorChoice)

    if(WinDoor == 1):
        print("Winning door is 1!")
        if(DoorChoice ==1):
            print("You win!")
            TotalGames=TotalGames+1
            ManualWins = ManualWins+1
        else:
            print("You lose!")
            TotalGames = TotalGames + 1
            ManualLosses = ManualLosses + 1

    if(WinDoor == 2):
        print("Winning door is 2!")
        if (DoorChoice == 2):
            print("You win!")
            TotalGames = TotalGames + 1
            ManualWins = ManualWins + 1
        else:
            print("You lose!")
            TotalGames = TotalGames + 1
            ManualLosses = ManualLosses + 1

    if(WinDoor == 3):
        print("Winning door is 3!")
        if (DoorChoice == 3):
            print("You win!")
            TotalGames = TotalGames + 1
            ManualWins = ManualWins + 1
        else:
            print("You lose!")
            TotalGames = TotalGames + 1
            ManualLosses = ManualLosses + 1

if(TotalGames!=0):
    print("After",TotalGames,"games...")
    print("You won",ManualWins,"times!")
    print("You lost",ManualLosses,"times!")
    WinPercent=ManualWins/TotalGames
    WinPercent=WinPercent*100
    WinPercent=str(WinPercent)
    print("You had a win rate of "+WinPercent+"%!")

PrintRule="N"
PrintRule=input("Print? Y/N")
if(PrintRule == "Y" or PrintRule == "y"):
    PrintRule == "Y"


#Swap
SwapWins=0
SwapLosses=0
TotalGames=0
GamesSim=0
GamesSim=int(input("How many Swapping Games?:"))
while(TotalGames<GamesSim):
    WinDoor = random.randint(1, 3)
    DoorChoice = random.randint(1, 3)
    RevealedDoor=random.randint(1,3)
    if(WinDoor==RevealedDoor or DoorChoice==RevealedDoor):
        NotPrizeDoor=0
        while(NotPrizeDoor!=1):
            #print("Rerolling! WinDoor == RevealedDoor or DoorChoice==RevealedDoor!")
            RevealedDoor = random.randint(1, 3)
            #print("RevealedDoor set!", RevealedDoor)
            if(RevealedDoor != WinDoor and RevealedDoor != DoorChoice):
                #print("RevealedDoor != WinDoor! and RevealedDoor != DoorChoice!")
                #print("Loop broke!")
                NotPrizeDoor=1

    if(PrintRule=="Y"):
        print("\n\n\n---------------------------------")
        print("Door Choice =",DoorChoice,"\nWinDoor",WinDoor,"\nRevealedDoor",RevealedDoor)
    #if(DoorChoice == 1):
    #    print("DoorChoice is 1!")

    #elif(DoorChoice == 2):
    #    print("DoorChoice is 2!")

    #elif(DoorChoice == 3):
    #    print("DoorChoice is 3!")

    if (PrintRule == "Y"):
        if(RevealedDoor == 1):
            print("RevealedDoor is 1! No prize!")

        elif(RevealedDoor == 2):
            print("RevealedDoor is 2! No prize!")

        elif(RevealedDoor == 3):
            print("RevealedDoor is 3! No prize!")

    Swap="Y"

    if(Swap=="Y" or Swap=="y"):
        if(DoorChoice==1 and RevealedDoor==2):
            DoorChoice=3
        elif (DoorChoice == 1 and RevealedDoor == 3):
            DoorChoice = 2
        elif (DoorChoice == 2 and RevealedDoor == 1):
            DoorChoice = 3
        elif (DoorChoice == 2 and RevealedDoor == 3):
            DoorChoice = 1
        elif (DoorChoice == 3 and RevealedDoor == 1):
            DoorChoice = 2
        elif (DoorChoice == 3 and RevealedDoor == 2):
            DoorChoice = 1
        if (PrintRule == "Y"):
            print("Door Swapped to",DoorChoice)

    if(WinDoor == 1):
        if (PrintRule == "Y"):
            print("Winning door is 1!")
        if(DoorChoice ==1):
            if (PrintRule == "Y"):
                print("You win!")
            TotalGames=TotalGames+1
            SwapWins = SwapWins+1
        else:
            if (PrintRule == "Y"):
                print("You lose!")
            TotalGames = TotalGames + 1
            SwapLosses = SwapLosses + 1

    if(WinDoor == 2):
        if (PrintRule == "Y"):
            print("Winning door is 2!")
        if (DoorChoice == 2):
            if (PrintRule == "Y"):
                print("You win!")
            TotalGames = TotalGames + 1
            SwapWins = SwapWins + 1
        else:
            if (PrintRule == "Y"):
                print("You lose!")
            TotalGames = TotalGames + 1
            SwapLosses = SwapLosses + 1

    if(WinDoor == 3):
        if (PrintRule == "Y"):
            print("Winning door is 3!")
        if (DoorChoice == 3):
            if (PrintRule == "Y"):
                print("You win!")
            TotalGames = TotalGames + 1
            SwapWins = SwapWins + 1
        else:
            if (PrintRule == "Y"):
                print("You lose!")
            TotalGames = TotalGames + 1
            SwapLosses = SwapLosses + 1

if(TotalGames!=0):
    print("After",TotalGames,"games...")
    print("You won",SwapWins,"times!")
    print("You lost",SwapLosses,"times!")
    WinPercent=SwapWins/TotalGames
    WinPercent=WinPercent*100
    WinPercent=str(WinPercent)
    print("Swapping had a win rate of "+WinPercent+"%!")

#Stick
StickWins=0
StickLosses=0
TotalGames=0
GamesSim=0
GamesSim=int(input("How many Sticking Games?:"))
while(TotalGames<GamesSim):
    WinDoor = random.randint(1, 3)
    DoorChoice = random.randint(1, 3)
    RevealedDoor=random.randint(1,3)
    if(WinDoor==RevealedDoor or DoorChoice==RevealedDoor):
        NotPrizeDoor=0
        while(NotPrizeDoor!=1):
            #print("Rerolling! WinDoor == RevealedDoor or DoorChoice==RevealedDoor!")
            RevealedDoor = random.randint(1, 3)
            #print("RevealedDoor set!", RevealedDoor)
            if(RevealedDoor != WinDoor and RevealedDoor != DoorChoice):
                #print("RevealedDoor != WinDoor! and RevealedDoor != DoorChoice!")
                #print("Loop broke!")
                NotPrizeDoor=1
    if (PrintRule == "Y"):
        print("\n\n\n---------------------------------")
        print("Door Choice =",DoorChoice,"\nWinDoor",WinDoor,"\nRevealedDoor",RevealedDoor)

    #if(DoorChoice == 1):
    #    print("DoorChoice is 1!")

    #elif(DoorChoice == 2):
    #    print("DoorChoice is 2!")

    #elif(DoorChoice == 3):
    #    print("DoorChoice is 3!")
    if (PrintRule == "Y"):
        if(RevealedDoor == 1):
            print("RevealedDoor is 1! No prize!")

        elif(RevealedDoor == 2):
            print("RevealedDoor is 2! No prize!")

        elif(RevealedDoor == 3):
            print("RevealedDoor is 3! No prize!")

    Swap="N"

    if(Swap=="Y" or Swap=="y"):
        if(DoorChoice==1 and RevealedDoor==2):
            DoorChoice=3
        elif (DoorChoice == 1 and RevealedDoor == 3):
            DoorChoice = 2
        elif (DoorChoice == 2 and RevealedDoor == 1):
            DoorChoice = 3
        elif (DoorChoice == 2 and RevealedDoor == 3):
            DoorChoice = 1
        elif (DoorChoice == 3 and RevealedDoor == 1):
            DoorChoice = 2
        elif (DoorChoice == 3 and RevealedDoor == 2):
            DoorChoice = 1
        if (PrintRule == "Y"):
            print("Door Swapped to",DoorChoice)

    if(WinDoor == 1):
        if (PrintRule == "Y"):
            print("Winning door is 1!")
        if(DoorChoice ==1):
            if (PrintRule == "Y"):
                print("You win!")
            TotalGames=TotalGames+1
            StickWins = StickWins+1
        else:
            if (PrintRule == "Y"):
                print("You lose!")
            TotalGames = TotalGames + 1
            StickLosses = StickLosses + 1

    if(WinDoor == 2):
        if (PrintRule == "Y"):
            print("Winning door is 2!")
        if (DoorChoice == 2):
            if (PrintRule == "Y"):
                print("You win!")
            TotalGames = TotalGames + 1
            StickWins = StickWins + 1
        else:
            if (PrintRule == "Y"):
                print("You lose!")
            TotalGames = TotalGames + 1
            StickLosses = StickLosses + 1

    if(WinDoor == 3):
        if (PrintRule == "Y"):
            print("Winning door is 3!")
        if (DoorChoice == 3):
            if (PrintRule == "Y"):
                print("You win!")
            TotalGames = TotalGames + 1
            StickWins = StickWins + 1
        else:
            if (PrintRule == "Y"):
                print("You lose!")
            TotalGames = TotalGames + 1
            StickLosses = StickLosses + 1

if(TotalGames!=0):
    print("After",TotalGames,"games...")
    print("You won",StickWins,"times!")
    print("You lost",StickLosses,"times!")
    WinPercent=StickWins/TotalGames
    WinPercent=WinPercent*100
    WinPercent=str(WinPercent)
    print("Swapping had a win rate of "+WinPercent+"%!")
