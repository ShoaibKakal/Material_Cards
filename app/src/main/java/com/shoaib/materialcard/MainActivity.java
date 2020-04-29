package com.shoaib.materialcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SportsAdapter sportsAdapter;
    private ArrayList<Sport> mSportsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        // Initialize the RecyclerView.
        recyclerView = findViewById(R.id.recyclerview);

        // Set the Layout Manager.
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        sportsAdapter = new SportsAdapter(this, mSportsData);
        recyclerView.setAdapter(sportsAdapter);

        // Get the data.
        initializeData();

        int swipDirs;
        if(gridColumnCount >1 ){
            //landscape
            swipDirs=0;
        }else{
            //Normal
            swipDirs = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        }
        final ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                swipDirs) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mSportsData, from, to);
                sportsAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                mSportsData.remove(viewHolder.getAdapterPosition());
                sportsAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }// onCreate Ends!

    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);

        TypedArray sportsImageResources = getResources().obtainTypedArray(R.array.sports_images);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Sport(sportsList[i],sportsInfo[i], sportsImageResources.getResourceId(i,0) ) );
        }
        sportsImageResources.recycle();

        // Notify the adapter of the change.
        sportsAdapter.notifyDataSetChanged();
    }




}
