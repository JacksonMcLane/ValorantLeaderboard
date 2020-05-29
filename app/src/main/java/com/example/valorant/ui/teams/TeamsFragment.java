package com.example.valorant.ui.teams;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.valorant.R;
import com.example.valorant.Team;
import com.example.valorant.TeamDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.util.List;

public class TeamsFragment extends Fragment {

    private ListView listViewTeams;
    private ImageView imageViewProfilePic;
    private TextView textViewTeamName;
    private TextView textViewTeamRank;
    private TeamsAdapter teamAdapter;
    private FloatingActionButton floatingActionButtonNewTeam;
    public static final String EXTRA_TEAM = "Team";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_teams, container, false);
        wireWidgets(rootView);
        loadDataFromBackendless();
        return rootView;
    }

    public void loadDataFromBackendless(){
        String userId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "members = " + "'" + userId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        Backendless.Data.of(Team.class).find(new AsyncCallback<List<Team>>() {
            @Override
            public void handleResponse(final List<Team> foundTeams) {
                teamAdapter = new TeamsAdapter(foundTeams);
                listViewTeams.setAdapter(teamAdapter);
                listViewTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("TeamsDetailActivity", "onItemClick: " + foundTeams.get(i).getObjectId());
                        Intent detailIntent = new Intent(getActivity(), TeamDetailActivity.class);
                        detailIntent.putExtra(EXTRA_TEAM, foundTeams.get(i));
                        startActivity(detailIntent);
                    }
                });

                floatingActionButtonNewTeam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                  }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

    }

    private void wireWidgets(View rootView){
        listViewTeams = rootView.findViewById(R.id.listView_teams);
        floatingActionButtonNewTeam = rootView.findViewById(R.id.floatingActionButton_teams_add_team);
    }

    public class TeamsAdapter extends ArrayAdapter {

        private List<Team> teamList;
        private int position;

        public TeamsAdapter(List<Team> teamList) {
            super(getActivity(), -1, teamList);
            this.teamList = teamList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_teams, parent, false);
            }

            imageViewProfilePic = convertView.findViewById(R.id.imageView_item_teams_picture);
            textViewTeamName = convertView.findViewById(R.id.textView_item_teams_name);
            textViewTeamRank = convertView.findViewById(R.id.textView_item_teams_rating);

            textViewTeamName.setText(teamList.get(position).getTeamName());
            textViewTeamRank.setText(String.valueOf(teamList.get(position).getRating()));
            Picasso.get().load(teamList.get(position).getTeamPicture()).into(imageViewProfilePic);

            return convertView;
        }
    }
}