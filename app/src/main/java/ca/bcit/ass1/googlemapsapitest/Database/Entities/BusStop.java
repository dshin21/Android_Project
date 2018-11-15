package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

import ca.bcit.ass1.googlemapsapitest.Landmark;

public class BusStop extends Landmark {
    public static ArrayList<BusStop> busStops = new ArrayList<>();

    private static int color = 120;

    /**
     *  lat and lng are actually swapped
     */
    public BusStop(String name, double lat, double lng) {
        super(name, "busstop", lat, lng, color);
    }
}
