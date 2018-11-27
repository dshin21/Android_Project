package ca.bcit.ass1.googlemapsapitest;

import android.bluetooth.BluetoothClass;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import ca.bcit.ass1.googlemapsapitest.Database.Entities.BusStop;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.FiberNetwork;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.MajorShopping;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.Park;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.SkytrainStationPts;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.SportsFields;

public class LandmarkManager {

    private HashMap<String, Boolean> map;
    private ArrayList<BusStop> busstop = new ArrayList<BusStop>();
    private ArrayList<FiberNetwork> fibernetwork = new ArrayList<FiberNetwork>();
    private ArrayList<MajorShopping> majorshopping = new ArrayList<MajorShopping>();
    private ArrayList<Park> park = new ArrayList<Park>();
    private ArrayList<SkytrainStationPts> skytrainstation = new ArrayList<SkytrainStationPts>();
    private ArrayList<SportsFields> sportsfield = new ArrayList<SportsFields>();


    public LandmarkManager() {
        map = new HashMap<String, Boolean>();
        map.put("busstop", true);
        map.put("fibernetwork", true);
        map.put("majorshopping", true);
        map.put("park", true);
        map.put("skytrainstation", true);
        map.put("sportsfield", true);
        populate();
    }

    private void populate() {
        busstop = BusStop.busStops;
        fibernetwork = FiberNetwork.fiberNetworks;
        majorshopping = MajorShopping.majorShoppings;
        park = Park.parks;
        skytrainstation = SkytrainStationPts.skytrainStationPts;
        sportsfield = SportsFields.sportsFields;
    }

    public ArrayList<Landmark> getLocations() {
        ArrayList<Landmark> list = new ArrayList<Landmark>();
        //green
        if(map.get("busstop")) {
            list.addAll(busstop);
        }
        //magenta
        if(map.get("fibernetwork")) {
            list.addAll(fibernetwork);
        }
        //light blue
        if(map.get("majorshopping")) {
            list.addAll(majorshopping);
        }
        // Purple
        if(map.get("park")) {
            list.addAll(park);
        }
        // teal
        if(map.get("skytrainstation")) {
            list.addAll(skytrainstation);
        }
        // dark blue
        if(map.get("sportsfield")) {
            list.addAll(sportsfield);
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
