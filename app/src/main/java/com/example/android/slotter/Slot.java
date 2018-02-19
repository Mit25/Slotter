package com.example.android.slotter;

/**
 * Created by Niraj Vadhaiya on 19-02-2018.
 */

public class Slot {
    int sNumber ;
    String stime;
    String etime;
    String uid;
    boolean auth ;
    boolean viewToUser ;

    public Slot(int sNumber, String stime, String etime, String uid, boolean auth, boolean viewToUser) {
        this.sNumber = sNumber;
        this.stime = stime;
        this.etime = etime;
        this.uid = uid;
        this.auth = auth;
        this.viewToUser = viewToUser;
    }

    public int getsNumber() {
        return sNumber;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }

    public String getUid() {
        return uid;
    }

    public boolean isAuth() {
        return auth;
    }

    public boolean isViewToUser() {
        return viewToUser;
    }
}
