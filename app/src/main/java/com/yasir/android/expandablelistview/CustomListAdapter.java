package com.yasir.android.expandablelistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YasirRajpoot on 03/09/2016.
 */
public class CustomListAdapter extends ArrayAdapter<RowItem> {

    private final Activity context;
    //public ArrayList<RowItem> newData;
    public ArrayList<RowItem> dataList;

    public CustomListAdapter(Activity context, ArrayList<RowItem> data) {
        super(context, R.layout.list_item, data);
        // TODO Auto-generated constructor stub
        //System.out.println(data.size());
        this.context=context;
        this.dataList = data;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        RowItem rowItem = dataList.get(position);
        View rowView=inflater.inflate(R.layout.list_item, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.lblListItem);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.myImageIcon);

        txtTitle.setText(rowItem.title);
        imageView.setImageResource(rowItem.image);

        return rowView;

    };
}