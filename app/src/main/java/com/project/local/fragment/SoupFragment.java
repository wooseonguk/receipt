package com.project.local.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.R;

public class SoupFragment extends Fragment {

    Context context;

    DetailFragment detailFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private String[] soupName={
            "부대찌개", "김치찌개", "콩나물국", "된장찌개","육개장","미역국"
    };

    private String[] soupRate={
            "3.4", "4.9", "3.2", "4.1","4.5","4.2"
    };

    private Integer[] soupImg={
            R.drawable.soup01, R.drawable.soup02,
            R.drawable.soup03, R.drawable.soup04,
            R.drawable.soup05, R.drawable.soup06
    };

    public interface OnSoupSelectedListener {
        public void onSoupSelection(int position);
    }

    SoupFragment.OnSoupSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  SoupFragment.OnSoupSelectedListener){
            mCallback = (SoupFragment.OnSoupSelectedListener) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        detailFragment = new DetailFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.localfragment_soup, container, false);
        GridView gridView = (GridView)rootView.findViewById(R.id.gridViewSoup);
        MyGridAdapter gridAdapter = new MyGridAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mCallback != null)
                    mCallback.onSoupSelection(position);

                bundle.putString("name",soupName[position]);
                bundle.putInt("img", soupImg[position]);

                detailFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.linearLayout, detailFragment);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }

    public class MyGridAdapter extends BaseAdapter{

        Context context;

        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return soupImg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.locallist_thumbnail,parent,false);

            ImageView iv = convertView.findViewById(R.id.ivImg);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvRate =  convertView.findViewById(R.id.tvRate);

            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(10,10,10,10);

            iv.setImageResource(soupImg[position]);
            tvName.setText(soupName[position]);
            tvRate.setText(soupRate[position]);

            return convertView;
        }
    }
}
