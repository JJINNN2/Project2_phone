package com.example.phonephoto.phone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonephoto.R;
import com.example.phonephoto.ServerFileAdapter;
import com.example.phonephoto.data.DownloadResponse;
import com.example.phonephoto.data.UploadResponse;
import com.example.phonephoto.network.RetrofitClient;
import com.example.phonephoto.network.ServiceApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerPhoneActivity extends AppCompatActivity {

    String TAG = "PJ2 ServerPhoneActivity";

    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;

    LinearLayout selectedImage;

    TextView phoneName;
    TextView phoneNumber;

    LinearLayoutManager layoutManager;
    ServerPhoneAdapter serverPhoneAdapter;
    ArrayList<String> nameArray, numberArray;

    Handler handler = new Handler();  // 외부쓰레드 에서 메인 UI화면을 그릴때 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_phone);

        constraintLayout = findViewById(R.id.constraintLayout);
        recyclerView = findViewById(R.id.recyclerView);
        selectedImage = findViewById(R.id.selevtedImage);   //폰에서 쓸까?
        phoneName = findViewById(R.id.phoneName);
        phoneNumber = findViewById(R.id.phoneNumber);
        // recyclerView 에 linear layout 매니저 연결
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showFile(); //폰에서 쓸까?
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();

        // 이름과 번호
        serverPhoneAdapter = new ServerPhoneAdapter(this, nameArray, numberArray);
        recyclerView.setAdapter(serverPhoneAdapter);
    }

    private void showFile() {
        Log.d(TAG, "showFile");
        ServiceApi getResponse = RetrofitClient.getRetrofit().create(ServiceApi.class);
        Call<PhoneDownloadResponse> call = getResponse.phoneDownload();

        Log.d(TAG, String.valueOf(call));

        call.enqueue(new Callback<PhoneDownloadResponse>() {
            @Override
            public void onResponse(Call<PhoneDownloadResponse> call, Response<PhoneDownloadResponse> response) {
                Log.d(TAG, "onResponse");

                PhoneDownloadResponse phonedownloadResponse = response.body();
                Log.d(TAG, String.valueOf(phonedownloadResponse));

                if (phonedownloadResponse != null) {
                    Log.d(TAG, "downloadResponse 받았다!");

                    nameArray = phonedownloadResponse.getName();
                    numberArray = phonedownloadResponse.getNumber();
                    //Log.d(TAG, nameArray[4]);
                    onResume();

                } else {
                    // Call은 제대로 보냈으나 response 값이 제대로 오지 않음
                    Log.d(TAG, "serverResponse 못받았어ㅠ_ㅠ");
                }
            }

            @Override
            public void onFailure(Call<PhoneDownloadResponse> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    public void zoomInImage(int position) {
        Log.d(TAG, "zoomInPhoto");
        final String name = nameArray.get(position);
        final String number = numberArray.get(position);
//        String path = numberArray[position];

        phoneName.setText(name);
        phoneNumber.setText(number);

        //Log.d(TAG, name + ", " + path);
//
//        ServiceApi getResponse = RetrofitClient.getRetrofit().create(ServiceApi.class);
//        Call<UploadResponse> call = getResponse.getOneFile(path);
//
//        call.enqueue(new Callback<UploadResponse>() {
//            @Override
//            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
//                Log.d(TAG, "onResponse");
//            }
//
//            @Override
//            public void onFailure(Call<UploadResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure");
//            }
//        });


//    public void downloadPhone(int position) {
//        Log.d(TAG, "downloadPhone");
//        final String name = nameArray.get(position);
//        final String number = nameArray.get(position);
//
//        ServiceApi getResponse = RetrofitClient.getRetrofit().create(ServiceApi.class);
//        Call<Response> call = getResponse.getOneFile(path);
//        call.enqueue(new Callback<UploadResponse>() {
//            @Override
//            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
//                Log.d(TAG, "onResponse");
//            }
//            @Override
//            public void onFailure(Call<UploadResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure");
//            }
//        });
//    }
//
//    public void saveBitmapToFile(Bitmap bitmap, String filePath, String fileName) {
//        Log.d(TAG, "BitmapToFile");
//        Log.d(TAG, filePath +", "+fileName);
//        Log.d(TAG, String.valueOf(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())));
//
//        // filePath 경로에 파일 인스턴스 생성
//        File newFile = new File(filePath, fileName);
//        Log.d(TAG, "newFile: " + String.valueOf(newFile));
//        try {
//            newFile.createNewFile();
//            Log.d(TAG, "newFile: " + String.valueOf(newFile));
//            FileOutputStream out = new FileOutputStream(newFile);
//            Log.d(TAG, "out: " + String.valueOf(out));
//
//            // 스트림에 비트맵 저장
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//            // 스트림 사용 후 닫기
//            out.close();
//
//            // 이미지 다운 받은 후..
//            // 시스템의 미디어 스캐너를 호출하여 사진을 미디어 제공자의 데이터베이스에 추가한 후
//            // Android 갤러리 애플리케이션 및 다른 앱에서 사용할 수 있도록 하는 방법을 보여줍니다.
//            // 아래 과정이 없으면 파일이 저장되긴 했으나 경로에 대한 정보가 업데이트가 되지 않은 상태로 남아있다.
//            // 그 결과 (실제 파일이 있으나) 갤러리앱 등에서 보이지 않는다.
//            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            Uri contentUri = Uri.fromFile(newFile);
//            mediaScanIntent.setData(contentUri);
//            this.sendBroadcast(mediaScanIntent);
//
//        } catch (FileNotFoundException e) {
//            Log.d(TAG, "FileNotFoundException: " + e.getMessage());
//        } catch (IOException e) {
//            Log.d(TAG, "IOException: " + e.getMessage());
//        }
//    }
//
//    public void backClick(View view) {
//        setResult(RESULT_OK);
//        finish();
//    }
//
//    public void backClick2(View view) {
//        imageView.setImageBitmap(null);
//        selectedImage.setVisibility(View.INVISIBLE);
//        constraintLayout.setVisibility(View.VISIBLE);
//    }
    }
}