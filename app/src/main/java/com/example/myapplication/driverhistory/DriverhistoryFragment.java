package com.example.myapplication.driverhistory;

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

import com.example.myapplication.DriverHistoryVerifyActivity;
import com.example.myapplication.R;


public class DriverhistoryFragment extends Fragment {

    private DriverhistoryViewModel driverhistoryViewModel;
    Context context;
//    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        driverhistoryViewModel =
                new ViewModelProvider(this).get(DriverhistoryViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_driverhistory, container, false);
        context =root.getContext();
        //final TextView textView = root.findViewById(R.id.button10);
        final Button button = root.findViewById(R.id.button17);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DriverHistoryVerifyActivity.class);
                startActivity(intent);
            }
        });


        driverhistoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                startActivity(new Intent(getActivity(), CostEstimationActivity1.class));
//                textView.setText(s);
            }

        });
        return root;
    }



}