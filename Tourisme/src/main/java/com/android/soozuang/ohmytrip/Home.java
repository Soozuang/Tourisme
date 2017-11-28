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
    DatabaseReference category;
    ArrayList<SectionProvince> allSampleData;
    ArrayList<Province> allProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set no top bar view
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        // Get database from Firebase
        allSampleData = new ArrayList<SectionProvince>();
        allProvince = new ArrayList<Province>();
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");
        category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot provinceSnapshot: dataSnapshot.getChildren()) {
                    Province sample = provinceSnapshot.getValue(Province.class);
                    allProvince.add(sample);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Design Space Navigation View
        spaceNavigationView = (SpaceNavigationView)findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("FAVORITE", R.drawable.heart));
        spaceNavigationView.addSpaceItem(new SpaceItem("ACCOUNT", R.drawable.account));
        spaceNavigationView.setCentreButtonIcon(R.drawable.home);
        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.colorPrimary));
        spaceNavigationView.setCentreButtonRippleColor(ContextCompat.getColor(this, R.color.selected_item_color));
        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this, R.color.space_white));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.colorPrimary));
        spaceNavigationView.setInActiveSpaceItemColor(ContextCompat.getColor(this, R.color.inactive_color));
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

        setUpRecyclerView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.tvTitle);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    } */

    private void setUpRecyclerView() {
        // Firebase execution...
        getProvinceData();
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.recyclerView);
        my_recycler_view.setHasFixedSize(true);
        RecyclerDataAdapter adapter = new RecyclerDataAdapter(this, allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }

    private void getProvinceData() {
        SectionProvince dm = new SectionProvince();
        dm.setHeaderTitle("Du lịch theo vùng miền ");
        dm.setAllPro(allProvince);
        allSampleData.add(dm);
        //}
    }
}