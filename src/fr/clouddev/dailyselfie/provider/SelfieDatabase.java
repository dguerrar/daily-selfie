package fr.clouddev.dailyselfie.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SelfieDatabase extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "selfie_bdd";
	private static final int DATABASE_VERSION = 1; 
	
	private static final String CREATE_STATEMENT = 
			"CREATE TABLE "+SelfieContract.SELFIE_TABLE_NAME+ " ("+
			SelfieContract.SELFIE_COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			SelfieContract.SELFIE_COLUMN_NAME+ " TEXT, "+
			SelfieContract.SELFIE_COLUMN_PATH+ " TEXT, "+
			SelfieContract.SELFIE_COLUMN_THUMB+ " TEXT" +
			")";
			
	
	
	public SelfieDatabase(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_STATEMENT);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+SelfieContract.SELFIE_TABLE_NAME);
		onCreate(db);
	}

}
