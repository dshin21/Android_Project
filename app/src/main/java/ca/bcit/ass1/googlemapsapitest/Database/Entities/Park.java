package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

import ca.bcit.ass1.googlemapsapitest.Landmark;

public class Park extends Landmark{
    public static ArrayList<Park> parks = new ArrayList<>();

    private static int color = 280;

    public Park(String name, double lat, double lng) {
        super(name, "park", lat, lng, color);
    }
}
