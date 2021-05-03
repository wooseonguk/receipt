package com.project.gourmet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.R;
import com.project.gourmet.dto.GourmetDTO;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GourmetAdapter extends BaseAdapter {

    Context context;
    ArrayList<GourmetDTO> arrayList;

    public GourmetAdapter(Context context, ArrayList<GourmetDTO> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gourmet_thumbnail,null);

        ImageView iv = (ImageView) view.findViewById(R.id.ivListGourmet);
        TextView tv1 = (TextView) view.findViewById(R.id.tvListGourmet1);
        TextView tv2 = (TextView) view.findViewById(R.id.tvListGourmet2);

        iv.setImageResource(arrayList.get(position).getImg());
        tv1.setText(arrayList.get(position).getName());
        tv2.setText(arrayList.get(position).getDesc());

        return view;
    }
}
