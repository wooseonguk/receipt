package com.project.gourmet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.project.R;
import com.project.gourmet.dto.GourmetDTO;
import com.project.local.LocalActivity;
import com.project.month.MonthActivity;
import com.project.search.SearchActivity;

import java.util.ArrayList;

public class GourmetActivity extends AppCompatActivity {

    //**DTO 배열 활용RecyclerViewApplication 참고**

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Intent intentLocal, intentGourmet, intentMonth, intentSearch, intentGDA;

    ActionBarDrawerToggle barDrawerToggle;

    private Context context = this;

    ArrayList<GourmetDTO> arrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gourmet);

        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon색조를 적용하지 않도록.. 설정 안하면 회색 색조
        navigationView.setItemIconTintList(null);

        intentLocal =  new Intent(this, LocalActivity.class);
        intentGourmet = new Intent(this, GourmetActivity.class);
        intentMonth = new Intent(this, MonthActivity.class);
        intentSearch = new Intent(this, SearchActivity.class);

        intentGDA = new Intent(this, DetailActivity.class);


        //네비게이션뷰의 아이템을 클릭했을때
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    //전체
                    //전체
                    case R.id.itemAllReceipt:
                        //Toast.makeText(MainActivity.this, "모두 보기", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("all","all");
                        startActivity(intentLocal);
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

        //Drawer 조절용 토글 버튼 객체 생성
        barDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,R.string.app_name);
        //ActionBar(제목줄)의 홈or업버튼의 위치에 토글아이콘이 보이게끔...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //삼선아이콘 모양으로 보이도록
        //토글버튼의 동기를 맞추기
        barDrawerToggle.syncState();

        //삼선 아이콘과 화살표아이콘이 자동으로 변환하도록
        drawerLayout.addDrawerListener(barDrawerToggle);

        arrayList = new ArrayList<GourmetDTO>();
        arrayList.add(new GourmetDTO(R.drawable.gourmet01,"홍유단","중식계의 초신성"));
        arrayList.add(new GourmetDTO(R.drawable.gourmet02,"장산각","빅뱅이 아직도 사장님이 좋아요 빅뱅"));
        arrayList.add(new GourmetDTO(R.drawable.gourmet03,"조운","혜성같이 나타난 차돌짬뽕"));
        arrayList.add(new GourmetDTO(R.drawable.gourmet04,"장궤요","북두칠성급 원조의맛 "));
        arrayList.add(new GourmetDTO(R.drawable.gourmet01,"북두반점","100년전통의 최고에 맛 고집하는"));
        arrayList.add(new GourmetDTO(R.drawable.gourmet02,"중화반점","서울 마포동 아연동 중화반점"));
        arrayList.add(new GourmetDTO(R.drawable.gourmet03,"환영각","짬뽕이 얼큰한 그집"));
        arrayList.add(new GourmetDTO(R.drawable.gourmet04,"자금성","단무지 공짜 양파 공짜"));

        listView = findViewById(R.id.listViewMainGourmet);
        GourmetAdapter myAdapter = new GourmetAdapter(this,arrayList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GourmetDTO gourmet = arrayList.get(position);
                intentGDA.putExtra("photo", gourmet.getImg());
                intentGDA.putExtra("name", gourmet.getName());
                intentGDA.putExtra("desc", gourmet.getDesc());
                startActivity(intentGDA);
            }
        });

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
