package com.sinapsissoft.rizoma.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.dto.Pest;

import java.util.ArrayList;
import java.util.List;

public class AdapterPest extends RecyclerView.Adapter<AdapterPest.MyViewHolder> {

    public List<Pest> pestList;

    public AdapterPest() {
        pestList = new ArrayList<>();
    }

    public void setDataSet(List<Pest> list) {
        pestList = list;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPestName;
        private TextView tvPestDescription;
        private TextView tvPestDescriptionSolution;
        private ImageView imgPestImage;
        Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvPestName = itemView.findViewById(R.id.name_pest);
            tvPestDescription = itemView.findViewById(R.id.description_pest);
            tvPestDescriptionSolution=itemView.findViewById(R.id.description_pest_solution);
            imgPestImage = itemView.findViewById(R.id.image_pest);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_pest, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvPestName.setText(pestList.get(i).getPestName());
        myViewHolder.tvPestDescription.setText(pestList.get(i).getPestDescription());
        myViewHolder.tvPestDescriptionSolution.setText(pestList.get(i).getPestCtrlName());
        //myViewHolder.tvPestName.setText(pestList.get(i).getPestName());
        myViewHolder.imgPestImage.setImageResource(pestList.get(i).getPestImgId());
    }

    @Override
    public int getItemCount() {
        return pestList.size();
    }


}
