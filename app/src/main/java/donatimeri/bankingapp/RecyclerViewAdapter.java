package donatimeri.bankingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG="RecyclerViewAdapter";

    private ArrayList<String> dateArray=new ArrayList<>();
    private ArrayList<String> recipientArray=new ArrayList<>();
    private ArrayList<String> amountTransferedArray=new ArrayList<>();
    private ArrayList<String> acutalValueArray=new ArrayList<>();
    private Context context;
    private View lastSelected;
    private boolean selected;

    public RecyclerViewAdapter(Context context, ArrayList<String> dateArray, ArrayList<String> recipientArray, ArrayList<String> amountTransferedArray, ArrayList<String> acutalValueArray) {
        this.dateArray=dateArray;
        this.recipientArray=recipientArray;
        this.amountTransferedArray=amountTransferedArray;
        this.acutalValueArray=acutalValueArray;
        this.context=context;
        lastSelected=null;
        selected=false;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transactions_layout, parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.dateTransfered.setText(dateArray.get(i));
        viewHolder.recipient.setText(recipientArray.get(i));
        viewHolder.transfered.setText(amountTransferedArray.get(i));
        viewHolder.actual.setText(acutalValueArray.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (lastSelected!=null && selected) {
                        lastSelected.setBackgroundColor(Color.parseColor("#ceffe4"));
                        selected=false;
                    }
                    else {
                        lastSelected = v;
                        v.setBackgroundColor(Color.rgb(216, 231, 255));
                        selected=true;
                    }

                    if (lastSelected!=v){
                        lastSelected = v;
                        v.setBackgroundColor(Color.rgb(216, 231, 255));
                        selected=true;
                    }
            }

        });
        viewHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, recipientArray.get(i)+" - "+amountTransferedArray.get(i),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipientArray.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateTransfered;
        public TextView recipient;
        public TextView transfered;
        public TextView actual;
        public LinearLayout parentLayout;


        public ViewHolder(View v) {
            super(v);
            dateTransfered=v.findViewById(R.id.date_transfered);
            recipient=v.findViewById(R.id.recipient_transfered);
            transfered=v.findViewById(R.id.transfered_amount);
            actual=v.findViewById(R.id.after_transfer_amount);
            parentLayout=v.findViewById(R.id.linear_layout);

        }
    }

}