package com.muliamaulana.gogadget;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.muliamaulana.gogadget.adapter.PhoneAdapter;
import com.muliamaulana.gogadget.setget.Phone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SonyActivity extends AppCompatActivity {

    ArrayList<Phone> phoneList;
    PhoneAdapter phoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sony);
        setTitle("Sony");

        phoneList = new ArrayList<Phone>();
        new SonyActivity.JSONAsyncTask().execute("http://ibacor.com/api/gsm-arena?view=brand&slug=sony&page=1");

        ListView listview = (ListView) findViewById(R.id.myListView);
        phoneAdapter = new PhoneAdapter(SonyActivity.this, R.layout.list_item_hp, phoneList);

        listview.setAdapter(phoneAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String kodeslug = String.valueOf(phoneList.get(position).getSlug());
                String namaHP = String.valueOf(phoneList.get(position).getTitle());
                String urlImg = String.valueOf(phoneList.get(position).getImage());
                Log.d("Kode Slug ", kodeslug);

                Intent detailPhone = new Intent(SonyActivity.this, DetailPhone.class);
                detailPhone.putExtra("slug", kodeslug);
                detailPhone.putExtra("title", namaHP);
                detailPhone.putExtra("img", urlImg);
                startActivity(detailPhone);
            }
        });
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(SonyActivity.this);
            dialog.setMessage("Please wait...");
//            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("data");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Phone phone = new Phone();
                        phone.setTitle(object.getString("title"));
                        phone.setImage(object.getString("img"));
                        phone.setSlug(object.getString("slug"));
                        phoneList.add(phone);
                    }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            phoneAdapter.notifyDataSetChanged();
            if (result == false)
                Toast.makeText(SonyActivity.this, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }
}
