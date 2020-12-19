package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;

public class ClaimImageListView extends ArrayAdapter {
    private String[] damageTypes;
    private String[] damageCategories;
    private double[] costs;
    private String[] filePaths;
    private Activity context;
    private Bitmap bitmap;

    public ClaimImageListView(Activity context, String[] damageTypes, String[] damageCategories, String[] filePaths, double[] costs) {
        super(context, R.layout.claim_image_list_item, damageTypes);
        this.context = context;
        this.damageTypes = damageTypes;
        this.damageCategories = damageCategories;
        this.filePaths = filePaths;
        this.costs=costs;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        if(convertView==null)
            row = inflater.inflate(R.layout.claim_image_list_item, null, true);
        if(damageTypes[position] !=null) {
        TextView txtDamageType = (TextView) row.findViewById(R.id.rp_damage_type);
        TextView txtDamageCat = (TextView) row.findViewById(R.id.rp_damage_Category);
        TextView txtCost = (TextView) row.findViewById(R.id.rp_cost_estimate);

            txtDamageType.setText(context.getString(R.string.damage_type_text) + damageTypes[position]);
            txtDamageCat.setText(context.getString(R.string.damage_cat_text) + damageCategories[position]);
            txtCost.setText(context.getString(R.string.estimate_cost_text) + Double.toString(costs[position]));
        }


        if(filePaths[position] != null ) {
            File imgFile = new File(filePaths[position]);
            ImageView imageFlag = (ImageView) row.findViewById(R.id.claim_image);

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                imageFlag.setImageBitmap(myBitmap);

            }
        }

        return  row;
    }


}
