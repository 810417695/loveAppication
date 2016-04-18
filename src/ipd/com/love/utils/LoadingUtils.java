package ipd.com.love.utils;

import android.content.Context;
import ipd.com.love.R;
import ipd.com.love.global.LoveApplication;

public class LoadingUtils {
	private static LoadingDialog dialog;

	public static void show(Context context) {
		dialog = new LoadingDialog(context, R.layout.view_tips_loading);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();

	}

	public static void dismiss() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				if (dialog != null && dialog.isShowing()) {
//					dialog.dismiss();
//				}
//
//			}
//		}).start();
		
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}

	}

}
