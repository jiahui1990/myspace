package com.scu.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {

	//创建一个ProgressDialog，这个没有屏蔽返回键
	public static ProgressDialog getProgressDialog(Context context,String msg) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setIndeterminate(true);
		progressDialog.setMessage(msg);
		progressDialog.setCancelable(true);
		return progressDialog;
	}
}
