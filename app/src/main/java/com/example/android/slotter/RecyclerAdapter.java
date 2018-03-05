package com.example.android.slotter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
/**
 * Created by Niraj Vadhaiya on 22-02-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

    List<Event> list;
    Context context;


    public RecyclerAdapter(List<Event> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_card_view,parent,false);
        MyHoder myHoder = new MyHoder(view,context,list);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Event mylist = list.get(position);
        holder.name.setText(mylist.getEventName());
        holder.email.setText(mylist.getSdate());
        holder.address.setText(mylist.getEdate());


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
        List<Event> l;
        Context ctx;
        public MyHoder(View itemView,Context ctx,List<Event> list) {
            super(itemView);
            this.ctx = ctx;
            this.l = list;
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.eName);
            email= (TextView) itemView.findViewById(R.id.sDate);
            address= (TextView) itemView.findViewById(R.id.eDate);

        }

        @Override
        public void onClick(View view) {
            int positon = getAdapterPosition();
            Event e = this.l.get(positon);
       //     Toast.makeText(view.getContext(),"NEW!!!!!!"+e.geteCode(),Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this.ctx,CardViewDetails.class);
            i.putExtra("Event Name",e.getEventName());
            i.putExtra("Start Date",e.getSdate());
            i.putExtra("End Date",e.getEdate());
            i.putExtra("Event Description",e.getDescription());
            i.putExtra("Event Code",e.geteCode());
            i.putExtra("Event Creator",e.geteCreator());
            this.ctx.startActivity(i);

        }


    }

}