package com.moutamid.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.expensemanager.databinding.ActivityMainBinding;

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

        balance = Double.parseDouble(Stash.getString(Constants.TOTAL, "0"));
        income = Double.parseDouble(Stash.getString(Constants.INCOME, "0"));
        spent = Double.parseDouble(Stash.getString(Constants.SPENT, "0"));

        list = new ArrayList<>();
        list = Stash.getArrayList(Constants.HISTORY, Model.class);
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