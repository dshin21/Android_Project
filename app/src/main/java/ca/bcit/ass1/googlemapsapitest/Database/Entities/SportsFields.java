package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

import ca.bcit.ass1.googlemapsapitest.Landmark;

public class SportsFields extends Landmark {
    public static ArrayList<SportsFields> sportsFields = new ArrayList<>();

    private static int color = 250;

    public SportsFields(String name, double lat, double lng) {
        super(name, "sportsfield", lat, lng, color);
    }
}
