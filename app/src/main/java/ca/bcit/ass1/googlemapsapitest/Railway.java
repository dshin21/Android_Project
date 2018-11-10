package ca.bcit.ass1.googlemapsapitest;

public class Railway extends Location {

    public Railway() {
        super();
        setType("railway");
    }

    public Railway(String name, double longitude, double latitude) {
        super(name, "railway", longitude, latitude, 300);
    }
}