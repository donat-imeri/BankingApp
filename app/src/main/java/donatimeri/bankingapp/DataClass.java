package donatimeri.bankingapp;

import java.io.Serializable;

public class DataClass implements Serializable {
    public String dateArray;
    public String recipientArray;
    public Money amountTransferedArray;
    public Money actualValueArray;


    public DataClass(String date, String recipient, Money amount, Money actual){
        this.dateArray=date;
        this.recipientArray=recipient;
        this.amountTransferedArray=amount;
        this.actualValueArray=actual;
    }

}
