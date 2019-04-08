package com.iteso.sesion9;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.sesion9.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {

    EditText title, store, location, phone;
    ImageView image;
    ItemProduct itemProduct;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        final ItemProduct itemProduct = getIntent().getParcelableExtra("ITEM");
        title = (EditText) findViewById(R.id.etTitle);
        store = (EditText) findViewById(R.id.etStore);
        location = (EditText) findViewById(R.id.etLocation);
        phone = (EditText) findViewById(R.id.etPhone);
        image = (ImageView) findViewById(R.id.imgProduct);
        btnSave = (Button) findViewById(R.id.btnsave);
        btnCancel = (Button) findViewById(R.id.btncancel);

        title.setText(itemProduct.getTitle());
        //store.setText(itemProduct.getStore());
        //location.setText(itemProduct.getLocation());
        //phone.setText(itemProduct.getPhone());
        switch(itemProduct.getImage()){
            case 0:
                image.setImageResource(R.drawable.mac); break;
            case 1:
                image.setImageResource(R.drawable.pillows); break;
            case 2:
                image.setImageResource(R.drawable.pillows); break;
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.btnsave){
                    itemProduct.setTitle(title.getText().toString());
                    //itemProduct.setStore(store.getText().toString());
                    //itemProduct.setLocation(location.getText().toString());
                    //itemProduct.setPhone(phone.getText().toString());
                    Intent intentResult = new Intent();
                    intentResult.putExtra("result", itemProduct);
                    setResult(Activity.RESULT_OK, intentResult);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });





    }



}
