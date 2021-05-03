package com.project.month;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.project.R;
import com.project.gourmet.GourmetActivity;
import com.project.local.LocalActivity;
import com.project.search.SearchActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class MonthActivity extends AppCompatActivity {

    TextView tvTest1;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Intent intentLocal, intentGourmet, intentMonth, intentSearch;

    ActionBarDrawerToggle barDrawerToggle;

    private Context context = this;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon색조를 적용하지 않도록.. 설정 안하면 회색 색조
        navigationView.setItemIconTintList(null);

        tvTest1 = findViewById(R.id.tvTest1);

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
                        intentLocal.putExtra("main","main");
                        finish();
                        startActivity(intentLocal);

                        break;
                    //국
                    case R.id.itemSoup:
                        //Toast.makeText(MainActivity.this, "국", Toast.LENGTH_SHORT).show();
                        intentLocal.putExtra("soup","soup");
                        finish();
                        startActivity(intentLocal);
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


        //달력
        MaterialCalendarView materialCalendarView;

        materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

        OneDayDecorator oneDayDecorator = new OneDayDecorator();
        materialCalendarView.addDecorators(
                oneDayDecorator
        );

        MaterialCalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setSelectedDate(CalendarDay.today());

        calendarView.addDecorator(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,1,16))));
        calendarView.addDecorator(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,1,20))));
        calendarView.addDecorator(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,1,28))));
        calendarView.addDecorator(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,1,4))));




    }//onCreate method..

    //액션바의 메뉴를 클릭하는 이벤트를 듣는
    //메소드를 통해서 클릭 상황을 전달하도록..
    //토글 버튼이 클릭 상황을 인지하도록..

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    class SaturdayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SundayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }
    }

    public class OneDayDecorator implements DayViewDecorator {

        private CalendarDay date;


        public OneDayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }

        public void setDate(Date date) {
            this.date = CalendarDay.from(date);
        }
    }

    public class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));
        }
    }
}