package com.android.soozuang.ohmytrip.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by soozuang on 11/12/2017.
 */

public class Province {
    private String Name;
    private String Image;

    public Province(){

    }

    public Province(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public ArrayList<Province> getPlayers(String gameId) {
        final ArrayList<Province> commentKeys = new ArrayList<>();
        DatabaseReference nishi = FirebaseDatabase.getInstance().getReference("Category");
        Query query = nishi.orderByChild("name").equalTo(gameId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    commentKeys.add(childSnapshot.child("name").getValue(Province.class));
                }
                Log.d("Data:", commentKeys.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return commentKeys;
    }
}
