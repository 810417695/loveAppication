package ipd.com.love.ui.fragment;

import org.androidannotations.annotations.EFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ipd.com.love.R;

/**
 * ’“π§»À
 * @author Administrator
 *
 */
@EFragment(R.layout.fragment_person)
public class PersonFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate( R.layout.fragment_person, null);
		initView(view);
		initData();
		initListener();
		return view;
	}
	private void initView(View view) {
		
	}
	private void initData() {
		
	}
	private void initListener() {
		
	}

}
