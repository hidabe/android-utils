package com.sopinet.utils.list;

import java.util.ArrayList;
import java.util.List;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import com.sopinet.utils.R;
import com.sopinet.utils.data.ImageTitleData;
import com.sopinet.utils.data.TextData;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TextList extends BaseAdapter implements Filterable {
	static class ViewHolder {
	    public TextView title;
	    public TextView subtitle;
	}	    	
	
	private TextData[] data;
	private ArrayList<TextData> filteredData = null;
	protected final LayoutInflater inflater;
	private ItemFilter mFilter = new ItemFilter();
	
	@SuppressWarnings("unchecked")
	public TextList(Activity context, TextData[] data) {
        this.data = data;
        filteredData = new ArrayList<TextData>(data.length);
        // Iniciamos los datos
        for (int i = 0; i < data.length; i++) {
        	filteredData.add(data[i]);
        }        
        inflater = LayoutInflater.from(context);
	}
	
	public TextList(Activity context, List<TextData> data) {
		this.data = new TextData[data.size()];		
		for(int i = 0; i < data.size(); i++) {
			this.data[i] = data.get(i);
		}
		Log.d("TEMA", String.valueOf(this.data.length));
        filteredData = new ArrayList<TextData>(this.data.length);
        // Iniciamos los datos
        for (int i = 0; i < this.data.length; i++) {
        	Log.d("TEMA", this.data[i].getTitle());
        	filteredData.add(this.data[i]);
        }		
		inflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return filteredData.size();
	}
 
	public Object getItem(int position) {
		return filteredData.get(position);
	}
 
	public long getItemId(int position) {
		return position;
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
	    View item = convertView;
	    ViewHolder holder;
	 
	    if(item == null)
	    {
	    	item = inflater.inflate(R.layout.text_item, null);
	 
	        holder = new ViewHolder();
	        holder.title = (TextView)item.findViewById(R.id.listTITLE);
	        holder.subtitle = (TextView)item.findViewById(R.id.listSUBTITLE);
	 
	        item.setTag(holder);
	    }
	    else
	    {
	        holder = (ViewHolder)item.getTag();
	    }
	    holder.title.setText(this.filteredData.get(position).getTitle());
	    holder.subtitle.setText(this.filteredData.get(position).getSubtitle());
	 
	    return(item);
	}
	
	public Filter getFilter() {
		return mFilter;
	}
 
	private class ItemFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			String filterString = constraint.toString().toLowerCase();
			
			FilterResults results = new FilterResults();
			
			final TextData[] list = data;
 
			int count = list.length;
			final ArrayList<TextData> nlist = new ArrayList<TextData>(count);
 			
			for (int i = 0; i < count; i++) {
				if (list[i].getTitle().toLowerCase().contains(filterString)
					|| (!list[i].getSubtitle().equals("") && list[i].getSubtitle().toLowerCase().contains(filterString))) {
					nlist.add(list[i]);
				}
			}
			
			results.values = nlist;
			results.count = nlist.size();
 
			return results;	
		}
 
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			filteredData = (ArrayList<TextData>) results.values;
			notifyDataSetChanged();
		}
 
	}
}