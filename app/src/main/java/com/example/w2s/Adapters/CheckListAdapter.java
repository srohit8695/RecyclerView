package com.example.w2s.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w2s.Interfaces.RecyclerClickFunctions;
import com.example.w2s.Models.Info;
import com.example.w2s.databinding.CheckListLayoutBinding;

import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.MyViewHolder> {

    private List<Info> data;
    private Context context;
    private RecyclerClickFunctions recyclerClickFunctions;



    public CheckListAdapter(List<Info> data, Context context, RecyclerClickFunctions recyclerClickFunctions) {
        this.data = data;
        this.context = context;
        this.recyclerClickFunctions = recyclerClickFunctions;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CheckListLayoutBinding checkListLayoutBinding;

        public MyViewHolder(CheckListLayoutBinding checkListLayoutBinding) {
            super(checkListLayoutBinding.getRoot());
            this.checkListLayoutBinding=checkListLayoutBinding;

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        CheckListLayoutBinding checkListLayoutBinding=CheckListLayoutBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(checkListLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Info information=data.get(position);
        holder.checkListLayoutBinding.txtdata.setText(information.getTitle());
        holder.checkListLayoutBinding.ckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    recyclerClickFunctions.onCheckboxClick(position);
                    holder.checkListLayoutBinding.ckbox.setChecked(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
