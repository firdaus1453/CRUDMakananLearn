package me.firdaus1453.crudmakanan.ui.makanan;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.firdaus1453.crudmakanan.R;
import me.firdaus1453.crudmakanan.adapter.MakananAdapter;
import me.firdaus1453.crudmakanan.model.kategorimakanan.DataItem;
import me.firdaus1453.crudmakanan.utils.StartSnapHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakananFragment extends Fragment implements MakananContract.View {


    @BindView(R.id.rv_kategori)
    RecyclerView rvKategori;
    @BindView(R.id.rv_makanan_news)
    RecyclerView rvMakananNews;
    @BindView(R.id.rv_makanan_populer)
    RecyclerView rvMakananPopuler;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    private ProgressDialog progressDialog;
    private MakananPresenter mMakananPresenter = new MakananPresenter(this, getContext());
    public MakananFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_makanan, container, false);
        unbinder = ButterKnife.bind(this, view);

        mMakananPresenter.getListKategori();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMakananPresenter.getListKategori();

            }
        });

        return view;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        progressDialog.dismiss();
    }

    @Override
    public void showDataList(List<DataItem> kategoriList) {
        Integer listViewType1 = MakananAdapter.TYPE_1;
        rvMakananNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMakananNews.setAdapter(new MakananAdapter(listViewType1,getContext(), kategoriList));

        Integer listViewType2 = MakananAdapter.TYPE_2;
        rvMakananPopuler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMakananPopuler.setAdapter(new MakananAdapter(listViewType2,getContext(), kategoriList));

        Integer listViewType3 = MakananAdapter.TYPE_3;
        rvKategori.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvKategori.setAdapter(new MakananAdapter(listViewType3,getContext(), kategoriList));

//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(rvKategori);

        SnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(rvKategori);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
