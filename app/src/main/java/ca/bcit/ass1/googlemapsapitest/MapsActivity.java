package ca.bcit.ass1.googlemapsapitest;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.bcit.ass1.googlemapsapitest.Database.DB;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.BusStop;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.FiberNetwork;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.MajorShopping;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.Park;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.SkytrainStationPts;
import ca.bcit.ass1.googlemapsapitest.Database.Entities.SportsFields;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DrawerLayout mDrawerLayout;
    private static LandmarkManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        new initDB().execute();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // set callback on fragment
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setTitle("Google Maps");

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(false);
                        // close drawer when item is tapped


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }


                });

    }

    public class initDB extends AsyncTask<Void, Void, Void> {
        public initDB() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            SQLiteOpenHelper helper = new DB(MapsActivity.this);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            makeBusStops();
            makeFiberNetwork();
            makeMajorShopping ();
            makeParks();
            makeSkytrainStationPts();
            makeSportFields();

            lm = new LandmarkManager();

            displayOutline();
//            displayLocations(lm);
        }

        void makeBusStops() {
            DB db = new DB(MapsActivity.this);
            List<String> name = db.getName("BUS_STOPS");
            List<String> lat = db.getLat("BUS_STOPS");
            List<String> lng = db.getLng("BUS_STOPS");
            for (int i = 0; i < name.size(); ++i) {
                BusStop.busStops.add(new BusStop(name.get(i),
                        Double.parseDouble(lat.get(i)),
                        Double.parseDouble(lng.get(i))));
            }
        }

        void makeFiberNetwork() {
            DB db = new DB(MapsActivity.this);
            List<String> name = db.getName("FIBER_NETWORK");
            List<String> lat = db.getLat("FIBER_NETWORK");
            List<String> lng = db.getLng("FIBER_NETWORK");
            for (int i = 0; i < name.size(); ++i) {
                FiberNetwork.fiberNetworks.add(new FiberNetwork(name.get(i),
                        Double.parseDouble(lat.get(i)),
                        Double.parseDouble(lng.get(i))));
            }
        }

        void makeMajorShopping() {
            DB db = new DB(MapsActivity.this);
            List<String> name = db.getName("MAJOR_SHOPPING");
            List<String> lat = db.getLat("MAJOR_SHOPPING");
            List<String> lng = db.getLng("MAJOR_SHOPPING");
            for (int i = 0; i < name.size(); ++i) {
                MajorShopping.majorShoppings.add(new MajorShopping(name.get(i),
                        Double.parseDouble(lat.get(i)),
                        Double.parseDouble(lng.get(i))));
            }
        }

        void makeParks() {
            DB db = new DB(MapsActivity.this);
            List<String> name = db.getName("PARKS");
            List<String> lat = db.getLat("PARKS");
            List<String> lng = db.getLng("PARKS");
            for (int i = 0; i < name.size(); ++i) {
                Park.parks.add(new Park(name.get(i),
                        Double.parseDouble(lat.get(i)),
                        Double.parseDouble(lng.get(i))));
            }
        }

        void makeSkytrainStationPts() {
            DB db = new DB(MapsActivity.this);
            List<String> name = db.getName("SKYTRAIN_STATIONS_PTS");
            List<String> lat = db.getLat("SKYTRAIN_STATIONS_PTS");
            List<String> lng = db.getLng("SKYTRAIN_STATIONS_PTS");
            for (int i = 0; i < name.size(); ++i) {
                SkytrainStationPts.skytrainStationPts.add(new SkytrainStationPts(name.get(i),
                        Double.parseDouble(lat.get(i)),
                        Double.parseDouble(lng.get(i))));
            }
        }

        void makeSportFields() {
            DB db = new DB(MapsActivity.this);
            List<String> name = db.getName("SPORTS_FIELDS");
            List<String> lat = db.getLat("SPORTS_FIELDS");
            List<String> lng = db.getLng("SPORTS_FIELDS");
            for (int i = 0; i < name.size(); ++i) {
                SportsFields.sportsFields.add(new SportsFields(name.get(i),
                        Double.parseDouble(lat.get(i)),
                        Double.parseDouble(lng.get(i))));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu. This adds items to the app bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();

                SearchView mSearchView = (SearchView) item.getActionView();
                mSearchView.setQueryHint("Search");
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {


                       displayAddress(query);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        return false;
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.hospital:
                if (checked)
                    Toast.makeText(this, "checked", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "unchecked", Toast.LENGTH_LONG).show();
                break;
            case R.id.park:
                if (checked)
                break;
            case R.id.school:
                if (checked)
                break;
            case R.id.transport:
                if (checked)
                break;
            case R.id.mall:
                if (checked)
                    onCheck("mall");
                else
                    onUncheck("mall");
                break;
            case R.id.railway:
                if (checked)
                    onCheck("railway");
                else
                    onUncheck("railway");
                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
                displayAddress(arg0);
            }
        });

        LatLng newWest = new LatLng(49.211677, -122.915867);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newWest));
        mMap.moveCamera(CameraUpdateFactory.zoomTo((float)12.5));
    }

    public void displayOutline() {
        Polygon newWestBoundary = mMap.addPolygon(new PolygonOptions()
                .add(
                        new LatLng(49.20098836654929, -122.96034210896909),
                        new LatLng(49.193360569333024, -122.95278900838315),
                        new LatLng(49.1911168756904, -122.9581105110687),
                        new LatLng(49.174734829126194, -122.95742386556088),
                        new LatLng(49.18124341470723, -122.93888443684995),
                        new LatLng(49.18573159575444, -122.93304795003354),
                        new LatLng(49.18920965608339, -122.92600983357846),
                        new LatLng(49.19459455746057, -122.92017334676206),
                        new LatLng(49.196277218935215, -122.91468018269956),
                        new LatLng(49.203680249419804, -122.89923065877377),
                        new LatLng(49.21074574480401, -122.8921925423187),
                        new LatLng(49.218707235863675, -122.87845963216245),
                        new LatLng(49.224649484875414, -122.87674301839292),
                        new LatLng(49.22902162687726, -122.87674301839292),
                        new LatLng(49.2305910193829, -122.87468308186948),
                        new LatLng(49.2324966432602, -122.87725800252377),
                        new LatLng(49.23372965483408, -122.87983292317807),
                        new LatLng(49.234514282538804, -122.88257950520932),
                        new LatLng(49.23496263563153, -122.88515442586362),
                        new LatLng(49.2357472437514, -122.88824433064877),
                        new LatLng(49.236195585652766, -122.89047592854917),
                        new LatLng(49.23798891256289, -122.8925358650726),
                        new LatLng(49.23697514899956, -122.89343239190458),
                        new LatLng(49.23652681417426, -122.89326073052763),
                        new LatLng(49.23585430430592, -122.89669395806669))
        );

        newWestBoundary.setStrokeColor(0xffFF0000);
    }
    public void displayLocations(LandmarkManager lm) {

        displayOutline();

        ArrayList<Landmark> list = lm.getLocations();
        for(int i = 0; i < list.size(); i++) {
            LatLng coords = new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude());
            Log.e("banana1", Double.toString(list.get(i).getLatitude()));
            mMap.addMarker(new MarkerOptions().position(coords).title(list.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(list.get(i).getColor())));
        }
    }

    public void onCheck(String type) {
        lm.show(type);
        mMap.clear();
        displayLocations(lm);
    }

    public void onUncheck(String type) {
        lm.hide(type);
        mMap.clear();
        displayLocations(lm);
    }

    public void displayAddress(String addr) {
        mMap.clear();
        displayOutline();

        String address = addr;
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try
        {
            List<Address> addresses = geoCoder.getFromLocationName(address, 5);
            if (addresses.size() > 0)
            {
                Double lat = (double) (addresses.get(0).getLatitude());
                Double lon = (double) (addresses.get(0).getLongitude());

                Log.d("lat-long", "" + lat + "......." + lon);
                final LatLng user = new LatLng(lat, lon);
                /*used marker for show the location */
                Marker hamburg = mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(address));
                // Move the camera instantly to hamburg with a zoom of 15.
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user, 15), 500, null);
                surroundingLocations(user);

                if(!(addresses.get(0).getAddressLine(0).toLowerCase().contains("new westminster"))) {
                    Toast t = Toast.makeText(getApplicationContext(), "Warning: You're currently not in New Westminster", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
            else {
                Toast t = Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_SHORT);
                t.show();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void displayAddress(LatLng user) {
        mMap.clear();
        displayOutline();

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        /*used marker for show the location */
        Marker hamburg = mMap.addMarker(new MarkerOptions()
                .position(user));
        // Move the camera instantly to hamburg with a zoom of 15.
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user, 15), 500, null);
        surroundingLocations(user);
    }

    public void surroundingLocations(LatLng location) {
        CircleOptions circleOptions = new CircleOptions()
        .center(location)
        .radius(300)
        .strokeWidth(3)
        .strokeColor(0xffffff99)
        .fillColor(0x99ffff99)
        .clickable(true);

        Circle circle = mMap.addCircle(circleOptions);

        float[] distance = new float[2];

        ArrayList<Landmark> list = lm.getLocations();
        for(int i = 0; i < list.size(); i++) {
            Location.distanceBetween(list.get(i).getLatitude(), list.get(i).getLongitude(), circle.getCenter().latitude,circle.getCenter().longitude,distance);
            if ( distance[0] <= circle.getRadius())
            {
                LatLng coords = new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(coords).title(list.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(list.get(i).getColor())));
            }
        }
    }

}
