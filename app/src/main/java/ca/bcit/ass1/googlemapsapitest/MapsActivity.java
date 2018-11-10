package ca.bcit.ass1.googlemapsapitest;

import android.support.v4.app.FragmentActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
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
                break;
            case R.id.railway:
                if (checked)
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

        // Add a marker in Sydney and move the camera
        LatLng newWest = new LatLng(49.211677, -122.915867);
        mMap.addMarker(new MarkerOptions().position(newWest).title("Sample Household"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newWest));
        mMap.moveCamera(CameraUpdateFactory.zoomTo((float)12.5));

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

        CircleOptions circleOptions = new CircleOptions()
                .center(newWest)
                .radius(300)
                .strokeWidth(3)
                .strokeColor(0xffffff99)
                .fillColor(0x99ffff99)
                .clickable(true);

        Circle circle = mMap.addCircle(circleOptions);

        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {
                mMap.animateCamera(CameraUpdateFactory.newLatLng(circle.getCenter()));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(1));
            }
        });
    }
}
