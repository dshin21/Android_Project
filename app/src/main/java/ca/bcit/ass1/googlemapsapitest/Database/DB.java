package ca.bcit.ass1.googlemapsapitest.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.ass1.googlemapsapitest.Database.Threads.GetBusStops;
import ca.bcit.ass1.googlemapsapitest.Database.Threads.GetParks;

public class DB extends SQLiteOpenHelper {
    private static final String DB_NAME = "COMP3717.sqlite";
    private static final int DB_VERSION = 1;
    private Context context;
    private SQLiteDatabase db;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BUS_STOPS -> FIBER_NETWORK -> MAJOR_SHOPPING
        //PARKS -> SKYTRAIN_STATIONS_PTS -> SPORTS_FIELDS
        db.execSQL("DROP TABLE IF EXISTS BUS_STOPS");
        db.execSQL(createTableBUS_STOPS());
        new GetBusStops(db).execute();

        db.execSQL("DROP TABLE IF EXISTS PARKS");
        db.execSQL(DB.createTablePARKS());
        new GetParks(db).execute();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String createTableBUS_STOPS() {
        String sql = "";
        sql += "CREATE TABLE BUS_STOPS (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "LAT TEXT, ";
        sql += "LNG TEXT ";
        sql += ");";

        return sql;
    }

    public static String createTableFIBER_NETWORK() {
        String sql = "";
        sql += "CREATE TABLE FIBER_NETWORK (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "LAT TEXT, ";
        sql += "LNG TEXT ";
        sql += ");";

        return sql;
    }

    public static String createTableMAJOR_SHOPPING() {
        String sql = "";
        sql += "CREATE TABLE MAJOR_SHOPPING (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "LAT TEXT, ";
        sql += "LNG TEXT ";
        sql += ");";

        return sql;
    }

    public static String createTablePARKS() {
        String sql = "";
        sql += "CREATE TABLE PARKS (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "LAT TEXT, ";
        sql += "LNG TEXT ";
        sql += ");";

        return sql;
    }

    public static String createTableSKYTRAIN_STATIONS_PTS() {
        String sql = "";
        sql += "CREATE TABLE SKYTRAIN_STATIONS_PTS (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "LAT TEXT, ";
        sql += "LNG TEXT ";
        sql += ");";

        return sql;
    }

    public static String createTableSPORTS_FIELDS() {
        String sql = "";
        sql += "CREATE TABLE SPORTS_FIELDS (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "LAT TEXT, ";
        sql += "LNG TEXT ";
        sql += ");";

        return sql;
    }

    public List<String> getName(String tableName) {
        String selectQuery = "SELECT * FROM " + tableName;

        return selectHelper(selectQuery, "NAME");
    }

    public List<String> getLat(String tableName) {
        String selectQuery = "SELECT * FROM " + tableName;

        return selectHelper(selectQuery, "LAT");
    }

    public List<String> getLng(String tableName) {
        String selectQuery = "SELECT * FROM " + tableName;

        return selectHelper(selectQuery, "LNG");
    }

    public List<String> selectHelper(String selectQuery, String colName) {
        List<String> temp = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                temp.add(cursor.getString(cursor.getColumnIndex(colName)));
            } while (cursor.moveToNext());
        }

        db.close();

        return temp;
    }
}
