package ca.bcit.ass1.googlemapsapitest;

public class Mall extends Landmark {

    private static int color = 120;
    public Mall() {
        super();
        setType("mall");
    }

    public Mall(String name, double longitude, double latitude) {
        super(name, "mall", longitude, latitude, 100);
    }
}
