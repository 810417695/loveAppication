package ipd.com.love.global;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class LoveApplication extends Application {
	
	//去的屏幕的宽和高
	public static int mScreenWidth, mScreenHeight;
	public static Context context;
	@Override
	public void onCreate() {
		super.onCreate();
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		mScreenHeight = display.getHeight();
		mScreenWidth = display.getWidth();
		context = this;
	}

	
}
