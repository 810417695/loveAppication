package ipd.com.love.ui.account;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.ui.account.addrole.AddPersonMechanicActivity_;

@EActivity(R.layout.activity_addrole)
public class AddRoleActivity extends BaseActivity {

	@ViewById
	TextView title;

	@AfterViews
	public void init() {
		title.setText("添加角色");
		initData();
		initListener();
	}

	private void initData() {

	}

	private void initListener() {

	}

	// 个人技工
	@Click(R.id.add_personal_mechanic)
	public void addPersonMechanic(View view) {
		intent = new Intent(this, AddPersonMechanicActivity_.class);
		startActivity(intent);
	}

	// 团队技工
	@Click(R.id.add_technician_team)
	public void addTechnicianTeam(View view) {

	}

	// 个人发包
	@Click(R.id.ad_individual_contract)
	public void adIndividualContract(View view) {

	}

	// 团队发包
	@Click(R.id.add_company_contract)
	public void addCompanyContract(View view) {

	}

}
