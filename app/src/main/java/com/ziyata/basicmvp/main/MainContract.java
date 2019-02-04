package com.ziyata.basicmvp.main;

import com.ziyata.basicmvp.model.UserData;

import java.util.List;

public interface MainContract {
    interface View{
        void showProgress();
        void hideProgress();

        // Menampilkan data list user ke view RecycleView
        void showDataListUser(List<UserData> userDataList);

        // Menampilkan pesan gagal
        void showFailureMessage(String msg);

    }

    interface Presenter{
        // Membuat method interface untuk mengambil data dari API
        void getDataListUSer();

    }
}
