package fr.clouddev.dailyselfie.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * The content provider class
 * a full copypasta of Adam Porter's ContentProvider Lab
 * @author CopyCat
 *
 */
public class SelfieContentProvider extends ContentProvider {
	
	@SuppressWarnings("unused")
	private static final String TAG = "SelfieContentProvider";
	
	private SelfieDatabase mHelper;
	 

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		//Not implemented
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		//Not Implemented
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = mHelper.getWritableDatabase().insert(
				SelfieContract.SELFIE_TABLE_NAME, "", values);
		if (rowID > 0) {
			Uri fullUri = ContentUris.withAppendedId(
					SelfieContract.SELFIE_URI, rowID);
			getContext().getContentResolver().notifyChange(fullUri, null);
			return fullUri;
		}
		throw new SQLException("Failed to add record into" + uri);
	}

	@Override
	public boolean onCreate() {
		mHelper = new SelfieDatabase(getContext());
		return mHelper != null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(SelfieContract.SELFIE_TABLE_NAME);

		Cursor cursor = qb.query(mHelper.getWritableDatabase(), projection, selection,
				selectionArgs, null, null, sortOrder);

		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		//Not Implemented
		return 0;
	}

}
