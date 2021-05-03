package com.project.local;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.project.R;
import com.project.gourmet.GourmetActivity;
import com.project.local.fragment.AllFragment;
import com.project.local.fragment.DetailFragment;
import com.project.local.fragment.OtherFragment;
import com.project.local.fragment.MainFragment;
import com.project.local.fragment.SoupFragment;
import com.project.local.fragment.SubFragment;
import com.project.month.MonthActivity;
import com.project.search.SearchActivity;

public class LocalActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Intent intentLocal, intentGourmet, intentMonth, intentSearch, getIntent;

    ActionBarDrawerToggle barDrawerToggle;

    private Context context = this;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    MainFragment mainFragment;
    SoupFragment soupFragment;
    SubFragment subFragment;
    OtherFragment otherFragment;

    AllFragment allFragment;

    DetailFragment detailFragment;

    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon색조를 적용하지 않도록.. 설정 안하면 회색 색조
        navigationView.setItemIconTintList(null);

        //fragment 객체 생성
        mainFragment = new MainFragment();
        soupFragment = new SoupFragment();
        subFragment = new SubFragment();
        otherFragment = new OtherFragment();
        allFragment = new AllFragment();
        //intent객체 설정
        intentLocal =  new Intent(this, LocalActivity.class);
        intentGourmet = new Intent(this, GourmetActivity.class);
        intentMonth = new Intent(this, MonthActivity.class);
        intentSearch = new Intent(this, SearchActivity.class);
        getIntent = getIntent();

        //프래그먼트 교체
        setFrag(4);

        //탭 레이아웃
        tabLayout = findViewById(R.id.tabs);

        switch(getIntent.getExtras().toString()){
            case "local":
                setFrag(0);
                break;
            case "soup":
                setFrag(1);
                break;
            case "sub":
                setFrag(2);
                break;
            case "other":
                setFrag(3);
                break;
            case "all":
                setFrag(4);
                break;
        }



        //탭 선택시 프래그먼트 전환
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                setFrag(pos);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //네비게이션뷰의 아이템을 클릭했을때
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    //전체
                    case R.id.itemAllReceipt:
                        setFrag(4);
                        break;
                    //밥
                    case R.id.itemMain:
                        tabLayout.setVerticalScrollbarPosition(0);
                        setFrag(0);
                        break;
                    //국
                    case R.id.itemSoup:
                        tabLayout.setVerticalScrollbarPosition(1);
                        setFrag(1);
                        break;
                    //반찬
                    case R.id.itemSub:
                        tabLayout.setVerticalScrollbarPosition(2);
                        setFrag(2);
                        break;
                    //기타
                    case R.id.itemOther:
                        tabLayout.setVerticalScrollbarPosition(3);
                        setFrag(3);
                        break;
                    //레시피 검색
                    case R.id.itemSearch:
                        startActivity(intentSearch);
                        finish();
                        break;
                    //이 달의 레시피
                    case R.id.itemMonth:
                        startActivity(intentMonth);
                        finish();
                        break;
                    //맛집 추천
                    case R.id.itemGourmet:
                        startActivity(intentGourmet);
                        finish();
                        break;
                }

                //Drawer를 닫기...
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });

        //Drawer 조절용 토글 버튼 객체 생성
        barDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,R.string.app_name);
        //ActionBar(제목줄)의 홈or업버튼의 위치에 토글아이콘이 보이게끔...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //삼선아이콘 모양으로 보이도록
        //토글버튼의 동기를 맞추기
        barDrawerToggle.syncState();

        //삼선 아이콘과 화살표아이콘이 자동으로 변환하도록
        drawerLayout.addDrawerListener(barDrawerToggle);

    }//onCreate method..

    //액션바의 메뉴를 클릭하는 이벤트를 듣는
    //메소드를 통해서 클릭 상황을 전달하도록..
    //토글 버튼이 클릭 상황을 인지하도록..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    //프래그 먼트 교체작업 메소드
    public void setFrag(int n){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (n){
            case 0:
                fragmentTransaction.replace(R.id.linearLayout, mainFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.linearLayout,soupFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.linearLayout,subFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.linearLayout,otherFragment);
                fragmentTransaction.commit();
                break;

            case 4:
                fragmentTransaction.replace(R.id.linearLayout,allFragment);
                fragmentTransaction.commit();
        }
    }
}
