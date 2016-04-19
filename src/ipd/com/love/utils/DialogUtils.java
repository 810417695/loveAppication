package ipd.com.love.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import ipd.com.love.R;
import ipd.com.love.global.LoveApplication;

public class DialogUtils {
	public static void writeDialog(Context context, String title, String commitMsg, String cancelMsg,
			final onDialogCommitClickListener listener) {
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		View dialogView = View.inflate(context, R.layout.dialog_write, null);
		TextView tv_title = (TextView) dialogView.findViewById(R.id.tv_title);
		final EditText et_content = (EditText) dialogView.findViewById(R.id.et_content);
		TextView tv_commit = (TextView) dialogView.findViewById(R.id.tv_commit);
		TextView tv_cancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
		tv_title.setText(title);
		tv_commit.setText(commitMsg);
		tv_cancel.setText(cancelMsg);

		dialog.setContentView(dialogView);
		dialog.show();

		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int) (LoveApplication.mScreenWidth / 4 * 3); // 设置宽度
		dialog.getWindow().setAttributes(lp);
		tv_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(dialog, et_content.getText().toString().trim());
				}
			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	public static View getWriteDialog(final Context context, String title, String commitMsg, String cancelMsg,
			final onDialogCommitClickListener listener) {
		View dialogView = View.inflate(context, R.layout.dialog_write, null);
		TextView tv_title = (TextView) dialogView.findViewById(R.id.tv_title);
		final EditText et_content = (EditText) dialogView.findViewById(R.id.et_content);
		TextView tv_commit = (TextView) dialogView.findViewById(R.id.tv_commit);
		TextView tv_cancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
		tv_title.setText(title);
		tv_commit.setText(commitMsg);
		tv_cancel.setText(cancelMsg);

		tv_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(et_content.getText().toString().trim())) {
					ToastCommom.createToastConfig().ToastShow(LoveApplication.context, "内容不能为空");
					return;
				}

				if (dialogPopup != null) {
					dialogPopup.dismiss();
				}

				if (listener != null) {
					listener.onClick(null, et_content.getText().toString().trim());
				}
			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (dialogPopup != null) {
					dialogPopup.dismiss();
				}
			}
		});
		return dialogView;
	}

	public static View getMessage(Context context, String msg) {
		return getMessage(context, msg, null);
	}

	public static View getMessage(Context context, String msg, OnClickListener listener) {
		View view = View.inflate(context, R.layout.toast_dialog, null);
		TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
		RelativeLayout rl_parent = (RelativeLayout) view.findViewById(R.id.rl_parent);
		tv_message.setText(msg);
		rl_parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (dialogPopup != null) {
					dialogPopup.dismiss();
				}
			}
		});
		if (listener != null) {
			tv_message.setOnClickListener(listener);
		}
		return view;

	}
	public static View getMessage2(Context context, String msg, final OnClickListener listener) {
		View view = View.inflate(context, R.layout.my_dialog2, null);
		final TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
		final TextView tv_commit = (TextView) view.findViewById(R.id.tv_commit);
		tv_message.setText(msg);
		tv_commit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (dialogPopup != null) {
					dialogPopup.dismiss();
				}
				if (listener != null) {
					listener.onClick(v);
				}
			}
		});
		
		return view;
		
	}

	

	private static PopupWindow dialogPopup;
	private static PopupWindow popup;




	public static void showView(Context context, View view, final Window window, View parent) {
		popup = new PopupWindow(context);
		popup.setHeight(LayoutParams.WRAP_CONTENT);
		popup.setWidth(LayoutParams.MATCH_PARENT);
		popup.setContentView(view);
		popup.setBackgroundDrawable(new BitmapDrawable());
		popup.setFocusable(true);
		backgroundAlpha(window, 0.5f);
		popup.showAsDropDown(parent);
		popup.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				backgroundAlpha(window, 1f);
			}
		});
	}

	public static PopupWindow showViewAtCenter(Context context, View view, final Window window, View parent) {
		return showViewAtCenter(context, view, window, parent, null);
	}

	public static PopupWindow showViewAtCenter(Context context, View view, final Window window, View parent,
			final OnMessageDismissListener listener) {
		dialogPopup = new PopupWindow(context);
		dialogPopup.setHeight(LayoutParams.WRAP_CONTENT);
		dialogPopup.setWidth(LayoutParams.MATCH_PARENT);
		dialogPopup.setContentView(view);
		dialogPopup.setBackgroundDrawable(new BitmapDrawable());
		dialogPopup.setFocusable(true);
		backgroundAlpha(window, 0.5f);
		dialogPopup.showAtLocation(parent, Gravity.CENTER, 0, 0);
		dialogPopup.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				backgroundAlpha(window, 1f);
				if (listener != null) {
					listener.onDismiss();
				}
			}
		});
		return dialogPopup;
	}

	public static View getDialog(Context context, String title, String msg, String commit, String cancel,
			final onMyDialogClickListener listener) {
		View view = View.inflate(context, R.layout.my_dialog, null);
		TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
		TextView tv_commit = (TextView) view.findViewById(R.id.tv_commit);
		TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
		TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
		if (TextUtils.isEmpty(title)) {
			tv_title.setVisibility(View.GONE);
		} else {
			tv_title.setText(title);
		}
		tv_message.setText(msg);
		tv_commit.setText(commit);
		tv_cancel.setText(cancel);
		tv_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (dialogPopup != null) {
					dialogPopup.dismiss();
				}
				if (listener != null) {
					listener.onCommit();
				}
			}
		});
		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (dialogPopup != null) {
					dialogPopup.dismiss();
				}
				if (listener != null) {
					listener.onCancel();
				}
			}
		});

		return view;

	}

	

	public static void backgroundAlpha(Window window, float alpha) {
		LayoutParams params = window.getAttributes();
		params.alpha = alpha;
		window.setAttributes(params);
	}

	public interface onDialogCommitClickListener {
		public void onClick(Dialog dialog, String content);
	}

	public void backgroundAlpha(Context context, float bgAlpha) {

	}

	public interface onMyDialogClickListener {
		public void onCommit();

		public void onCancel();

	}

	public interface OnMessageDismissListener {
		public void onDismiss();

	}

}
