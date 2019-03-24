package donatimeri.bankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddRecipient extends AppCompatActivity {

    public Button addRecipient;
    public TextView recipientName;
    public EditText recipientNameText;
    ArrayList<String> recipientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipient);

        addRecipient =(Button)findViewById(R.id.btn_add_recipient);
        recipientName=(TextView)findViewById(R.id.txt_recipient_name);
        recipientNameText=(EditText)findViewById(R.id.txt_recipient_name);

        String pattern="^[A-Za-z\\s]+$";
        Intent intent=getIntent();
        recipientList=intent.getStringArrayListExtra(MainActivity.srecipients);


        addRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Pattern.matches(pattern,recipientNameText.getText())) {
                    recipientList.add(recipientName.getText().toString());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(TransferAcitivity.srecipient, recipientList);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
                else
                {
                    Toast.makeText(AddRecipient.this,"Invalid name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
