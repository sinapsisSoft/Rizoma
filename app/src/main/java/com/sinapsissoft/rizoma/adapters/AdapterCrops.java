package com.sinapsissoft.rizoma.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sinapsissoft.rizoma.DetailCrop;
import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.dto.Product;
import java.util.ArrayList;
import java.util.List;

public class AdapterCrops extends RecyclerView.Adapter<AdapterCrops.MyViewHolder>  {

    public List<Product> productList;


    public AdapterCrops() {
        productList = new ArrayList<>();
    }

    public void setDataSet(List<Product> list) {
        productList = list;
        notifyDataSetChanged();

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvProductName;
        private TextView tvProductDescription;
        private ImageView imgProductImage;
        private ImageView imgBtnDetail;
        Context context;

        public MyViewHolder(View view) {
            super(view);
            context=view.getContext();
            tvProductName = (TextView) view.findViewById(R.id.tv_product_name);
            tvProductDescription = (TextView) view.findViewById(R.id.tv_product_description);
            imgProductImage = (ImageView) view.findViewById(R.id.img_product);
            imgBtnDetail = (ImageView) view.findViewById(R.id.img_btn_detail_crops);

        }

        public void setOnClickListener() {
            imgBtnDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_btn_detail_crops:
                    Intent intent=new Intent(context,DetailCrop.class);
                    context.startActivity(intent);

                    break;
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_crops, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvProductName.setText(productList.get(i).getProductName());
        myViewHolder.tvProductDescription.setText(productList.get(i).getProducDescription());
        myViewHolder.imgProductImage.setImageResource(productList.get(i).getProductImgId());
        myViewHolder.setOnClickListener();

    }

    @Override
    public int getItemCount() {

        return productList.size();
    }


}
