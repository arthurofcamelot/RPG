package game;

import java.util.Random;
import java.util.Scanner;
public class RPG 
{
    static Random d20 = new Random();
    static Scanner plinput = new Scanner(System.in);
    static int skip =0;
    static int stun =0;
    static int result =0;
    static int roll = 0;
    static int hppot=0;
    static int strpot=0;
    static int mppot=0;
    static int playing=1;
    static int selection=1;
    static int store=1;
    static int statp=40;
    static int coin=5;
    static int strength=15;
    static int intelligence=15;
    static int dexterity=10;
    static int yn = 0;
    public static void main(String[]args)
    {
        //selection
        startup strt = new startup();
        strt.starter();
        // store
        shop shp = new shop();
        shp.shoppe();

        int batint = intelligence;
        int batstr= strength;
        int mxhp = 10+strength*2;
        double hp = mxhp;
        int mxmp = 30;
        int mp = mxmp;
        int enemhp = 50;
        System.out.println("A monster approaches! What will you do?");
        while(playing==1)
    {
        System.out.println("Your health:"+hp+" Your MP:"+mp+" Monster health:"+enemhp);
        if(batint>20)
        {   System.out.println("And you regenerate "+(batint-20)+" MP"); 
            mp= mp +(batint-20);
            if(mp>mxmp){System.out.println("But you can't go over the MX MP"); mp=mxmp;}
        }
        if(batstr>20)
        {   System.out.println("And you deal "+(batstr-20)+" damage to your foe.");
            enemhp=enemhp-(batstr-20);
            if (enemhp<1){ System.out.println("And this manages to kill your enemy"); stun=1; skip=1;}
        }
        if(batint<1)
        {   System.out.println("And you lose "+Math.abs(batint-1)+" MP"); 
            mp= mp-Math.abs(batint-1);
            if(mp<0){System.out.println("But you can't go into negative MP"); mp=0;}
        }
        if(batstr<1)
        {
            System.out.println("And you take "+Math.abs(batstr-1)+" damage");hp=hp-Math.abs(batstr-1);
            if(hp<0){System.out.println("and WOW, you killed yourself from exertion."); playing=0; skip=1; stun=1;}
        }
        if (stun==0)
        {
        System.out.println("1: magic  2: strike  3: item  4: stats");
        int choice = plinput.nextInt();
        switch(choice)
        {
            //magic
            case 1: System.out.println("1: fireball 10mp  2: heal 10 mp  3: enrage 5 mp 4: Return");
            int magchoice = plinput.nextInt();
            switch(magchoice)
            {
                case 1: /*fireball*/ mp=mp-10;
                roll = d20.nextInt(20);
                if (batint>20)
                {result=20-roll;}
                else if(batint<1)
                {result=1-roll;}
                else
                {result=batint-roll;}
                if(result==0)
                {
                    enemhp=enemhp-20;
                    System.out.println("CRIT! 20 Damage");
                } else if(result<0)
                {System.out.println("Fail");}
                else
                {enemhp= enemhp-(5+result);
                System.out.println((5+result)+" Damage");}
                break;

                case 2: /*heal*/ mp=mp-10;
                roll =d20.nextInt(20);
                if(batint>20)
                {result=20-roll;}
                else if(batint<1)
                {result=1-roll;}
                else
                {result=batint-roll;}
                if (result==0){ 
                    hp=hp+20;
                    if(hp>mxhp)
                    {
                        hp = hp- (Math.floor((hp-mxhp)/2));
                    }
                    System.out.println("Crit!");
                } else if(result<0)
                {System.out.println("fail");}
                else
                {
                    hp=hp+10;
                    if(hp>mxhp)
                    {hp=mxhp;}
                    System.out.println("10 Heal");
                }
                break;

                case 3: /*enrage*/ mp=mp-5;
                roll=d20.nextInt(20);
                if(batint>20)
                {result=20-roll;}
                else if(batint<1)
                {result=1-roll;}
                else
                {result=batint-roll;}
                if(result==0)
                {
                    batint--;
                    batstr= batstr+2;
                    System.out.println("CRIT!");
                } else if(result<0)
                {System.out.println("Fail");}
                else
                {
                    batint--;
                    batstr++;
                }
                break;

                case 4: /*return*/ skip++; break;
                default: skip++; break;
            }
            break;
            //strike
            case 2:
            roll=d20.nextInt(20);
            if(roll==20){enemhp=enemhp-25;
            System.out.println("CRIT! 25 damage");}
            else{ 
                 if (batstr>20) 
                 { enemhp=enemhp-20; System.out.println("20 damage");}
                 else if (batstr<1)
                {enemhp=enemhp-1;System.out.println("1 damage");}
                 else 
                {enemhp=enemhp-batstr;System.out.println(batstr+" damage");}
                }
            break;
            //items
            case 3: System.out.println("1: Hp Potions "+hppot+", 2: Strength Potions "+strpot+", 3: Mana Potions "+mppot+", 4: return");
                int potchoice = plinput.nextInt();
                switch(potchoice)
                {
                    // Health potion
                    case 1: if(hppot==0){System.out.println("Out of Health Potions!"); skip++;}
                    else {hppot--; hp=hp+25; 
                        if (hp>mxhp){hp=mxhp;}}
                    break;
                    //strength potion
                    case 2:  if(strpot>0){batstr=batstr+2; strpot--;}
                    else{System.out.println("Out of Strength Potions!"); skip++;;}
                    break;
                    //Mana Potion
                    case 3: if(mppot==0){System.out.println("Out of Mana Potions!"); skip++;}
                    else{mp=mp+20; mppot--; if(mp>30){mp=30;}}
                    break;
                    //return
                    case 4: skip++;
                    default: skip++; break;
                }
            break;
            //stats
            case 4:
            System.out.println("intelligence:"+intelligence+" Current intelligence:"+batint+" Strength:"+strength+" Current strength:"+batstr+" Dexterity:"+dexterity);
            skip++; break;
            default: skip++; break;
        }
        }   
        if(enemhp>0)
        {
            //enemy turn
            if(skip==0)
            {
            roll = d20.nextInt(50);
            result = dexterity-roll;
            if(result==0)
            {
                System.out.println("CRIT! 30 damage.");
                hp=hp-30;
            } else if(result>0)
            {System.out.println("Dodge!");}
            else{hp=hp-15; System.out.println("You took 15 damage");}
            } else {skip=0;}
        } else
        {
            playing=0;
            enemhp=0;
            System.out.println("You won with "+hp+" health left! You got 3 coins, You have "+(coin+3)+" coins!");
        }
        if (hp<0)
        {
            playing=0;
            hp =0;
            System.out.println("You lost with only "+enemhp+" more damage left to go!");
        }
    }
        plinput.close();
    }
}