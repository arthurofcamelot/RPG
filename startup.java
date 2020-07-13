package game;

public class startup extends RPG
{
    public void starter()
    {
        while(selection==1)
        {
            System.out.println("You have 40 points, give each stat 1-20 points. Excess points are converted to extra coins to be used in store/");
            System.out.println("Strength determines Max HP and strike damage. You have "+statp+" points remaining");
            strength=plinput.nextInt();
            statp = statp-strength;
            System.out.println("Intelligence determines success rate and damage scaling of spells. You have "+statp+" points remaining.");
            intelligence = plinput.nextInt();
            statp = statp-intelligence;
            System.out.println("Dexterity determines dodge chance. You have "+statp+" points remaining");
            dexterity = plinput.nextInt();
            statp = statp-dexterity;
            if(statp<0||strength<1||strength>20||intelligence<1||intelligence>20||dexterity<1||dexterity>20)
            {
                System.out.println("NO, you didn't follow the rules!");
            }
            else{selection--;}
        }
        if(statp>0)
        { coin = coin + statp;}
    }
}