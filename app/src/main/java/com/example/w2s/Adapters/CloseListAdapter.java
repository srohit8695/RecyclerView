package com.example.w2s.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w2s.Interfaces.RecyclerClickFunctions;
import com.example.w2s.Models.Info;
import com.example.w2s.databinding.CloseLayoutBinding;

import java.util.List;

public class CloseListAdapter extends RecyclerView.Adapter<CloseListAdapter.MyViewHolder> {

    private List<Info> data;
    private Context context;
    private RecyclerClickFunctions recyclerClickFunctions;

    public CloseListAdapter(List<Info> data, Context context, RecyclerClickFunctions recyclerClickFunctions) {
        this.data = data;
        this.context = context;
        this.recyclerClickFunctions = recyclerClickFunctions;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CloseLayoutBinding closeLayoutBinding;

        public MyViewHolder(CloseLayoutBinding closeLayoutBinding) {
            super(closeLayoutBinding.getRoot());
            this.closeLayoutBinding=closeLayoutBinding;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        CloseLayoutBinding closeLayoutBinding=CloseLayoutBinding.inflate(inflater,parent,false);
        return new MyViewHolder(closeLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Info info=data.get(position);
        holder.closeLayoutBinding.txtdata.setText(info.getTitle());
        holder.closeLayoutBinding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerClickFunctions.onCloseClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }






}
