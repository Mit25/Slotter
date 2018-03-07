package com.example.android.slotter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
/**
 * Created by Niraj Vadhaiya on 22-02-2018.
 */

public class RecyclerAdapterAuth extends RecyclerView.Adapter<RecyclerAdapterAuth.MyHoder>{

    List<Slot> list;
    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    String eventkey;

    public RecyclerAdapterAuth(List<Slot> list, Context context,String key) {
        this.list = list;
        this.context = context;
        this.eventkey=key;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        myRef= FirebaseDatabase.getInstance().getReference().child("Event").child(eventkey).child("SLOTDETAILS");
        myRef.keepSynced(true);

        View view = LayoutInflater.from(context).inflate(R.layout.event_card_view,parent,false);
        MyHoder myHoder = new MyHoder(view,context,list,myRef);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Slot mylist = list.get(position);
        holder.name.setText(mylist.getsNumber()+" "+mylist.getDate());
        holder.email.setText(mylist.getStime());
        holder.address.setText(mylist.getEtime());


    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }
        }catch (Exception e){
        }
        return arr;
    }


    public static class MyHoder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,email,address;
        List<Slot> l;
        FirebaseDatabase database;
        DatabaseReference myRef ;

        Context ctx;

        public MyHoder(View itemView,Context ctx,List<Slot> list,DatabaseReference ref) {
            super(itemView);
            this.ctx = ctx;
            this.l = list;
            this.myRef = ref;
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.eName);
            email= (TextView) itemView.findViewById(R.id.sDate);
            address= (TextView) itemView.findViewById(R.id.eDate);

        }

        @Override
        public void onClick(View view) {
            int positon = getAdapterPosition();
            Slot e = this.l.get(positon);
            String slotnum = Integer.toString(e.getsNumber());
            myRef.child(slotnum).child("auth").setValue(true);

            //view.(Color.parseColor("#ADE07B"));
            //change to false and true
        }


    }

}