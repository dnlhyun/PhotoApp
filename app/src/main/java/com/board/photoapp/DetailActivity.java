package com.board.photoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class DetailActivity extends Activity {
    String TAG;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();

        setContentView(R.layout.detail_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        Bitmap bitmap = bundle.getParcelable("img");
        Log.d(TAG, "bitmap is "+bitmap);

        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);

    }


}
