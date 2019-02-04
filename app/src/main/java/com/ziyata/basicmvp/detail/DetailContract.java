package com.ziyata.basicmvp.detail;

import android.os.Bundle;

import com.ziyata.basicmvp.model.UserData;

public interface DetailContract {
    interface View{
        void showProgrees();
        void hideProgress();
        void showDataSingleUser(UserData userData);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataSingelUser(Bundle bundle);

    }
}
