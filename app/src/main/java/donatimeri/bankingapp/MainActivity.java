package donatimeri.bankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String sdataTransactions="dataTransactions";
    public final static String ssuccesful="Transfer Done";
    public final static String srandomInt="randomInt";
    public final static String ownerName="Donat Imeri";
    public final static String srecipients="recipients";
    public final static String scurrAmount="currAmount";


    private TextView lbl_balance;
    private Button btn_transactions;
    private Button btn_transfer;
    public Money randomMoney;
    public ArrayList<String> recipients;
    private List<DataClass> transactionList;
    public final int RC_TRANSFER=1;
    public Calendar cal;
    public SimpleDateFormat formatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomMoney=Money.initalValue();
        cal = Calendar.getInstance();
        formatTime = new SimpleDateFormat("HH:mm:ss");
        transactionList=new ArrayList<DataClass>();
        lbl_balance=(TextView)findViewById(R.id.lbl_balance);
        btn_transactions=(Button) findViewById(R.id.btn_transactions);
        btn_transfer=(Button) findViewById(R.id.btn_transfer);


        transactionList.add(new DataClass(formatTime.format(cal.getTime()), ownerName, randomMoney,randomMoney));
        lbl_balance.setText(randomMoney.toString());

        recipients=new ArrayList<>();
        recipients.add("Lisi Kodra");
        recipients.add("Aron Begu");
        recipients.add("Roski Lato");
        recipients.add("Meri Koja");
        recipients.add("Lardejahuev Nikto");
        recipients.add("Albert Einstein");

        btn_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lunchIntent=new Intent(MainActivity.this, TransactionActivity.class);
                lunchIntent.putExtra(sdataTransactions, (Serializable) transactionList);
                startActivity(lunchIntent);
            }
        });

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lunchIntent=new Intent(MainActivity.this, TransferAcitivity.class);
                lunchIntent.putExtra(srecipients, recipients);
                lunchIntent.putExtra(scurrAmount, randomMoney);
                startActivityForResult(lunchIntent, RC_TRANSFER);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode==RC_TRANSFER){

                String recipient=data.getStringExtra(TransferAcitivity.srecipient);
                Money amountTransfered= (Money) data.getSerializableExtra(TransferAcitivity.samount);

                randomMoney=Money.sub(randomMoney, amountTransfered);
                lbl_balance.setText(randomMoney.toString());
                Toast.makeText(this, ssuccesful, Toast.LENGTH_LONG).show();

                cal=Calendar.getInstance();
                transactionList.add(new DataClass(formatTime.format(cal.getTime()), recipient, amountTransfered, randomMoney));
            }
        }
        catch (Exception e){

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(srandomInt,randomMoney);
        outState.putSerializable(sdataTransactions,(Serializable) transactionList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        randomMoney= (Money) savedInstanceState.getSerializable(srandomInt);
        lbl_balance.setText(randomMoney.toString());
        transactionList= (List<DataClass>) savedInstanceState.getSerializable(sdataTransactions);
    }

}
