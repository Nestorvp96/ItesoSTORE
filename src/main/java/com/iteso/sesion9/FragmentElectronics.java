package com.iteso.sesion9;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.ItemProductControl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentElectronics extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ItemProductControl itemProductControl;
    DataBaseHandler dataBaseHandler;

    public FragmentElectronics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        RecyclerView recyclerView =
                view.findViewById(R.id.fragment_recycler);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        itemProductControl = new ItemProductControl();
        dataBaseHandler = new DataBaseHandler(getContext());
        ArrayList<ItemProduct> myDataSet = new ArrayList<ItemProduct>();
        //ItemProduct itemProduct = new ItemProduct();
        //itemProduct.setTitle("Microondas");
        //itemProduct.setStore("View");
        //itemProduct.setLocation("Gdl, Jalisco");
        //itemProduct.setPhone("33 12345678");
        //itemProduct.setImage(3);
        //myDataSet.add(itemProduct);

        myDataSet = itemProductControl.getItemProductsByCategory(3, dataBaseHandler);

        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        itemProductControl = null;

        return view;
    }

}
