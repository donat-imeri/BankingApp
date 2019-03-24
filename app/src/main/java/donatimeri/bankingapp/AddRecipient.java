package donatimeri.bankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AddRecipient extends AppCompatActivity {

    public Button addRecipient;
    public TextView recipientName;
    ArrayList<String> recipientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipient);

        addRecipient =(Button)findViewById(R.id.btn_add_recipient);
        recipientName=(TextView)findViewById(R.id.txt_recipient_name);

        Intent intent=getIntent();
        recipientList=intent.getStringArrayListExtra("recipients");


        addRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipientList.add(recipientName.getText().toString());
                Intent returnIntent=new Intent();
                returnIntent.putExtra("recipient", recipientList);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
