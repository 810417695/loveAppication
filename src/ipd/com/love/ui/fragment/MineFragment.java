package ipd.com.love.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ipd.com.love.R;
import ipd.com.love.ui.mine.activity.MineDateActivity_;

public class MineFragment extends Fragment {
	
	
	private ListView lv_mine;
	private String[] titleArray = {"添加角色","我的发布","我的收藏","推荐好友","我的积分","我的消息","联系我们"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate( R.layout.fragment_mine, null);
		initView(view);
		initData();
		initListener();
		return view;
	}
	private void initView(View view) {
		lv_mine = (ListView) view.findViewById(R.id.lv_mine);
		lv_mine.setAdapter(new MineListViewAdapter());
		View heanView = View.inflate(getActivity(), R.layout.mine_data, null);
		lv_mine.addHeaderView(heanView);
	}
	private void initData() {
		
	}
	private void initListener() {
		lv_mine.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position == 0){    //个人资料
					Intent intent = new Intent(getActivity(),MineDateActivity_.class);
					startActivity(intent);
				}
			}
		});
		
	}

	private class MineListViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return titleArray.length;
		}

		@Override
		public Object getItem(int position) {
			return titleArray[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(getActivity(), R.layout.item_mine, null);
			TextView title = (TextView) convertView.findViewById(R.id.mine_title);
			title.setText(titleArray[position]);
			return convertView;
		}
	}
	
	
}
