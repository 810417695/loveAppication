package ipd.com.love.ui.infromation;

import java.util.ArrayList;

import com.jumpbox.jumpboxlibrary.pulltorefresh.PullToRefreshBase;
import com.jumpbox.jumpboxlibrary.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.jumpbox.jumpboxlibrary.pulltorefresh.PullToRefreshListView;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import ipd.com.love.R;
import ipd.com.love.ui.adapter.InfromationAdapter3;
import ipd.com.love.utils.MyTimeUtils;

/**
 * 招投标信息
 * @author Administrator
 *
 */
public class TechnicalSupport {
	
	public Context context;
	private PullToRefreshListView lv_sell_skill;
	
	public TechnicalSupport(Context context){
		this.context = context;
	}
	
	public View getView(){
		View view = View.inflate(context, R.layout.layout_listview, null);
		lv_sell_skill = (PullToRefreshListView) view.findViewById(R.id.lv_sell_skill);
		
		initData();
		initListener();
		return view;
	}
	
	private void initListener() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++){
			list.add("");
		}
		lv_sell_skill.getRefreshableView().setAdapter(new InfromationAdapter3(context,list));
	}

	private void initData() {
		lv_sell_skill.setAutoRefresh(false);
		lv_sell_skill.setPullLoadEnabled(true);
		lv_sell_skill.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//				pages = 0;
//				getData(pages);
				lv_sell_skill.setLastUpdatedLabel(MyTimeUtils.getStringDate());
				lv_sell_skill.onPullDownRefreshComplete();
				lv_sell_skill.onPullUpRefreshComplete();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//				pages++;
//				getData(pages);
				lv_sell_skill.onPullDownRefreshComplete();
				lv_sell_skill.onPullUpRefreshComplete();
			}
		});
	}
	
}
