package com.ssiwo.miglasses.ssiwo.miglasses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ssiwo.miglasses.R;

import com.ssiwo.miglasses.ssiwo.miglasses.bean.ListBean;
import com.ssiwo.miglasses.ssiwo.miglasses.constant.Api;
import com.ssiwo.miglasses.ssiwo.miglasses.utils.GlideRoundTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ssiwo on 2016/11/8.
 */

public class CategroyAdapter extends RecyclerView.Adapter<CategroyAdapter.MyHolder> {


    private List<ListBean.DataBean> showItem;
    private Context mContext;
    private OnMyItemClickListener listener;

    public CategroyAdapter(List<ListBean.DataBean> showItem, Context mContext) {
        this.showItem = showItem;
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final ListBean.DataBean dataBean = showItem.get(position);
        Glide.with(mContext).load(Api.BASE_URL + dataBean.getCate_icon())
                .transform(new GlideRoundTransform(mContext,20))
                .into(holder.ivMain);
//        switch (position) {
//            case 0:
//                holder.ivMain.setBackgroundResource(R.drawable.one);
//                break;
//            case 1:
//                holder.ivMain.setBackgroundResource(R.drawable.two);
//                break;
//            case 2:
//                holder.ivMain.setBackgroundResource(R.drawable.three);
//                break;
//            case 3:
//                holder.ivMain.setBackgroundResource(R.drawable.four);
//                break;
//            case 4:
//                holder.ivMain.setBackgroundResource(R.drawable.firth);
//                break;
//        }
        holder.tvDes.setText(dataBean.getCat_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onMyItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return showItem.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_main)
        ImageView ivMain;
        @Bind(R.id.tv_des)
        TextView tvDes;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnMyItemClickListener {
        void onMyItemClick(int position);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        this.listener = onMyItemClickListener;
    }
}
