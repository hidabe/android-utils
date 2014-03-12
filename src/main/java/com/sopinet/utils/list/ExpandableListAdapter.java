package com.sopinet.utils.list;

import java.util.HashMap;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sopinet.utils.R;
import com.sopinet.utils.data.ImageTitleData;
import com.sopinet.utils.list.ImageTitleList.ViewHolder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	static class ViewHolder {
	    public TextView title;
	    public TextView subtitle;
		public ImageView image;
	}		

	private ImageLoader imageLoader;
	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<ImageTitleData>> _listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<ImageTitleData>> listChildData, ImageLoader imageLoader) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
		this.imageLoader = imageLoader;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final ImageTitleData childData = (ImageTitleData) getChild(groupPosition, childPosition);

	    View item = convertView;
	    ViewHolder holder;
	 
	    LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    if(item == null)
	    {
	    	item = infalInflater.inflate(R.layout.list_item, null);
	 
	        holder = new ViewHolder();
	        holder.image = (ImageView)item.findViewById(R.id.listIMG);
	        holder.title = (TextView)item.findViewById(R.id.listTITLE);
	        //holder.subtitle = (TextView)item.findViewById(R.id.listSUBTITLE);
	 
	        item.setTag(holder);
	    }
	    else
	    {
	        holder = (ViewHolder)item.getTag();
	    }
	    holder.title.setText(childData.getTitle());
	    //holder.subtitle.setText(childData.getSubtitle());
	    //UrlImageViewHelper.setUrlDrawable(holder.image, this.filteredData.get(position).getImage());
	    if (childData.getImage() != null) {
	    	this.imageLoader.displayImage(childData.getImage(), holder.image);
	    }		

		return(item);
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}