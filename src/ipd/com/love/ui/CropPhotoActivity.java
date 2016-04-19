package ipd.com.love.ui;

import java.io.File;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.jumpbox.jumpboxlibrary.bitmap.BitmapUtils;
import com.jumpbox.jumpboxlibrary.utils.MyBitmapUtils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.global.GlobalParams;
import ipd.com.love.utils.LoadingUtils;
import ipd.com.love.utils.ToastUtils;
import ipd.com.love.view.CropImageView;

@EActivity(R.layout.activity_crop_photo)
public class CropPhotoActivity extends BaseActivity {
	@ViewById
	TextView title;
	@ViewById
	TextView tv_right;
	@ViewById
	LinearLayout ll_parent;
	@ViewById
	CropImageView cropimage;
	
	@AfterViews
	public void init() {
		title.setText("≤√ºÙ");
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setText("±£¥Ê");
		initData();
		initListener();

	}

	@SuppressWarnings("deprecation")
	private void initData() {
		String path = getIntent().getStringExtra("path");
		try {
			cropimage.setDrawable(new BitmapDrawable(BitmapUtils.getInstance().reSizeBitmap(this, new File(path))), 200,
					(int) getResources().getDimension(R.dimen.plot_img_height));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String SDPATH = Environment.getExternalStorageDirectory() + "/";
	@Click(R.id.tv_right)
	public void save(View v) {
		if (Environment.getExternalStorageState() == Environment.MEDIA_UNMOUNTED) {
			ToastUtils.show(this, "Œ¥’“µΩSDø®");
			return;
		}
		LoadingUtils.show(this);
		String path = GlobalParams.ROOT_PATH + "/" + System.currentTimeMillis() + ".png";
		File file = new File(GlobalParams.ROOT_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		Bitmap mBitmap = cropimage.getCropImage();
		try {
			MyBitmapUtils.savePhotoToSDCard(mBitmap, path);
			intent = new Intent();
			intent.putExtra("path", path);
			setResult(RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LoadingUtils.dismiss();
		}
	}

	private void initListener() {

	}
}
