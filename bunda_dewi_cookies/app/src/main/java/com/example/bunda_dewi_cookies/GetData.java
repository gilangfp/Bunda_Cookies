package com.example.bunda_dewi_cookies;

import com.google.gson.annotations.SerializedName;

public class GetData {
    @SerializedName("id") private int Id;
    @SerializedName("nama") private String Nama;
    @SerializedName("harga") private String Harga;

    @SerializedName("gambar") private String gambar;

    public int getId() {
        return Id;
    }

    public String getNama() {
        return Nama;
    }



    public String getHarga() {
        return Harga;
    }

    public String getgambar() {
        return gambar;
    }
}
