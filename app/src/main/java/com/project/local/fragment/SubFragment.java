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

public class SubFragment extends Fragment{

    Context context;

    DetailFragment detailFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    private String[] subName={
            "제육볶음", "버섯볶음", "감자볶음", "연근조림","소세지야채볶음","무채"
    };

    private String[] subRate={
            "3.5", "4.0", "5.0", "3.7","4.3","4.7"
    };

    private Integer[] subImg={
            R.drawable.sub01, R.drawable.sub02,
            R.drawable.sub03, R.drawable.sub04,
            R.drawable.sub05, R.drawable.sub06
    };

    public interface OnSubSelectedListener {
        public void onSubSelection(int position);
    }

    SubFragment.OnSubSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  SubFragment.OnSubSelectedListener){
            mCallback = (SubFragment.OnSubSelectedListener) context;
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailFragment = new DetailFragment();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();

        //rootView
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.localfragment_sub, container, false);
        GridView gridView = (GridView)rootView.findViewById(R.id.gridViewSub);
        MyGridAdapter gridAdapter = new MyGridAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mCallback != null)
                    mCallback.onSubSelection(position);

                bundle.putString("name",subName[position]);
                bundle.putInt("img", subImg[position]);

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
            return subImg.length;
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

            iv.setImageResource(subImg[position]);
            tvName.setText(subName[position]);
            tvRate.setText(subRate[position]);

            return convertView;
        }
    }


}
