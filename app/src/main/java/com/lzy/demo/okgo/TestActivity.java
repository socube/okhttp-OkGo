package com.lzy.demo.okgo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lzy.demo.R;
import com.lzy.demo.base.BaseActivity;
import com.lzy.demo.callback.JsonConvert;
import com.lzy.demo.model.LzyResponse;
import com.lzy.demo.model.ServerModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.BitmapConvert;
import com.lzy.okgo.convert.FileConvert;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Response;
import rx.Observable;

public class TestActivity extends BaseActivity {

    @Bind(R.id.image) ImageView imageView;
    @Bind(R.id.edit) EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle("测试页面");
    }

    @OnClick(R.id.btn1)
    public void btn1(View view) {
        Call<String> stringCall = OkGo.get("").getCall(StringConvert.create());
        Call<Bitmap> bitmapCall = OkGo.get("").getCall(BitmapConvert.create());
        Call<File> fileCall = OkGo.get("").getCall(new FileConvert());
        Call<LzyResponse<ServerModel>> call = OkGo.get("").getCall(new JsonConvert<LzyResponse<ServerModel>>() {});
        Call<LzyResponse<ServerModel>> listCall = OkGo.get("").getCall(new JsonConvert<LzyResponse<ServerModel>>() {});

        Observable<String> stringObservable = OkGo.get("").getCall(StringConvert.create(), RxAdapter.<String>create());
        Observable<LzyResponse<ServerModel>> observable = OkGo.get("").getCall(new JsonConvert<LzyResponse<ServerModel>>() {}, RxAdapter.<LzyResponse<ServerModel>>create());
    }

    @OnClick(R.id.btn2)
    public void btn2(View view) {
        OkGo.get("http://app.mi.com/download/433097?ref=search")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        System.out.println("---onSuccess--");
                    }

                    @Override
                    public void onError(okhttp3.Call call, Response response, Exception e) {
                        e.printStackTrace();
                        System.out.println("--onError---");
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        System.out.println(currentSize + " " + totalSize + " " + progress);
                    }
                });
    }

    @OnClick(R.id.btn3)
    public void btn3(View view) {
    }
}