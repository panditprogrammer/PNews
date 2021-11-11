
package com.panditprogrammer.pnews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    String url;
    ImageView newsImage;
    JSONObject jsonObject;
    News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find the recyclerview layout
        RecyclerView recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsImage = findViewById(R.id.newsImageId);

        ArrayList<News> newsArrayList = new ArrayList<>();

        // Instantiate the RequestQueue.
        RequestQueue requestQueue =CustomSingleton.getInstance(this).getRequestQueue();
        url ="http://api.mediastack.com/v1/news?access_key=7d8c1d9dcb87f1dfaa38db01ae5d982e&countries=in";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        //jsonObject = response.getJSONObject("articles");  // get the string url from json object
                        try {

                            // get the array from jsonObject first time
                            JSONArray jsonArray =  response.getJSONArray("data");

                            for (int i = 0; i< jsonArray.length(); i++){
                                // get the inner jsonObject of jsonArray
                                jsonObject = jsonArray.getJSONObject(i);

                                // create an object and set the value from api
                                news = new News(
                                        jsonObject.getString("description"),
                                        jsonObject.getString("title"),
                                        jsonObject.getString("author"),
                                        jsonObject.getString("url"),
                                        jsonObject.getString("image")
                                );
                                    newsArrayList.add(news);
                            }

                            // create object of NewsListAdapter class and pass arrayList
                            NewsListAdapter newsListAdapter = new NewsListAdapter(MainActivity.this,newsArrayList);
                            // show the data in recyclerView
                            recyclerView.setAdapter(newsListAdapter);
                            Log.i("json", "onResponse: Title of news is "+news.getTitle());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Unable to load data", Toast.LENGTH_SHORT).show();
                            }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Network Problem Detect ", Toast.LENGTH_SHORT).show();

                    }});

        requestQueue.add(jsonObjectRequest);
    }


}