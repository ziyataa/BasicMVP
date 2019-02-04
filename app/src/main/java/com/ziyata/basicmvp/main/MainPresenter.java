package com.ziyata.basicmvp.main;

import com.ziyata.basicmvp.model.UserResponse;
import com.ziyata.basicmvp.network.ApiClient;
import com.ziyata.basicmvp.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;
    private ApiInterface apiInterface = ApiClient.getCliend().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListUSer() {
        // TODO 2 Menampilkan progress dialog
        view.showProgress();

        // TODO 3 merequest data ke API
        // Membuat object call untuk mendetting endpoint dan parameter yang di butuhkan
        Call<UserResponse> call = apiInterface.getDataUser(9);

        // Menjalankan request ke API
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Menutup progress
                view.hideProgress();

                // Mengecek data response body
                if (response.body() != null){
                    UserResponse userResponse = response.body();

                    // Mengecek data user
                    if (userResponse.getUserDataList() != null){
                        // Mengirimkan data list user ke view untuk di tampilkan
                        view.showDataListUser(userResponse.getUserDataList());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Menutup progress
                view.hideProgress();
                // Menampilkan pesan penolakan server ke view
                view.showFailureMessage(t.getMessage());

            }
        });
    }
}
