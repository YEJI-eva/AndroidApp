package com.example.second;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class GetUploadPhotoActivity extends AppCompatActivity {

    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_upload_photo);


        ImageView imageView;
        Button button;

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setAction(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }


            /*run();*/
        }
    }

    /**
     * Saves a JPEG {@link Image} into the specified {@link File}.
     */
    private class ImageUpLoader implements Runnable {

        /**
         * The JPEG image
         */
        private final Image mImage;

        ImageUpLoader(Image image) {
            mImage = image;

        }

        @Override
        public void run() {
            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);

            // 스토어 넘버별로 저장하기 위함
            Intent intent = getIntent();
            String userNum = intent.getStringExtra("restaurantNum");

            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageRef = storage.getReference();

            final StorageReference mountainImagesRef = storageRef.child("restNum/1/restImage.jpg");


            UploadTask uploadTask = mountainImagesRef.putBytes(bytes);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        Log.e("실패1","실패");
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return mountainImagesRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Log.e("성공","성공: "+downloadUri);
                    } else {
                        // Handle failures
                        // ...
                        Log.e("실패2","실패");
                    }
                }
            });

        }

    }
}
