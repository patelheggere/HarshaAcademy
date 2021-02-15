package com.patelheggere.harshaacademy.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String CATEGORY_TABLE = "category_tbl";
    public static final String SUB_CATEGORY_TABLE = "sub_category_table";
    public static final String CHAIN_TBL = "chain_tbl";
    public static final String BUILDING_TYPE = "building_type_tbl";
    public static final String REST_TYPE = "REST_TYPE_TABLE";
    public static final String SERVICE_TABLE = "SERVICE_TABLE";
    public static final String POLYGON_TABLE = "POLYGON_TABLE";

    public static final String CATEGORY = "category";
    public static final String CATEGORY_ID = "category_id";
    public static final String SUB_CATEGORY_ID = "sub_category_id";

    public static final String CHAIN_ID_VALUE_TABLE = "CHAIN_ID_VALUE_tbl";
    public static final String ISHAVING_SERVICE_TYPE = "ISHAVING_SERVICE_TYPE";
    public final String TAG = SqliteHelper.this.getClass().getName();
    // Database Version
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "forest_boundary";
    public static final String TABLE_DATA_COLLECTED = "master_table_main";
    public static final String TABLE_NEW_POINT_COLLECTED = "new_point_collected_table";
    public static final String NEW_POINT_TABLE = "new_point_table";

    public static final String TABLE_Villagename = "master_village";
    public static final String TABLE_FULLDETAILS = "table_fullDetails";
    public static final String TABLE_LOGIN= "table_login";
    public static final String TABLE_REGISTRATION= "table_registration";
    public static final String TABLE_STAGE2= "Stage2";
    public static final String TABLE_DIST ="dist";
    public static final String TABLE_TALUK= "taluk";
    public static final String TABLE_ASSET= "asset_table";

    public static final String KEY_ID = "key_id";
    public static final String POLYGON = "polygon";
    public static final String VILLAGE_NAME = "villagename";
    public static final String VILLAGE_CODE = "village_code";
    public static final String BENEFICIARY_NAME ="name";
    public static final String SERVEY_NO = "servey_no";
    public static final String HISSA_NO = "hissano";

    public static final String STATUS = "status";
    public static final String USerID = "USerID";
    public static final String ANDROID_VERSION = "androidversion";
    public static final String PASSWORD = "password";
    public static final String SHAPE_FILE_GEOMETRY ="shapefilegeometry";
    public static final String STAGE_PROGress = "stageprogress";
    public static final String OBJ_ID = "obj_id";
    public static final String DIST_CODE ="distcode";
    public static final String TALUK ="taluk";
    public static final String TALUK_CODE ="talukcode";
    public static final String UNIT = "unit";
    public static final String NAME = "name";
    public static final String ISHAVING_SUB_CAT = "ISHAVING_SUB_CAT";
    public static final String ID = "id";
    public static final String KEY1 = "id";
    public static final String KEY2 = "plantation";
    public static final String BEAT_ID = "beat_id";

    public static final String PLANTATION_NAME= "plantation_name";
    public static final String SPECIES_INFO= "species_info";
    public static final String TABLE_UNIT_NAMES= "unit_names";
    //    public static final String TABLE_UNIT_NAMES = "asset_table";
    public static final String POINT_NAME = "point_name";
    public static final String FOREST_NAME = "forest_name";
    public static final String POINT_NO = "point_no";
    public static final String FOREST_ID = "forest_id";
    public static final String UNIQUE_POINT_CODE = "UniquePointCode";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    public static final String POINTS_TABLE = "points_table";

    public static final String PAGE_NO = "page_no";
    public static final String POINT_ID = "point_id";
    public static final String LEFT_POINT = "left_point";
    public static final String RIGHT_POINT = "right_point";
    public static final String NEW_POINT = "new_point";


    public SqliteHelper(Context context) {
        /*  super(context, context.getExternalFilesDir(null).getAbsolutePath() + "/" + DATABASE_NAME, null, DATABASE_VERSION);*/
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String categoryTable = "CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID+" VARCHAR, "+
            ISHAVING_SERVICE_TYPE+" INT, "+
            NAME+" VARCHAR );";

    String chaintable = "CREATE TABLE IF NOT EXISTS " + REST_TYPE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID+" VARCHAR, "+
            NAME+" VARCHAR );";

    String serviceTable = "CREATE TABLE IF NOT EXISTS " + SERVICE_TABLE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID+" VARCHAR, "+
            CATEGORY_ID+" VARCHAR, "+
            SUB_CATEGORY_ID+" VARCHAR, "+
            NAME+" VARCHAR );";

    String polygonTable = "CREATE TABLE IF NOT EXISTS " + POLYGON_TABLE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID+" VARCHAR, "+
            USerID+" VARCHAR, "+
            POLYGON+" VARCHAR );";

    String subcategory = "CREATE TABLE IF NOT EXISTS " + SUB_CATEGORY_TABLE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID+" VARCHAR, "+
            NAME+" VARCHAR, "+
            ISHAVING_SERVICE_TYPE+" INT, "+
            CATEGORY_ID+" VARCHAR );";

    String chainIDValue = "CREATE TABLE IF NOT EXISTS " + BUILDING_TYPE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID+" VARCHAR, "+
            NAME+" VARCHAR, "+
            CATEGORY_ID+" VARCHAR );";


    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(categoryTable);
        db.execSQL(subcategory);
        db.execSQL(chainIDValue);
        db.execSQL(chaintable);
        db.execSQL(serviceTable);
        db.execSQL(polygonTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+CATEGORY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+SUB_CATEGORY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+CHAIN_ID_VALUE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+CHAIN_TBL);
            db.execSQL("DROP TABLE IF EXISTS "+SERVICE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+POLYGON_TABLE);

            db.execSQL(categoryTable);
            db.execSQL(subcategory);
            db.execSQL(chainIDValue);
            db.execSQL(chaintable);
            db.execSQL(serviceTable);
            db.execSQL(polygonTable);
        }
    }



    public void insert_values(String table, ContentValues value){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "insert_values: ");
        db.insert(table,null,value);
        db.close();
    }
    public int  get_Total_points(String Table){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        int count = 0;
        String ss="SELECT * FROM "+Table+" ;";
        Cursor c=db.rawQuery(ss,null);
        if(c.getCount()!=0){
            count=c.getCount();
        }
        c.close();
        db.close();
        return  count;
    }


    public String getgp_code(String gp){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String lib_name= "";
        String s="SELECT  "+KEY1+" FROM "+TABLE_UNIT_NAMES+ " WHERE "+
                KEY2+" = '"+gp+"'  ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name=c.getString(c.getColumnIndex(KEY1));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }
    public int get_count_user(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_count_download: ");
        int count=0;
        String query="SELECT * FROM "+TABLE_LOGIN+" WHERE "+USerID+"='"+username+"' ;";

        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query, null);
        count=c.getCount();
        Log.d("cursor_query", String.valueOf(c.getCount()));

        c.close();
        db.close();

        return count;
    }
    public int get_count_dist() {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_count_download: ");
        int count=0;
        String query="SELECT * FROM "+TABLE_DIST+" ;";
        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query, null);
        count=c.getCount();
        Log.d("cursor_query", String.valueOf(c.getCount()));
        c.close();
        db.close();
        return count;
    }

    public List<String> getPolygonData(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        List<String> assettypes = new ArrayList<>();

        String query="SELECT DISTINCT "+POLYGON+" FROM "+POLYGON_TABLE+" WHERE "+USerID+ "= '"+username+"' ;";

        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query,null);

        Log.d("cursor_query", String.valueOf(c.getCount()));
        if(c.getCount()!=0){
            c.moveToFirst();
            while (!c.isAfterLast()){
                assettypes.add(c.getString(c.getColumnIndex(POLYGON)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
       // assettypes.add(0,"Select");
        return assettypes;
    }
    public List<String> getAllUnits() {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_assettypelist: ");
        List<String> assettypes = new ArrayList<>();

        String query="SELECT DISTINCT "+KEY2+" FROM "+TABLE_UNIT_NAMES+" WHERE "+STATUS+"='1' ;";

        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query,null);

        Log.d("cursor_query", String.valueOf(c.getCount()));
        if(c.getCount()!=0){
            c.moveToFirst();
            while (!c.isAfterLast()){
                assettypes.add(c.getString(c.getColumnIndex(KEY2)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();

        assettypes.add(0,"Select");
        return assettypes;
    }

    public List<String> get_speciesinfo(String wing) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_assettypelist: ");
        List<String> assettypes = new ArrayList<>();

        String query="SELECT DISTINCT "+KEY2+" FROM "+SPECIES_INFO+" WHERE "+STATUS+"='1' AND WING='"+wing+"';";

        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query,null);

        Log.d("cursor_query", String.valueOf(c.getCount()));
        if(c.getCount()!=0){
            c.moveToFirst();
            while (!c.isAfterLast()){
                assettypes.add(c.getString(c.getColumnIndex(KEY2)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();

        assettypes.add(0,"Select");
        return assettypes;
    }
    public int get_count_taluk(String district) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_count_download: ");
        int count=0;
        String query="SELECT * FROM "+TABLE_TALUK+" WHERE "+DIST_CODE+"='"+district+"' ;";
        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query, null);
        count=c.getCount();
        Log.d("cursor_query", String.valueOf(c.getCount()));

        c.close();
        db.close();

        return count;
    }
    public void  delete_login(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count:delete ");
        String ss="DELETE  FROM "+TABLE_LOGIN+" WHERE "+USerID+"='"+username+"' ;";
        Log.d("deletelogin", ss);
        db.execSQL(ss);
    }


    public void  truncateTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ tableName);
    }


    public List<String> getTaluk(String dist){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        List<String> lib_name=new ArrayList<>();
        String s="SELECT DISTINCT "+TALUK+" FROM "+TABLE_TALUK+ " WHERE "+STATUS+"='1' AND "+DIST_CODE+ "= '"+dist+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name.add(c.getString(c.getColumnIndex(TALUK)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        lib_name.add(0,"Select");
        return lib_name;
    }

    public String gettalukcode(String str_district, String Taluk) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String lib_name="";
        String s="SELECT DISTINCT "+TALUK_CODE+" FROM "+TABLE_TALUK+ " WHERE "+STATUS+"='1' AND "+DIST_CODE+ "= '"+str_district+
                "'AND "+TALUK+ "= '"+Taluk+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name=c.getString(c.getColumnIndex(TALUK_CODE));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }
    public List<String> getVillageNameList(String userid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        List<String> lib_name=new ArrayList<>();
        String s="SELECT DISTINCT "+VILLAGE_NAME+" FROM "+TABLE_Villagename+ " WHERE "+STATUS+"='2' AND "+USerID+ "= '"+userid+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name.add(c.getString(c.getColumnIndex(VILLAGE_NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }
    public int get_count_download(String villagename) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_count_download: ");
        int count=0;
        String query="SELECT * FROM "+TABLE_Villagename+" WHERE "+VILLAGE_NAME+"='"+villagename+"' ;";
        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query, null);
        count=c.getCount();
        Log.d("cursor_query", String.valueOf(c.getCount()));

        c.close();
        db.close();

        return count;
    }


    public String getUsername() {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String lib_name="";
        String s="SELECT DISTINCT "+USerID+" FROM "+TABLE_LOGIN+ " WHERE "+STATUS+"='1' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name=c.getString(c.getColumnIndex(USerID));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }

    public List<String> get_unitname(String userid) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_assettypelist: ");
        List<String> assettypes = new ArrayList<>();

        String query="SELECT DISTINCT "+UNIT+" FROM "+TABLE_Villagename+" WHERE "+USerID+"='"+userid+"' AND "+STATUS+"='1' ;";
        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query,null);

        Log.d("cursor_query", String.valueOf(c.getCount()));
        if(c.getCount()!=0){
            c.moveToFirst();
            while (!c.isAfterLast()){
                assettypes.add(c.getString(0));
                c.moveToNext();
            }
        }
        c.close();
        db.close();

        assettypes.add(0,"Select");
        return assettypes;
    }

    public String getgeometryshapefile(String UserID, String Villagecode, String Serveyno, String Hissano, String Stageprog){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String lib_name="";
        String s="SELECT DISTINCT "+SHAPE_FILE_GEOMETRY+" FROM "+TABLE_FULLDETAILS+ " WHERE "+ USerID+
                "= '"+UserID+"' AND "+VILLAGE_CODE +"= '"+Villagecode+"' AND "+SERVEY_NO +"= '"+Serveyno+"' AND "+
                HISSA_NO +"= '"+Hissano+"' AND "+STAGE_PROGress +"= '"+Stageprog+"' AND "+STATUS+"='1' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name =c.getString(c.getColumnIndex(SHAPE_FILE_GEOMETRY));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }
    public String getvillagecode(String VillageName){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String lib_name="";
        String s="SELECT DISTINCT "+VILLAGE_CODE+" FROM "+TABLE_Villagename+ " WHERE "+ VILLAGE_NAME+
                "= '"+VillageName+"' AND "+STATUS+"='1' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name =c.getString(c.getColumnIndex(VILLAGE_CODE));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }

    public int get_count(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String s="SELECT POINT_NO FROM "+table+" WHERE "+FOREST_ID+"='"+forestId+"';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();
        return Count;
    }
    public List<String> addSelectItem(List<String> list){
        list.add(0,"Show Point(s)");
        return list;
    }
    public List<String> getAllPointsList(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT DISTINCT * FROM "+table+" WHERE "+FOREST_ID+"='"+forestId+"';";
        List<String> list = new ArrayList<>();
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                list.add(c.getString(c.getColumnIndex(UNIQUE_POINT_CODE))+"("+c.getString(c.getColumnIndex(LATITUDE))+","+c.getString(c.getColumnIndex(LONGITUDE))+")");
                c.moveToNext();
            }
        }
        list = addSelectItem(list);
        c.close();
        db.close();
        return list;
    }


    public int get_countNotsynced(String table, String selectedForestId){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String s="SELECT DISTINCT * FROM "+table+" WHERE "+FOREST_ID+"='"+selectedForestId+"'  AND "+STATUS+"='1';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }

    public List<String> getAllPointsNotSynced(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT DISTINCT * FROM "+table+" WHERE " +FOREST_ID+"='"+forestId+"';";
        List<String> list = new ArrayList<>();
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                //  list.add(c.getString(c.getColumnIndex(POINT_NAME))+"("+c.getString(c.getColumnIndex(LATITUDE))+","+c.getString(c.getColumnIndex(LONGITUDE))+")");
                list.add(c.getString(c.getColumnIndex(UNIQUE_POINT_CODE)));
                c.moveToNext();
            }
        }
        list = addSelectItem(list);

        c.close();
        db.close();
        return list;
    }

    public int verifiedSynced(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT DISTINCT POINT_NO FROM "+table+" WHERE "+STATUS+"='2' AND " +FOREST_ID+"='"+forestId+"';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }

    public int getPendingCount(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT * FROM "+table+" WHERE "+STATUS+"='0' AND " +FOREST_ID+"='"+forestId+"';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }

    public List<String> PointsverifiedSynced(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT DISTINCT "+UNIQUE_POINT_CODE +"," +LATITUDE+","+LONGITUDE+" FROM "+table+" WHERE "+STATUS+"='2' AND "+FOREST_ID+"='"+forestId+"';";
        List<String> list = new ArrayList<>();
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                //  list.add(c.getString(c.getColumnIndex(POINT_NO))+"("+c.getString(c.getColumnIndex(LATITUDE))+","+c.getString(c.getColumnIndex(LONGITUDE))+")");
                list.add(c.getString(c.getColumnIndex(UNIQUE_POINT_CODE)));
                c.moveToNext();
            }
        }
        list = addSelectItem(list);
        c.close();
        db.close();
        return list;
    }

    public int notVerified(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT DISTINCT POINT_NO FROM "+table+" WHERE "+STATUS+"='0' AND " +FOREST_ID+"='"+forestId+"';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }

    public List<String> PointNotVerified(String table, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        String s="SELECT DISTINCT "+UNIQUE_POINT_CODE +"," +LATITUDE+","+LONGITUDE+" FROM "+table+" WHERE "+STATUS+"='0' AND " +FOREST_ID+"='"+forestId+"';";
        List<String> list = new ArrayList<>();
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                //  list.add(c.getString(c.getColumnIndex(POINT_NO))+"("+c.getString(c.getColumnIndex(LATITUDE))+","+c.getString(c.getColumnIndex(LONGITUDE))+")");
                list.add(c.getString(c.getColumnIndex(UNIQUE_POINT_CODE)));
                c.moveToNext();
            }
        }
        list = addSelectItem(list);
        c.close();
        db.close();
        return list;
    }


    public String getVillageCode(String Villagename){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String lib_name="";
        String s="SELECT DISTINCT "+VILLAGE_CODE+" FROM "+TABLE_FULLDETAILS+ " WHERE "+VILLAGE_NAME+"='"+Villagename+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name = c.getString(c.getColumnIndex(VILLAGE_CODE));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }
    public int get_count2(String table, String Username){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String s="SELECT * FROM "+table+" WHERE "+USerID+"='"+Username+"' ;";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }
    public boolean match_user_password(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_count_download: ");
        int count=0;
        boolean flag = false;
        String query="SELECT * FROM "+TABLE_LOGIN+" WHERE "+USerID+"='"+username+"' ;";
        Log.d("cursor_query",query);
        Cursor c= db.rawQuery(query, null);
        count=c.getCount();
        Log.d("cursor_query", String.valueOf(c.getCount()));

        if(c.getCount()!=0){
            c.moveToFirst();
            String user=c.getString(c.getColumnIndex(USerID));
            String pwd=c.getString(c.getColumnIndex(PASSWORD));
            if(user.equals(username)&&pwd.equals(password)){
                flag=true;
            }
        }

        c.close();
        db.close();

        return flag;
    }
    public int getfullcount(String userid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String s="SELECT * FROM "+TABLE_Villagename+" WHERE "+USerID+"='"+userid +"';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }
    public int getfullcount_village_detaile(String userid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String s="SELECT DISTINCT "+VILLAGE_NAME+" FROM "+TABLE_FULLDETAILS+" WHERE "+USerID+"='"+userid +"';";
        Cursor c=db.rawQuery(s,null);
        int Count=0;
        if(c!=null){
            Count=c.getCount();
        }else{
            Count=0;
        }
        c.close();
        db.close();

        return Count;
    }
    public void  changeStatus_flag_Of_VillageList(String satus, String villname){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "change status: ");
        String ss="UPDATE "+TABLE_Villagename+" SET "+STATUS+"='"+satus+"' WHERE "+VILLAGE_NAME+"='"+villname+"' ;";
        Log.d("jmkjhljk",ss);
        db.execSQL(ss);

    }

    public void  delete_viewflag_Of_DetailVillageList(String userId, String villname){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String ss="DELETE FROM "+TABLE_FULLDETAILS+" WHERE "+VILLAGE_NAME+"='"+villname+"' AND "+USerID+"='"+userId+"' ;";
        db.execSQL(ss);
    }

    public void updatePointTable(String uniquecode) {
        SQLiteDatabase db = this.getWritableDatabase();
        String ss="UPDATE "+POINTS_TABLE+" SET "+ STATUS+"='"+0+"' WHERE "+UNIQUE_POINT_CODE+"='"+uniquecode+"' ;";
        db.execSQL(ss);
    }

    public void  delete_collected_data(String Table, String keyid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        // Log.e( "keyid: ",keyid);
        String ss = "";
        ss="DELETE FROM "+Table+" WHERE "+UNIQUE_POINT_CODE+"='"+keyid+"' ;";

        db.execSQL(ss);
    }

    public void  delete_collecteddata(String keyid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        // Log.e( "keyid: ",keyid);
        String ss = "";
        ss="DELETE FROM "+TABLE_DATA_COLLECTED+" WHERE "+UNIQUE_POINT_CODE+"='"+keyid+"' ;";

        db.execSQL(ss);
    }
    public void  new_delete_collecteddata(String leftkeyid, String rightkeyid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        // Log.e( "keyid: ",keyid);
        String ss = "";
        ss="DELETE FROM "+TABLE_NEW_POINT_COLLECTED+" WHERE "+LEFT_POINT+"='"+leftkeyid+"' AND "+RIGHT_POINT+"='"+rightkeyid+"' ;";

        db.execSQL(ss);
    }

    public void  delete_view(String keyid, String forestId){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        // Log.e( "keyid: ",keyid);
        String ss = "";
        if(forestId==null) {
            ss="DELETE FROM "+TABLE_NEW_POINT_COLLECTED+" WHERE "+POINT_NAME+"='"+keyid+"' ;";
        }
        else
        {
            ss="DELETE FROM "+TABLE_DATA_COLLECTED+" WHERE "+POINT_NAME+"='"+keyid+"'  AND forest_id='"+forestId+"';";

        }
        db.execSQL(ss);
    }

    public void  deleteFromNewPointTbl(String src, String dst){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String ss = "";
        ss="DELETE FROM "+TABLE_NEW_POINT_COLLECTED+" WHERE "+LEFT_POINT+"='"+src+"'  AND +"+RIGHT_POINT+"='"+dst+"';";
        Log.d(TAG, "deleteFromNewPointTbl: "+ss);
        db.execSQL(ss);
    }

    public void  delete_login(){
        SQLiteDatabase db = this.getWritableDatabase();
        String ss="DELETE FROM "+TABLE_LOGIN+";";
        db.execSQL(ss);
    }

    public int  get_sync_count(){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        int count = 0;
        String ss="SELECT * FROM "+TABLE_DATA_COLLECTED+" WHERE "+STATUS+"= '1' ;";
        Cursor c=db.rawQuery(ss,null);
        if(c.getCount()!=0){
            count=c.getCount();
        }
        c.close();
        db.close();
        return  count;
    }
    public int  get_sync_count_unit(String Unit){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        int count = 0;
        String ss="SELECT * FROM "+PLANTATION_NAME+" WHERE "+UNIT+"= '"+Unit+"' ;";
        Cursor c=db.rawQuery(ss,null);
        if(c.getCount()!=0){
            count=c.getCount();
        }
        c.close();
        db.close();
        return  count;
    }
    public void update_SyncTable(String benificiaryId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String ss="UPDATE "+TABLE_DATA_COLLECTED+" SET "+ STATUS+"='"+status+"' WHERE "+KEY_ID+"='"+benificiaryId+"' ;";
        db.execSQL(ss);
    }

    public List<String> get_listVillagecode_frombeneficiaryId(){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        List<String> lib_name=new ArrayList<>();
        String s="SELECT DISTINCT "+VILLAGE_CODE+" FROM "+TABLE_DATA_COLLECTED+ " WHERE "+STATUS+"='2' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name.add(c.getString(c.getColumnIndex(VILLAGE_CODE)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }

    public List<String> get_listbeneficiaryId(String Villagecode){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        List<String> lib_name=new ArrayList<>();
        String s="SELECT DISTINCT "+OBJ_ID+" FROM "+TABLE_FULLDETAILS+ " WHERE "+VILLAGE_CODE+"='"+Villagecode+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name.add(c.getString(c.getColumnIndex(OBJ_ID)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return lib_name;
    }
    public void  update_record(String id){
       /* SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        String ss="UPDATE "+TABLE_MASTER_MAIN+" SET "+REMARKS+"='"+remarks+"',"+ACRE+"='"+acre+"',"+GUNTA+"='"+gunta+"',"+BENEFICIARY_NAME+" = '"+surveyno+"',"+VILLAGE_NAME+" = '"+plantationname+"' WHERE "+KEY_ID+"='"+id+"' ;";
        db.execSQL(ss);*/

    }
    public List<String> getPoints(String forest_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        List<String> lib_name=new ArrayList<>();
        //  String s="SELECT DISTINCT "+FIRNO+" FROM "+TABLE_MASTER+ " WHERE "+POLICE_STATION+" = '"+ps  +  "' AND "+STATUS+"='1' ;";
        String s="SELECT DISTINCT "+POINT_NO+" FROM "+TABLE_ASSET+ " WHERE "+
                FOREST_ID+" = '"+forest_id+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name.add(c.getString(c.getColumnIndex(POINT_NO)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        lib_name.add(0,"Select points for verification");
        return lib_name;
    }


    public List<String> getForestNamesList()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+FOREST_NAME+" FROM "+POINTS_TABLE+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(FOREST_NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Forest");
        return names;
    }

    public String getCatId(String cateName)
    {
        cateName = cateName.replace("'","''");
        SQLiteDatabase db = this.getWritableDatabase();
        String forestId = "";
        Cursor c = db.rawQuery( "select * from "+CATEGORY_TABLE+ " WHERE "+NAME+"='"+cateName+"'", null );
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                forestId = c.getString(c.getColumnIndex(ID));
                break;
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return forestId;
    }

    public String getSubCatId(String cateName)
    {
        //cateName = cateName.replace("'", "\\'");
        cateName = cateName.replace("'","''");
        SQLiteDatabase db = this.getWritableDatabase();
        String forestId = "";
        Cursor c = db.rawQuery( "select * from "+SUB_CATEGORY_TABLE+ " WHERE "+NAME+"= '"+cateName+"' ", null );
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                forestId = c.getString(c.getColumnIndex(ID));
                break;
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return forestId;
    }

    public List<String> getForestIDList()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+FOREST_ID+" FROM "+POINTS_TABLE+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(FOREST_ID)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Forest");
        return names;

    }
    public int  get_Total_points_downloaded(String Table){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        int count = 0;
        //  String ss="SELECT * FROM "+Table+" WHERE "+Status+"='1' ;";
        String ss="SELECT * FROM "+Table+";";
        Cursor c=db.rawQuery(ss,null);
        if(c.getCount()!=0){
            count=c.getCount();
        }
        c.close();
        db.close();
        return  count;
    }
    public List<String> getGPSPoints(String forest_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "get_sync_count: ");
        List<String> lib_name=new ArrayList<>();
        //  String s="SELECT DISTINCT "+FIRNO+" FROM "+TABLE_MASTER+ " WHERE "+POLICE_STATION+" = '"+ps  +  "' AND "+STATUS+"='1' ;";
        String s="SELECT DISTINCT "+POINT_NO+" FROM "+POINTS_TABLE+ " WHERE "+
                FOREST_ID+" = '"+forest_id+"' ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                lib_name.add(c.getString(c.getColumnIndex(POINT_NO)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        lib_name.add(0,"Select points for verification");
        return lib_name;
    }



    public void truncateAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ CATEGORY_TABLE);
        db.execSQL("delete from "+ BUILDING_TYPE);
        db.execSQL("delete from "+ SUB_CATEGORY_TABLE);
        db.execSQL("delete from "+ REST_TYPE);
        db.execSQL("delete from "+ SERVICE_TABLE);
        db.execSQL("delete from "+ POLYGON_TABLE);
        db.close();
    }


    public List<String> getCategoryList() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+CATEGORY_TABLE+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Category");
        return names;
    }

    public List<String> getRestTyp() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+REST_TYPE+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Food Type");
        return names;
    }

    public List<String> getSubCategoryList(String catid) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+SUB_CATEGORY_TABLE+" WHERE "+CATEGORY_ID+"="+catid+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Sub category");
        return names;
    }



    public List<String> getBuildingType() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+BUILDING_TYPE+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Building Type");
        return names;
    }

    public List<String> getServiceList(String category) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+SERVICE_TABLE+" WHERE  "+CATEGORY_ID+"="+category+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Service Type");
        return names;
    }

    public List<String> getServiceListSub(String subcategory) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+SERVICE_TABLE+" WHERE  "+SUB_CATEGORY_ID+"="+subcategory+" ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Service Type");
        return names;
    }

    public List<String> getServiceList2() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> names=new ArrayList<>();
        String s="SELECT DISTINCT "+NAME+ " FROM "+SERVICE_TABLE+"  ;";
        Cursor c=db.rawQuery(s,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                names.add(c.getString(c.getColumnIndex(NAME)));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        names.add(0,"Select Service Type");
        return names;
    }

}
