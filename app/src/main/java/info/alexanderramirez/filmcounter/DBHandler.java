package info.alexanderramirez.filmcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "filmdb";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "films";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String TITLE_COL = "title";

    // below variable id for our course duration column.
    private static final String WATCH_COUNT_COL = "watch_count";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + WATCH_COUNT_COL + " INTEGER)";
        db.execSQL(query);
    }

    public void addNewFilm(String filmTitle, int filmWatchCount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE_COL, filmTitle);
        values.put(WATCH_COUNT_COL, Integer.toString(filmWatchCount));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<FilmModel> readFilms(){
        Log.i("activity", "MyClass.getView() — get item number ");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorFilms = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + WATCH_COUNT_COL + " DESC", null);
        ArrayList<FilmModel> filmModelArrayList = new ArrayList<>();
        if (cursorFilms.moveToFirst()) {
            //String filmTitle, int filmWatchCount, int id
            do {

                System.out.println(cursorFilms.getString(1));
                filmModelArrayList.add(new FilmModel(cursorFilms.getString(1),
                        Integer.parseInt(cursorFilms.getString(2)),
                        Integer.parseInt(cursorFilms.getString(0))));
            } while (cursorFilms.moveToNext());
        }

        cursorFilms.close();
        return filmModelArrayList;
    }

    public void updateFilm(int id, String filmTitle, int filmWatchCount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE_COL, filmTitle);
        values.put(WATCH_COUNT_COL, filmWatchCount);
        String id_str = Integer.toString(id);

        db.update(TABLE_NAME, values,"id=?", new String[] {id_str});
        db.close();
    }

    public void deleteFilm(int filmId){
        SQLiteDatabase db = this.getWritableDatabase();
        String filmIdStr = Integer.toString(filmId);
        db.delete(TABLE_NAME, "id=?", new String[]{filmIdStr});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
