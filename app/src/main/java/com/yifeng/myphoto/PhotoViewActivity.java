package com.yifeng.myphoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yifeng.myphoto.adapter.AdviceAdapter;
import com.yifeng.myphoto.entity.Bean;
import com.yifeng.myphoto.interfaces.OnPhotoClickListener;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private PhotoView p;
    private int currentPosition;
    private AdviceAdapter adapter;
    private TextView mTvImageCount;
    private LinearLayout layout;
    private List<Integer> Urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.photo_viewPager);
        mTvImageCount = (TextView) findViewById(R.id.count);

    }

    private void initData() {

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);
        Bean bean = (Bean) intent.getSerializableExtra("photo");
        Urls = bean.getPhoto();

        adapter = new AdviceAdapter(PhotoViewActivity.this,Urls);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mTvImageCount.setText(currentPosition+1 + "/" + Urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
            }
        });
        adapter.setOnPhotoClickListener(new OnPhotoClickListener()
        {
            @Override
            public void photoClick(MotionEvent motionEvent, Integer position)
            {
                finish();
            }
        });

    }

}