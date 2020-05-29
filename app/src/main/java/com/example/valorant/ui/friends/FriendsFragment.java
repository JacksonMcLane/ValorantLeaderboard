package com.example.valorant.ui.friends;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.valorant.FriendDetailActivity;
import com.example.valorant.R;
import com.example.valorant.Users;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FriendsFragment extends Fragment {
    private ListView listViewFriend;
    private ImageView imageViewProfilePic;
    private TextView textViewUsername;
    private FriendAdapter friendAdapter;
    public static final String EXTRA_FRIEND = "Friend";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        wireWidgets(rootView);
        loadDataFromBackendless();
        return rootView;
    }

    public void loadDataFromBackendless(){
        String userId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "users = " + "'" + userId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        Backendless.Data.of(Users.class).find(queryBuilder, new AsyncCallback<List<Users>>(){
            @Override
            public void handleResponse(final List<Users> foundFriends)
            {
                friendAdapter = new FriendAdapter(foundFriends);
                listViewFriend.setAdapter(friendAdapter);

                // we're sure that the list of friends exists at this point in the code
                listViewFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent detailIntent = new Intent(getActivity(), FriendDetailActivity.class);
                        detailIntent.putExtra(EXTRA_FRIEND, foundFriends.get(i));
                        startActivity(detailIntent);
                    }
                });

                /**
                 * open up add friend activity, search by username
                 * **/
//                floatingActionButtonNewFriend.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent newFriendIntent = new Intent(FriendListActivity.this, FriendDetailActivity.class);
//                        startActivity(newFriendIntent);
//                    }
//                });


            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(getActivity(), fault.getDetail(), Toast.LENGTH_SHORT).show();
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_friends, parent, false);
            }

            imageViewProfilePic = convertView.findViewById(R.id.imageView_friendItem_profilePicture);
            textViewUsername = convertView.findViewById(R.id.textView_friendItem_username);

            Picasso.get().load(friendsList.get(position).getProfilePicture()).into(imageViewProfilePic);
            textViewUsername.setText(friendsList.get(position).getUsername());

            return convertView;
        }
    }
}