package com.example.myapplication.findgarages;

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

import com.example.myapplication.FindGaragesActivity;
import com.example.myapplication.MapsActivity;
import com.example.myapplication.R;

import java.util.Map;

public class FindgaragesFragment extends Fragment {

    private FindgaragesViewModel findgaragesViewModel;
    Context context;
//    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        findgaragesViewModel =
                new ViewModelProvider(this).get(FindgaragesViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_findgarages, container, false);
        context =root.getContext();
        //final TextView textView = root.findViewById(R.id.button12);
        final Button button = root.findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });


        findgaragesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                startActivity(new Intent(getActivity(), CostEstimationActivity1.class));
//                textView.setText(s);
            }

        });
        return root;
    }




}