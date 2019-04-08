package com.iteso.sesion9;


import android.content.Intent;
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
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTechnology extends Fragment {



    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private ArrayList<ItemProduct> myDataSet;
    ArrayList<ItemProduct> myDataSet = new ArrayList<ItemProduct>();
    ItemProductControl itemProductControl;
    DataBaseHandler dataBaseHandler;

    public FragmentTechnology() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        RecyclerView recyclerView =
                view.findViewById(R.id.fragment_recycler);


        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //ArrayList<ItemProduct> myDataSet = new ArrayList<ItemProduct>();
        itemProductControl = new ItemProductControl();
        dataBaseHandler = new DataBaseHandler(getContext());

        //ItemProduct itemProduct = new ItemProduct();
        myDataSet = itemProductControl.getItemProductsByCategory(1, dataBaseHandler);

        //itemProduct.setTitle("MacBook Pro 17");
        //itemProduct.setStore("BestBuy");
        //itemProduct.setLocation("Zapopan, Jalisco");
        //itemProduct.setPhone("33 12345678");
        //itemProduct.setImage(0);
        //myDataSet.add(itemProduct);

        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        itemProductControl = null;
        return view;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ItemProduct itemProduct = data.getParcelableExtra("result");;
        Iterator<ItemProduct> iterator = myDataSet.iterator();
        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                myDataSet.set(position, itemProduct);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();
    }

}
