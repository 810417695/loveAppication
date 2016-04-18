package ipd.com.love.ui.mine.activity;

import java.io.File;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.jumpbox.jumpboxlibrary.bitmap.BitmapUtils;
import com.jumpbox.jumpboxlibrary.utils.MyBitmapUtils;
import com.jumpbox.jumpboxlibrary.view.CircleImageView;
import com.jumpbox.jumpboxlibrary.wheelview.PopupUtils;
import com.jumpbox.jumpboxlibrary.wheelview.PopupUtils.onSelectFinishListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.CropPhotoActivity_;
import ipd.com.love.utils.MySelfSheetDialog;
import ipd.com.love.utils.MySelfSheetDialog.OnSheetItemClickListener;
import ipd.com.love.utils.MySelfSheetDialog.SheetItemColor;

@EActivity(R.layout.activity_mine_data)
public class MineDateActivity extends BaseActivity {

	@ViewById
	TextView title;
	@ViewById
	TextView show_name;
	@ViewById
	TextView show_data;
	@ViewById
	TextView show_address;
	@ViewById
	CircleImageView head_image; // 
	
	public static final int PHOTOZOOM = 0;
	public static final int PHOTOTAKE = 1;
	public static final int IMAGE_COMPLETE = 2; // 
	public static final int CROPREQCODE = 3; // 
	private String photoSaveName, photoSavePath = Environment.getExternalStorageDirectory() + "/";
	@AfterViews
	public void init() {
		title.setText("个人资料");
		initData();
		initListener();
	}

	private void initListener() {
		
	}

	private void initData() {
		
	}
	  
	@Click(R.id.data_name)
	public void mineName(View view){
		intent = new Intent(this, MineNickName_.class);
		String name = show_name.getText().toString().trim();
		intent.putExtra("name", name);
		startActivityForResult(intent, 100);
	}
	@Click(R.id.mine_data)
	public void mineData(View view){
		PopupUtils.showPopwindow(this, PopupUtils.getDataPick2(this, new onSelectFinishListener() {

			@Override
			public void onSelectFinish(String startDate, String startTime, String endDate, String endTime) {
			}

			@Override
			public void onSelectFinish(final String year, String month, String day) {
				show_data.setText(year+"年"+month+"月"+day+"日");
			}
		}));
	
		
	}
	@Click(R.id.mine_address)
	public void mineAddress(View view){
		
	}
	@Click(R.id.mine_head) 
	public void mineHeadImage(View view){
		new MySelfSheetDialog(this).builder().addSheetItem("拍照", SheetItemColor.Black, new OnSheetItemClickListener() {

			@Override
			public void onClick(int which) {
				photoSaveName = String.valueOf(System.currentTimeMillis()) + ".png";
				Uri imageUri = null;
				Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				imageUri = Uri.fromFile(new File(photoSavePath, photoSaveName));
				openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(openCameraIntent, PHOTOTAKE);
			}
		}).addSheetItem("从相册选取", SheetItemColor.Black, new OnSheetItemClickListener() {

			@Override
			public void onClick(int which) {
				Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
				openAlbumIntent.setType("image/*");
				startActivityForResult(openAlbumIntent, PHOTOZOOM);
			}
		}).show();

	
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(requestCode == 100 && resultCode == 200){
			show_name.setText(intent.getStringExtra("name"));
		}
		if (resultCode == RESULT_OK) {
			String path;
			switch (requestCode) {
			case PHOTOZOOM:// 相册
				if (intent == null) {
					return;
				}
				path = BitmapUtils.getInstance().getPath(this, intent.getData());
				intent = new Intent(this, CropPhotoActivity_.class);
				intent.putExtra("path", path);
				startActivityForResult(intent, IMAGE_COMPLETE);

				break;
			case PHOTOTAKE:// 拍照
				path = photoSavePath + photoSaveName;
				intent = new Intent(this, CropPhotoActivity_.class);
				intent.putExtra("path", path);
				startActivityForResult(intent, IMAGE_COMPLETE);

				break;
			// 截取返回的圆形图片
			case IMAGE_COMPLETE:// 完成
				final String temppath = intent.getStringExtra("path");
				Bitmap picBitmap = MyBitmapUtils.getimage(MineDateActivity.this, temppath);
				head_image.setImageBitmap(picBitmap);
		}
		}
	}	
}
