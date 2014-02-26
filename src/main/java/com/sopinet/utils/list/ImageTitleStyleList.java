package com.sopinet.utils.list;

import java.util.ArrayList;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import com.sopinet.utils.R;
import com.sopinet.utils.data.ImageTitleData;

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

public class ImageTitleStyleList extends BaseAdapter implements Filterable {

	static class ViewHolder {
	    public TextView title;
	    public TextView subtitle;
		public ImageView image;
		public ViewGroup shadow;
	}
	
	private ImageLoader imageLoader;
	private ImageTitleData[] data;
	private ArrayList<ImageTitleData> filteredData = null;
	protected final LayoutInflater inflater;
	private ItemFilter mFilter = new ItemFilter();
	
	@SuppressWarnings("unchecked")
	public ImageTitleStyleList(Activity context, ImageTitleData[] data, ImageLoader imageLoader) {
        filteredData = new ArrayList<ImageTitleData>(data.length);
        // Iniciamos los datos
        for (int i = 0; i < data.length; i++) {
        	filteredData.add(data[i]);
        }
        this.data = data;
        this.imageLoader = imageLoader;
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
	    	item = inflater.inflate(R.layout.its_item, null);
	 
	        holder = new ViewHolder();
	        holder.image = (ImageView) item.findViewById(R.id.listIMG);
	        holder.title = (TextView) item.findViewById(R.id.listTITLE);
	        holder.subtitle = (TextView) item.findViewById(R.id.listSUBTITLE);
	        
	        /* // Esto no tiene el efecto deseado. Se lleva el elemento arriba del todo 
	        holder.shadow = (ViewGroup) item.findViewById(R.id.cellLayout);
	        holder.shadow.bringToFront();
	        */
	        
	        item.setTag(holder);
	    }
	    else
	    {
	        holder = (ViewHolder)item.getTag();
	    }
	    
	    holder.title.setText(this.filteredData.get(position).getTitle());
	    holder.subtitle.setText(this.filteredData.get(position).getSubtitle());
	    //UrlImageViewHelper.setUrlDrawable(holder.image, this.filteredData.get(position).getImage());
	    if (this.filteredData.get(position).getImage() != null) {
	    	this.imageLoader.displayImage(this.filteredData.get(position).getImage(), holder.image);
	    }
	 
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
			
			final ImageTitleData[] list = data;
 
			int count = list.length;
			final ArrayList<ImageTitleData> nlist = new ArrayList<ImageTitleData>(count);
 			
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
			filteredData = (ArrayList<ImageTitleData>) results.values;
			notifyDataSetChanged();
		}
 
	}
}