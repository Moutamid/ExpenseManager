package com.moutamid.whywhyexpense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.whywhyexpense.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    double balance, income, spent;

    ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);

        balance = Double.parseDouble(Stash.getString(Constants.TOTAL, "200"));
        income = Double.parseDouble(Stash.getString(Constants.INCOME, "250"));
        spent = Double.parseDouble(Stash.getString(Constants.SPENT, "50"));

        list = new ArrayList<>();
        list = Stash.getArrayList(Constants.HISTORY, Model.class);
        if (list.isEmpty()) {
            list.add(new Model("Pay", 250.0, false));
            list.add(new Model("Bill", 50.0, true));
            Stash.put(Constants.HISTORY, list);
        }
        Collections.reverse(list);
        binding.recyler.setLayoutManager(new LinearLayoutManager(this));
        binding.recyler.setHasFixedSize(false);

        HistorySpent adapter = new HistorySpent(this, list);
        binding.recyler.setAdapter(adapter);

        binding.balance.setText("$" + String.format("%,.2f", balance));
        binding.income.setText("$" + String.format("%,.2f", income));
        binding.spent.setText("$" + String.format("%,.2f", spent));

        binding.addSpent.setOnClickListener(v -> {
            startActivity(new Intent(this, SubActivity.class));
            finish();
        });
        binding.addIncome.setOnClickListener(v -> {
            startActivity(new Intent(this, AddActivity.class));
            finish();
        });

    }
}