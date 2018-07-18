package com.example.a844302049;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 844302049 on 2018/7/9.
 */

public class fragment1 extends Fragment {
    public ListView listView;//listView
    public myAdapter adapter;//listView的适配器
    public List<ContentView_one.DataBean> mList = new ArrayList<>();//ListView子项集合
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag_layout1,container,false);
        listView = view.findViewById( R.id.listview);
        adapter = new myAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"功能敬请期待",Toast.LENGTH_SHORT).show();
            }
        });//listView子项点击事件
        initData();
        return view;
    }
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

            Image.LoaderNet(getActivity(),mList.get(position).getImagename(),viewHodle.IMAGE);//展示图片
            viewHodle.TEXT.setText(mList.get(position).getTitle());
            return convertView;
        }
        class viewHodle {
            private ImageView IMAGE;
            private TextView TEXT;
        }
    }//listView适配器
    public void initData(){
        String address = "http://wx.cheshijie.com/index.php/home/api/newest?page=1&limit=5";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();
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

            private void runOnUiThread(Runnable runnable) {
            }
        });
    }//获取并加载数据


}
