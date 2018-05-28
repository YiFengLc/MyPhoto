package com.yifeng.myphoto.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yifeng.myphoto.interfaces.OnPhotoClickListener;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class AdviceAdapter extends PagerAdapter
{
    private Context context;
    private List<Integer> list;
    private OnPhotoClickListener listener;
    public AdviceAdapter(Context context,List<Integer> list)
    {
        this.context = context;
        this.list = list;
    }
      // 显示多少个页面
      @Override
      public int getCount() {
          return list.size();
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
          return view == object;
      }

      // 初始化显示的条目对象
      @Override
      public Object instantiateItem(final ViewGroup container, final int position) {
          // return super.instantiateItem(container, position);
          // 准备显示的数据，一个简单的TextView
          final PhotoView iv = new PhotoView(context);
          iv.setImageResource(list.get(position%list.size()));
//          iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//          iv.setOnClickListener(new View.OnClickListener()
//          {
//              @Override
//              public void onClick(View view)
//              {
//                 Toast.makeText(context,position%list.size()+"",Toast.LENGTH_SHORT).show();
//
//              }
//          });
          iv.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener()
          {
              @Override
              public boolean onSingleTapConfirmed(MotionEvent motionEvent)
              {
                  Toast.makeText(context,position%list.size()+"",Toast.LENGTH_SHORT).show();
                  listener.photoClick(motionEvent,position);
                  return true;
              }

              @Override
              public boolean onDoubleTap(MotionEvent ev)
              {
                  float scale = iv.getScale();
                  float x = ev.getX();
                  float y = ev.getY();

                  if (scale < iv.getMediumScale()) {
                      iv.setScale(iv.getMediumScale(), x, y, true);
                  } else if (scale >= iv.getMediumScale() && scale < iv.getMaximumScale()) {
                      iv.setScale(iv.getMaximumScale(), x, y, true);
                  } else {
                      iv.setScale(iv.getMinimumScale(), x, y, true);
                  }
                  return true;
              }

              @Override
              public boolean onDoubleTapEvent(MotionEvent motionEvent)
              {
                  return false;
              }
          });
          iv.setOnLongClickListener(new View.OnLongClickListener()
          {
              @Override
              public boolean onLongClick(View view)
              {
                  Toast.makeText(context,position%list.size()+"",Toast.LENGTH_SHORT).show();
                  return false;
              }
          });
          // 添加到ViewPager容器
          container.addView(iv);
          // 返回填充的View对象
          return iv;
      }

      // 销毁条目对象
      @Override
      public void destroyItem(ViewGroup container, int position, Object object) {
          // super.destroyItem(container, position, object);
          container.removeView((View) object);
      }
      public void setOnPhotoClickListener(OnPhotoClickListener listener)
      {
          this.listener = listener;
      }
  }