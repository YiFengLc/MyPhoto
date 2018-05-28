package com.yifeng.myphoto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yifeng.myphoto.R;
import com.yifeng.myphoto.interfaces.OnItemClickLinstener;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    List<Integer> data=new ArrayList<Integer>();
    private OnItemClickLinstener mOnItemClickLinstener;
    static class MyViewHolder extends RecyclerView.ViewHolder{  
        ImageView photo;

        public MyViewHolder(View itemView) {
            super(itemView);  
            this.photo=itemView.findViewById(R.id.photo);

        }
    }  
  
    public RecycleViewAdapter(List<Integer> data) {
        super();  
        this.data=data;  
    }  
  
    @Override  
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);  
        return myViewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.photo.setImageResource(data.get(position));
        if(mOnItemClickLinstener!=null)
        {
            holder.photo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mOnItemClickLinstener.onItemClick(holder.photo,holder.getLayoutPosition());
                }
            });
        }
    }  
  
    @Override  
    public int getItemCount() {  
        return data.size();  
    }

    public void setmOnItemClickLinstener(OnItemClickLinstener listener)
    {
        this.mOnItemClickLinstener = listener;
    }
} 