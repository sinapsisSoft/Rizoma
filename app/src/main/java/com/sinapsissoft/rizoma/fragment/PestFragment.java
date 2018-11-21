package com.sinapsissoft.rizoma.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sinapsissoft.rizoma.R;
import com.sinapsissoft.rizoma.adapters.AdapterPest;
import com.sinapsissoft.rizoma.dto.Pest;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerViewPest;
    private AdapterPest adapterPest;
    private ProgressBar mProgressBarLoading;
    private Pest pest;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PestFragment newInstance(String param1, String param2) {
        PestFragment fragment = new PestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view=inflater.inflate(R.layout.fragment_pests,container,false);
        recyclerViewPest=view.findViewById(R.id.recycle_view_pest);
        mProgressBarLoading=view.findViewById(R.id.progressBarLoading);
        recyclerViewPest.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Integer> arrayListImg=new ArrayList<>();
        arrayListImg.add(R.drawable.pulgon_negro);
        arrayListImg.add(R.drawable.pulguilla_remolacha);
        arrayListImg.add(R.drawable.mosca_reolacha);

        ArrayList<String> arrayListTitle=new ArrayList<>();
        arrayListTitle.add("Pulgón negro");
        arrayListTitle.add("Pulguilla de la remolacha");
        arrayListTitle.add("Mosca de la remolacha");

        ArrayList<String> arrayListDescription=new ArrayList<>();
        arrayListDescription.add("Los pulgones son insectos pequeños de apenas unos milímetros de largo, pero fáciles de ver a simple vista. Pertenecen al orden de los hemípteros como las cochinillas y las moscas blancas, que tal vez te resulten familiares. Al igual que éstas, se alimentan de los jugos de las plantas. Su aparato bucal picador y chupador les sirve para perforar la planta y succionar su savia");
        arrayListDescription.add("Pulguilla de la remolacha");
        arrayListDescription.add("Mosca de la remolacha");

        ArrayList<String> arrayListSolution=new ArrayList<>();
        arrayListSolution.add("Si esto te fuera imposible por la cantidad de plantas afectadas, puedes aplicar un tratamiento con jabón potásico. Diluyes este producto al 2% en agua (o lo que es lo mismo, 20ml. de jabón por cada litro de agua) y lo pulverizas sobre las zonas afectadas, verás que enseguida notas mejoría. A pesar de ello deberás volver a tratar a las 2 semanas para asegurar la eliminación del pulgón. Yo te aconsejo que hagas al menos 3 aplicaciones, respetando estos plazos de tiempo. No olvides rociar bien la parte posterior de las hojas.;");
        arrayListSolution.add("Pulguilla de la remolacha");
        arrayListSolution.add("Mosca de la remolacha");

        final ArrayList<Pest> listPest = new ArrayList<>();
        for(int i=0; i<3; i++){
            pest=new Pest();
            pest.setPestId(i);
            pest.setPestName("Plaga: "+arrayListTitle.get(i));
            pest.setPestDescription("Descripción del plaga  "+arrayListDescription.get(i));
            pest.setPestCtrlName("Descripción del la solución "+arrayListSolution.get(i));
            pest.setPestImgId(arrayListImg.get(i));
            listPest.add(pest);
        }
        adapterPest=new AdapterPest();
        adapterPest.setDataSet(listPest);
        recyclerViewPest.setAdapter(adapterPest);



        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
