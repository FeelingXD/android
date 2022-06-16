package com.example.project11_01;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> ArrayList1 = new ArrayList<>();
    //어댑터에 사용되는 데이터의 갯수를 리턴한다.

    @Override
    public int getCount() {
        return ArrayList1.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayList1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item , viewGroup,false);
            ImageView iconImageView = view.findViewById(R.id.imageView1);
            TextView titleTextView = view.findViewById(R.id.textView1);
            TextView descTextView = view.findViewById(R.id.textView2);

            //포지션 위치 데이터 참조
            ListViewItem listViewItem = ArrayList1.get(i);


            iconImageView.setImageDrawable(listViewItem.getIconDrawable());
            titleTextView.setText(listViewItem.getTitlStr());
            descTextView.setText(listViewItem.getDescStr());


        }

        return view;
    }
    public void addItem(Drawable icon, String title, String desc){
        ListViewItem item = new ListViewItem();
        item.setIconDrawable(icon);
        item.setTitlStr(title);
        item.setDescStr(desc);

        ArrayList1.add(item);
    }
}
