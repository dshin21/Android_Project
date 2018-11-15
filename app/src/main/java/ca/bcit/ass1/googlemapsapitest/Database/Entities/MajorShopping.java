package ca.bcit.ass1.googlemapsapitest.Database.Entities;

import java.util.ArrayList;

public class MajorShopping {
    public static ArrayList<MajorShopping> majorShoppings = new ArrayList<>();

    private String name;
    private double lat;
    private double lng;

    public MajorShopping(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
