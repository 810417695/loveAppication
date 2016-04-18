package ipd.com.love.ui.mine.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.utils.ToastCommom;

@EActivity(R.layout.activity_nickname)
public class MineNickName extends BaseActivity {

	@ViewById
	TextView title;
	@ViewById
	EditText et_nickname;
	@ViewById
	TextView tv_right;

	@AfterViews
	public void init() {
		title.setText("姓名");
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setText("保存");
		String nickName = getIntent().getStringExtra("name");
		et_nickname.setText(nickName);
		initListener();
	}


	private void initListener() {
		tv_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = et_nickname.getText().toString().trim();
				if (name.equals("")) {
					ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "请，您还没有输入姓名呢!");
				} else {
					intent = new Intent();
					intent.putExtra("name", name);
					setResult(200, intent);
					finish();
//					changeNickname(name);
				}
			}
		});
	}


	protected void changeNickname(final String nickname) {
		
	}

}
