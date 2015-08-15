package com.jve.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import biz.source_code.base64Coder.Base64Coder;

public class FileUtils {
	/**
	 * 将图片流以字符串形式存储下来 QinChuan
	 * 
	 * @param bitmap
	 * @return
	 */
	public static String encodePicture(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 60, stream);
		byte[] b = stream.toByteArray();
		return new String(Base64Coder.encodeLines(b));
	}
	
	
	/*尽量不要使用setImageBitmap或setImageResource或BitmapFactory.decodeResource来设置一张大图，
	因为这些函数在完成decode后，最终都是通过java层的createBitmap来完成的，需要消耗更多内存。

	因此，改用先通过BitmapFactory.decodeStream方法，创建出一个bitmap，再将其设为ImageView的 source，
	decodeStream最大的秘密在于其直接调用JNI>>nativeDecodeAsset()来完成decode，
	无需再使用java层的createBitmap，从而节省了java层的空间。
	如果在读取时加上图片的Config参数，可以跟有效减少加载的内存，从而跟有效阻止抛out of Memory异常
	另外，decodeStream直接拿的图片来读取字节码了， 不会根据机器的各种分辨率来自动适应， 
	使用了decodeStream之后，需要在hdpi和mdpi，ldpi中配置相应的图片资源， 
	否则在不同分辨率机器上都是同样大小（像素点数量），显示出来的大小就不对了。*/
	/**
	 * 以最省内存的方式读取本地资源的图片(防止内存溢出)
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;//如果 inPurgeable 设为True的话表示使用BitmapFactory创建的Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
}
