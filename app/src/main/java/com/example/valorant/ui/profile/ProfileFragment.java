package com.example.valorant.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.valorant.R;

public class ProfileFragment extends Fragment {

    private ImageView imageViewProfilePhoto;
    private TextView textViewUsername;
    private TextView textViewAimHeader;
    private RatingBar ratingBarAim;
    private TextView textViewGamesenseHeader;
    private RatingBar ratingBarGamesense;
    private TextView textViewCommunication;
    private RatingBar ratingBarCommunication;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        wireWidgets(rootView);
        setValues();
        return rootView;
    }

    private void setValues() {

    }

    private void wireWidgets(View rootView) {
        imageViewProfilePhoto = rootView.findViewById(R.id.imageView_profile_picture);
        textViewUsername = rootView.findViewById(R.id.textView_profile_username);
        textViewAimHeader = rootView.findViewById(R.id.textView_profile_aim_header);
        ratingBarAim = rootView.findViewById(R.id.ratingBar_profile_aim);
        textViewGamesenseHeader = rootView.findViewById(R.id.textView_profile_gamesense_header);
        ratingBarGamesense = rootView.findViewById(R.id.ratingBar_profile_gamesense);
        textViewCommunication = rootView.findViewById(R.id.textView_profile_communication_header);
        ratingBarCommunication = rootView.findViewById(R.id.ratingBar_profile_communication);

        //prevents mouse/tabbing into the rating bar
        ratingBarAim.setFocusable(false);
        ratingBarGamesense.setFocusable(false);
        ratingBarCommunication.setFocusable(false);
        //prevents user from changing their own ratings
        ratingBarAim.setIsIndicator(true);
        ratingBarGamesense.setIsIndicator(true);
        ratingBarCommunication.setIsIndicator(true);
    }
}