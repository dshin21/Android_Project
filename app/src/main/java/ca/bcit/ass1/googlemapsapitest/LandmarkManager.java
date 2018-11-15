package ca.bcit.ass1.googlemapsapitest;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import ca.bcit.ass1.googlemapsapitest.Database.Entities.BusStop;

public class LandmarkManager {

    private HashMap<String, Boolean> map;
    private ArrayList<Mall> malls = new ArrayList<Mall>();
    private ArrayList<Railway> railways = new ArrayList<Railway>();
    private ArrayList<BusStop> busstop = new ArrayList<BusStop>();

    public LandmarkManager() {
        map = new HashMap<String, Boolean>();
        map.put("mall", true);
        map.put("railway", true);
        map.put("busstop", true);

        populate();
    }

    private void populate() {
        busstop = BusStop.busStops;
    }

    public ArrayList<Landmark> getLocations() {
        ArrayList<Landmark> list = new ArrayList<Landmark>();
        if(map.get("mall")) {
            list.addAll(malls);
        }
        if(map.get("railway")) {
            list.addAll(railways);
        }
        if(map.get("busstop")) {
            list.addAll(busstop);
        }
        return list;
    }

    public void show(String type){
        if(map.containsKey(type)) {
            map.put(type, true);
        }
    }

    public void hide(String type){
        if(map.containsKey(type)) {
            map.put(type, false);
        }
    }
}
