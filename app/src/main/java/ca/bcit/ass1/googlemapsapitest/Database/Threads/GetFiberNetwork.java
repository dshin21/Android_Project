package ca.bcit.ass1.googlemapsapitest.Database.Threads;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.danie.finalproject.Database.Utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.bcit.ass1.googlemapsapitest.Database.DB;

public class GetFiberNetwork extends AsyncTask<Void, Void, Void> {
    private SQLiteDatabase db;

    public GetFiberNetwork(SQLiteDatabase db) {
        this.db = db;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        String jsonStr;
        jsonStr = sh.makeServiceCall("https://api.mlab.com/api/1/databases/comp3717/collections/FIBER_NETWORK_LOCATIONS?apiKey=Q3kcLrAb4liR46OZJ46LzwhScsXPYvLn");

        if (jsonStr != null) {
            try {
                JSONArray countriesJSONArray = new JSONArray(jsonStr);
                for (int i = 0; i < countriesJSONArray.length(); i++) {
                    JSONObject c = countriesJSONArray.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("NAME", c.getString("name"));
                    values.put("LAT", c.getString("lat"));
                    values.put("LNG", c.getString("lng"));
                    long id = db.insert("FIBER_NETWORK", null, values);
                }
            } catch (final JSONException e) {
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // make objects?
        super.onPostExecute(result);
        db.execSQL("DROP TABLE IF EXISTS MAJOR_SHOPPING");
        db.execSQL(DB.createTableMAJOR_SHOPPING());
        new GetMajorShopping(db).execute();
    }
}