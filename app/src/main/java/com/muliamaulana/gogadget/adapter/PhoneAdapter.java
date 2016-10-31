package com.muliamaulana.gogadget.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.muliamaulana.gogadget.R;
import com.muliamaulana.gogadget.setget.Phone;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by muliamaulana on 10/30/16.
 */

public class PhoneAdapter extends ArrayAdapter<Phone> {

    ArrayList<Phone> phoneList;
    LayoutInflater layoutList;
    int Resource;
    ViewHolder holder;

    public PhoneAdapter(Context context, int resource, ArrayList<Phone> objects) {
        super(context, resource, objects);
        layoutList = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        phoneList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View view = convertView;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutList.inflate(Resource, null);
            holder.imageview = (ImageView) view.findViewById(R.id.imgPhone);
            holder.tvTitle = (TextView) view.findViewById(R.id.namePhone);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageview.setImageResource(R.drawable.ic_phone);
        new DownloadImageTask(holder.imageview).execute(phoneList.get(position).getImage());
        holder.tvTitle.setText(phoneList.get(position).getTitle());

        return view;

    }


    static class ViewHolder {
        public ImageView imageview;
        public TextView tvTitle;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }

}

