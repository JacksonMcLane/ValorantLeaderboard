package com.example.valorant.ui.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.backendless.Backendless;
import com.example.valorant.R;
import com.example.valorant.Users;
import com.squareup.picasso.Picasso;

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

        private List<Users> friendsList;
        private int position;

        public FriendAdapter(List<Users> friendsList) {
            super(getActivity(), -1, friendsList);
            this.friendsList = friendsList;
        }

        public List<Users> getFriendsList() {
            return friendsList;
        }

        public void setFriendsList(List<Users> friendsList) {
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
            imageViewProfilePic = convertView.findViewById(R.id.imageView_friendItem_profilePicture);
            textViewUsername = convertView.findViewById(R.id.textView_friendItem_username);

            Picasso.get().load("").into(imageViewProfilePic);
            textViewUsername.setText(friendsList.get(position).getUsername());

            return convertView;
        }
    }
}