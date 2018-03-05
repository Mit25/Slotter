package com.example.android.slotter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mit25 on 22/1/18.
 */

public class Upcoming extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Event> list;
    RecyclerView recycle;
    RecyclerAdapter recyclerAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("heyyy","opps you entered");

        View rootView = inflater.inflate(R.layout.cretedevent_dashboard, container, false);
        super.onCreate(savedInstanceState);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myRef= FirebaseDatabase.getInstance().getReference().child("Event");
        myRef.keepSynced(true);
        recycle = (RecyclerView) getView().findViewById(R.id.my_recycler_view);




        list = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(list,getActivity());
        RecyclerView.LayoutManager recyce = new GridLayoutManager(this.getActivity(),1);
        //RecyclerView.LayoutManager recyce = new LinearLayoutManager(getActivity());
        //recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        //RecyclerAdapter ad = new RecyclerAdapter(getActivity(),recyce)
        recycle.setLayoutManager(recyce);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);

        prepareData();
    }

    public void prepareData() {
        //final Query getPass = myRef.child("Event");
        //Event e = new Event("a","b","c","d","g","h");
        //list.add(e);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //list = new ArrayList<Event>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Event value = dataSnapshot1.getValue(Event.class);
                    Event fire = new Event();

                    String name = value.getEventName();
                    String sdate = value.getSdate();
                    String edate = value.getEdate();
                    String description = value.getDescription();
                    String ecreator = value.geteCreator();
                    String ecode = value.geteCode();

                    //Log.d("heyy event name!!!!",name);

                    fire.setEventName(name);
                    fire.setSdate(sdate);
                    fire.setEdate(edate);
                    fire.setDescription(description);
                    fire.seteCreator(ecreator);
                    fire.seteCode(ecode);
                    list.add(fire);
                    recyclerAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

    }
}
