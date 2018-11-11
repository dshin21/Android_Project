package ca.bcit.ass1.googlemapsapitest;

import java.util.ArrayList;
import java.util.HashMap;

public class LandmarkManager {

    private HashMap<String, Boolean> map;
    private ArrayList<Mall> malls = new ArrayList<Mall>();
    private ArrayList<Railway> railways = new ArrayList<Railway>();

    public LandmarkManager() {
        map = new HashMap<String, Boolean>();
        map.put("mall", true);
        map.put("railway", true);

        populate();
    }

    private void populate() {
        Landmark[] l = new Landmark[4];
        Mall m = new Mall("mall1", -122.912645675014,49.2233306124747);
        Mall m2 = new Mall("mall2", -122.9248642, 49.2125498);
        Railway r = new Railway("railway1", -122.912835200134, 49.2234847546306);
        Railway r2 = new Railway("railway2", 49.212525, -122.917193);
        l[0] = m;
        l[1] = r;
        l[2] = m2;
        l[3] = r2;

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

    public ArrayList<Landmark> getLocations() {
        ArrayList<Landmark> list = new ArrayList<Landmark>();
        if(map.get("mall")) {
            list.addAll(malls);
        }
        if(map.get("railway")) {
            list.addAll(railways);
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
