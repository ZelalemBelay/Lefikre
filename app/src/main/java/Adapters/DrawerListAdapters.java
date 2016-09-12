package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeeloo.lefikre.R;

import java.util.ArrayList;

import Model.ListModel;

public class DrawerListAdapters extends BaseAdapter {

    private Activity context;
    private ArrayList<ListModel> navDrawerItems;

    public DrawerListAdapters(Activity context, ArrayList<ListModel> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }



    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = context.getLayoutInflater().inflate(R.layout.drawer_list_foramt, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        return convertView;
    }

}
