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
import android.widget.Toast;

import com.sinapsissoft.rizoma.DetailCrop;
import com.sinapsissoft.rizoma.LoginActivity;
import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.dto.FirebaseReferences;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class AdapterCrops extends RecyclerView.Adapter<AdapterCrops.MyViewHolder>  {

    public List<Crops> cropsList;
    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;
    public static final String EXTRA_MESSAGE_LOGIN = "com.sinapsissoft.rizoma.CROPS";


    public AdapterCrops() {
        cropsList = new ArrayList<>();
    }

    public void setDataSet(List<Crops> list) {
        cropsList = list;
        notifyDataSetChanged();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
                    FirebaseReferences.CROP=cropsList.get(getPosition());
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
        Context context=myViewHolder.imgProductImage.getContext();
        myViewHolder.tvProductName.setText(cropsList.get(i).getCropName());
        myViewHolder.tvProductDescription.setText(cropsList.get(i).getCropDescription());
        myViewHolder.imgBtnDetail.setTag(cropsList.get(i).getCropId());
        Picasso.with(context).load(cropsList.get(i).getCropImg()).resize(MAX_WIDTH,MAX_HEIGHT).centerCrop().into(myViewHolder.imgProductImage);

        myViewHolder.setOnClickListener();

    }

    @Override
    public int getItemCount() {

        return cropsList.size();
    }


}
