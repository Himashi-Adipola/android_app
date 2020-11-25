package com.example.myapplication.findshops;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.FindSparePartsActivity;
import com.example.myapplication.R;


public class FindshopsFragment extends Fragment {

    private FindshopsViewModel findshopsViewModel;
    Context context;
//    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        findshopsViewModel =
                new ViewModelProvider(this).get(FindshopsViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_findshops, container, false);
        context =root.getContext();
        //final TextView textView = root.findViewById(R.id.button12);
        final Button button = root.findViewById(R.id.button15);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FindSparePartsActivity.class);
                startActivity(intent);
            }
        });


        findshopsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                startActivity(new Intent(getActivity(), CostEstimationActivity1.class));
//                textView.setText(s);
            }

        });
        return root;
    }




}