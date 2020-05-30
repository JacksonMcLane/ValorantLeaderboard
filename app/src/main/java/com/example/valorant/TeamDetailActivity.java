package com.example.valorant;

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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.valorant.ui.teams.TeamsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamDetailActivity extends AppCompatActivity {

    private TextView textViewTeamName;
    private ListView listViewTeamMembers;
    private TextView textViewTeamMemberName;
    private ImageView imageViewMemberProfilePic;
    private Team foundTeam;
    private MemberAdapter memberAdapter;
    public static final String EXTRA_MEMBER = "Member";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_detail_activity);
        wireWidgets();
        Intent foundIntent = getIntent();
        foundTeam = foundIntent.getParcelableExtra(TeamsFragment.EXTRA_TEAM);
        loadDataFromBackendless();
    }



    private void wireWidgets() {
        textViewTeamName = findViewById(R.id.textView_teamDetail_teamName);
        listViewTeamMembers = findViewById(R.id.listView_teamDetail_teamMembers);
    }

    private void loadDataFromBackendless() {
        String teamId = foundTeam.getObjectId();
        Log.d("TeamDetailActivity", "loadDataFromBackendless: " + foundTeam.getObjectId());
        String whereClause = "objectId = " + "'" + teamId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setRelationsDepth(1);
        Backendless.Data.of(Team.class).find(queryBuilder, new AsyncCallback<List<Team>>() {
            @Override
            public void handleResponse(final List<Team> response) {
                if(response != null) {
                    memberAdapter = new MemberAdapter(response.get(0).getMembers());
                    listViewTeamMembers.setAdapter(memberAdapter);
                    textViewTeamName.setText(response.get(0).getTeamName());
                    listViewTeamMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent memberIntent = new Intent(TeamDetailActivity.this, FriendDetailActivity.class);
                            memberIntent.putExtra(TeamDetailActivity.EXTRA_MEMBER, response.get(0).getMembers().get(i));
                            startActivity(memberIntent);
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("TEAMDETAILACTIVITY", "handleFault: " + fault.getMessage());
            }
        });

    }

    public class MemberAdapter extends ArrayAdapter {

        private List<Users> memberList;
        private int position;

        public MemberAdapter(List<Users> memberList) {
            super(TeamDetailActivity.this, -1, memberList);
            this.memberList = memberList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_team_detail, parent, false);
            }

            textViewTeamMemberName = convertView.findViewById(R.id.textView_itemTeamDetail_username);
            imageViewMemberProfilePic = convertView.findViewById(R.id.imageView_itemTeamDetail_picture);
            Users member = memberList.get(position);
            if(member.getProfilePicture() != null && member.getProfilePicture().length() > 0) {
                Picasso.get().load(memberList.get(position).getProfilePicture()).resize(50, 50)
                        .centerCrop().into(imageViewMemberProfilePic);
            }
            textViewTeamMemberName.setText(memberList.get(position).getUsername());

            return convertView;
        }
    }
}
