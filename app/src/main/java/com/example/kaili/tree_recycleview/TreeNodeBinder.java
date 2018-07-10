package com.example.kaili.tree_recycleview;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaili.tree_recycleview.tree_view.TreeNode;
import com.example.kaili.tree_recycleview.tree_view.TreeViewBinder;


/**
 * Created by tlh on 2016/10/1 :)
 */

public class TreeNodeBinder extends TreeViewBinder<TreeNodeBinder.ViewHolder> {

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, final TreeNode node) {
        holder.ivArrow.setRotation(0);
        holder.ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_black_18dp);
        int rotateDegree = node.isExpand() ? 90 : 0;
        holder.ivArrow.setRotation(rotateDegree);
        final TreeData treeNode = (TreeData) node.getContent();
        holder.tvName.setText(treeNode.name);
        if (node.isLeaf())
            holder.ivArrow.setVisibility(View.INVISIBLE);
        else
            holder.ivArrow.setVisibility(View.VISIBLE);
        holder.cbChecked.setChecked(treeNode.getChecked());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tree;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView ivArrow;
        private TextView tvName;
        private CheckBox cbChecked;

        public ViewHolder(View rootView) {
            super(rootView);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.cbChecked = (CheckBox) rootView.findViewById(R.id.cb_checked);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getChecked() {
            return cbChecked;
        }
    }
}
