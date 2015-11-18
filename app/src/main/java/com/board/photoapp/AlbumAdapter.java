package com.board.photoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.renderscript.Element;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter{
    Context context;
    ArrayList<ImageView> list = new ArrayList<ImageView>();

    public AlbumAdapter(Context context) {
        this.context = context;
        init();
    }

    /*SD카드내의 사진이 있는 경로를 조사하여 ArrayList에 담아서 사진출력*/
    public void init(){
        File dir = Environment.getExternalStorageDirectory();
        String path = dir.getAbsolutePath()+"/MyMedia/photo/";
        File file = new File(path);

        File[] files = file.listFiles();

        for(int i=0; i<files.length; i++){
                if(files[i].isFile()){ /*디렉토리가 아닌 파일이라면*/
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize=16;
                    Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath(), options);
                    ImageView imageView = new ImageView(context);

                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap rotateBitmap= Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                    imageView.setImageBitmap(rotateBitmap);
                    list.add(imageView);
            }
        }

    }

    public int getCount() {
        return list.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  list.get(position);
        return view;
    }
}
