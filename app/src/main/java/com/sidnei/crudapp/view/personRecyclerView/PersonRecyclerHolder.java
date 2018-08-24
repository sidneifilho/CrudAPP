package com.sidnei.crudapp.view.personRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sidnei.crudapp.R;

public class PersonRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvName;
    public TextView tvCPF;
    public TextView tvID;

    private ItemClickListener itemClickListener;

    /**
     * Function listener to when tue user click the recyclerview, we will generate a listener to select the holder in the adapter
     * */
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public PersonRecyclerHolder(View itemView){
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvCPF = itemView.findViewById(R.id.tvCPF);
        tvID = itemView.findViewById(R.id.tvID);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view, getAdapterPosition());
    }

}
