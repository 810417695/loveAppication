package ipd.com.love.ui.account;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.MainActivity_;
import ipd.com.love.utils.LoadingUtils;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

	
	@ViewById
	EditText et_phone;
	@ViewById
	EditText et_password;
	@ViewById
	TextView title;
	
	@AfterViews
	public void init(){
		title.setText("登录");
		initData();
		initListener();
	}

	private void initData() {
		
	}

	private void initListener() {
		
	}
	
	
	@Click(R.id.login)
	public void login(View view){
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
		LoadingUtils.show(this);
		intent = new Intent(this,MainActivity_.class);
		startActivity(intent);
		this.finish();
		LoadingUtils.dismiss();
		
	}
	@Click(R.id.regis)
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
		
		intent = new Intent(this,RegisterActivity_.class);
		startActivityForResult(intent, 100);
		
	}
	
	@Click(R.id.forget_pass)
	public void forgetPass(View view){
		intent = new Intent(this,ForgetPassActivity_.class);
		startActivityForResult(intent, 100);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 100 && resultCode == 101 ){
			et_phone.setText(data.getStringExtra("phone"));
			et_password.setText(data.getStringExtra("password"));
		}
	}
	
	
}
