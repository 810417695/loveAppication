package ipd.com.love.ui.account;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.MainActivity_;
import ipd.com.love.utils.CommonUtils;
import ipd.com.love.utils.LoadingUtils;
import ipd.com.love.utils.ToastCommom;

@EActivity(R.layout.activity_rigster)
public class RegisterActivity extends BaseActivity {

	
	@ViewById
	EditText et_phone;  //手机号
	@ViewById
	EditText et_password;//密码
	@ViewById
	EditText et_scord;//验证码
	@ViewById
	TextView title;//标题
	
	@AfterViews
	public void init(){
		initTitle();
		initData();
		initListener();
	}

	private void initTitle() {
		title.setText("注册");
	}

	private void initData() {
		
	}

	private void initListener() {
		
	}
	
	
	@Click(R.id.register)
	public void register(View view){
		String phone = et_phone.getText().toString().trim();
		String password = et_password.getText().toString().trim();
//		if (TextUtils.isEmpty(phone)) {
//			ToastCommom.createToastConfig().ToastShow(this, "手机号不能为空");
//			return;
//		} else if (TextUtils.isEmpty(password)) {
//			ToastCommom.createToastConfig().ToastShow(this, "密码不能为空");
//			return;
//		} else if (!CommonUtils.isMobileNO(phone)) {
//			ToastCommom.createToastConfig().ToastShow(this, "手机号格式不正确");
//			return;
//		}
//		intent = new Intent(this,LoginActivity_.class);
//		startActivity(intent);
		ToastCommom.createToastConfig().ToastShow(this, "注册成功");
		intent = new Intent(this,PerfectDataActivity_.class);
//		intent.putExtra("phone", phone);
//		intent.putExtra("password", password);
//		setResult(101, intent);
		startActivity(intent);
//		finish();
		
	}
	@Click(R.id.get_scrode)  //获取验证码
	public void getScorde(View view){
		String phone = et_phone.getText().toString().trim();
		
		if (TextUtils.isEmpty(phone)) {
			ToastCommom.createToastConfig().ToastShow(this, "手机号不能为空");
			return;
		} 
		 else if (!CommonUtils.isMobileNO(phone)) {
			ToastCommom.createToastConfig().ToastShow(this, "手机号格式不正确");
			return;
		}
	}
	
	
}
