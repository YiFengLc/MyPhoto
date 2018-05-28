package com.yifeng.myphoto.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逸风 on 2018/5/27.
 */

public class Bean implements Serializable
{
    private List<Integer> photo = new ArrayList<>();

    public List<Integer> getPhoto()
    {
        return photo;
    }

    public void setPhoto(List<Integer> photo)
    {
        this.photo = photo;
    }
}
