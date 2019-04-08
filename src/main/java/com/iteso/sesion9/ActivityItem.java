package com.iteso.sesion9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.database.CategoryControl;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.ItemProductControl;
import com.iteso.sesion9.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {


    Spinner spnImagenes, spnCategorias, spnTiendas;
    ArrayList<Category> categories;
    ArrayList<Store> stores;
    DataBaseHandler dataBaseHandler;
    CategoryControl categoryControl;
    StoreControl storeControl;
    Button btnGuardar;
    EditText etTitle, etDescripcion;
    ItemProductControl itemProductControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        spnImagenes = findViewById(R.id.spnImagenes);
        String[] imagenes = getResources().getStringArray(R.array.array_images);
        ArrayAdapter<String> adapterimagenes=new ArrayAdapter<String>(this,R.layout.spinner_design, R.id.tvspinner,imagenes);

        spnImagenes.setAdapter(adapterimagenes);

        categories = new ArrayList<Category>();
        categoryControl = new CategoryControl();
        storeControl = new StoreControl();
        dataBaseHandler = new DataBaseHandler(this);

       spnCategorias = findViewById(R.id.spnCategorias);
       categories = categoryControl.getCategories(dataBaseHandler);

       spnTiendas = findViewById(R.id.spnTiendas);
       stores = storeControl.getStores(dataBaseHandler);

       String[] categorias = new  String[categories.size()];

        for (int j = 0; j < categories.size(); j++) {

            // Assign each value to String array
            categorias[j] = categories.get(j).getName();
            //Toast.makeText(getBaseContext(), categories.get(j).getName()+ "position: " + j, Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adaptercategorias= new ArrayAdapter<String>(this,R.layout.spinner_design, R.id.tvspinner, categorias);
        spnCategorias.setAdapter(adaptercategorias);


        String[] tiendas = new  String[stores.size()];

        for (int j = 0; j < stores.size(); j++) {

            // Assign each value to String array
            tiendas[j] = stores.get(j).getName();
            //Toast.makeText(getBaseContext(), stores.get(j).getName()+ "position: " + j, Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapterstores= new ArrayAdapter<String>(this,R.layout.spinner_design, R.id.tvspinner, tiendas);
        spnTiendas.setAdapter(adapterstores);



        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ItemProduct itemProduct = new ItemProduct();

                itemProduct.setCode(19);

                etTitle = findViewById(R.id.etTitulo);
                itemProduct.setTitle(etTitle.getText().toString());

                etDescripcion = findViewById(R.id.etDescripcion);
                itemProduct.setDescription(etDescripcion.getText().toString());

                //Toast.makeText(getBaseContext(), spnImagenes.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                if(spnImagenes.getSelectedItem().toString().equals("MAC"))
                    itemProduct.setImage(0);
                else if(spnImagenes.getSelectedItem().toString() == "Almohadas")
                    itemProduct.setImage(2);
                else if(spnImagenes.getSelectedItem().toString().equals("Microondas"))
                    itemProduct.setImage(1);

                if(spnCategorias.getSelectedItem().toString().equals("TECHNOLOGY"))
                    itemProduct.setCategory(1);
                else if(spnCategorias.getSelectedItem().toString().equals("HOME"))
                    itemProduct.setCategory(2);
                else if(spnCategorias.getSelectedItem().toString().equals("ELECTRONICS"))
                    itemProduct.setCategory(3);

                if(spnTiendas.getSelectedItem().toString().equals("BEST BUY"))
                    itemProduct.setStore(1);
                else if(spnTiendas.getSelectedItem().toString().equals("RadioShack"))
                    itemProduct.setStore(2);
                else if(spnTiendas.getSelectedItem().toString().equals("Liverpool")) {
                    itemProduct.setStore(3);
                    //mac3Toast.makeText(getBaseContext(), "ENTRO A LIVERPOOL", Toast.LENGTH_SHORT).show();
                }

                itemProductControl = new ItemProductControl();

                itemProductControl.addItemProduct(itemProduct, dataBaseHandler);

                //Intent intent = new Intent(getBaseContext(), MainActivity.class);
                //startActivity(intent);

                Toast.makeText(getBaseContext(), "Producto: "+ itemProduct.getTitle().toString(), Toast.LENGTH_SHORT);
                finish();
            }
        });


    }
}
