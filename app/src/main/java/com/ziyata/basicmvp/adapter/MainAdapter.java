package com.ziyata.basicmvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziyata.basicmvp.R;
import com.ziyata.basicmvp.model.UserData;
import com.ziyata.basicmvp.utils.Constants;
import com.ziyata.basicmvp.view.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final Context context;
    private final List<UserData> userDataList;


    public MainAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserData userData = userDataList.get(i);

        viewHolder.txtFirst.setText(userData.getFirstName());
        viewHolder.txtLast.setText(userData.getLastName());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(userData.getAvatar()).apply(options).into(viewHolder.imgAvatar);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengirim data menggunakan bundle
                // Buat object bundle
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.KEY_ID, userData.getId());

                // Berpindah halaman dengan membawa data
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));
            }
        });


    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAvatar)
        ImageView imgAvatar;
        @BindView(R.id.txtFirst)
        TextView txtFirst;
        @BindView(R.id.txtLast)
        TextView txtLast;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
