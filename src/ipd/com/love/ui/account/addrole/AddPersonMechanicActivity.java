package ipd.com.love.ui.account.addrole;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseActivity;
import ipd.com.love.view.FlowLayout;

@EActivity(R.layout.add_person_mechanic)
public class AddPersonMechanicActivity extends BaseActivity {

	@ViewById
	TextView title;
	@ViewById
	TextView area_notip2; //没有选择区域提示1
	@ViewById
	TextView area_notip1;//没有选择区域提示2
	@ViewById
	TextView type_notip1;//没有类型的提示1
	@ViewById
	TextView type_notip2;//没有类型的提示2
	@ViewById
	TextView type_notip3;//没有类型的提示3
	@ViewById
	FlowLayout fl_area; // 显示选择的区域
	@ViewById
	FlowLayout fl_type; // 显示选择的技能类型
	private MarginLayoutParams lp; // 热搜组件的布局参数
	private String[] areaArray = {"上海-上海,","江苏-南京,","江苏-南京市,","江苏-南京京,",
			"上海-上海,","江苏-南京,","江苏-南京市,","江苏-南京京"};
	
	private void setData() {
		lp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		area_notip1.setVisibility(View.GONE);
		area_notip2.setVisibility(View.GONE);
		fl_area.setVisibility(View.VISIBLE);
		for (int i = 0; i < areaArray.length; i++) {
			TextView tv = new TextView(AddPersonMechanicActivity.this);
			final String hotWords = areaArray[i];
			tv.setText(hotWords);
			tv.setPadding(3, 3, 0, 3);
			tv.setLayoutParams(lp);
			fl_area.addView(tv);
		}
	}

	@AfterViews
	public void init() {
		title.setText("个人技工");
		initData();
		setData();
		initListener();
	}

	private void initListener() {
		
	}

	private void initData() {
		
	}
}
