package com.example.a844302049;

import android.Manifest;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.xmlpull.v1.XmlPullParser.TEXT;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RadioGroup radioGroup;
    private Banner banner;
    private ArrayList<String> list_path;//放置于Banner的图片集合
    private ArrayList<String> list_title;//放置于Banner的标题集合
    private List<ContentView_one.DataBean> mList = new ArrayList<>();
    private ListView listView;
    private myAdapter adapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        listView = findViewById( R.id.listview);
        adapter = new myAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,i+"功能敬请期待",Toast.LENGTH_SHORT).show();
            }
        });//listView子项点击事件
        huamenu();
        cehuamenu();
        initView();
        initData();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }//关联menu布局在Toolbar上
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.chazhao:
                Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }//Toolbar点击事件
    public void huamenu(){
        radioGroup = findViewById(R.id.radio_0);
        final RadioButton radioButton1 = findViewById(R.id.radio_1);
        final RadioButton radioButton2 = findViewById(R.id.radio_2);
        final RadioButton radioButton3 = findViewById(R.id.radio_3);
        final RadioButton radioButton4 = findViewById(R.id.radio_4);
        final RadioButton radioButton5 = findViewById(R.id.radio_5);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if ( radioButton1.getId() == i ){
                    Intent intent = new Intent (MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if ( radioButton2.getId() == i ){
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                if ( radioButton3.getId() == i ){
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                if ( radioButton4.getId() == i ){
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                if ( radioButton5.getId() == i ){
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//顶部滑动按钮点击事件
    public void cehuamenu(){
        radioGroup = findViewById(R.id.ra_0);
        final RadioButton raButton1 = findViewById(R.id.ra_1);
        final RadioButton raButton2 = findViewById(R.id.ra_2);
        final RadioButton raButton3 = findViewById(R.id.ra_3);
        final RadioButton raButton4 = findViewById(R.id.ra_4);
        final RadioButton raButton5 = findViewById(R.id.ra_5);
        final RadioButton raButton6 = findViewById(R.id.ra_6);
        final RadioButton raButton7 = findViewById(R.id.ra_7);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if ( raButton1.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
                if ( raButton2.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
                if ( raButton3.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
                if ( raButton4.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
                if ( raButton5.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
                if ( raButton6.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
                if ( raButton7.getId() == i ){
                    Toast.makeText(MainActivity.this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//侧滑点击事件
    public void initView(){
        banner = findViewById(R.id.banner_1);
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        list_path.add("http://img.cheshijie.com/video-thumb/csj_28738655275-jwzrHKAMwi.jpg?x-oss-process=image/resize,w_898/crop,x_161,y_60,w_718,h_539");
        list_path.add("http://img.cheshijie.com/video-thumb/csj_13488809342-85NGR8Mi6T.jpg?x-oss-process=image/resize,w_958/crop,x_119,y_0,w_718,h_539");
        list_path.add("http://img.cheshijie.com/video-thumb/csj_38738655275-4kDxiCiCwH.jpg?x-oss-process=image/resize,w_680/crop,x_46,y_3,w_596,h_397");
        list_path.add("http://img.cheshijie.com/video-thumb/csj_13488809342-mbWz2rWGNT.png?x-oss-process=image/resize,w_958/crop,x_136,y_0,w_717,h_538");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");

        banner.setImageLoader(new MyLoader());//设置图片加载器，图片加载器在下方
        banner.setImages(list_path);//设置图片网址或地址的集合
        banner.setBannerAnimation(Transformer.Default);//设置轮播的动画效果，内含多种特效，可点入
        // 方法内查找后内逐一体验
        banner.setBannerTitles(list_title);//设置轮播图的标题集合
        banner.setDelayTime(3000);//设置轮播间隔时间
        banner.isAutoPlay(true);//设置是否为自动轮播，默认是“是”。
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置，小点点，左中右。
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this,"你点击了",Toast.LENGTH_SHORT).show();
            }
        }).start();
        actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_1);
        }
    }//banner逻辑
    public void initData(){
        String address = "http://wx.cheshijie.com/index.php/home/api/newest?page=1&limit=5";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this,"获取失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();//获得请求结果数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("====",responseData);
                        ContentView_one contentView_one = GsonUtils.parseJSON(responseData,ContentView_one.class);
                        List<ContentView_one.DataBean> data = contentView_one.getData();
                        mList.addAll(data);
                        Log.i("====", "run: "+mList.get(0).getImagename());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }//获取并加载数据
    class myAdapter extends BaseAdapter {//适配器
        @Override
        public int getCount() {
            return mList.size();
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
            myAdapter.viewHodle viewHodle;
            if (convertView == null) {
                viewHodle = new myAdapter.viewHodle();
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                convertView.setTag(viewHodle);
            } else {
                viewHodle = (myAdapter.viewHodle) convertView.getTag();
            }


            viewHodle.IMAGE = convertView.findViewById(R.id.image_1);//控件ID
            viewHodle.TEXT = convertView.findViewById(R.id.text_1);

            Image.LoaderNet(MainActivity.this,mList.get(position).getImagename(),viewHodle.IMAGE);//展示图片
            viewHodle.TEXT.setText(mList.get(position).getTitle());
            return convertView;
        }
        class viewHodle {
            private ImageView IMAGE;
            private TextView TEXT;
        }
    }//listView适配器
}


