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

public class MainFragment extends Fragment {

    Context context;

    DetailFragment detailFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private String[] mainName={
            "간장 계란밥", "야끼 오니기리", "비빔밥", "누룽지","김밥","볶음밥"
    };

    private String[] mainRate={
            "4.2", "4.0", "3.2", "3.7","4.1","3.2"
    };

    private Integer[] mainImg={
            R.drawable.rice01, R.drawable.rice02,
            R.drawable.rice03, R.drawable.rice04,
            R.drawable.rice05, R.drawable.rice06
    };


    public interface OnMainSelectedListener {
        public void onMainSelection(int position);
    }

    MainFragment.OnMainSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragment.OnMainSelectedListener) {
            mCallback = (MainFragment.OnMainSelectedListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailFragment = new DetailFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.localfragment_main, container, false);
        GridView gridView = (GridView)rootView.findViewById(R.id.gridViewRice);
        MyGridAdapter gridAdapter = new MyGridAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mCallback != null)
                    mCallback.onMainSelection(position);

                bundle.putString("name",mainName[position]);
                bundle.putInt("img", mainImg[position]);

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
            return mainImg.length;
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.locallist_thumbnail,parent,false);

            ImageView iv = convertView.findViewById(R.id.ivImg);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvRate =  convertView.findViewById(R.id.tvRate);

            //iv.setLayoutParams(new ViewGroup.LayoutParams(500,800));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(10,10,10,10);


            iv.setImageResource(mainImg[position]);
            tvName.setText(mainName[position]);
            tvRate.setText(mainRate[position]);

            return convertView;
        }
    }
}
