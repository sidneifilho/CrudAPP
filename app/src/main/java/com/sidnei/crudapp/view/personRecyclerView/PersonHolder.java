package com.sidnei.crudapp.view.personRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sidnei.crudapp.R;

public class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvName;
    public TextView tvCPF;
    public TextView tvID;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public PersonHolder(View itemView){
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
