package haoran.bwie.com.rikao1227;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.xlistviewflush.view.XListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import haoran.bwie.com.rikao1227.adapter.MyAdapter;
import haoran.bwie.com.rikao1227.bean.Bean;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xlistview;
    private String url = "http://120.27.23.105/product/getProducts?pscid=39&page=1";
    private MyAdapter myAdapter;
    private List<Bean.DataBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MyAsync().execute(url);

    }

    class MyAsync extends AsyncTask<String, Void, List<Bean.DataBean>> {

        @Override
        protected List<Bean.DataBean> doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                OkHttpClient okHttpClient = new OkHttpClient();
                Request build = new Request.Builder().url(url).build();
                Call call = okHttpClient.newCall(build);
                Response execute = call.execute();
                String string = execute.body().string();
                Gson gson = new Gson();
                Bean bean = gson.fromJson(string, Bean.class);
                return bean.getData();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Bean.DataBean> dataBeans) {
            super.onPostExecute(dataBeans);
            list.addAll(dataBeans);
            myAdapter = new MyAdapter(list, MainActivity.this);
            xlistview.setAdapter(myAdapter);

        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
