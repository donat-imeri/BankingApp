package donatimeri.bankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {
    final static String transactionTitle="Transactions History";


    private ArrayList<String> dateArray=new ArrayList<>();
    private ArrayList<String> recipientArray=new ArrayList<>();
    private ArrayList<String> amountTransferedArray=new ArrayList<>();
    private ArrayList<String> actualValueArray=new ArrayList<>();
    private List<DataClass> dataTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        setTitle(transactionTitle);

        Intent intent=getIntent();
        dataTransactions= (List<DataClass>) intent.getSerializableExtra(MainActivity.sdataTransactions);

        for (DataClass d: dataTransactions) {
            dateArray.add(d.dateArray);
            recipientArray.add(d.recipientArray);
            amountTransferedArray.add(d.amountTransferedArray.toString());
            actualValueArray.add(d.actualValueArray.toString());
        }

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView=findViewById(R.id.lst_transactions);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this, dateArray, recipientArray, amountTransferedArray, actualValueArray);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
