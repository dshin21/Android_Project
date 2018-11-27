package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

import ca.bcit.ass1.googlemapsapitest.Landmark;

public class MajorShopping extends Landmark {
    public static ArrayList<MajorShopping> majorShoppings = new ArrayList<>();

    private static int color = 200;

    public MajorShopping(String name, double lat, double lng) {
        super(name, "majorshopping", lat, lng, color);
    }
}
