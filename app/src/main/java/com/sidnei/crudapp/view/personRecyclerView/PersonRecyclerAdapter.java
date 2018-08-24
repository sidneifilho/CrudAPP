package com.sidnei.crudapp.view.personRecyclerView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sidnei.crudapp.R;
import com.sidnei.crudapp.model.Person;

import java.util.ArrayList;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonHolder> {

    private ArrayList<Person> listPersons;
    private int selectedRow = -1; ///< default value when there are not rows selected
    private Person selectedPerson = null;

    public PersonRecyclerAdapter(){
        this.listPersons = new ArrayList<>();
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new PersonHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position){
        holder.tvName.setText(listPersons.get(position).getName());
        holder.tvCPF.setText(listPersons.get(position).getCpf());
        holder.tvID.setText(Integer.toString(listPersons.get(position).getId()));

        /**
         * Function to generate a listener when a row is selected, we will update the intern variable
         * */
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                selectedRow = position;
                selectedPerson = listPersons.get(position);
                notifyDataSetChanged();
            }
        });

        /// changing the background of the cell that was selected by the user
        if(selectedRow == position){
            holder.itemView.setBackgroundColor(Color.parseColor("#D3D3D3"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount(){
        return listPersons.size();
    }

    public Person getSelectedPerson(){
        return selectedPerson;
    }

    public int getSelectedRow(){
        return selectedRow;
    }

    public void clearSelection(){
        selectedRow = -1;
        selectedPerson = null;
    }

    public void setListPersons(ArrayList<Person> listPersons){
        this.listPersons = listPersons;
        clearSelection();
        notifyDataSetChanged();
    }

    /***/
    public void addPerson(Person p){
        listPersons.add(p);
        clearSelection();
        notifyDataSetChanged();
    }
    /*
    * **/
    public void setPerson(int position, Person p){
        listPersons.set(position, p);
        clearSelection();
        notifyDataSetChanged();
    }

    public Person getPerson(int position){
        try{
            return listPersons.get(position);
        }catch (Exception ex){
            Log.d("PersonAdapter getPerson", "Error: " + ex.getMessage());
        }
        return null;
    }

    /***/
    public void removePerson(Person p){
        listPersons.remove(p);
        clearSelection();
        notifyDataSetChanged();
    }

    /***/
    public void clearList(){
        listPersons.clear();
        clearSelection();
        notifyDataSetChanged();
    }

}
