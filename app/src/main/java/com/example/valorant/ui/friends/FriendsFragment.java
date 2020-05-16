package com.example.valorant.ui.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.valorant.MainActivity;
import com.example.valorant.R;
import com.example.valorant.User;

import java.util.List;

public class FriendsFragment extends Fragment {
    ListView listViewFriend;
    ImageView imageViewProfilePic;
    TextView textViewUsername;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        wireWidgets(rootView);

        return rootView;
    }

    private void wireWidgets(View rootView) {
        listViewFriend = rootView.findViewById(R.id.listView_friendFragment_friendsList);
    }


    public class FriendAdapter extends ArrayAdapter {

        private List<User> friendsList;
        private int position;

        public FriendAdapter(List<User> friendsList) {
            super(getActivity().this, -1, friendsList);
            this.friendsList = friendsList;
        }

        public List<User> getFriendsList() {
            return friendsList;
        }

        public void setFriendsList(List<User> friendsList) {
            this.friendsList = friendsList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_friends, parent, false);
            }

            textViewUsername = convertView.findViewById(R.id.textView_friendItem_username);
            //do picture things with picasso

            textViewUsername.setText(friendsList.get(position).getUsername());

            return convertView;
        }
    }
}