package com.ziyata.basicmvp.detail;

import android.os.Bundle;

import com.ziyata.basicmvp.model.SingelUserResponse;
import com.ziyata.basicmvp.network.ApiClient;
import com.ziyata.basicmvp.network.ApiInterface;
import com.ziyata.basicmvp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter{

    // TODO 1 Membuat konstruktor dan variable yang dibutuhkan
    private final DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getCliend().create(ApiInterface.class);
    private int id;


    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingelUser(Bundle bundle) {
        // TODO 2 Mengecek bundle apakah ada isinya atau tidak
        if (bundle != null){
            // Mengambil id dan dimasukkan ke dalama variable id
            id = bundle.getInt(Constants.KEY_ID);

            // TODO 3 Menampilkan data dengan id
            // Tampilkan progress dialog
            view.showProgrees();

            Call<SingelUserResponse> call = apiInterface.getDataSingleUser(id);
            call.enqueue(new Callback<SingelUserResponse>() {
                @Override
                public void onResponse(Call<SingelUserResponse> call, Response<SingelUserResponse> response) {
                    // Menutup progress dialog
                    view.hideProgress();

                    // Mengecek response body
                    if (response.body() != null){
                        // Memasukkan response body ke dalam SingelUserResponse
                        SingelUserResponse singelUserResponse = response.body();
                        // Mengecek apakah singelUserResponse data ada isinya?
                        if (singelUserResponse.getUserData() != null){
                            // Mengirimkan data single user ke view untuk di tampilkan
                            view.showDataSingleUser(singelUserResponse.getUserData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<SingelUserResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());

                }
            });
        }


    }
}
