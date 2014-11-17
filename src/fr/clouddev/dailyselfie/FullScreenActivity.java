package fr.clouddev.dailyselfie;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
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
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		Log.i(TAG,"onCreate");
		setContentView(R.layout.full_screen_activity);
		
		
		mBitmap = (ImageView)findViewById(R.id.selfie_bitmap);
		String selfieName = getIntent().getStringExtra(EXTRA_NAME);
		Log.i(TAG,"displaying fullscreen for selfie "+selfieName);
		String filePath = getIntent().getStringExtra(EXTRA_PATH);
		setTitle(selfieName);
		new LoadBitmapTask(this,mBitmap).execute(filePath);
		setProgressBarIndeterminateVisibility(true);
	}
	
	static class LoadBitmapTask extends AsyncTask<String, String, Bitmap> {

		private ImageView mImageView;
		private Activity mActivity;
		
		public LoadBitmapTask(Activity activity,ImageView imageView) {
			mImageView = imageView;
			mActivity = activity;
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			String selfiePath = params[0];
			
			return BitmapUtil.getBitmapFromFile(selfiePath);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			mImageView.setImageBitmap(result);
			mActivity.setProgressBarIndeterminateVisibility(false);
			super.onPostExecute(result);
		}
	}
}
