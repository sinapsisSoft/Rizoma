package com.sinapsissoft.rizoma.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinapsissoft.rizoma.DetailCrop;
import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.my_class.FirebaseReferences;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCrops extends RecyclerView.Adapter<AdapterCrops.MyViewHolder>  {

    public List<Crops> cropsList;
    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;
    private Activity myActivity;


    public AdapterCrops(Activity activity) {
        cropsList = new ArrayList<>();
        this.myActivity=activity;
    }

    public void setDataSet(List<Crops> list) {
        cropsList = list;
        notifyDataSetChanged();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvProductName;
        private TextView tvProductDescription;
        private CircleImageView imgProductImage;
        private ImageView imgBtnDetail;


        public MyViewHolder(View view) {
            super(view);

            tvProductName =  view.findViewById(R.id.tv_product_name);
            tvProductDescription =  view.findViewById(R.id.tv_product_description);
            imgProductImage = view.findViewById(R.id.img_product);
            imgBtnDetail =  view.findViewById(R.id.img_btn_detail_crops);


        }

        public void setOnClickListener() {
            imgBtnDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.img_btn_detail_crops:
                    Intent intent=new Intent(myActivity,DetailCrop.class);
                    FirebaseReferences.CROP=cropsList.get(getPosition());
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){


                        myActivity.getWindow().setExitTransition(new Explode() );
                        myActivity.startActivity(intent,
                                ActivityOptionsCompat.makeSceneTransitionAnimation(myActivity,v,myActivity.getString(R.string.transition)).toBundle());
                    }else{
                        myActivity.startActivity(intent);
                    }


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
        Picasso
                .with(context)
                .load(cropsList.get(i).getCropImg())
                .resize(MAX_WIDTH,MAX_HEIGHT).
                centerCrop().
                into(myViewHolder.imgProductImage);

        myViewHolder.setOnClickListener();

    }

    @Override
    public int getItemCount() {

        return cropsList.size();
    }


}
