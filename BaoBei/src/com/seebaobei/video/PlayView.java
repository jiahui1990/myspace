package com.seebaobei.video;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import com.huamaitel.api.HMCallback;
import com.seebaobei.appmain.MainApp;

public class PlayView extends GLSurfaceView {
	private PlayRenderer playRenderer;
	private boolean isFirstIn = true;

	public PlayView(Context context, AttributeSet attrs) {
		super(context);

		// Create an OpenGL ES 2.0 context.
		setEGLContextClientVersion(2);

		// Set the Renderer for drawing on the GLSurfaceView
		playRenderer = new PlayRenderer();
		setRenderer(playRenderer);

		// Set render mode.
		setRenderMode(RENDERMODE_WHEN_DIRTY);

		// Register the OpenGL render callback.
		MainApp.getJni().setRenderCallback(new HMCallback.RenderCallback() {
			@Override
			public void onRequest() {
				requestRender(); // Force to render if video data comes.
			}
		});
	}

	// 这个接口定义了在一个OpenGL的GLSurfaceView中绘制图形所需要的方法。
	class PlayRenderer implements GLSurfaceView.Renderer {

		// 设置OpenGL的环境变量，或是初始化OpenGL的图形物体。
		public void onSurfaceChanged(GL10 gl, int w, int h) {
			MainApp.getJni().gLResize(w, h);
		}

		// 这个方法主要完成绘制图形的操作。
		public void onDrawFrame(GL10 gl) {
			if (isFirstIn) {
				isFirstIn = false;
				return;
			}

			MainApp.getJni().gLRender();
		}

		// 主要用来对GLSurfaceView容器的变化进行响应。
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			MainApp.getJni().gLInit();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		super.surfaceDestroyed(holder);
		MainApp.getJni().gLUninit();
	}
}
