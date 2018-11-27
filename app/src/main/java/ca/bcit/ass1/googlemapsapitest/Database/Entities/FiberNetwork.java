package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

import ca.bcit.ass1.googlemapsapitest.Landmark;

public class FiberNetwork extends Landmark{
    public static ArrayList<FiberNetwork> fiberNetworks = new ArrayList<>();

    private static int color = 60;

    public FiberNetwork(String name, double lat, double lng) {
        super(name, "fibernetwork", lat, lng, color);
    }
}
