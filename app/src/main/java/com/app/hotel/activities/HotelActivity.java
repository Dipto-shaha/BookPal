package com.app.hotel.activities;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.hotel.R;
import com.app.hotel.adapters.HotelAdapter;
import com.app.hotel.viewModels.Hotel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HotelAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private CardView hotelCardView;

    FirebaseStorage mStorage;
    private ValueEventListener mDBListener;


    private DatabaseReference mDatabaseRef;
    private List<Hotel> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mStorage = FirebaseStorage.getInstance();

//        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Hotel upload = postSnapshot.getValue(Hotel.class);
                    mUploads.add(upload);
                }

                mAdapter = new HotelAdapter(HotelActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
//                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HotelActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

//        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                mUploads.clear();
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Hotel upload = postSnapshot.getValue(Hotel.class);
//                    Objects.requireNonNull(upload).setKey(postSnapshot.getKey());
//                    mUploads.add(upload);
//                }
//
//                mAdapter.notifyDataSetChanged();
//
////                mProgressCircle.setVisibility(View.INVISIBLE);
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(HotelActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
////                mProgressCircle.setVisibility(View.INVISIBLE);
//            }
//        });

//        @Override
//        public void onItemClick(int position) {
//            Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onWhatEverClick(int position) {
//            Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onDeleteClick(int position) {
//            Hotel selectedItem = mUploads.get(position);
//            final String selectedKey = selectedItem.getKey();
//
//            StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
//            imageRef.delete().addOnSuccessListener(aVoid -> {
//                mDatabaseRef.child(selectedKey).removeValue();
//                Toast.makeText(HotelActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
//            });
//        }
//
//        @Override
//        protected void onDestroy() {
//            super.onDestroy();
//            mDatabaseRef.removeEventListener(mDBListener);
//        }

    }
}