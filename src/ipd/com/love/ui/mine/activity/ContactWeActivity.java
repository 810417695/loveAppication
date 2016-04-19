package ipd.com.love.ui.mine.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;

@EActivity(R.layout.activity_mine_contact)
public class ContactWeActivity extends BaseActivity {

	@ViewById
	TextView title;
	
	@AfterViews
	public void init() {
		title.setText("联系我们");
	}

	//平台保障
	@Click(R.id.platform_support)
	public void publicProjecy(View view){
		
	}
	//投诉反馈
	@Click(R.id.complaint_feedback)
	public void publicTask(View view){
		
	}
	//帮助
	@Click(R.id.help)
	public void mineProduct(View view){
	
	}
}
