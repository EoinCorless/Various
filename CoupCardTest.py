TotalCards = 14
Dukes = 2
Dealt = 2

def CardChanceFunc(TotalCards, CardNum, Dealt):
    TotalCombos = ((TotalCards)*(TotalCards-1))/((Dealt)*(Dealt-1))
    NotGettingCard = ((TotalCards-CardNum)*((TotalCards-CardNum)-1))/((Dealt)*(Dealt-1))
    ChanceOfNotCard = NotGettingCard/TotalCombos
    CardChance = 1-ChanceOfNotCard
    CardChance = CardChance*100
    CardChance = round(CardChance)
    print(CardChance)

CardChanceFunc(TotalCards, Dukes, Dealt)