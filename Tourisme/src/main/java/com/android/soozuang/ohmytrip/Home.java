package com.android.soozuang.ohmytrip;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.soozuang.ohmytrip.Adapter.RecyclerDataAdapter;
import com.android.soozuang.ohmytrip.Adapter.SectionDataAdapter;
import com.android.soozuang.ohmytrip.Interface.ItemClickListener;
import com.android.soozuang.ohmytrip.Model.Province;
import com.android.soozuang.ohmytrip.Model.SectionProvince;
import com.android.soozuang.ohmytrip.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.luseen.spacenavigation.SpaceOnLongClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class Home extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;
    FirebaseDatabase database;

    ArrayList<SectionProvince> allSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        allSection = new ArrayList<SectionProvince>();
        database = FirebaseDatabase.getInstance();

        // Edit spacenavigation View
        spaceNavigationView = (SpaceNavigationView)findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("FAVORITE", R.drawable.heart));
        spaceNavigationView.addSpaceItem(new SpaceItem("ACCOUNT", R.drawable.account));
        spaceNavigationView.setCentreButtonIcon(R.drawable.home);
        spaceNavigationView.setSpaceItemIconSize(R.dimen.space_item_icon_size);
        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.fbutton_color_midnight_blue));
        spaceNavigationView.setCentreButtonRippleColor(ContextCompat.getColor(this, R.color.fbutton_color_midnight_blue));
        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this, R.color.fbutton_color_clouds));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.fbutton_color_midnight_blue));
        spaceNavigationView.setInActiveSpaceItemColor(ContextCompat.getColor(this, R.color.cardview_dark_background));
        spaceNavigationView.showIconOnly();

        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Log.d("onCentreButtonClick ", "onCentreButtonClick");
                spaceNavigationView.shouldShowFullBadgeText(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.d("onItemClick ", "" + itemIndex + " " + itemName);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Log.d("onItemReselected ", "" + itemIndex + " " + itemName);
            }
        });

        spaceNavigationView.setSpaceOnLongClickListener(new SpaceOnLongClickListener() {
            @Override
            public void onCentreButtonLongClick() {
                Toast.makeText(Home.this, "onCentreButtonLongClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });


        suggestProvince();
        suggestPlace();
        suggestResort();
        suggestFood();
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.recyclerView);
        my_recycler_view.setHasFixedSize(true);
        RecyclerDataAdapter adapter = new RecyclerDataAdapter(this, allSection);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    private void suggestProvince() {
        database.getReference("Province").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Province> allProvince = new ArrayList<Province>();
                for (DataSnapshot provinceSnapshot : dataSnapshot.getChildren()) {
                    allProvince.add(new Province(provinceSnapshot.child("name").getValue(String.class),provinceSnapshot.child("image").getValue(String.class)));
                }
                SectionProvince temp = new SectionProvince();
                temp.setHeaderTitle("Du lịch vùng miền ");
                allSection.add(temp);
                temp.setAllPro(allProvince);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void suggestPlace(){
        database.getReference("Place").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Province> allPlace = new ArrayList<Province>();
                for (DataSnapshot provinceSnapshot : dataSnapshot.getChildren()) {
                    allPlace.add(new Province(provinceSnapshot.child("name").getValue(String.class),provinceSnapshot.child("image").getValue(String.class)));
                }
                SectionProvince temp = new SectionProvince();
                temp.setHeaderTitle("Các địa điểm nổi tiếng");
                allSection.add(temp);
                temp.setAllPro(allPlace);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void suggestResort(){
        database.getReference("Resort").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Province> allPlace = new ArrayList<Province>();
                for (DataSnapshot provinceSnapshot : dataSnapshot.getChildren()) {
                    allPlace.add(new Province(provinceSnapshot.child("name").getValue(String.class),provinceSnapshot.child("image").getValue(String.class)));
                }
                SectionProvince temp = new SectionProvince();
                temp.setHeaderTitle("Khu nghỉ dưỡng");
                allSection.add(temp);
                temp.setAllPro(allPlace);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void suggestFood(){
        database.getReference("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Province> allPlace = new ArrayList<Province>();
                for (DataSnapshot provinceSnapshot : dataSnapshot.getChildren()) {
                    allPlace.add(new Province(provinceSnapshot.child("name").getValue(String.class),provinceSnapshot.child("image").getValue(String.class)));
                }
                SectionProvince temp = new SectionProvince();
                temp.setHeaderTitle("Các món ăn ngon");
                allSection.add(temp);
                temp.setAllPro(allPlace);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}