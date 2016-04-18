package ipd.com.love.ui.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.base.BaseFragment;
import ipd.com.love.ui.adapter.InfromViewPagerAdapter;
import ipd.com.love.ui.infromation.BiddingInformation;
import ipd.com.love.ui.infromation.PeerExchange;
import ipd.com.love.ui.infromation.TechnicalSupport;

public class MessageFragment extends BaseFragment {

	private ViewPager vp_infromation; // viewpager
	private TextView screening_conditions; // 筛选条件
	private RadioGroup rb_tab_infromation; // 三个标题
	private RadioButton tv_bidding_information, tv_peer_exchange, tv_technical_information;
	private ArrayList<View> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, null);
		initView(view);
		initData();
		initListener();
		return view;
	}

	private void initView(View view) {
		vp_infromation = (ViewPager) view.findViewById(R.id.vp_infromation);
		screening_conditions = (TextView) view.findViewById(R.id.screening_conditions);
		rb_tab_infromation = (RadioGroup) view.findViewById(R.id.rb_tab_infromation);
		tv_bidding_information = (RadioButton) view.findViewById(R.id.tv_bidding_information);
		tv_peer_exchange = (RadioButton) view.findViewById(R.id.tv_peer_exchange);
		tv_technical_information = (RadioButton) view.findViewById(R.id.tv_technical_information);
	}

	private void initData() {
		list = new ArrayList<View>();
		BiddingInformation biddingInformation = new BiddingInformation(getActivity());
		PeerExchange peerExchange = new PeerExchange(getActivity());
		TechnicalSupport technicalSupport = new TechnicalSupport(getActivity());
		list.add(biddingInformation.getView());
		list.add(peerExchange.getView());
		list.add(technicalSupport.getView());
		vp_infromation.setAdapter(new InfromViewPagerAdapter(list));
		vp_infromation.setCurrentItem(1);
	}

	@SuppressWarnings("deprecation")
	private void initListener() {
		vp_infromation.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0) {
					tv_bidding_information.setChecked(true);
					screening_conditions.setText("招投标信息区域选择");
				} else if (arg0 == 1) {
					tv_peer_exchange.setChecked(true);
					screening_conditions.setText("同行交流类别筛选");
				} else {
					tv_technical_information.setChecked(true);
					screening_conditions.setText("技术交流类别选择");
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		rb_tab_infromation.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tv_bidding_information:
					vp_infromation.setCurrentItem(0);
					break;
				case R.id.tv_peer_exchange:
					vp_infromation.setCurrentItem(1);
					break;
				case R.id.tv_technical_information:
					vp_infromation.setCurrentItem(2);
					break;

				}
			}
		});
	}

}
