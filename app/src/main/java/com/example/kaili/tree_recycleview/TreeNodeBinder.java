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
        holder.ivArrow.setImageResource(R.drawable.arrow_black_down);
        int rotateDegree = node.isExpand() ? 180 : 0;
        holder.ivArrow.setRotation(rotateDegree);
        final TreeData treeNode = (TreeData) node.getContent();
        holder.tvName.setText(treeNode.name);
        if (node.isLeaf()) {
            holder.itemView.setBackgroundResource(R.color.black);
            holder.ivArrow.setVisibility(View.INVISIBLE);
            holder.tvCount.setVisibility(View.GONE);
            holder.line.setVisibility(View.VISIBLE);
        } else {
            int count = 0;
            for (int i = 0; i < node.getChildList().size(); i++) {
                if (((TreeData) ((TreeNode) (node.getChildList().get(i))).getContent()).getChecked()) {
                    count++;
                }
            }
            holder.itemView.setBackgroundResource(R.color.white);
            holder.ivArrow.setVisibility(View.VISIBLE);
            if (count > 0) {
                holder.tvCount.setText("(" + count + ")");
                holder.tvCount.setVisibility(View.VISIBLE);
            } else {
                holder.tvCount.setVisibility(View.GONE);
            }
            holder.line.setVisibility(View.GONE);
        }
        holder.cbChecked.setChecked(treeNode.getChecked());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tree;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView ivArrow;
        private TextView tvName;
        private TextView tvCount;
        private CheckBox cbChecked;
        private View line;

        public ViewHolder(View rootView) {
            super(rootView);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvCount = (TextView) rootView.findViewById(R.id.tv_count);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.cbChecked = (CheckBox) rootView.findViewById(R.id.cb_checked);
            this.line = (View) rootView.findViewById(R.id.line);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvCount() {
            return tvCount;
        }

        public View getLine() {
            return line;
        }

        public TextView getChecked() {
            return cbChecked;
        }
    }
}
