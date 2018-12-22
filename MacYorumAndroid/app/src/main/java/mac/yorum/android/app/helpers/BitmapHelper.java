package mac.yorum.android.app.helpers;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapHelper 
{	
	public static Bitmap getBitmapFromPath(String path)
	{
		Bitmap bitmap = null;
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inDither = false;
        bfOptions.inPurgeable = true;
        bfOptions.inInputShareable = true;             
        bfOptions.inTempStorage = new byte[32 * 1024]; 
 
        File file = new File(path);
        FileInputStream fs = null;
        try 
        {
            fs = new FileInputStream(file);
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
 
        try 
        {
            if(fs != null)
            {
                bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        { 
            if(fs != null) 
            {
                try 
                {
                    fs.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
 
        return bitmap;
	}
	
	public static String getBase64StringFromImage(ImageView imageView)
	{
		return Base64.encodeToString(getByteFromImage(imageView), Base64.DEFAULT);
	}
	
	public static byte[] getByteFromImage(ImageView imageView)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
		drawable.getBitmap().compress(CompressFormat.JPEG, 50, bos);
		byte[] result = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getBase64StringFromBitmap(Bitmap bitmap)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 50, bos);
		//String result = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
		byte[]arrayB=bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.encodeToString(arrayB, Base64.DEFAULT);
	}
	
	public static String getByteStringFromBitmap(Bitmap bitmap)
    {
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          bitmap.compress(CompressFormat.PNG, 50, bos);
          String result = bos.toByteArray().toString();
          try {
                bos.close();
          } catch (IOException e) {
                e.printStackTrace();
          }
          return result;
    }
	
	public static Bitmap getBitmapFromBase64String(String base64String)
	{
		if(!base64String.equals(""))
		{
			try
			{
				byte [] encodeByte = Base64.decode(base64String, Base64.URL_SAFE);
				Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
			    return bitmap;
			}
			catch(Exception ex)
			{
				return null;
			}
		}
		else return null;
	}
	
	public static Bitmap getBitmapFromByteString(String byteString)
	{
		if(!byteString.equals(""))
		{
			try
			{
				InputStream stream = new ByteArrayInputStream(Base64.decode(byteString.getBytes(), Base64.DEFAULT));
				Bitmap bitmap = BitmapFactory.decodeStream(stream);
			    return bitmap;
			}
			catch(Exception ex)
			{
				return null;
			}
		}
		else return null;
	}
}
