package com.example.valorant.ui.teams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.valorant.R;
import com.example.valorant.Users;

import java.util.List;

public class TeamsFragment extends Fragment {

    ListView listViewTeams;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_teams, container, false);
        wireWidgets(rootView);
        return rootView;
    }

    private void wireWidgets(View rootView){
        listViewTeams = rootView.findViewById(R.id.listView_teams);
    }

    public class TeamsAdapter extends ArrayAdapter {

        private List<Users> teamList;
        private int position;

        public TeamsAdapter(List<Users> teamList) {
            super(getActivity().this, -1, teamList);
            this.teamList = teamList;
        }

        public List<Users> getFriendsList() {
            return teamList;
        }

        public void setFriendsList(List<Users> friendsList) {
            this.teamList = friendsList;
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