package com.example.android.slotter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mit25 on 22/1/18.
 */

public class Createevent extends Fragment {

    private RecyclerView mBlogListner;
    private DatabaseReference mref;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cretedevent_dashboard, container, false);

        mref= FirebaseDatabase.getInstance().getReference();
        mref.keepSynced(true);

        mBlogListner = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);
        mBlogListner.setHasFixedSize(true);
        mBlogListner.setLayoutManager(new LinearLayoutManager(super.getContext()));
        return rootView;
    }

}
