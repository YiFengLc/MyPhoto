package com.yifeng.myphoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yifeng.myphoto.adapter.RecycleViewAdapter;
import com.yifeng.myphoto.entity.Bean;
import com.yifeng.myphoto.interfaces.OnItemClickLinstener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逸风 on 2018/5/27.
 */

public class MainActivity extends AppCompatActivity
{
    private List<Integer> list = new ArrayList<>();
    private XRecyclerView recyclerView;
    private RecycleViewAdapter xAdapter;
    private Bean bean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        xAdapter = new RecycleViewAdapter(bean.getPhoto());
        recyclerView.setAdapter(xAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        xAdapter.setmOnItemClickLinstener(new OnItemClickLinstener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
//                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,PhotoViewActivity.class);
                intent.putExtra("currentPosition",position-1);
                intent.putExtra("photo",bean);
                startActivity(intent);
            }
        });
    }

    private void initData()
    {
        bean = new Bean();
        for(int i =0;i<9;i++)
        {
            bean.getPhoto().add(R.drawable.bg);
        }
    }
}
