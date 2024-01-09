package com.moutamid.whywhyexpense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistorySpent extends RecyclerView.Adapter<HistorySpent.HistoryVH> {
    Context context;
    ArrayList<Model> list;

    public HistorySpent(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryVH(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {
        Model model = list.get(holder.getAdapterPosition());
        holder.name.setText(model.getName());
        if (model.isExpense){
            holder.isIncome.setText("Spent");
            holder.price.setText("$" + String.format("%,.2f", model.price));
            holder.icon.setImageResource(R.drawable.round_remove_24);
            holder.price.setTextColor(context.getResources().getColor(R.color.spent));
        } else {
            holder.isIncome.setText("Income");
            holder.price.setText("$" + String.format("%,.2f", model.price));
            holder.icon.setImageResource(R.drawable.add);
            holder.price.setTextColor(context.getResources().getColor(R.color.income));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class HistoryVH extends RecyclerView.ViewHolder{
        TextView isIncome, name, price;
        ImageView icon;
        public HistoryVH(@NonNull View itemView) {
            super(itemView);
            isIncome = itemView.findViewById(R.id.isIncome);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            icon = itemView.findViewById(R.id.icon);
        }
    }

}
