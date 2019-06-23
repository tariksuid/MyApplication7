package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private static final int CAMERA_REQUEST = 1888, PICK_IMAGE = 2;
    private ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    // int height , width ;
    int index = -1;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) this.findViewById(R.id.imageView1);
        imageView2 = (ImageView) this.findViewById(R.id.imageView2);
        imageView3 = (ImageView) this.findViewById(R.id.imageView3);
        imageView4 = (ImageView) this.findViewById(R.id.imageView4);
        imageView5 = (ImageView) this.findViewById(R.id.imageView5);
        imageView6 = (ImageView) this.findViewById(R.id.imageView6);
        imageView7 = (ImageView) this.findViewById(R.id.imageView7);
        imageView8 = (ImageView) this.findViewById(R.id.imageView8);
        imageView9 = (ImageView) this.findViewById(R.id.imageView9);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        for (int i = 1; i < 10; i++) {
            String x = sharedPreferences.getString(Integer.toString(i), "");

            if (!x.equalsIgnoreCase("")) {

                byte[] b = Base64.decode(x, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                Bitmap bitMAP1 = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
         /*String ss = "imageView";
         ss+=Integer.toString(i);
         */
                if (i == 1)
                    imageView1.setImageBitmap(bitMAP1);
                else if (i == 2)
                    imageView2.setImageBitmap(bitMAP1);
                else if (i == 3)
                    imageView3.setImageBitmap(bitMAP1);
                else if (i == 4)
                    imageView4.setImageBitmap(bitMAP1);
                else if (i == 5)
                    imageView5.setImageBitmap(bitMAP1);
                else if (i == 6)
                    imageView6.setImageBitmap(bitMAP1);
                else if (i == 7)
                    imageView7.setImageBitmap(bitMAP1);
                else if (i == 8)
                    imageView8.setImageBitmap(bitMAP1);
                else
                    imageView9.setImageBitmap(bitMAP1);


            }

        }

        /*for (int i = 1 ; i<10 ; i++)
        {



        }
*/
        //ImageView img  = findViewById(R.id.imageView1)  ;
/*height =   img.getHeight() ;
  width = img.getWidth();*/

    }

    public void onClickDialog(View v) {
/*
Intent intent = new Intent (this , DialogActivity.class) ;

startActivity(intent);
*/
        if (v.getId() == R.id.imageView1) {
            imageView = imageView1;
            index = 1;
        } else if (v.getId() == R.id.imageView2) {
            index = 2;
            imageView = imageView2;
        } else if (v.getId() == R.id.imageView3) {
            imageView = imageView3;
            index = 3;
        } else if (v.getId() == R.id.imageView4) {
            imageView = imageView4;
            index = 4;

        } else if (v.getId() == R.id.imageView5) {
            imageView = imageView5;
            index = 5;

        } else if (v.getId() == R.id.imageView6) {
            imageView = imageView6;

            index = 6;

        } else if (v.getId() == R.id.imageView7) {
            imageView = imageView7;
            index = 7;
        } else if (v.getId() == R.id.imageView8) {
            imageView = imageView8;
            index = 8;
        } else {
            index = 9;
            imageView = imageView9;
        }
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_dialog);
        dialog.setTitle("GET IMAGE");

        // EditText textViewUser = new EditText(getApplicationContext());
        // textViewUser = (TextView) findViewById(R.id.textBrand);
        // textViewUser.setText("Hi");

        dialog.show();

    }


    public void onClick2(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View v) {

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 1) {

            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d("re", "requestCode!=ok");
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            /*int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
             int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;*/


            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Bitmap bMapScaled = Bitmap.createScaledBitmap(photo, 300, 300, true);

            imageView.setImageBitmap(bMapScaled);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            editor.putString(Integer.toString(index), encodedImage);

            editor.commit();


        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                        Bitmap bMapScaled = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

                        imageView.setImageBitmap(bMapScaled);
                        // imageView.setImageBitmap(bitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] b = baos.toByteArray();
                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                        editor.putString(Integer.toString(index), encodedImage);

                        editor.commit();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
