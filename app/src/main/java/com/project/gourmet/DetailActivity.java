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
        tv3.setText("????????? : 6000???");
        tv4.setText("?????? : 6000???");
        tv5.setText("????????? : 16000???");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0510124567"));
                startActivity(call);
            }
        });

        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon????????? ???????????? ?????????.. ?????? ????????? ?????? ??????
        navigationView.setItemIconTintList(null);

        intentLocal =  new Intent(this, LocalActivity.class);
        intentGourmet = new Intent(this, GourmetActivity.class);
        intentMonth = new Intent(this, MonthActivity.class);
        intentSearch = new Intent(this, SearchActivity.class);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    //??????
                    //??????
                    case R.id.itemAllReceipt:
                        //Toast.makeText(MainActivity.this, "?????? ??????", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("all","all");
                        startActivity(intentLocal);
                        break;
                    //???
                    case R.id.itemMain:
                        //Toast.makeText(MainActivity.this, "??? ?????????", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("main","main");
                        startActivity(intentLocal);
                        break;
                    //???
                    case R.id.itemSoup:
                        //Toast.makeText(MainActivity.this, "???", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("soup","soup");
                        startActivity(intentLocal);
                        break;
                    //??????
                    case R.id.itemSub:
                        //Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("sub","sub");
                        startActivity(intentLocal);
                        break;
                    //??????
                    case R.id.itemOther:
                        //Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("other","other");
                        startActivity(intentLocal);
                        break;
                    //????????? ??????
                    case R.id.itemSearch:
                        //Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        startActivity(intentSearch);
                        break;
                    //??? ?????? ?????????
                    case R.id.itemMonth:
                        //Toast.makeText(MainActivity.this, "?????????", Toast.LENGTH_SHORT).show();
                        startActivity(intentMonth);
                        break;
                    //?????? ??????
                    case R.id.itemGourmet:
                        //Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        startActivity(intentGourmet);
                        break;
                }

                //Drawer??? ??????...
                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });

        //Drawer ????????? ?????? ?????? ?????? ??????
        barDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,R.string.app_name);
        //ActionBar(?????????)??? ???or???????????? ????????? ?????????????????? ????????????...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //??????????????? ???????????? ????????????
        //??????????????? ????????? ?????????
        barDrawerToggle.syncState();

        //?????? ???????????? ????????????????????? ???????????? ???????????????
        drawerLayout.addDrawerListener(barDrawerToggle);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng SEOUL = new LatLng(35.172468, 129.174601);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("??????");
        markerOptions.snippet("????????? ??????");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 18));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}