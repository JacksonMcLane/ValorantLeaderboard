package com.example.valorant.ui.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private ListView listViewLeaderboard;
    private TextView textViewRanking;
    private TextView textViewUsername;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        wireWidgets(rootView);
        return rootView;
    }

    private void wireWidgets(View rootView) {
        listViewLeaderboard = rootView.findViewById(R.id.listView_leaderboardFragment_leaderboard);
    }


    public class LeaderboardAdapter extends ArrayAdapter {

        private List<User> leaderboardList;
        private int position;

        public LeaderboardAdapter(List<User> leaderboardList) {
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

            textViewRanking = convertView.findViewById(R.id.textView_leaderboardItem_ranking);
            textViewUsername = convertView.findViewById(R.id.textView_leaderboardItem_username);

            return convertView;
        }
    }
}