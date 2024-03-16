package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextItemCode, editTextItemCount;
    private RadioGroup radioGroupMembership;
    private RadioButton radioButtonGold, radioButtonSilver, radioButtonRegular;
    private Button buttonProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.nameEditText);
        editTextItemCode = findViewById(R.id.itemCodeEditText);
        editTextItemCount = findViewById(R.id.quantityEditText);
        radioGroupMembership = findViewById(R.id.memberRadioGroup);
        radioButtonGold = findViewById(R.id.goldRadioButton);
        radioButtonSilver = findViewById(R.id.silverRadioButton);
        radioButtonRegular = findViewById(R.id.regularRadioButton);
        buttonProcess = findViewById(R.id.processButton);

        buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editTextName.getText().toString();
                String kodeBarang = editTextItemCode.getText().toString();
                int jumlahBarang = 0;
                try {
                    jumlahBarang = Integer.parseInt(editTextItemCount.getText().toString());
                } catch (NumberFormatException e) {
                    editTextItemCount.setError("Jumlah barang harus angka");
                    return;
                }
                String tipeMember = "";
                int selectedRadioButtonId = radioGroupMembership.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.goldRadioButton) {
                    tipeMember = "Gold";
                } else if (selectedRadioButtonId == R.id.silverRadioButton) {
                    tipeMember = "Silver";
                } else if (selectedRadioButtonId == R.id.regularRadioButton) {
                    tipeMember = "Biasa";
                }

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("kode_barang", kodeBarang);
                intent.putExtra("tipe_member", tipeMember);
                intent.putExtra("jumlah_barang",jumlahBarang);
                startActivity(intent);
            }
        });
    }
}
