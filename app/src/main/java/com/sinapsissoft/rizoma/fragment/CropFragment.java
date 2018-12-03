package com.sinapsissoft.rizoma.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.my_class.FirebaseReferences;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CropFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CropFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CropFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final int MAX_WIDTH = 250;
    private static final int MAX_HEIGHT = 250;

    private OnFragmentInteractionListener mListener;

    public CropFragment() {
        // Required empty public constructor
    }


    public static CropFragment newInstance(String param1, String param2) {
        CropFragment fragment = new CropFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Please note the third parameter should be false, otherwise a java.lang.IllegalStateException maybe thrown.
        View retView = inflater.inflate(R.layout.fragment_view_crop, container, false);
        loadDataDetail(retView);
        return retView;
    }
    public void loadDataDetail(View view){
        Crops crops=FirebaseReferences.CROP;
        ImageView imgViewCrop=view.findViewById(R.id.image_crop);
        TextView tViewCropName=view.findViewById(R.id.name_crops);
        TextView tViewCropNameScientific=view.findViewById(R.id.name_crops_scientific);
        TextView tVCropDescription=view.findViewById(R.id.description_product);

        Picasso.with(getContext()).load(crops.getCropImg()).fit().into(imgViewCrop);
        tViewCropName.setText(crops.getCropName());
        tViewCropNameScientific.setText(crops.getCropNameScientific());
        tVCropDescription.setText(crops.getCropDescription());

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
