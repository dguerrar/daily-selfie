package fr.clouddev.dailyselfie;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * The activity for displaying the full sized selfie
 * @author CopyCat
 *
 */
public class FullScreenActivity extends Activity {
	
	private static final String TAG = "FullScreenActivity";
	
	public static final String EXTRA_NAME = "name";
	public static final String EXTRA_PATH = "path";
	
	private ImageView mBitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate");
		setContentView(R.layout.full_screen_activity);
		
		mBitmap = (ImageView)findViewById(R.id.selfie_bitmap);
		String selfieName = getIntent().getStringExtra(EXTRA_NAME);
		Log.i(TAG,"displaying fullscreen for selfie "+selfieName);
		String filePath = getIntent().getStringExtra(EXTRA_PATH);
		setTitle(selfieName);
		mBitmap.setImageBitmap(BitmapUtil.getBitmapFromFile(filePath));
		
	}
}
