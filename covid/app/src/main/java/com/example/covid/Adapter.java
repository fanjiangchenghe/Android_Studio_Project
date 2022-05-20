package com.example.covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    int n=1;
    Context context;
    List<Area> provincelist;

    public Adapter(Context context, List<Area> provincelist) {
        this.context = context;
        this.provincelist = provincelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_layout_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Area area=provincelist.get(position);
        if(n==1){
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(area.getTotal().getConfirm())));
        }
        else if(n==2){
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(area.getTotal().getHeal())));
        }
        else if(n==3){
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(area.getTotal().getDead())));
        }
        else{
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(area.getTotal().getSuspect())));

        }

        holder.country.setText(area.getName());

    }

    @Override
    public int getItemCount() {
        return provincelist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cases,country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cases=itemView.findViewById(R.id.countrycase);
            country=itemView.findViewById(R.id.countryname);
        }
    }


    public void filter(String charText) {
        if(charText.equals("cases")){
            n=1;
        }
        else if(charText.equals("recovered")){
            n=2;
        }
        else if(charText.equals("deaths")){
            n=3;
        }
        else{
            n=4;
        }

        notifyDataSetChanged();
    }



}
