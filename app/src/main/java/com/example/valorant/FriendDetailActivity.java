package com.example.valorant;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.valorant.ui.friends.FriendsFragment;

public class FriendDetailActivity extends AppCompatActivity {

    private ImageView imageViewPicture;
    private TextView textViewUsername;
    private RatingBar ratingBarAim;
    private RatingBar ratingBarGamesense;
    private RatingBar ratingBarCommunication;
    private Users user;
    private Button buttonSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friend_detail_activity);
        wireWidgets();
        Intent foundIntent = getIntent();
        user = foundIntent.getParcelableExtra(FriendsFragment.EXTRA_FRIEND);

        ratingBarAim.setProgress(user.getAimRating());
        ratingBarGamesense.setProgress(user.getGamesenseRating());
        ratingBarCommunication.setProgress(user.getCommunicationRating());
        textViewUsername.setText(user.getUsername());
        setButtonListener();
    }

    private void setButtonListener() {

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setAimRating(ratingBarAim.getProgress());
                user.setGamesenseRating(ratingBarGamesense.getProgress());
                user.setCommunicationRating(ratingBarCommunication.getProgress());
                Backendless.Persistence.save(user, new AsyncCallback<Users>() {
                    @Override
                    public void handleResponse(Users response) {
                        Toast.makeText(FriendDetailActivity.this, "Successfully Updated Friend", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void handleFault(BackendlessFault fault) {
                    }
                });
            }
        });

    }

    private void wireWidgets() {

        imageViewPicture = findViewById(R.id.imageView_friendDetail_picture);
        textViewUsername = findViewById(R.id.textView_friendDetail_username);
        ratingBarAim = findViewById(R.id.ratingBar_friendDetail_aim);
        ratingBarGamesense = findViewById(R.id.ratingBar_friendDetail_gamesense);
        ratingBarCommunication = findViewById(R.id.ratingBar_friendDetail_communication);
        buttonSave = findViewById(R.id.button_friendDetail_save);

    }
}
