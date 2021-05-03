package com.project.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.project.R;
import com.project.gourmet.GourmetActivity;
import com.project.local.LocalActivity;
import com.project.month.MonthActivity;
import com.project.search.fragment.SearchFragment;

public class SearchActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Intent intentLocal, intentGourmet, intentMonth, intentSearch;

    ActionBarDrawerToggle barDrawerToggle;

    private Context context = this;

    SearchFragment searchFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //드로어 설정
        navigationView = findViewById(R.id.nav);
        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView.setItemIconTintList(null);

        //인텐트
        intentLocal = new Intent(this, LocalActivity.class);
        intentGourmet = new Intent(this, GourmetActivity.class);
        intentMonth = new Intent(this, MonthActivity.class);
        intentSearch = new Intent(this, SearchActivity.class);

        //프래그먼트
        searchFragment = new SearchFragment();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.search_layout, searchFragment);
        fragmentTransaction.commit();

        //네비게이션뷰의 아이템을 클릭
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    //전체
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
                        intentLocal.putExtra("main","main");
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

        //Drawer 조절용 토글
        barDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        barDrawerToggle.syncState();
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
}