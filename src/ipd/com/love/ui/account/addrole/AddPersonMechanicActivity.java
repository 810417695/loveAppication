package ipd.com.love.ui.account.addrole;

import java.io.File;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.jumpbox.jumpboxlibrary.bitmap.BitmapUtils;
import com.jumpbox.jumpboxlibrary.utils.MyBitmapUtils;
import com.jumpbox.jumpboxlibrary.view.CircleImageView;
import com.jumpbox.jumpboxlibrary.wheelview.PopupUtils;
import com.jumpbox.jumpboxlibrary.wheelview.PopupUtils.OnFinishListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.CropPhotoActivity_;
import ipd.com.love.utils.DialogUtils;
import ipd.com.love.utils.DialogUtils.onMyDialogClickListener;
import ipd.com.love.utils.MySelfSheetDialog;
import ipd.com.love.utils.MySelfSheetDialog.OnSheetItemClickListener;
import ipd.com.love.utils.MySelfSheetDialog.SheetItemColor;
import ipd.com.love.utils.ToastUtils;
import ipd.com.love.view.FlowLayout;
import kankan.wheel.widget.WheelView;

@EActivity(R.layout.add_person_mechanic)
public class AddPersonMechanicActivity extends BaseActivity implements android.widget.CompoundButton.OnCheckedChangeListener{

	public static final int TAG_AREA = 5;
	public static final int TAG_SKILLTYPE = 6;
	@ViewById
	TextView title;
	 //标签选择
    @ViewById
    RadioGroup vg_select_label;
	@ViewById
	CircleImageView head_image; // 
	@ViewById
	TextView head_tip;
	@ViewById
	TextView area_notip2; //没有选择区域提示1
	@ViewById
	TextView area_notip1;//没有选择区域提示2
	@ViewById
	TextView type_notip1;//没有类型的提示1
	@ViewById
	TextView type_notip2;//没有类型的提示2
	@ViewById
	TextView type_notip3;//没有类型的提示3
	@ViewById
	FlowLayout fl_area; // 显示选择的区域
	@ViewById
	FlowLayout fl_type; // 显示选择的技能类型
	private MarginLayoutParams lp; // 热搜组件的布局参数
	private String[] areaArray = {"上海-上海,","江苏-南京,","江苏-南京市,","江苏-南京京,",
			"上海-上海,","江苏-南京,","江苏-南京市,","江苏-南京京"};
	
	private void setData() {
		lp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		area_notip1.setVisibility(View.GONE);
		area_notip2.setVisibility(View.GONE);
		fl_area.setVisibility(View.VISIBLE);
		for (int i = 0; i < areaArray.length; i++) {
			TextView tv = new TextView(AddPersonMechanicActivity.this);
			final String hotWords = areaArray[i];
			tv.setText(hotWords);
			tv.setPadding(3, 3, 0, 3);
			tv.setLayoutParams(lp);
			fl_area.addView(tv);
		}
	}

	@AfterViews
	public void init() {
		title.setText("个人技工");
		initData();
		setData();
		initListener();
	}

	private void initListener() {
		cb1.setOnCheckedChangeListener(this);
		cb2.setOnCheckedChangeListener(this);
		cb3.setOnCheckedChangeListener(this);
		cb4.setOnCheckedChangeListener(this);
		cb5.setOnCheckedChangeListener(this);
		cb6.setOnCheckedChangeListener(this);
		cb7.setOnCheckedChangeListener(this);
		cb8.setOnCheckedChangeListener(this);
		cb9.setOnCheckedChangeListener(this);
		cb10.setOnCheckedChangeListener(this);
	}

	private void initData() {
		
	}
	public static final int PHOTOZOOM = 0;
	public static final int PHOTOTAKE = 1;
	public static final int IMAGE_COMPLETE = 2; // 
	public static final int CROPREQCODE = 3; // 
	private String photoSaveName, photoSavePath = Environment.getExternalStorageDirectory() + "/";
	//上传头像
	@Click(R.id.mine_data_headimage)
	public void headImage(View v){
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
    	if (resultCode == RESULT_OK) {//照相返回的条件
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
				Bitmap picBitmap = MyBitmapUtils.getimage(AddPersonMechanicActivity.this, temppath);
				head_image.setImageBitmap(picBitmap);
				head_tip.setVisibility(View.INVISIBLE);
			}
		}
    }
    @ViewById
    LinearLayout ll_parent;
    //加入
    @Click(R.id.join)
    public void join(View v){
		DialogUtils.showViewAtCenter(this,
				DialogUtils.getDialog(this, "您的申请已经提交", "是否申请平台保障提高接单率？", "稍后申请", "我想了解", new onMyDialogClickListener() {
					@Override
					public void onCommit() {
						
					}
					@Override
					public void onCancel() {

					}
				}), getWindow(), ll_parent);
	
    }
    //服务区域
    @Click(R.id.service_area)
    public void serviceArea(View v){
    	intent = new Intent(this, ServiceAreaActivity_.class);
    	startActivityForResult(intent, TAG_AREA);
    }
    //技能类型
    @Click(R.id.skill_type)
    public void skillType(View v){
    	intent = new Intent(this, SkillTypeActivity_.class);
    	startActivityForResult(intent, TAG_SKILLTYPE);
    }
    @ViewById
    TextView show_working_year;
    String[] workArray= {"1-3年","3-5年","5-8年","8年以上"};
    //工龄
    @Click(R.id.working_years)
    public void workingYear(View v){
    	PopupUtils.showView(this, workArray, new OnFinishListener() {
			@Override
			public void onFinish(String country, String city, String ccity) {
			}
			
			@Override
			public void onFinish(WheelView view) {
				show_working_year.setText(workArray[view.getCurrentItem()]);
			}
		});
    }

    @ViewById
    CheckBox cb1;
    @ViewById
    CheckBox cb2;
    @ViewById
    CheckBox cb3;
    @ViewById
    CheckBox cb4;
    @ViewById
    CheckBox cb5;
    @ViewById
    CheckBox cb6;
    @ViewById
    CheckBox cb7;
    @ViewById
    CheckBox cb8;
    @ViewById
    CheckBox cb9;
    @ViewById
    CheckBox cb10;
    private int total = 0;
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		if(isChecked){
			if(total>4){
				ToastUtils.show(this, "最多选择5个哦！亲");
				buttonView.setChecked(!isChecked);
			}else{
				total++;
			}
		}else{
			total--;
		}
	}
    
}
