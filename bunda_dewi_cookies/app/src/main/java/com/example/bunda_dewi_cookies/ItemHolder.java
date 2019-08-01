package com.example.bunda_dewi_cookies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class ItemHolder extends RecyclerView.ViewHolder {

    public ImageView imgView;
    public ItemHolder(View view) {
        super(view);

        imgView = (ImageView) view.findViewById(R.id.imgView);
    }
}
