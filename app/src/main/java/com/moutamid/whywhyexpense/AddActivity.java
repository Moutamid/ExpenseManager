package com.moutamid.whywhyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.whywhyexpense.databinding.ActivityAddBinding;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    ArrayList<Model> list;
    double income, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        list = new ArrayList<>();
        list = Stash.getArrayList(Constants.HISTORY, Model.class);

        income = Double.parseDouble(Stash.getString(Constants.INCOME, "0"));
        balance = Double.parseDouble(Stash.getString(Constants.TOTAL, "0"));
        Log.d("INCOME", "income " +  income);

        binding.addIncome.setOnClickListener(v -> {
            if (binding.amount.getEditText().getText().toString().isEmpty()){
                Toast.makeText(this, "Add income", Toast.LENGTH_SHORT).show();
            } else {
                Double amount = Double.parseDouble(binding.amount.getEditText().getText().toString());

                list.add(new Model(binding.desc.getEditText().getText().toString(), amount, false));
                balance = balance + amount;
                amount = amount + income;

                Stash.put(Constants.HISTORY, list);
                Stash.put(Constants.INCOME, ""+amount);
                Stash.put(Constants.TOTAL, ""+balance);

                Toast.makeText(this, "Income Added", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}