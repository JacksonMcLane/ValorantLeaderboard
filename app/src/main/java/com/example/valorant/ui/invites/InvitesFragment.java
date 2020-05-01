package com.example.valorant.ui.invites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.valorant.R;

public class InvitesFragment extends Fragment {

    private InvitesViewModel invitesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invitesViewModel =
                ViewModelProviders.of(this).get(InvitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_invites, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        invitesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}