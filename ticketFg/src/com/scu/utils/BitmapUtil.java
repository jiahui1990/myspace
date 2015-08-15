package com.scu.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class BitmapUtil
{

	public BitmapUtil()
	{
		// TODO Auto-generated constructor stub
	}

	public static boolean saveBitmap2file(Bitmap bmp, String filepath, String filename)
	{
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 50;
		OutputStream stream = null;
		try
		{
			stream = new FileOutputStream(filepath + "/" + filename);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bmp.compress(format, quality, stream);
	}
}
