package ipd.com.love.ui.account;

import java.lang.annotation.Annotation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.lidroid.xutils.view.annotation.event.OnChildClick;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.MainActivity_;
import ipd.com.love.utils.CommonUtils;
import ipd.com.love.utils.LoadingUtils;
import ipd.com.love.utils.ToastCommom;

@EActivity(R.layout.activity_perfect)
public class PerfectDataActivity extends BaseActivity {

	
	@ViewById
	EditText et_phone;  //�ֻ���
	@ViewById
	EditText et_password;//����
	@ViewById
	EditText et_scord;//��֤��
	@ViewById
	TextView title;//����
	@ViewById
	TextView tv_right;//�Ժ�����
	
	@AfterViews
	public void init(){
		initTitle();
		initData();
		initListener();
	}

	private void initTitle() {
		title.setText("ע��");
		tv_right.setVisibility(View.VISIBLE);
	}

	private void initData() {
		
	}

	private void initListener() {
		tv_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(PerfectDataActivity.this, MainActivity_.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	
	@Click(R.id.tv_perfect)
	public void prefect(View view){
		intent = new Intent(this, AddRoleActivity_.class);
		startActivity(intent);
	}
	
}
