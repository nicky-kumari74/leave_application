package com.example.leave_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.viewholder> {
    Context context;
ArrayList<String> list1;
    ArrayList<String> list2;
    ArrayList<String> list3;
    ArrayList<String> list4;

    public myadapter(Context context, ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3, ArrayList<String> list4) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        viewholder vh=new viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.type.setText(list1.get(position));
        holder.from.setText(list2.get(position));
        holder.to.setText(list3.get(position));
        holder.status.setText(list4.get(position));
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView type,from,to,status;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            from=itemView.findViewById(R.id.from);
            to=itemView.findViewById(R.id.to);
            status=itemView.findViewById(R.id.status);
        }
    }
}
