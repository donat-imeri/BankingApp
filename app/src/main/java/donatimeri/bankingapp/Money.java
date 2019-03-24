package donatimeri.bankingapp;

import java.io.Serializable;
import java.util.Random;


public class Money implements Serializable {
    private int euro;
    private int cents;
    private boolean zeroAfterPoint; //Special case to check for zero after decimal point

    public Money(int euro, int cents){
        this.euro=euro;
        this.cents=cents;
        zeroAfterPoint=false;
    }

    public static Money sub(Money actual, Money substract){
        int newCents, newEuro;
        if (actual.cents>=substract.cents) {
            newCents = actual.cents - substract.cents;
            newEuro = actual.euro - substract.euro;
        }
        else{
            newCents=actual.cents+100-substract.cents;
            newEuro = actual.euro - substract.euro-1;
        }

        return new Money(newEuro,newCents);
    }

    public static boolean canSubstract(Money currentValue, Money substractValue){

        if ((currentValue.euro*100+currentValue.cents)>=(substractValue.euro*100+substractValue.cents)
                && (substractValue.euro*100+substractValue.cents)>0)
            return true;
        else
            return false;
    }

    public static Money initalValue(){
        int randomCent=0, randomEuro=0;
        Random rand=new Random();
        Random rand2=new Random();
        randomEuro=rand.nextInt(21)+90;
        if (randomEuro!=110)
        randomCent=rand2.nextInt(100);

        return new Money(randomEuro,randomCent);
    }

    public static Money getMoneyParts(String s){
        int randomCent=0, randomEuro=0;
        String[] parts;

        if(s.matches("^[0-9]+$"))
            randomEuro=Integer.parseInt(s);
        else if(s.matches("^[0-9]+\\.\\d+$")) {
            parts = s.split("\\.");
            randomEuro=Integer.parseInt(parts[0]);
            randomCent = Integer.parseInt(parts[1]);
            if (Integer.parseInt(String.valueOf(parts[1].charAt(0)))!=0) {
                if (String.valueOf(randomCent).length()<2)
                    randomCent*=10;
            }
        }
        else if(s.matches("^[0-9]+\\.$")){
            parts=s.split("\\.");
            randomEuro=Integer.parseInt(parts[0]);
        }

        return new Money(randomEuro,randomCent);
    }

    public String toString(){
        String money=euro+"."+cents;
        if (String.valueOf(cents).length()<2)
        money=euro+".0"+cents;

        return money;
    }
}
