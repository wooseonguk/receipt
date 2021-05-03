package com.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.project.gourmet.GourmetActivity;
import com.project.local.LocalActivity;
import com.project.mainpagerfragment.FirstFragment;
import com.project.mainpagerfragment.FourthFragment;
import com.project.mainpagerfragment.SecondFragment;
import com.project.mainpagerfragment.ThirdFragment;
import com.project.month.MonthActivity;
import com.project.search.SearchActivity;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle barDrawerToggle;

    private Context context = this;
    ViewPager viewPager;
    FragmentPagerAdapter adapterViewPager;

    Intent intentLocal, intentGourmet, intentMonth, intentSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon색조를 적용하지 않도록.. 설정 안하면 회색 색조
        navigationView.setItemIconTintList(null);

        intentLocal =  new Intent(this, LocalActivity.class);
        intentGourmet = new Intent(this, GourmetActivity.class);
        intentMonth = new Intent(this, MonthActivity.class);
        intentSearch = new Intent(this, SearchActivity.class);


        //네비게이션뷰의 아이템을 클릭했을때
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                        //전체
                        case R.id.itemAllReceipt:
                            //Toast.makeText(MainActivity.this, "모두 보기", Toast.LENGTH_SHORT).show();
                            intentLocal.putExtra("all","all");
                            startActivity(intentLocal);
                            finish();
                            break;
                        //밥
                        case R.id.itemMain:
                            //Toast.makeText(MainActivity.this, "밥 레시피", Toast.LENGTH_SHORT).show();
                            intentLocal.putExtra("local","local");
                            startActivity(intentLocal);
                            finish();
                            break;
                        //국
                        case R.id.itemSoup:
                            //Toast.makeText(MainActivity.this, "국", Toast.LENGTH_SHORT).show();
                            intentLocal.putExtra("soup","soup");
                            startActivity(intentLocal);
                            finish();
                            break;
                        //반찬
                        case R.id.itemSub:
                            //Toast.makeText(MainActivity.this, "반찬", Toast.LENGTH_SHORT).show();
                            intentLocal.putExtra("sub","sub");
                            startActivity(intentLocal);
                            finish();
                            break;
                        //기타
                        case R.id.itemOther:
                            //Toast.makeText(MainActivity.this, "기타", Toast.LENGTH_SHORT).show();
                            intentLocal.putExtra("other","other");
                            startActivity(intentLocal);
                            finish();
                            break;
                        //레시피 검색
                        case R.id.itemSearch:
                            //Toast.makeText(MainActivity.this, "검색", Toast.LENGTH_SHORT).show();
                            startActivity(intentSearch);
                            finish();
                            break;
                        //이 달의 레시피
                        case R.id.itemMonth:
                            //Toast.makeText(MainActivity.this, "이번달", Toast.LENGTH_SHORT).show();
                            startActivity(intentMonth);
                            finish();
                            break;
                        //맛집 추천
                        case R.id.itemGourmet:
                            //Toast.makeText(MainActivity.this, "맛집", Toast.LENGTH_SHORT).show();
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
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return FirstFragment.newInstance(0, "Page # 1");
                case 1:
                    return SecondFragment.newInstance(1, "Page # 2");
                case 2:
                    return ThirdFragment.newInstance(2, "Page # 3");
                case 3:
                    return FourthFragment.newInstance(3, "Page # 4");
                default:
                    return null;
            }
        }
        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

}

