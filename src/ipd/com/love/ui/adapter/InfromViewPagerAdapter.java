package ipd.com.love.ui.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class InfromViewPagerAdapter extends PagerAdapter {

	private List<View> mList;
	public InfromViewPagerAdapter(List<View> mList) {
		this.mList = mList;
	}
	
	@Override
	public int getCount() {
		return mList == null ? 0:mList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mList.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mList.get(position));
		return mList.get(position);
	}

	
}
