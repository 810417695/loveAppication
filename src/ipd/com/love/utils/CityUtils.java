package ipd.com.love.utils;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.bean.CityBean;
import ipd.com.love.dao.CityDao;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

public class CityUtils implements OnWheelChangedListener {
	private static CityUtils cityUtils;

	public static CityUtils getInstance() {
		if (cityUtils == null) {
			synchronized (CityUtils.class) {
				if (cityUtils == null) {
					cityUtils = new CityUtils();
				}
			}
		}

		return cityUtils;
	}

	private WheelView mViewProvince;
	private WheelView mViewCity;
	private TextView mBtnConfirm;
	private Context context;
	private CityDao cityDao;
	private CityWheelAdapter provinceAdapter;
	private Dialog cityDialog;

	public void showSelectDialog(final Context context, final onSelectCityFinishListener listener) {
		cityDao = new CityDao(context);
		this.context = context;
		View view = View.inflate(context, R.layout.city_choose_dialog2, null);
		mViewProvince = (WheelView) view.findViewById(R.id.wv_country);
		mViewCity = (WheelView) view.findViewById(R.id.wv_city);
		mBtnConfirm = (TextView) view.findViewById(R.id.tv_commit);
		mBtnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cityDialog.dismiss();
				CityBean provinceBean = cityDao.getProvince().get(mViewProvince.getCurrentItem()+1);
				List<CityBean> citys = cityDao.getCity(provinceBean.id);
				CityBean city = citys.get(mViewCity.getCurrentItem());
				if (listener != null) {
					listener.onFinish(provinceBean, city);
				}
			}
		});
		setUpListener();
		setUpData();

		cityDialog = new Dialog(context);
		cityDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		cityDialog.setContentView(view);
		Window window = cityDialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.PopupAnimation);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		cityDialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		cityDialog.setCanceledOnTouchOutside(true);
		cityDialog.show();
	}

	private void setUpData() {
		List<CityBean> province = cityDao.getProvince();
		province.remove(0);
		provinceAdapter = new CityWheelAdapter(context, province);
		mViewProvince.setViewAdapter(provinceAdapter);

		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		updateCities();
	}

	private void setUpListener() {
		mViewProvince.addChangingListener(this);
		mViewCity.addChangingListener(this);
		// mBtnConfirm.setOnClickListener(this);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
		}
	}

	private void updateCities() {

		mViewCity.setViewAdapter(new CityWheelAdapter(context,
				cityDao.getCity(provinceAdapter.getCityId(mViewProvince.getCurrentItem()))));
		mViewCity.setCurrentItem(0);
	}

	class CityWheelAdapter extends AbstractWheelTextAdapter {
		List<CityBean> list;

		public CityWheelAdapter(Context context, List<CityBean> list) {
			super(context);
			this.list = list;
		}

		@Override
		public int getItemsCount() {
			return list == null ? 0 : list.size();
		}

		public String getCityId(int position) {
			return list.get(position).id;
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index).name;
		}

	}

	public interface onSelectCityFinishListener {
		public void onFinish(CityBean province, CityBean city);
	}

}
