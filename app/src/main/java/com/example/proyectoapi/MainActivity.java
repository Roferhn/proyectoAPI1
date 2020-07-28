package com.example.proyectoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView TVResult;
    private RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TVResult = findViewById(R.id.TVResult);
        Button BtnSync = findViewById(R.id.BtnSync);
        queue= Volley.newRequestQueue(this);

        BtnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDatos();
            }
        });

    }




    private void GetDatos(){

        String url = "https://api.rawg.io/api/games";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray Juegos = response.getJSONArray("results");

                    for(int i=0; i<Juegos.length(); i++){
                        JSONObject object = Juegos.getJSONObject(i);
                        String name = object.getString("name");

                        TVResult.append(name + "\n\n");
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            }


        });
        queue.add(request);
    }



}