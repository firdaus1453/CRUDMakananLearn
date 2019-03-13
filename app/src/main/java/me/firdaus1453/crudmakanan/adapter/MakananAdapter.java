package me.firdaus1453.crudmakanan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firdaus1453.crudmakanan.R;
import me.firdaus1453.crudmakanan.model.kategorimakanan.DataItem;

/**
 * Created by firdaus1453 on 3/12/2019.
 */
public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    Integer listViewType;
    private final Context context;
    private final List<DataItem> makananItemList;

    public MakananAdapter(Integer listViewType, Context context, List<DataItem> makananItemList) {
        this.context = context;
        this.makananItemList = makananItemList;
        this.listViewType = listViewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_news, null);
                return new ItemPertamaViewHolder(view);
            case TYPE_2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_populer, null);
                return new ItemKeduaViewHolder(view);
            case TYPE_3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_kategory, null);
                return new ItemKetigaViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataItem makananItem = makananItemList.get(position);

        int viewType = listViewType;
        switch (viewType) {
            case TYPE_1:
                ItemPertamaViewHolder itemPertamaViewHolder = (ItemPertamaViewHolder) holder;
                RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
                Glide.with(context).load(makananItem.getUrlMakanan()).apply(options).into(itemPertamaViewHolder.imgMakanan);
                itemPertamaViewHolder.txtTitle.setText(makananItem.getNamaKategori());
                itemPertamaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                break;
            case TYPE_2:
                ItemKeduaViewHolder itemKeduaViewHolder = (ItemKeduaViewHolder) holder;
                RequestOptions options2 = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
                Glide.with(context).load(makananItem.getUrlMakanan()).apply(options2).into(itemKeduaViewHolder.imgMakanan);
                itemKeduaViewHolder.txtTitle.setText(makananItem.getNamaKategori());
                itemKeduaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

                break;
            case TYPE_3:
                ItemKetigaViewHolder itemKetigaViewHolder = (ItemKetigaViewHolder) holder;
                RequestOptions options3 = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
                Glide.with(context).load(makananItem.getUrlMakanan()).apply(options3).into(itemKetigaViewHolder.image);
                itemKetigaViewHolder.txtNamaKategory.setText(makananItem.getNamaKategori());
                itemKetigaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return makananItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listViewType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ItemPertamaViewHolder extends ViewHolder {
        @BindView(R.id.img_makanan)
        ImageView imgMakanan;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_desc)
        TextView txtDesc;

        public ItemPertamaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    class ItemKeduaViewHolder extends ViewHolder {
        @BindView(R.id.img_makanan)
        ImageView imgMakanan;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_desc)
        TextView txtDesc;

        public ItemKeduaViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ItemKetigaViewHolder extends ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txt_nama_kategory)
        TextView txtNamaKategory;

        public ItemKetigaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
