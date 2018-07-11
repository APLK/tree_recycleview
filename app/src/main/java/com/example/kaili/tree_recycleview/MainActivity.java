package com.example.kaili.tree_recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaili.tree_recycleview.tree_view.TreeNode;
import com.example.kaili.tree_recycleview.tree_view.TreeViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv;
    private TreeViewAdapter adapter;
    private TextView tv_reset;
    private TextView tv_sure;
    private List<TreeNode> mNodes;
    private ArrayList<TreeData> selectDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        selectDataList = new ArrayList<>();
        mNodes = new ArrayList<>();
        TreeNode<TreeData> app = new TreeNode<>(new TreeData("testMain1", false));
        mNodes.add(app);
        app.addChild(
                new TreeNode<>(new TreeData("testChild11", false))
        ).addChild(new TreeNode<>(new TreeData("testChild12", true)))
                .addChild(new TreeNode<>(new TreeData("testChild13", false)))
                .addChild(new TreeNode<>(new TreeData("testChild14", false)))
                .addChild(new TreeNode<>(new TreeData("testChild15", true)));
        TreeNode<TreeData> app2 = new TreeNode<>(new TreeData("testMain2", false));
        mNodes.add(app2);
        app2.addChild(
                new TreeNode<>(new TreeData("testChild21", true))
        ).addChild(new TreeNode<>(new TreeData("testChild22", true)))
                .addChild(new TreeNode<>(new TreeData("testChild23", false)))
                .addChild(new TreeNode<>(new TreeData("testChild24", true)))
                .addChild(new TreeNode<>(new TreeData("testChild25", false)))
                .addChild(new TreeNode<>(new TreeData("testChild26", false)))
                .addChild(new TreeNode<>(new TreeData("testChild27", false)));
        TreeNode<TreeData> app3 = new TreeNode<>(new TreeData("testMain3", false));
        mNodes.add(app3);
        app3.addChild(
                new TreeNode<>(new TreeData("testChild31", false))
        ).addChild(new TreeNode<>(new TreeData("testChild32", true)))
                .addChild(new TreeNode<>(new TreeData("testChild33", true)))
                .addChild(new TreeNode<>(new TreeData("testChild34", false)));

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(mNodes, Arrays.asList(new TreeNodeBinder()));
        // whether collapse child nodes when their parent node was close.
        //        adapter.ifCollapseChildWhileCollapseParent(true);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    //Update and toggle the node.
                    onToggle(!node.isExpand(), holder);
                    //                    if (!node.isExpand())
                    //                        adapter.collapseBrotherNode(node);
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                TreeNodeBinder.ViewHolder dirViewHolder = (TreeNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 180 : -180;
                ivArrow.animate().rotationBy(rotateDegree)
                        .start();
            }

            @Override
            public void onCheckedChanged(TreeNode selectedNode, Boolean isChecked) {
                Log.i("1", "isChecked0=" + isChecked);
                //设置子元素都是isChecked
                if (selectedNode != null) {
                    for (int i = 0; i < selectedNode.getChildList().size(); i++) {
                        ((TreeData) ((TreeNode) (selectedNode.getChildList().get(i))).getContent()).setChecked(!isChecked);
                    }
                }
//                adapter.notifyDataSetChanged();
            }
        });
        rv.setAdapter(adapter);
    }

    private void initView() {

        rv = (RecyclerView) findViewById(R.id.rv);
        tv_reset = (TextView) findViewById(R.id.tv_reset);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDataList.clear();
                if (mNodes != null) {
                    for (int i = 0; i < mNodes.size(); i++) {
                        if (!mNodes.get(i).isLeaf()) {
                            ((TreeData) ((TreeNode) (mNodes.get(i))).getContent()).setChecked(false);
                            for (int j = 0; j < mNodes.get(i).getChildList().size(); j++) {
                                ((TreeData) ((TreeNode) (mNodes.get(i).getChildList().get(j))).getContent()).setChecked(false);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNodes != null) {
                    for (int i = 0; i < mNodes.size(); i++) {
                        if (!mNodes.get(i).isLeaf()) {
                            Log.i("1", "sb222=" + ((TreeData) ((TreeNode) (mNodes.get(i))).getContent()).getChecked());
                            /*if (((TreeData) ((TreeNode) (mNodes.get(i))).getContent()).getChecked()) {
                                selectDataList.add(((TreeData) ((TreeNode) (mNodes.get(i))).getContent()));
                            }*/
                            for (int j = 0; j < mNodes.get(i).getChildList().size(); j++) {
                                if (((TreeData) ((TreeNode) (mNodes.get(i).getChildList().get(j))).getContent()).getChecked()) {
                                    selectDataList.add(((TreeData) ((TreeNode) (mNodes.get(i).getChildList().get(j))).getContent()));
                                }
                            }
                        }
                    }
                }
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < selectDataList.size(); i++) {
                    sb.append(selectDataList.get(i).name).append(",");
                }
                Log.i("1", "sb=" + sb);
                //TODO 跳转的功能,携带数据进行跳转
                selectDataList.clear();
            }
        });
    }

}
