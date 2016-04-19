package ipd.com.love.ui.account.addrole;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;

@EActivity(R.layout.activity_skill_type)
public class SkillTypeActivity extends BaseActivity {

	@ViewById
	TextView title;

	@AfterViews
	public void init() {
		title.setText("技能类型");
		initData();
		initListener();
	}

	private void initListener() {
		
	}

	private void initData() {
		
	}
}
