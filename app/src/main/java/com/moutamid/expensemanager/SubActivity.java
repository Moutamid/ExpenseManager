package com.moutamid.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.expensemanager.databinding.ActivitySubBinding;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    ActivitySubBinding binding;
    ArrayList<Model> list;
    double spent, balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        list = new ArrayList<>();
        list = Stash.getArrayList(Constants.HISTORY, Model.class);

        spent = Double.parseDouble(Stash.getString(Constants.SPENT, "0"));
        balance = Double.parseDouble(Stash.getString(Constants.TOTAL, "0"));
        Log.d("INCOME", "income " +  spent);

        binding.addSpent.setOnClickListener(v -> {
            if (binding.amount.getEditText().getText().toString().isEmpty()){
                Toast.makeText(this, "Add Amount", Toast.LENGTH_SHORT).show();
            } else {
                Double amount = Double.parseDouble(binding.amount.getEditText().getText().toString());

                list.add(new Model(binding.desc.getEditText().getText().toString(), amount, true));
                balance = balance - amount;
                amount = amount + spent;

                Stash.put(Constants.HISTORY, list);
                Stash.put(Constants.SPENT, ""+amount);
                Stash.put(Constants.TOTAL, ""+balance);

                Toast.makeText(this, "Spent Added", Toast.LENGTH_SHORT).show();
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