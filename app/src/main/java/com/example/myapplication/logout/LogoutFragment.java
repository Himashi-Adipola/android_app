package com.example.myapplication.logout;

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

import com.example.myapplication.FAQActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.faq.FaqViewModel;

public class LogoutFragment extends Fragment {

    private LogoutViewModel logoutViewModel;
    Context context;
//    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel =
                new ViewModelProvider(this).get(LogoutViewModel.class);
//
       final View root = inflater.inflate(R.layout.activity_main, container, false);
//        context =root.getContext();
//        inflater.getContext()

        startActivity(new Intent(inflater.getContext(),MainActivity.class));
        //final TextView textView = root.findViewById(R.id.button10);
//        final Button button = root.findViewById(R.id.button17);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MainActivity.class);
//                startActivity(intent);
//            }
//        });


        logoutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               //startActivity(new Intent(getActivity(), MainActivity.class));

            }

        });
        return root;
    }



}
