package donatimeri.bankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class TransferAcitivity extends AppCompatActivity {

    private Money currAmount;
    public ArrayList<String> recipientList;
    private Button btnPay;
    private Spinner spnRecipient;
    private TextView txtAmount;
    private TextView lblCheck;
    private Button addRecipient;
    final private int RC_ADD_RECIPIENT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        setTitle("Transfer your money");

        btnPay=(Button)findViewById(R.id.btn_pay);
        spnRecipient=(Spinner) findViewById(R.id.lst_recipient);
        txtAmount=(TextView) findViewById(R.id.txt_amount);
        lblCheck=(TextView)findViewById(R.id.lbl_amount_check);
        addRecipient=(Button) findViewById(R.id.add_recipient);


        Intent intent = getIntent();
        recipientList = intent.getStringArrayListExtra("recipients");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, recipientList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRecipient.setAdapter(adapter);

        currAmount= (Money) intent.getSerializableExtra("currAmount");


        txtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEnable();
            }
        });


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getValue = String.valueOf(txtAmount.getText());
                Money amount=Money.getMoneyParts(getValue);
                String recipient=spnRecipient.getSelectedItem().toString();

                Intent returnIntent=new Intent();
                returnIntent.putExtra("recipient", recipient);
                returnIntent.putExtra("amount",amount);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        addRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lunchIntent=new Intent(TransferAcitivity.this, AddRecipient.class);
                lunchIntent.putExtra("recipients", recipientList);
                startActivityForResult(lunchIntent, RC_ADD_RECIPIENT);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode==RC_ADD_RECIPIENT){
                recipientList=data.getStringArrayListExtra("recipient");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_spinner_item, recipientList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnRecipient.setAdapter(adapter);
            }
        }
        catch (Exception e){

        }
    }

    private void checkEnable(){
        Money amount=null;
        boolean valid=false;
        String getValue = String.valueOf(txtAmount.getText());
        amount=Money.getMoneyParts(getValue);

        if (getValue.matches("^\\d+\\.?\\d{0,2}$"))
            valid=true;

        if (valid && spnRecipient.getSelectedItem()!=null && Money.canSubstract(currAmount, amount)){
            btnPay.setEnabled(true);
            btnPay.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lblCheck.setText("");
        }
        else{
            btnPay.setEnabled(false);
            lblCheck.setText("Amount is outside of bounds");
            btnPay.setBackgroundColor(getResources().getColor(R.color.darkGrey));

        }
    }

}
