package com.muliamaulana.gogadget;

import android.app.ProgressDialog;
import android.content.Entity;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailPhone extends AppCompatActivity {

    TextView txtNamePhone,
            txtTechnology, txt2GBand,txt3GBand, txt4GBand, txtSpeed, txtGPRS, txtEdge,
            txtAnnounced, txtStatus,
            txtDimensi, txtWeight, txtSim,
            txtTypeDisp, txtSize, txtResolution, txtMultitouch,
            txtOS, txtChipset, txtCPU, txtGPU,
            txtCardSlot,txtInternal,
            txtPrimary, txtSecondary,txtFeaturesCam,txtVideo,
            txtAlertType, txtLoudspeaker, txt35mmjack,
            txtWLAN, txtBluetooth, txtGPS,txtRadio,txtUSB,
            txtTypeBatt, txtTalkTime, txtMusicPlay;

    String nilai_technology, nilai_2G, nilai_3G, nilai_4G, nilai_Speed, nilai_GPRS, nilai_Edge,
            nilai_announced, nilai_status,
            nilai_Dimensi, nilai_Weight, nilai_SIM,
            nilaiTypeDisp, nilaiSize, nilaiResoultion,nilai_Multitouch,
            nilai_os, nilai_chipset, nilai_cpu, nilai_gpu,
            nilai_CardSlot, nilai_internal,
            nilai_primary, nilai_secondary, nilaiFeatureCam, nilai_Video,
            nilai_AlertType, nilai_Loudspeaker, nilai_35mmjack,
            nilai_WLAN, nilai_Bluetooth, nilai_GPS, nilaiRadio, nilai_USB,
            nilai_TypeBatt, nilai_Talktime, nilai_MusicPlay;

    String namaHP,kodeSlug,urlImg;
    ImageView imgPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);
        setTitle("Spesification");

        txtNamePhone = (TextView) findViewById(R.id.namePhone);
        txtTechnology = (TextView) findViewById(R.id.nilai_technology);
        txt2GBand = (TextView) findViewById(R.id.nilai_2g_band);
        txt3GBand = (TextView) findViewById(R.id.nilai_3g_band);
        txt4GBand = (TextView) findViewById(R.id.nilai_4g_band);
        txtSpeed = (TextView) findViewById(R.id.nilai_speed);
        txtGPRS = (TextView) findViewById(R.id.nilai_gprs);
        txtEdge = (TextView) findViewById(R.id.nilai_edge);

        txtAnnounced = (TextView) findViewById(R.id.nilai_announced);
        txtStatus = (TextView) findViewById(R.id.nilai_status);

        txtDimensi = (TextView) findViewById(R.id.nilai_dimensi);
        txtWeight = (TextView) findViewById(R.id.nilai_weight);
        txtSim = (TextView) findViewById(R.id.nilai_sim);

        txtTypeDisp = (TextView) findViewById(R.id.nilai_type_disp);
        txtSize = (TextView) findViewById(R.id.nilai_size);
        txtResolution = (TextView) findViewById(R.id.nilai_resolution);
        txtMultitouch = (TextView) findViewById(R.id.nilai_multitouch);

        txtOS = (TextView)findViewById(R.id.nilai_os);
        txtChipset = (TextView) findViewById(R.id.nilai_chipset);
        txtCPU = (TextView) findViewById(R.id.nilai_cpu);
        txtGPU = (TextView) findViewById(R.id.nilai_gpu);

        txtCardSlot = (TextView) findViewById(R.id.nilai_card_slot);
        txtInternal = (TextView)findViewById(R.id.nilai_internal);

        txtPrimary = (TextView)findViewById(R.id.nilai_primary);
        txtSecondary = (TextView)findViewById(R.id.nilai_secondary);
        txtFeaturesCam = (TextView)findViewById(R.id.nilai_features);
        txtVideo = (TextView) findViewById(R.id.nilai_video);

        txtAlertType = (TextView)findViewById(R.id.nilai_alert_types);
        txtLoudspeaker = (TextView) findViewById(R.id.nilai_loadspeaker);
        txt35mmjack = (TextView) findViewById(R.id.nilai_35mm_jack);

        txtWLAN = (TextView)findViewById(R.id.nilai_wlan);
        txtBluetooth = (TextView) findViewById(R.id.nilai_bluetooth);
        txtGPS = (TextView) findViewById(R.id.nilai_gps);
        txtRadio = (TextView) findViewById(R.id.nilai_radio);
        txtUSB = (TextView)findViewById(R.id.nilai_usb);

        txtTypeBatt = (TextView) findViewById(R.id.nilai_empty);
        txtTalkTime = (TextView) findViewById(R.id.nilai_talk_time);
        txtMusicPlay = (TextView) findViewById(R.id.nilai_music_play);

        Intent detailSamsung= getIntent();
        kodeSlug = detailSamsung.getStringExtra("slug");
        namaHP = detailSamsung.getStringExtra("title");
        urlImg = detailSamsung.getStringExtra("img");

        txtNamePhone.setText(namaHP);

        imgPhone = (ImageView) findViewById(R.id.imgPhone);
        Picasso.with(getApplicationContext()).load(urlImg).into(imgPhone);


        new DetailPhone.JSONAsyncTask().execute("http://ibacor.com/api/gsm-arena?view=product&slug="+kodeSlug);
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DetailPhone.this);
            dialog.setMessage("Loading...");
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
                    JSONObject deskripsi = jsono.getJSONObject("data");

                    JSONObject body = deskripsi.getJSONObject("body");
                    JSONObject network = deskripsi.getJSONObject("network");
                    JSONObject launch = deskripsi.getJSONObject("launch");
                    JSONObject display = deskripsi.getJSONObject("display");
                    JSONObject platform = deskripsi.getJSONObject("platform");
                    JSONObject memory = deskripsi.getJSONObject("memory");
                    JSONObject camera = deskripsi.getJSONObject("camera");
                    JSONObject sound = deskripsi.getJSONObject("sound");
                    JSONObject comm = deskripsi.getJSONObject("comms");
                    JSONObject battery = deskripsi.getJSONObject("battery");

                    nilai_technology = network.getString("technology");
                    nilai_2G = network.getString("2g_bands");
                    nilai_3G = network.getString("3g_bands");
                    nilai_4G = network.getString("4g_bands");
                    nilai_Speed = network.getString("speed");
                    nilai_GPRS = network.getString("gprs");
                    nilai_Edge = network.getString("edge");

                    nilai_announced = launch.getString("announced");
                    nilai_status = launch.getString("status");

                    nilai_Dimensi = body.getString("dimensions");
                    nilai_Weight = body.getString("weight");
                    nilai_SIM = body.getString("sim");

                    nilaiTypeDisp = display.getString("type");
                    nilaiSize = display.getString("size");
                    nilaiResoultion = display.getString("resolution");
                    nilai_Multitouch = display.getString("multitouch");

                    nilai_os = platform.getString("os");
                    nilai_chipset = platform.getString("chipset");
                    nilai_cpu = platform.getString("cpu");
                    nilai_gpu = platform.getString("gpu");

                    nilai_CardSlot = memory.getString("card_slot");
                    nilai_internal = memory.getString("internal");

                    nilai_primary = camera.getString("primary");
                    nilai_secondary = camera.getString("secondary");
                    nilaiFeatureCam = camera.getString("features");
                    nilai_Video = camera.getString("video");

                    nilai_AlertType = sound.getString("alert_types");
                    nilai_Loudspeaker = sound.getString("loudspeaker_");
                    nilai_35mmjack = sound.getString("35mm_jack_");

                    nilai_WLAN = comm.getString("wlan");
                    nilai_Bluetooth = comm.getString("bluetooth");
                    nilai_GPS = comm.getString("gps");
                    nilaiRadio = comm.getString("radio");
                    nilai_USB = comm.getString("usb");

                    nilai_TypeBatt = battery.getString("_empty_");
                    nilai_Talktime = battery.getString("talk_time");
                    nilai_MusicPlay = battery.getString("music_play");
//
//                    System.out.println("DIMENSI " + body.getString("dimensions"));
//                    System.out.println("BERAT "+body.getString("weight"));
//                    System.out.println("SIM "+body.getString("sim"));

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

            txtTechnology.setText(nilai_technology);
            txt2GBand.setText(nilai_2G);
            txt3GBand.setText(nilai_3G);
            txt4GBand.setText(nilai_4G);
            txtSpeed.setText(nilai_Speed);
            txtGPRS.setText(nilai_GPRS);
            txtEdge.setText(nilai_Edge);

            txtAnnounced.setText(nilai_announced);
            txtStatus.setText(nilai_status);

            txtDimensi.setText(nilai_Dimensi);
            txtWeight.setText(nilai_Weight);
            txtSim.setText(nilai_SIM);

            txtTypeDisp.setText(nilaiTypeDisp);
            txtSize.setText(nilaiSize);
            txtResolution.setText(nilaiResoultion);
            txtMultitouch.setText(nilai_Multitouch);

            txtOS.setText(nilai_os);
            txtChipset.setText(nilai_chipset);
            txtCPU.setText(nilai_cpu);
            txtGPU.setText(nilai_gpu);

            txtCardSlot.setText(nilai_CardSlot);
            txtInternal.setText(nilai_internal);

            txtPrimary.setText(nilai_primary);
            txtSecondary.setText(nilai_secondary);
            txtFeaturesCam.setText(nilaiFeatureCam);
            txtVideo.setText(nilai_Video);

            txtAlertType.setText(nilai_AlertType);
            txtLoudspeaker.setText(nilai_Loudspeaker);
            txt35mmjack.setText(nilai_35mmjack);

            txtWLAN.setText(nilai_WLAN);
            txtBluetooth.setText(nilai_Bluetooth);
            txtGPS.setText(nilai_GPS);
            txtRadio.setText(nilaiRadio);
            txtUSB.setText(nilai_USB);

            txtTypeBatt.setText(nilai_TypeBatt);
            txtTalkTime.setText(nilai_Talktime);
            txtMusicPlay.setText(nilai_MusicPlay);

            dialog.cancel();
            if(result == false)
                Toast.makeText(DetailPhone.this, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }
}
