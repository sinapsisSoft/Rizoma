package com.sinapsissoft.rizoma.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinapsissoft.rizoma.MainActivity;
import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class AdapterCrops extends RecyclerView.Adapter<AdapterCrops.MyViewHolder> {

    public List<Product> productList;
    public AdapterCrops() {
       productList=new ArrayList<>();
    }

    public void setDataSet(List<Product> list){
        productList = list;
        notifyDataSetChanged();

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvProductName;
        private TextView tvProductDescription;
        private ImageView imgProductImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvProductName =(TextView)itemView.findViewById(R.id.tv_product_name);
            tvProductDescription =(TextView)itemView.findViewById(R.id.tv_product_description);
            imgProductImage =(ImageView) itemView.findViewById(R.id.img_product);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_crops,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvProductName.setText(productList.get(i).getProductName());
        myViewHolder.tvProductDescription.setText(productList.get(i).getProducDescription());
        //myViewHolder.imgProductImage.setText(productList.get(i).getProductName());

    }

    @Override
    public int getItemCount() {

        return productList.size();
    }




}
