package me.firdaus1453.crudmakanan.ui.makanan;

import java.util.List;

import me.firdaus1453.crudmakanan.model.kategorimakanan.DataItem;

/**
 * Created by firdaus1453 on 3/12/2019.
 */
public interface MakananContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<DataItem> kategoriList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getListKategori();
    }
}
