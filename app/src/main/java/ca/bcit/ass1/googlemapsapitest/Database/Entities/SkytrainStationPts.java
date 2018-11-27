package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

import ca.bcit.ass1.googlemapsapitest.Landmark;

public class SkytrainStationPts extends Landmark {
    public static ArrayList<SkytrainStationPts> skytrainStationPts = new ArrayList<>();

    private static int color = 170;

    public SkytrainStationPts(String name, double lat, double lng) {
        super(name, "skytrainstation", lat, lng, color);
    }
}
