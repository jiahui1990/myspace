package com.jve.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.jve.util.http.HttpUtils;
import com.seebaobei.R;

public class GetNetImageUtils extends AsyncTask<String, Void, Bitmap>{

	private Context context;
	
	public GetNetImageUtils(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = HttpUtils.getHttpBitmap(params[0]);
		if(bitmap == null){
			bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_launcher);
		}
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}
	
}
