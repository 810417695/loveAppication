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
	EditText et_phone;  //�ֻ���
	@ViewById
	EditText et_password;//����
	@ViewById
	EditText et_scord;//��֤��
	@ViewById
	TextView title;//����
	
	@AfterViews
	public void init(){
		initTitle();
		initData();
		initListener();
	}

	private void initTitle() {
		title.setText("ע��");
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
//			ToastCommom.createToastConfig().ToastShow(this, "�ֻ��Ų���Ϊ��");
//			return;
//		} else if (TextUtils.isEmpty(password)) {
//			ToastCommom.createToastConfig().ToastShow(this, "���벻��Ϊ��");
//			return;
//		} else if (!CommonUtils.isMobileNO(phone)) {
//			ToastCommom.createToastConfig().ToastShow(this, "�ֻ��Ÿ�ʽ����ȷ");
//			return;
//		}
//		intent = new Intent(this,LoginActivity_.class);
//		startActivity(intent);
		ToastCommom.createToastConfig().ToastShow(this, "ע��ɹ�");
		intent = new Intent(this,PerfectDataActivity_.class);
//		intent.putExtra("phone", phone);
//		intent.putExtra("password", password);
//		setResult(101, intent);
		startActivity(intent);
//		finish();
		
	}
	@Click(R.id.get_scrode)  //��ȡ��֤��
	public void getScorde(View view){
		String phone = et_phone.getText().toString().trim();
		
		if (TextUtils.isEmpty(phone)) {
			ToastCommom.createToastConfig().ToastShow(this, "�ֻ��Ų���Ϊ��");
			return;
		} 
		 else if (!CommonUtils.isMobileNO(phone)) {
			ToastCommom.createToastConfig().ToastShow(this, "�ֻ��Ÿ�ʽ����ȷ");
			return;
		}
	}
	
	
}
