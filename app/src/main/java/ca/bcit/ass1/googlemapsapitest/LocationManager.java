package ca.bcit.ass1.googlemapsapitest;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LocationManager {

    private HashMap<String, Boolean> map;
    private ArrayList<Mall> malls = new ArrayList<Mall>();
    private ArrayList<Railway> railways = new ArrayList<Railway>();

    public LocationManager() {
        map = new HashMap<String, Boolean>();
        map.put("mall", true);
        map.put("railway", true);

        populate();
    }

    private void populate() {
        Location[] l = new Location[2];
        Mall m = new Mall("brentwood", -122.912645675014,49.2233306124747);
        Railway r = new Railway("fakeRailway", -122.912835200134, 49.2234847546306);
        l[0] = m;
        l[1] = r;

        for(int i = 0; i < l.length; i++) {
            switch(l[i].getType()){
                case "mall":
                    malls.add((Mall) l[i]);
                    break;
                case "railway":
                    railways.add((Railway) l[i]);
                    break;
            }
        }
    }

    public ArrayList<Location> getLocations() {
        ArrayList<Location> list = new ArrayList<Location>();
        if(map.get("mall")) {
            list.addAll(malls);
        }
        if(map.get("railway")) {
            list.addAll(railways);
        }
        return list;
    }
}
