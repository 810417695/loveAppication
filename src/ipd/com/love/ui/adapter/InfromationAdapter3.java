package ipd.com.love.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ipd.com.love.R;

public class InfromationAdapter3 extends BaseAdapter {
	
	private Context context;
	private List<String> mList;
	public InfromationAdapter3(Context context,List<String> list) {
		this.context = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler hodler;
		if(convertView == null){
			hodler = new ViewHodler();
			convertView = View.inflate(context, R.layout.item_infromation3, null);
			hodler.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
			hodler.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
			hodler.imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);
			hodler.infrom_title = (TextView) convertView.findViewById(R.id.infrom_title);
			hodler.infrom_time = (TextView) convertView.findViewById(R.id.infrom_time);
			convertView.setTag(hodler);
		}else{
			hodler  = (ViewHodler) convertView.getTag();
		}
		return convertView;
	}
	
	private class ViewHodler{
		private ImageView imageView1;
		private ImageView imageView2;
		private ImageView imageView3;
		private TextView infrom_title;
		private TextView infrom_time;
	}

}
