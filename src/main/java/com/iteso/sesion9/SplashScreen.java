package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.StoreControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    User usuario;
    StoreControl storeControl;
    ArrayList<Store> stores;
    DataBaseHandler dataBaseHandler;
    Store store1, store2, store3;
    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        storeControl = new StoreControl();
        dataBaseHandler = new DataBaseHandler(this);


        stores = storeControl.getStores(dataBaseHandler);

        if(stores.size() == 0){

            store1 = new Store();
            store2 = new Store();
            store3 = new Store();
            city = new City();

            city.setIdCity(1);
            city.setName("El Salto");

            store1.setId(1);
            store1.setName("BEST BUY");
            store1.setPhone("36734742");
            store1.setThumbnail(2);
            store1.setLatitude(1234.12);
            store1.setLongitude(1463.3);
            store1.setCity(city);
            storeControl.addStore(store1, dataBaseHandler);

            store2.setId(1);
            store2.setName("RadioShack");
            store2.setPhone("36732542");
            store2.setThumbnail(2);
            store2.setLatitude(2323.12);
            store2.setLongitude(1463.3);
            store2.setCity(city);
            storeControl.addStore(store2, dataBaseHandler);

            store3.setId(1);
            store3.setName("Liverpool");
            store3.setPhone("34334742");
            store3.setThumbnail(2);
            store3.setLatitude(3434.12);
            store3.setLongitude(9063.3);
            store3.setCity(city);
            storeControl.addStore(store3, dataBaseHandler);

        }


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("com.example.preferences.PREFERENCES", MODE_PRIVATE);

                usuario = new User();

                usuario.setName(sharedPreferences.getString("USERNAME", null));
                usuario.setPassword(sharedPreferences.getString("PASSWORD", null));
                usuario.setLogged(sharedPreferences.getBoolean("LOGGED", false));

                if(usuario.isLogged() == false ||usuario.getPassword() == null || usuario.getName() == null){
                    //open login activity

                    Intent intent = new Intent(SplashScreen.this, LogIn.class);
                    startActivity(intent);
                    finish();
                }
                else{//open main activity

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }

            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 2000);


    }
}
