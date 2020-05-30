package com.example.valorant.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.valorant.R;
import com.example.valorant.Users;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private ImageView imageViewProfilePhoto;
    private TextView textViewUsername;
    private TextView textViewAimHeader;
    private RatingBar ratingBarAim;
    private TextView textViewGamesenseHeader;
    private RatingBar ratingBarGamesense;
    private TextView textViewCommunicationHeader;
    private RatingBar ratingBarCommunication;
    private EditText editTextEditUsername;
    private EditText editTextProfilePictureUrl;
    private Button buttonSaveProfile;
    private BackendlessUser user = Backendless.UserService.CurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        wireWidgets(rootView);
        setValues();
        setHasOptionsMenu(true);
        setOnClickListener();
        return rootView;
    }

    private void setOnClickListener() {
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextEditUsername.setVisibility(View.INVISIBLE);
                editTextProfilePictureUrl.setVisibility(View.INVISIBLE);
                buttonSaveProfile.setVisibility(View.INVISIBLE);
                textViewUsername.setVisibility(View.VISIBLE);
                if(editTextEditUsername.getText() != null) {
                    user.setProperty("username", String.valueOf(editTextEditUsername.getText()));
                }
                if(editTextProfilePictureUrl.getText() != null) {
                    user.setProperty("profilePicture", String.valueOf(editTextProfilePictureUrl.getText()));
                }
                user.setProperty("objectId", Backendless.UserService.CurrentUser().getObjectId());
                Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(getActivity(), "Successfully Updated Profile", Toast.LENGTH_SHORT).show();
                        textViewUsername.setText(String.valueOf(editTextEditUsername.getText()));
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.d("UPDATE PROFILE", "handleFault: " + fault.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.options_menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item__profile_fragment_edit_profile:
                editTextEditUsername.setVisibility(View.VISIBLE);
                editTextProfilePictureUrl.setVisibility(View.VISIBLE);
                buttonSaveProfile.setVisibility(View.VISIBLE);
                textViewUsername.setVisibility(View.INVISIBLE);
                if(user.getProperty("profilePicture") != null && user.getProperty("profilePicture").toString().length() > 0){
                    Picasso.get()
                            .load(user.getProperty("profilePicture").toString())
                            .resize(250, 250)
                            .centerCrop()
                            .into(imageViewProfilePhoto);
                    Picasso.get().load(user.getProperty("profilePicture").toString()).into(imageViewProfilePhoto);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setValues() {

        textViewUsername.setText(user.getProperty("username").toString());
        if(user.getProperty("profilePicture") != null &&((String)user.getProperty("profilePicture")).length() > 0){
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
        editTextEditUsername = rootView.findViewById(R.id.editText_profile_edit_username);
        editTextProfilePictureUrl = rootView.findViewById(R.id.editText_profile_edit_photo_url);
        buttonSaveProfile = rootView.findViewById(R.id.button_profile_save_profile);

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