package ca.bcit.ass1.googlemapsapitest;

public class Landmark {

    private String name;
    private String type;
    private double longitude;
    private double latitude;
    private float color;

    public Landmark(){
        name = "Empty";
        type = "none";
        longitude = 0;
        latitude = 0;
        color = 0;
    }

    public Landmark(String n, String t, double lo, double la, float c) {
        name = n;
        type = t;
        longitude = lo;
        latitude = la;
        color = c;
    }

    public void setType(String t) {
        type = t;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public float getColor() { return color; }
}

