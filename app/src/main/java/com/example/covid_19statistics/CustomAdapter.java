package com.example.covid_19statistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> l;
    private List<CountryModel> lfiltered;
    public CustomAdapter(Context context, List<CountryModel> l){
        super(context, R.layout.list_custom_item,l);
        this.context=context;
        this.l=l;
        this.lfiltered=l;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView name=view.findViewById(R.id.cntrynme);
        ImageView img=view.findViewById(R.id.flagimg);

        name.setText(lfiltered.get(position).getCntry());
        Glide.with(context).load(lfiltered.get(position).getFlag()).into(img);

        return view;
    }

    @Override
    public int getCount() {
        return lfiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return lfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter f=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults=new FilterResults();
                if(charSequence==null){
                    filterResults.count=l.size();
                    filterResults.values=l.size();
                }
                else{
                    List<CountryModel> rm=new ArrayList<>();
                    String searchStr=charSequence.toString().toLowerCase();
                    for(CountryModel items:l){
                        if(items.getCntry().toLowerCase().contains(searchStr)){
                            rm.add(items);
                        }
                        filterResults.count=rm.size();
                        filterResults.values=rm;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                lfiltered=(List<CountryModel>) filterResults.values;
                Affected_Countries.list=(List<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }

        };
        return f;
    }
}
