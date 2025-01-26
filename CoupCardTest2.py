import random

#duke 1,2,3
#assa 4,5,6
#capt 7,8,9
#amba 10,11,12
#cont 13,14,15
runs = 0
runsWithduke = 0

while(runs != 1000000):
    x = random.randint(1,15)
    y = random.randint(1,15)

    while(x == y):
        y = random.randint(1,15)


    if(x == 1):
        runsWithduke = runsWithduke+1
    ##elif(x == 2):
    ##    runsWithduke = runsWithduke+1
    #elif(x == 3):
    #   runsWithduke = runsWithduke+1
    elif(y == 1):
        runsWithduke = runsWithduke+1
    ##elif(y == 2):
    ##    runsWithduke = runsWithduke+1
    #elif(y == 3):
    #   runsWithduke = runsWithduke+1

    runs = runs+1

print(runs)
print(runsWithduke)

chance = runsWithduke/runs
chance = chance*100

print(chance,"%")