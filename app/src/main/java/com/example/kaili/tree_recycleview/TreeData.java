package com.example.kaili.tree_recycleview;


import com.example.kaili.tree_recycleview.tree_view.LayoutItemType;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class TreeData implements LayoutItemType {
    public String name;
    public Boolean isChecked=false;

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }


    public TreeData(String name, Boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public TreeData(String name) {
        this.name = name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tree;
    }
}
