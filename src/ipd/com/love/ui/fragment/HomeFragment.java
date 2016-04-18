package ipd.com.love.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import ipd.com.love.R;
import ipd.com.love.global.LoveApplication;


public class HomeFragment extends Fragment implements OnClickListener{
	private LinearLayout ly_1;
	private LinearLayout ly_2;
	private RelativeLayout call_phone;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate( R.layout.fragment_home, null);
		initView(view);
		initData();
		initListener();
		return view;
	}

	private void initView(View view) {
		ly_1 = (LinearLayout) view.findViewById(R.id.ly_1);
		ly_2 = (LinearLayout) view.findViewById(R.id.ly_2);
		call_phone = (RelativeLayout) view.findViewById(R.id.call_phone);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LoveApplication.mScreenWidth/2, 1);
		ly_1.setLayoutParams(layoutParams);
		LayoutParams layoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LoveApplication.mScreenWidth/2, 1);
		ly_2.setLayoutParams(layoutParam);
		
	}
	private void initData() {
		
	}
	private void initListener() {
		call_phone.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.call_phone:
			Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"4006666666"));  
            startActivity(intent); 
			break;
		}
	}

}
