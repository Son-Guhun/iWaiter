package com.example.songuhun.adapters;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.songuhun.myapplication.R;
import com.example.songuhun.objects.Order;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private List<Order> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public OrderListAdapter(List<Order> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                          int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.mTextView.setText(generateText(mDataset.get(position)));

        final OrderListAdapter reyclerViewAdapter = this;
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText0 = new EditText(view.getContext());
                editText0.setTag("details");
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                        .setTitle("Pedido: "+((TextView) view).getText())
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setMessage("Observações:")
                        .setView(editText0)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setNegativeButton("Remover", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDataset.remove(holder.getAdapterPosition());
                                reyclerViewAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNeutralButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private String generateText(Order order) {
        String s;

        if (order.getAdditionalDetails() != null && !order.getAdditionalDetails().trim().equals(""))
            s = order.getBaseDish().getName() +  " (" + order.getAdditionalDetails() + ")";
        else
            s = order.getBaseDish().getName();
        return s;
    }

}
