package game;

public class shop extends RPG{
    public void shoppe()
    {
        while (store==1)
        {
            System.out.println("You have "+coin+" coins. 1: HP Potion  2: MP Potion  3: Strength Potion  4: Leave");
            int buychoice = plinput.nextInt();
            switch(buychoice)
            {
                //hp pot
                case 1: System.out.println("Restores 25 HP, can't overheal, 2 coins. 1: purchase 2: return");
                yn = plinput.nextInt();
                if (yn==1&coin>1)
                { hppot++; coin = coin-2;}
                break;
                //Mana pot
                case 2: System.out.println("Restores 20 Mana Points, 3 coins. 1: purchase 2: return");
                yn = plinput.nextInt();
                if (yn==1&coin>2)
                { mppot++; coin = coin -3;}
                break;
                //Str Pot
                case 3: System.out.println("Gives you 2 combat strength, 5 coins. 1: purchase 2: return");
                yn = plinput.nextInt();
                if(yn==1&coin>4)
                {strpot++; coin = coin - 5;}
                break;
                default: store=0; break;
            }
        }
    }
}