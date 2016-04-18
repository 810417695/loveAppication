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
	TextView area_notip2; //û��ѡ��������ʾ1
	@ViewById
	TextView area_notip1;//û��ѡ��������ʾ2
	@ViewById
	TextView type_notip1;//û�����͵���ʾ1
	@ViewById
	TextView type_notip2;//û�����͵���ʾ2
	@ViewById
	TextView type_notip3;//û�����͵���ʾ3
	@ViewById
	FlowLayout fl_area; // ��ʾѡ�������
	@ViewById
	FlowLayout fl_type; // ��ʾѡ��ļ�������
	private MarginLayoutParams lp; // ��������Ĳ��ֲ���
	private String[] areaArray = {"�Ϻ�-�Ϻ�,","����-�Ͼ�,","����-�Ͼ���,","����-�Ͼ���,",
			"�Ϻ�-�Ϻ�,","����-�Ͼ�,","����-�Ͼ���,","����-�Ͼ���"};
	
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
		title.setText("���˼���");
		initData();
		setData();
		initListener();
	}

	private void initListener() {
		
	}

	private void initData() {
		
	}
}
