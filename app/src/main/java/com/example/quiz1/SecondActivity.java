package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String tipeMember = intent.getStringExtra("tipe_member");
        String kodeBarang = intent.getStringExtra("kode_barang");

        int jumlahBarang = intent.getIntExtra("jumlah_barang", 1);


        // Mendapatkan informasi harga barang berdasarkan kode barang
        String namaBarang = "Barang tidak ditemukan";
        float hargaBarang = 0;
        switch (kodeBarang) {
            case "SGS":
                namaBarang = "Samsung Galaxy S";
                hargaBarang = 12999999;
                break;
            case "IPX":
                namaBarang = "Iphone X";
                hargaBarang = 5725300;
                break;
            case "PCO":
                namaBarang = "POCO M3";
                hargaBarang = 2730551;
                break;
            case "O17":
                namaBarang = "Oppo A17";
                hargaBarang = 2500990;
                break;
            case "OAS":
                namaBarang = "Oppo A5s";
                hargaBarang = 1989123;
                break;
            case "AAE":
                namaBarang = "Acer Aspire E14";
                hargaBarang = 8676981;
                break;
            case "AV4":
                namaBarang = "Asus Vivobook 14";
                hargaBarang = 9150999;
                break;
            case "LV3":
                namaBarang = "Lenovo V14 Gen 3";
                hargaBarang = 6666666;
                break;
            case "AA5":
                namaBarang = "Acer Aspire 5";
                hargaBarang = 9999999;
                break;
            case "MP3":
                namaBarang = "Macbook Pro M3";
                hargaBarang = 28999999;
                break;
        }

        float totalHarga = (float) (hargaBarang * jumlahBarang);

// Menghitung diskon tambahan jika total harga melebihi Rp 10.000.000
        float diskonTambahan = 0;
        if (totalHarga > 10000000) {
            diskonTambahan = 100000; // Diskon tambahan sebesar Rp 100.000
        }

// Menghitung jumlah bayar sebelum diskon tambahan
        float jumlahBayarSebelumDiskonTambahan = totalHarga;

// Menghitung diskon berdasarkan tipe member
        float diskonHarga = 0;
        switch (tipeMember) {
            case "Gold":
                diskonHarga = (float) (0.1 * totalHarga); // Diskon Gold 10% dari total harga
                break;
            case "Silver":
                diskonHarga = (float) (0.05 * totalHarga); // Diskon Silver 5% dari total harga
                break;
            case "Biasa":
                diskonHarga = (float) (0.02 * totalHarga); // Diskon Biasa 2% dari total harga
                break;
        }

// Menghitung jumlah bayar setelah diskon
        float jumlahBayar = (float) (totalHarga - diskonHarga);

// Menghitung jumlah bayar setelah diskon tambahan
        jumlahBayar -= diskonTambahan;

// Menampilkan informasi diskon tambahan jika diterapkan
        TextView additionalDiscountTextView = findViewById(R.id.additionalDiscountTextView);
        if (diskonTambahan > 0) {
            additionalDiscountTextView.setText("Diskon Tambahan: Rp " + String.format("%.0f", diskonTambahan));
        } else {
            additionalDiscountTextView.setText("Tidak ada diskon tambahan diterapkan.");
        }

        // Menampilkan selamat datang, tipe member, dan informasi transaksi
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Selamat datang, " + nama + "!\nTipe Member: " + tipeMember);

        TextView memberTypeTextView = findViewById(R.id.memberTypeTextView);
        memberTypeTextView.setText("Tipe Member: " + tipeMember);

        TextView itemCodeTextView = findViewById(R.id.itemCodeTextView);
        itemCodeTextView.setText("Kode Barang: " + kodeBarang);

        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        itemNameTextView.setText("Nama Barang: " + namaBarang);

        TextView itemPriceTextView = findViewById(R.id.itemPriceTextView);
        itemPriceTextView.setText("Harga Barang: Rp " + String.format("%.0f", hargaBarang)); // Menggunakan format untuk menghilangkan desimal

        TextView discountPriceTextView = findViewById(R.id.discountPriceTextView);
        discountPriceTextView.setText("Diskon Harga: Rp " + String.format("%.0f", diskonHarga)); // Menggunakan format untuk menghilangkan desimal

        TextView totalAmountTextView = findViewById(R.id.totalAmountTextView);
        totalAmountTextView.setText("Jumlah Bayar: Rp " + String.format("%.0f", jumlahBayar)); // Menggunakan format untuk menghilangkan desimal

        Button shareButton = findViewById(R.id.shareButton);
        float finalJumlahBayar = jumlahBayar;
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat pesan yang akan dibagikan
                String message = "Total Pembayaran: Rp " + String.format("%.0f", finalJumlahBayar);

                // Membuat intent untuk berbagi teks
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);

                // Memulai aktivitas untuk berbagi
                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
            }
        });
    }
}