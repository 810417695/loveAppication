package ipd.com.love.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {
	public Intent intent;
	public int currentIndent = 0;
	public int type = 0; 

	public OnViewInitedListener listener;

	public void back(View v) {
		getActivity().finish();
	}

	public void setPage(int page) {
		currentIndent = page;
		type = page;
	}

	public void setViewInitedListener(OnViewInitedListener listener) {

	}

	public interface OnViewInitedListener {
		public void onInited();
	}
}
