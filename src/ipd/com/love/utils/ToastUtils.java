package ipd.com.love.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	private static Toast mToast;
	private static long oneTime;
	private static long twoTime;
	private static String oldMsg = "";

	public static void show(Context context, String msg) {
		// if (mToast == null) {
		// mToast = mToast.makeText(context, msg, mToast.LENGTH_SHORT);
		// mToast.show();
		// oneTime = System.currentTimeMillis();
		// } else {
		// twoTime = System.currentTimeMillis();
		// if (msg.equals(oldMsg)) {
		// if (twoTime - oneTime > mToast.LENGTH_SHORT) {
		// mToast.show();
		// }
		// } else {
		// oldMsg = msg;
		// mToast.setText(msg);
		// mToast.show();
		// }
		// }
		// oneTime = twoTime;
		ToastCommom.createToastConfig().ToastShow(context, msg);
	}
}
