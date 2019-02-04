package com.ziyata.basicmvp.login;

import com.ziyata.basicmvp.model.LoginBody;
import com.ziyata.basicmvp.model.LoginResponse;
import com.ziyata.basicmvp.network.ApiClient;
import com.ziyata.basicmvp.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{

    // TODO 1 Menyiapkan variable yang dibutuhkan
    // Membuat object apiInterface untuk mensetting baseUrl retrofit
    private ApiInterface apiInterface = ApiClient.getCliend().create(ApiInterface.class);

    // Membuat objeck LoginContract View
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }


    @Override
    public void doLogin(String email, String password) {
        // Mengecek email dan password apakah ada isinya
        if (email == null || email.isEmpty()){
            view.loginFailure("Email kosong");
            return;
        }

        if (password == null || password.isEmpty()){
            view.loginFailure("Password kosong");
            return;
        }

        // Menampilkan progress dialog agar memberitahu ada proses yang sedang berjalan
        view.showProgress();

        // Memasukkan data email dan password ke dalam LoginBody
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        // Mengeksekusi data ke server
        // Membuat object call untuk mengirim loginBody
        Call<LoginResponse> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // menyuruh view menutup progress dialog
                view.hideProgress();

                // Mengecek response apakah ada datanya?
                if (response.body() != null){
                    // Mengambil data response body dan memasukkan ke dalam kelas model LoginResponse
                    LoginResponse loginResponse = response.body();
                    // Mengecek isi token apakah ada isinya isinya? agar tidak force close apabila null
                    if (loginResponse.getToken() != null){
                        // Login success mengirimkan token dan meminta view untuk berpindah halaman
                        view.loginSuccess(loginResponse.getToken());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Menutup progress dialog
                view.hideProgress();
                // Menampilkan pesan
                view.loginFailure(t.getMessage());

            }
        });

    }
}
