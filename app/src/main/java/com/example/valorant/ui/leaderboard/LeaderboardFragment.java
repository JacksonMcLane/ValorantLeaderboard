package com.example.valorant.ui.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.valorant.R;
import com.example.valorant.User;

import java.util.List;

public class LeaderboardFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        return rootView;
    }



    public class FriendAdapter extends ArrayAdapter {

        private List<User> leaderboardList;
        private int position;

        public FriendAdapter(List<User> leaderboardList) {
            super(getActivity().this, -1, leaderboardList);
            this.leaderboardList = leaderboardList;
        }

        public List<User> getFriendsList() {
            return leaderboardList;
        }

        public void setFriendsList(List<User> friendsList) {
            this.leaderboardList = friendsList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_leaderboard, parent, false);
            }



            return convertView;
        }
    }
}