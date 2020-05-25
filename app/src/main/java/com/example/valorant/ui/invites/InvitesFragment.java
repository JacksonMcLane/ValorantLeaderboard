package com.example.valorant.ui.invites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.valorant.R;
import com.example.valorant.Users;

import java.util.List;

public class InvitesFragment extends Fragment {

    private TextView textViewUsername;
    private Button buttonAccept;
    private Button buttonDecline;
    private ListView listViewInvites;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        wireWidgets(rootView);
        return rootView;
    }

    private void wireWidgets(View rootView) {
        listViewInvites = rootView.findViewById(R.id.listView_teams);
    }


    public class InvitesAdapter extends ArrayAdapter {

        private List<Users> inviteList;
        private int position;

        public InvitesAdapter(List<Users> invitesList) {
            super(getActivity(), -1, invitesList);
            this.inviteList = invitesList;
        }

        public List<Users> getInviteList() {
            return inviteList;
        }

        public void setInviteList(List<Users> inviteList) {
            this.inviteList = inviteList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_invites, parent, false);
            }

            textViewUsername = convertView.findViewById(R.id.textView_invitesItem_name);
            buttonAccept = convertView.findViewById(R.id.button_inviteItem_accept);
            buttonDecline = convertView.findViewById(R.id.button_inviteItem_decline);


            textViewUsername.setText(inviteList.get(position).getUsername());

            return convertView;
        }
    }
}