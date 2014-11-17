package fr.clouddev.dailyselfie;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * A class for handling the bitmap operations
 * Most of this code comes from android developer
 * or Adam Porter labs
 * 
 * @author CopyCat
 *
 */
public class BitmapUtil {
	public static final String APP_DIR = "DailySelfie/Selfies";
	public static String mBitmapStoragePath;
	
	/**
	 * Creates and set the application storage path for the selfies
	 * Source comes from Adam Porter ContentProviderLab
	 * @param context the context of the application
	 */
	public static void initStoragePath(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				String root = context.getExternalFilesDir(null)
						.getCanonicalPath();
				if (null != root) {
					File bitmapStorageDir = new File(root, BitmapUtil.APP_DIR);
					bitmapStorageDir.mkdirs();
					mBitmapStoragePath = bitmapStorageDir.getCanonicalPath();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Read a bitmap from a file
	 * @param filePath the full path of the bitmap
	 * @return the decoded bitmap in full size
	 */
	public static Bitmap getBitmapFromFile(String filePath) {
		return BitmapFactory.decodeFile(filePath);
	}

	/**
	 * Saves a bitmap to a file
	 * Source comes from Adam Porter ContentProviderLab
	 * @param bitmap the bitmap to save
	 * @param filePath the full path of the file
	 * @return true if save is successful, false otherwise
	 */
	public static boolean storeBitmapToFile(Bitmap bitmap, String filePath) {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			try {

				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(filePath));
				bitmap.compress(CompressFormat.PNG, 100, bos);
				bos.flush();
				bos.close();
			} catch (FileNotFoundException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a path for storing the image thumbnail based on full sized picture
	 * Basically the output is </full/path/to/image/filename>_thumb.jpg
	 * @param imagePath the path to the full sized bitmap (/full/path/to/image/filename.jpg)
	 * @return the full path to the thumb bitmap
	 */
	public static String getThumbPath(String imagePath) {
		File image = new File(imagePath);
		String path = image.getAbsolutePath();
		
		String fileWithoutExt = image.getName().split("\\.")[0];
		String newPath = path+fileWithoutExt+"_thumb.jpg";
		return newPath;
	}
	
	/**
	 * Creates a temporary empty image file in the selfie folder
	 * It is used for the camera intent
	 * Source comes mainly from Android Developer
	 * @return the filename
	 * @throws IOException
	 */
	public static File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	    String imageFileName = "selfie_" + timeStamp;
	    
	    File image = new File(mBitmapStoragePath+"/"+imageFileName+".jpg");
	    image.createNewFile();
	        
	    return image;
	}
}
