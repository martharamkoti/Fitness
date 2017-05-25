package com.example.fitness;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ProfilePicActivity extends AppCompatActivity {
    ImageView profileImage;
    private static final int GALLERY_REQUET_CODE = 11111;
    private static final int CAMERA_REQUEST_CODE = 100;
    Button savePic, cancelPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        profileImage = (ImageView) findViewById(R.id.profileIcon);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ProfilePicActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.image_selection_dialog);
                ImageView camera = (ImageView) dialog.findViewById(R.id.camera);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent takePicture = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, CAMERA_REQUEST_CODE);
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });

        savePic = (Button) findViewById(R.id.savePic);
        cancelPic = (Button) findViewById(R.id.cancelPic);

        savePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String customerImageBitmapToString = "";
        if (data != null) {
            Bitmap bitmap;
            if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                customerImageBitmapToString = BitMapToString(bitmap);
                profileImage.setImageBitmap(getCroppedBitmap(bitmap, profileImage.getWidth()));
            }
            if (requestCode == GALLERY_REQUET_CODE && resultCode == Activity.RESULT_OK) {
                bitmap = getBitmapFromCameraData(data, getApplicationContext());

                profileImage.setImageBitmap(getCroppedBitmap(bitmap, profileImage.getWidth()));
            }
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.NO_WRAP | Base64.NO_CLOSE | Base64.NO_PADDING);
        System.out.println(" JPEG Bitmap size : " + temp.length() + " JPEG Byte array length :" + b.length);
        return temp;
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap, int width) {
        Bitmap output = Bitmap.createBitmap(width,
                width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, width);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(width / 2, width / 2,
                width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    public static Bitmap getBitmapFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        int sample = 32;
        BitmapFactory.decodeFile(picturePath, option);
        if (option.outHeight > 960 || option.outWidth > 200) {
            if (option.outHeight > option.outWidth) {
                sample = option.outHeight / 200;
            } else {
                sample = option.outWidth / 200;
            }
        }
        option.inSampleSize = sample;
        option.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(picturePath, option);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
