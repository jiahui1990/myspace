/**
 * ��ʾ�� ��
 * ��������
 * ����
 */
package com.scu.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	public static void showShort(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

	public static void showShort(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}
	
	
}
