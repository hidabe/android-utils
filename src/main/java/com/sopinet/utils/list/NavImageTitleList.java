package com.sopinet.utils.list;

import java.util.ArrayList;
import java.util.List;

import com.sopinet.utils.R;
import com.sopinet.utils.data.MenuData;
import com.sopinet.utils.data.NavImageTitleData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class NavImageTitleList extends BaseAdapter implements Filterable {
	static class ViewHolder {
	    public TextView title;
		public ImageView image;
	}
	
	private NavImageTitleData[] data;
	private Context context;
	protected final LayoutInflater inflater;
	
	public NavImageTitleList(Context context, NavImageTitleData[] data) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}
	
	public NavImageTitleList(Context context, List<NavImageTitleData> dataList) {
		this.context = context;
		this.data = new NavImageTitleData[dataList.size()];
		for(int i = 0; i < dataList.size(); i++) {
			this.data[i] = new NavImageTitleData();
			this.data[i] = dataList.get(i);
		}
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return this.data.length;
	}


	@Override
	public Object getItem(int position) {
		return this.data[position];
	}


	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View item = convertView;
	    ViewHolder holder;
	 
	    if(item == null)
	    {
	    	item = inflater.inflate(R.layout.navimagetitle_item, null);
	 
	        holder = new ViewHolder();
	        holder.image = (ImageView)item.findViewById(R.id.icon);
	        holder.title = (TextView)item.findViewById(R.id.title);
	 
	        item.setTag(holder);
	    }
	    else
	    {
	        holder = (ViewHolder)item.getTag();
	    }
	    holder.title.setText(this.data[position].getTitle());
	    //UrlImageViewHelper.setUrlDrawable(holder.image, this.filteredData.get(position).getImage());
	    if (this.data[position].getImage() != null) {
	    	holder.image.setImageDrawable(this.data[position].getImage());
	    	//holder.image.
	    	//this.imageLoader.displayImage(this.filteredData.get(position).getImage(), holder.image);
	    }
	 
	    return(item);
	}


	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}