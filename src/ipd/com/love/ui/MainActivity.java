package ipd.com.love.ui;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.ui.fragment.HomeFragment;
import ipd.com.love.ui.fragment.MessageFragment;
import ipd.com.love.ui.fragment.MineFragment;
import ipd.com.love.ui.fragment.PersonFragment;
import ipd.com.love.ui.fragment.ProJectFragment;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

	@ViewById
	RelativeLayout home_container; // 包含fragment的相对布局
	@ViewById
	RadioGroup radio_group;
	@ViewById
	RelativeLayout base_title;
	@ViewById
	TextView title;

	private FragmentManager fragmentManager;
	private Fragment homeFragment, projectFragment, personFragment, messageFragment, mineFragment;

	@AfterViews
	public void init() {
		initData();
		initListener();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	}
	public void initData() {
		fragmentManager = getSupportFragmentManager();
		setTabSelection(0);//初始化选择第一个
		initListener();
	}

	private void initListener() {
		radio_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					title.setText("首页");
					base_title.setVisibility(View.VISIBLE);
					setTabSelection(0);
					break;
				case R.id.rb_project:
					title.setText("找项目");
					base_title.setVisibility(View.VISIBLE);
					setTabSelection(1);
					break;
				case R.id.rb_person:
					title.setText("找工人");
					base_title.setVisibility(View.VISIBLE);
					setTabSelection(2);
					break;
				case R.id.rb_message:
					base_title.setVisibility(View.GONE);
					setTabSelection(3);
					break;
				case R.id.rb_mine:
					title.setText("我的");
					base_title.setVisibility(View.VISIBLE);
					setTabSelection(4);
					break;
				}
			}
		});
	}

	private void setTabSelection(int position) {

		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (position) {
		case 0:
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
				transaction.add(R.id.home_container, homeFragment);
			} else {
				transaction.show(homeFragment);
			}
			break;
		case 1:
			if (projectFragment == null) {
				projectFragment = new ProJectFragment();
				transaction.add(R.id.home_container, projectFragment);
			} else {
				transaction.show(projectFragment);
			}
			break;
		case 2:
			if (personFragment == null) {
				personFragment = new PersonFragment();
				transaction.add(R.id.home_container, personFragment);
			} else {
				transaction.show(personFragment);
			}
			break;
		case 3:
			if (messageFragment == null) {
				messageFragment = new MessageFragment();
				transaction.add(R.id.home_container, messageFragment);
			} else {
				transaction.show(messageFragment);
			}
			break;
		case 4:
			if (mineFragment == null) {
				mineFragment = new MineFragment();
				transaction.add(R.id.home_container, mineFragment);
			} else {
				transaction.show(mineFragment);
			}
			break;
		
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (projectFragment != null) {
			transaction.hide(projectFragment);
		}
		if (personFragment != null) {
			transaction.hide(personFragment);
		}
		if(messageFragment != null){
			transaction.hide(messageFragment);
		}
		if (mineFragment != null) {
			transaction.hide(mineFragment);
		}
	}
}
