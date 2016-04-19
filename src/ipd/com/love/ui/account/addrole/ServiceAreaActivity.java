package ipd.com.love.ui.account.addrole;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;

@EActivity(R.layout.activity_service_area)
public class ServiceAreaActivity extends BaseActivity {

	@ViewById
	TextView title;

	@AfterViews
	public void init() {
		title.setText("·þÎñÇøÓò");
		initData();
		initListener();
	}

	private void initListener() {
		
	}

	private void initData() {
		
	}
}
