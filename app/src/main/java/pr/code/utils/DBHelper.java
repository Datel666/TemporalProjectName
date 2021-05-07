package pr.code.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Класс-помощник для работы с базой данных SQLite
 */
public class DBHelper extends SQLiteOpenHelper  {

    //region variables

    private static DBHelper mInstance = null;

    public static int DATABASE_VERSION = 6;


    public static final String DATABASE_NAME = "cookbookDB";

    public static final String TABLE_CATEGORIES = "categories";
    public static final String KEY_IDCATEGORY = "idCategory";
    public static final String KEY_NAMECATEGORY = "strCategory";
    public static final String KEY_PHOTOCATEGORY = "strCategoryThumb";
    public static final String KEY_DESCRIPTIONCATEGORY = "strCategoryDescription";

    public static final String TABLE_VERSIONS = "versions";
    public static final String KEY_IDVERSION = "idversion";
    public static final String KEY_IDDATE = "date";
    public static final String KEY_IDVER = "version";


    public static final String TABLE_RECIPES = "meals";
    public static final String KEY_IDRECIPE = "idMeal";
    public static final String KEY_NAMERECIPE = "strMeal";
    public static final String KEY_CATEGORYRECIPE = "strCategory";
    public static final String KEY_AREARECIPE = "strArea";
    public static final String KEY_INSTRUCTIONSRECIPE = "strInstructions";
    public static final String KEY_PHOTORECIPE = "strMealThumb";
    public static final String KEY_TAGSRECIPE = "strTags";
    public static final String KEY_INGREDIENTSRECIPE = "strIngredients";
    public static final String KEY_MEASURESRECIPE = "strMeasures";
    public static final String KEY_MEALINFO = "strMealInfo";
    public static final String KEY_COOKTIME = "strCookTime";
    //endregion


    public static DBHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new DBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    // Конструктор класса
    private DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    /**
     * Создание базы данных
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
        + TABLE_CATEGORIES + "("
        + KEY_IDCATEGORY + " integer primary key autoincrement, "
        + KEY_NAMECATEGORY + " text,"
        + KEY_PHOTOCATEGORY + " text,"
        + KEY_DESCRIPTIONCATEGORY + " text " +")");

        db.execSQL("create table "
        + TABLE_RECIPES + "("
        + KEY_IDRECIPE + " integer primary key autoincrement, "
        + KEY_NAMERECIPE + " text,"
        + KEY_CATEGORYRECIPE + " text,"
        + KEY_AREARECIPE + " text,"
        + KEY_INSTRUCTIONSRECIPE + " text,"
        + KEY_PHOTORECIPE + " text,"
        + KEY_TAGSRECIPE + " text,"
        + KEY_INGREDIENTSRECIPE + " text,"
        + KEY_MEASURESRECIPE + " text,"
        + KEY_MEALINFO + " text,"
        + KEY_COOKTIME + " text " +")");

        db.execSQL("create table "
                + TABLE_VERSIONS + "("
                + KEY_IDVERSION + " integer primary key autoincrement, "
                + KEY_IDVER + " integer,"
                + KEY_IDDATE + " text " + ")");

        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(DBHelper.KEY_IDVER, -1);
            db.insert(DBHelper.TABLE_VERSIONS, null, values);
            db.setTransactionSuccessful();
        }
        catch(Exception ex)
        { }
        finally {
            db.endTransaction();
        }


    }

    public static void forceUpgrade(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSIONS);

        db.execSQL("create table "
                + TABLE_CATEGORIES + "("
                + KEY_IDCATEGORY + " integer primary key autoincrement, "
                + KEY_NAMECATEGORY + " text,"
                + KEY_PHOTOCATEGORY + " text,"
                + KEY_DESCRIPTIONCATEGORY + " text " +")");

        db.execSQL("create table "
                + TABLE_RECIPES + "("
                + KEY_IDRECIPE + " integer primary key autoincrement, "
                + KEY_NAMERECIPE + " text,"
                + KEY_CATEGORYRECIPE + " text,"
                + KEY_AREARECIPE + " text,"
                + KEY_INSTRUCTIONSRECIPE + " text,"
                + KEY_PHOTORECIPE + " text,"
                + KEY_TAGSRECIPE + " text,"
                + KEY_INGREDIENTSRECIPE + " text,"
                + KEY_MEASURESRECIPE + " text,"
                + KEY_MEALINFO + " text,"
                + KEY_COOKTIME + " text " +")");

        db.execSQL("create table "
                + TABLE_VERSIONS + "("
                + KEY_IDVERSION + " integer primary key autoincrement, "
                + KEY_IDVER + " integer,"
                + KEY_IDDATE + " text " + ")");

        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(DBHelper.KEY_IDVER, -1);
            db.insert(DBHelper.TABLE_VERSIONS, null, values);
            db.setTransactionSuccessful();
        }
        catch(Exception ex)
        { }
        finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSIONS);


        onCreate(db);
    }
}