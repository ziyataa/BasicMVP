package com.ziyata.basicmvp.login;

public interface LoginContract {
    // Membuat interface untuk method yg dibutuhkan pada view
    interface View{

        // Menampilkan dan menutup progress loading dialog
        void showProgress();
        void hideProgress();

        // Menampilkan dan melakukan sesuatu pada saat server merespon berhasil ataupun  gagal
        void loginFailure(String msg);
        void loginSuccess(String token5);
    }

    // Membuat interface untuk method yang dibutuhkan pada Presenter / Mediator dengan model(Bisnis Logic)
    interface Presenter{
        // Membuat method untuk berkomunikasi dengan model
        // Method untuk mengeksekusi bisnis logic untuk login contoh pengecekan data dan pengiriman data ke internet
        void doLogin(String email, String password);

    }
}
