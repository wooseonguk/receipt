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

public class OtherFragment extends Fragment {

    Context context;

    DetailFragment detailFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private String[] otherName={
            "베이컨말이", "콘치즈", "건빵튀김", "호떡","치즈라면","소떡소떡"
    };

    private String[] otherRate={
            "4.4", "3.2", "4.4", "4.1","5.0","3.7"
    };

    private Integer[] otherImg={
            R.drawable.other01, R.drawable.other02,
            R.drawable.other03, R.drawable.other04,
            R.drawable.other05, R.drawable.other06
    };

    public interface OnOtherSelectedListener {
        public void onOtherSelection(int position);
    }

    OtherFragment.OnOtherSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  OtherFragment.OnOtherSelectedListener){
            mCallback = (OtherFragment.OnOtherSelectedListener) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailFragment = new DetailFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.localfragment_other, container, false);
        GridView gridView = (GridView)rootView.findViewById(R.id.gridViewOther);
        MyGridAdapter gridAdapter = new MyGridAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mCallback != null)
                    mCallback.onOtherSelection(position);

                bundle.putString("name",otherName[position]);
                bundle.putInt("img", otherImg[position]);

                detailFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.linearLayout, detailFragment);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }

    public class MyGridAdapter extends BaseAdapter {

        Context context;

        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return otherImg.length;
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

            iv.setImageResource(otherImg[position]);
            tvName.setText(otherName[position]);
            tvRate.setText(otherRate[position]);

            return convertView;
        }
    }
}

