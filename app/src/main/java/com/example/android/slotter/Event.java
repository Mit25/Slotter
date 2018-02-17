/**
 * Created by Niraj Vadhaiya on 16-02-2018.
 */
package com.example.android.slotter;

public class Event {

    String eventName;
    String sdate;
    String edate;
    String description;
    String eCode;
    String eCreator;

    public Event()
    {

    }

    public Event(String eventName, String sdate, String edate, String description, String eCode, String eCreator) {
        this.eventName = eventName;
        this.sdate = sdate;
        this.edate = edate;
        this.description = description;
        this.eCode = eCode;
        this.eCreator = eCreator;
    }

    public String getEventName() {
        return eventName;
    }

    public String getSdate() {
        return sdate;
    }

    public String getEdate() {
        return edate;
    }

    public String getDescription() {
        return description;
    }

    public String geteCode() {
        return eCode;
    }

    public String geteCreator() {
        return eCreator;
    }
}
