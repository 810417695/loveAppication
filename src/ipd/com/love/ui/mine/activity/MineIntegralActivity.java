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
import android.view.View;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.CropPhotoActivity_;
import ipd.com.love.utils.MySelfSheetDialog;
import ipd.com.love.utils.MySelfSheetDialog.OnSheetItemClickListener;
import ipd.com.love.utils.MySelfSheetDialog.SheetItemColor;

@EActivity(R.layout.activity_mine_integral)
public class MineIntegralActivity extends BaseActivity {

	@ViewById
	TextView title;
	
	@AfterViews
	public void init() {
		title.setText("我的积分");
	}

	@Click(R.id.public_projecy)
	public void publicProjecy(View view){
		
	}
	@Click(R.id.public_task)
	public void publicTask(View view){
		
	}
	@Click(R.id.mine_product)
	public void mineProduct(View view){
	
	}
	@Click(R.id.publich_infromation) 
	public void publicInfromation(View view){
		
	}
}
