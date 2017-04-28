package com.silver.chat.entity;

import com.silver.chat.view.recycleview.entity.MultiItemEntity;

/**
 * Created by joe on 2017/4/25.
 * 群组姓名和头像的bean
 */

public class GroupEntity extends BaseBean implements MultiItemEntity {
    public static final int GROUP_TYPE = 1;
    public static final int GROUP_NAME = 2;
    private String name;
    private String ImageUrl;

    public GroupEntity(String name, String imageUrl,int itemype) {
        this.name = name;
        this.ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
