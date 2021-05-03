package com.project.search.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.project.R;
import com.project.local.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.Arrays;

import static androidx.core.content.ContextCompat.getSystemService;


public class SearchFragment extends Fragment {
    //AutoTextApplication 참고
    AutoCompleteTextView auto;
    String[] items = {"제육볶음", "버섯볶음", "감자볶음", "연근조림","소세지야채볶음","무채",
            "부대찌개", "김치찌개", "콩나물국", "된장찌개","육개장","미역국",
            "간장 계란밥", "야끼 오니기리", "비빔밥", "누룽지","김밥","볶음밥",
            "베이컨말이", "콘치즈", "건빵튀김", "호떡","치즈라면","소떡소떡"};

    private Integer[] searchImg={
            R.drawable.sub01, R.drawable.sub02,
            R.drawable.sub03, R.drawable.sub04,
            R.drawable.sub05, R.drawable.sub06,
            R.drawable.soup01, R.drawable.soup02,
            R.drawable.soup03, R.drawable.soup04,
            R.drawable.soup05, R.drawable.soup06,
            R.drawable.rice01, R.drawable.rice02,
            R.drawable.rice03, R.drawable.rice04,
            R.drawable.rice05, R.drawable.rice06,
            R.drawable.other01, R.drawable.other02,
            R.drawable.other03, R.drawable.other04,
            R.drawable.other05, R.drawable.other06

    };


    Button btn;
    String result;
    TextView tv;
    Integer p;

    InputMethodManager imm;

    DetailFragment detailFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.searchfragment_main, container, false);
        auto = rootView.findViewById(R.id.autoComplete);
        btn = rootView.findViewById(R.id.btnSearch);
        tv = rootView.findViewById(R.id.test);

        imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);


        detailFragment = new DetailFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();

        ArrayList<String> name = new ArrayList<>(Arrays.asList(items));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,items);
        auto.setAdapter(adapter);

        auto.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               result = ((TextView)view).getText().toString();
               p = name.indexOf(result);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result == null) {
                    //tv.setVisibility(View.VISIBLE);
                    //tv.setText("검색어를 입력하세요");
                    Toast.makeText(getActivity().getApplicationContext(),"검색어를 입력해 주세요",Toast.LENGTH_SHORT).show();
                    imm.hideSoftInputFromWindow(auto.getWindowToken(), 0);
                } else {
                    //tv.setVisibility(View.GONE);
                    imm.hideSoftInputFromWindow(auto.getWindowToken(), 0);

                    bundle.putString("name", result);
                    bundle.putInt("img", searchImg[p]);

                    detailFragment.setArguments(bundle);
                    tv.setText(result + "11" + "/ 위치 : " + p);

                    fragmentTransaction.replace(R.id.searchDetailLayout, detailFragment);
                    fragmentTransaction.commit();
                    }
                }
        });

        return rootView;
    }
}
