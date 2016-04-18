package ipd.com.love.base;

import com.jumpbox.jumpboxlibrary.progresslayout.ShapeLoadingDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

public class BaseActivity extends Activity {
	public Intent intent;
	public int page = 0;

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
	}

	public void back(View v) {
		finish();
	}

	ShapeLoadingDialog loadingDialog;

	public void showDialog(String msg) {
		if (loadingDialog == null) {
			loadingDialog = new ShapeLoadingDialog(this);
			loadingDialog.setCanceledOnTouchOutside(false);
			loadingDialog.setOnkeyDownListener(new OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						loadingDialog.dismiss();
						finish();
					}
					return false;
				}
			});
		}
		if (TextUtils.isEmpty(msg)) {
			loadingDialog.setLoadingText("Мгдижа...");
		} else {
			loadingDialog.setLoadingText(msg);
		}

		loadingDialog.show();
	}

	public void DialogDismiss() {
		if (loadingDialog != null) {
			loadingDialog.dismiss();
		}
	}

}
