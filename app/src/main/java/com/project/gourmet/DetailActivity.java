package com.project.gourmet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.project.R;
import com.project.local.LocalActivity;
import com.project.month.MonthActivity;
import com.project.search.SearchActivity;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Intent getIntent;
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView iv;
    Integer img;
    String name, desc;
    Button btn;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Intent intentLocal, intentGourmet, intentMonth, intentSearch, intentGDA;

    ActionBarDrawerToggle barDrawerToggle;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gourmet_detail);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        iv = findViewById(R.id.ganpan);
        tv1 = findViewById(R.id.resName);
        tv2 = findViewById(R.id.resDesc);
        tv3 = findViewById(R.id.resMenu1);
        tv4 = findViewById(R.id.resMenu2);
        tv5 = findViewById(R.id.resMenu3);
        btn = findViewById(R.id.btnCallOrder);

        getIntent = getIntent();
        img = getIntent.getIntExtra("photo",0);
        name = getIntent.getStringExtra("name");
        desc = getIntent.getStringExtra("desc");

        iv.setImageResource(img);
        tv1.setText(name);
        tv2.setText("~" + desc +"~");
        tv3.setText("짜장면 : 6000원");
        tv4.setText("짬뽕 : 6000원");
        tv5.setText("탕수육 : 16000원");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0510124567"));
                startActivity(call);
            }
        });

        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon색조를 적용하지 않도록.. 설정 안하면 회색 색조
        navigationView.setItemIconTintList(null);

        intentLocal =  new Intent(this, LocalActivity.class);
        intentGourmet = new Intent(this, GourmetActivity.class);
        intentMonth = new Intent(this, MonthActivity.class);
        intentSearch = new Intent(this, SearchActivity.class);

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
                        break;
                    //국
                    case R.id.itemSoup:
                        //Toast.makeText(MainActivity.this, "국", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("soup","soup");
                        startActivity(intentLocal);
                        break;
                    //반찬
                    case R.id.itemSub:
                        //Toast.makeText(MainActivity.this, "반찬", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("sub","sub");
                        startActivity(intentLocal);
                        break;
                    //기타
                    case R.id.itemOther:
                        //Toast.makeText(MainActivity.this, "기타", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("other","other");
                        startActivity(intentLocal);
                        break;
                    //레시피 검색
                    case R.id.itemSearch:
                        //Toast.makeText(MainActivity.this, "검색", Toast.LENGTH_SHORT).show();
                        startActivity(intentSearch);
                        break;
                    //이 달의 레시피
                    case R.id.itemMonth:
                        //Toast.makeText(MainActivity.this, "이번달", Toast.LENGTH_SHORT).show();
                        startActivity(intentMonth);
                        break;
                    //맛집 추천
                    case R.id.itemGourmet:
                        //Toast.makeText(MainActivity.this, "맛집", Toast.LENGTH_SHORT).show();
                        startActivity(intentGourmet);
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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng SEOUL = new LatLng(35.172468, 129.174601);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 18));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}