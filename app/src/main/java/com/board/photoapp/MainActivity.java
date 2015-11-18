package com.board.photoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    String TAG;
    public static final int  REQUEST_CODE=1;
    File file;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        TAG = this.getClass().getName();
    }

    /* startActivityForResult 메서드 호출 후, 해당 액티비티나
        앱이 결과를 보낼때 자동으로 초훌되는 메서드
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Log.d(TAG, "사진앱이 제대로 동작하고 결과 보내옴");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize=16;

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            /*그래픽의 왜곡처리*/
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            imageView.setImageBitmap(rotateBitmap);
        }
    }

    public void callCameraApp(){
        /*이미 존재하는 카메라 앱을 호출할때 사용하는 인텐트*/
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /*사진앱에서 사진촬영시, 해당 사진을 저장할 경로 지정*/
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyMedia/photo";
        long time = System.currentTimeMillis();
        String fileName=time+".jpg";
        file = new File(path, fileName);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void btnClick(View view ){
        if(view.getId() == R.id.bt_take){
            callCameraApp();
        }else if(view.getId()==R.id.bt_album){
            Intent intent = new Intent(this, AlbumActivity.class);
            startActivity(intent);
        }
    }
}
