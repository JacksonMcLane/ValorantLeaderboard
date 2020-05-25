package com.example.valorant.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.property.UserProperty;
import com.example.valorant.R;
import com.example.valorant.Users;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileFragment extends Fragment {

    private ImageView imageViewProfilePhoto;
    private TextView textViewUsername;
    private TextView textViewAimHeader;
    private RatingBar ratingBarAim;
    private TextView textViewGamesenseHeader;
    private RatingBar ratingBarGamesense;
    private TextView textViewCommunicationHeader;
    private RatingBar ratingBarCommunication;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        wireWidgets(rootView);
        setValues();
        return rootView;
    }

    private void setValues() {
        BackendlessUser user = Backendless.UserService.CurrentUser();

        textViewUsername.setText(user.getProperty("username").toString());
        if(user.getProperty("profilePicture") != null){
            Picasso.get().load(String.valueOf(user.getProperty("profilePicture"))).into(imageViewProfilePhoto);
        }
        textViewAimHeader.setText(R.string.aim_rating);
        textViewGamesenseHeader.setText(R.string.gamesense_rating);
        textViewCommunicationHeader.setText(R.string.communication_rating);
        if(user.getProperty("aimRating") != null) {
            ratingBarAim.setProgress((int) user.getProperty("aimRating"));
        }
        else{
            ratingBarAim.setProgress(0);
        }

        if(user.getProperty("gamesenseRating") != null){
            ratingBarGamesense.setProgress((int) user.getProperty("gamesenseRating"));
        }
        else{
            ratingBarGamesense.setProgress(0);
        }

        if(user.getProperty("communicationRating") != null){
            ratingBarCommunication.setProgress((int) user.getProperty("communicationRating"));
        }
        else{
            ratingBarCommunication.setProgress(0);
        }
    }

    public String getCurrentUser(){
        return Backendless.UserService.CurrentUser().getUserId();
    }

    private void wireWidgets(View rootView) {
        imageViewProfilePhoto = rootView.findViewById(R.id.imageView_profile_picture);
        textViewUsername = rootView.findViewById(R.id.textView_profile_username);
        textViewAimHeader = rootView.findViewById(R.id.textView_profile_aim_header);
        ratingBarAim = rootView.findViewById(R.id.ratingBar_profile_aim);
        textViewGamesenseHeader = rootView.findViewById(R.id.textView_profile_gamesense_header);
        ratingBarGamesense = rootView.findViewById(R.id.ratingBar_profile_gamesense);
        textViewCommunicationHeader = rootView.findViewById(R.id.textView_profile_communication_header);
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