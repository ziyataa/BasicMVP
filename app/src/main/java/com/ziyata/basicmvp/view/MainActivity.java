package com.ziyata.basicmvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ziyata.basicmvp.R;
import com.ziyata.basicmvp.adapter.MainAdapter;
import com.ziyata.basicmvp.main.MainContract;
import com.ziyata.basicmvp.main.MainPresenter;
import com.ziyata.basicmvp.model.UserData;
import com.ziyata.basicmvp.network.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    // TODO 1 membuat variable yang dibutuhkan
    private ProgressDialog progressDialog;
    private final MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // TODO 2 Mengambil data ke Internet yang dilakukan oleh Presenter
        mainPresenter.getDataListUSer();

        // TODO 5 Membuat swipe refresh listener
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDataListUSer();
            }
        });


    }
    // TODO 3 Mengisi perintah method view yang dibutuhkan
    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void showDataListUser(List<UserData> userDataList) {
        // TODO 4 Mensetting adapter untuk menampilkan data ke RecyclerView
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(new MainAdapter(this, userDataList));

    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
