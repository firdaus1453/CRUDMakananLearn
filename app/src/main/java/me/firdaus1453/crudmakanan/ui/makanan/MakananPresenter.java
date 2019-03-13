package me.firdaus1453.crudmakanan.ui.makanan;

import android.content.Context;

import me.firdaus1453.crudmakanan.data.remote.ApiClient;
import me.firdaus1453.crudmakanan.data.remote.ApiInterface;
import me.firdaus1453.crudmakanan.model.kategorimakanan.KategoriResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by firdaus1453 on 3/12/2019.
 */
public class MakananPresenter implements MakananContract.Presenter {

    private final MakananContract.View view;
    private ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MakananPresenter(MakananContract.View view, Context context) {
        this.view = view;
    }

    @Override
    public void getListKategori() {
        view.showProgress();
        Call<KategoriResponse> call = mApiInterface.getKategori();
        call.enqueue(new Callback<KategoriResponse>() {
            @Override
            public void onResponse(Call<KategoriResponse> call, Response<KategoriResponse> response) {
                view.hideProgress();
                if (response.body() != null){
                    view.showDataList(response.body().getData());
                }else {
                    view.showFailureMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<KategoriResponse> call, Throwable throwable) {
                view.hideProgress();
                view.showFailureMessage(throwable.getMessage());
            }
        });
    }
}
