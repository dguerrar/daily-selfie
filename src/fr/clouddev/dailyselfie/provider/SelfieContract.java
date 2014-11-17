package fr.clouddev.dailyselfie.provider;

import android.net.Uri;

public class SelfieContract {
	
	public static final String AUTHORITY = "fr.clouddev.dailyselfie.provider";
	public static final Uri BASE_URI = Uri
			.parse("content://" + AUTHORITY + "/");
	
	public static final String SELFIE_TABLE_NAME = "seflie";

	// The URI for this table.
	public static final Uri SELFIE_URI = Uri.withAppendedPath(BASE_URI, SELFIE_TABLE_NAME);
	
	
	//Columns for the selfie table
	public static final String SELFIE_COLUMN_ID = "_id";
	public static final String SELFIE_COLUMN_NAME = "name";
	public static final String SELFIE_COLUMN_PATH = "path";
	public static final String SELFIE_COLUMN_THUMB = "thumb";

}
