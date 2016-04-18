package ipd.com.love.ui.account.addrole;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;

@EActivity(R.layout.add_person_contract)
public class InDividualContractActivity extends BaseActivity {

	
	@ViewById
	TextView title;

	@AfterViews
	public void init() {
		title.setText("个人发包");
		initData();
		initListener();
	}

	private void initListener() {
		// TODO Auto-generated method stub
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		
	}
	
}
