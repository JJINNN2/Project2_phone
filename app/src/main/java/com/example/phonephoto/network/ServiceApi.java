package com.example.phonephoto.network;

import com.example.phonephoto.data.DownloadResponse;
import com.example.phonephoto.data.JoinData;
import com.example.phonephoto.data.JoinResponse;
import com.example.phonephoto.data.LoginData;
import com.example.phonephoto.data.LoginResponse;
import com.example.phonephoto.data.UploadResponse;
import com.example.phonephoto.phone.PhoneUploadData;
import com.example.phonephoto.phone.PhoneDownloadResponse;
import com.example.phonephoto.phone.PhoneUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceApi {
    @Multipart
    @POST("/upload")
    Call<UploadResponse> uploadFile(@Part MultipartBody.Part file, @Part("ham2") RequestBody name);

    // URL이 고정된 서버에서 파일 다운
    @POST("/download")
    Call<DownloadResponse> downloadFile();

    @POST("/get_image")
    Call<UploadResponse> getOneFile(@Body String filePath);

    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/phone/upload")
    Call<PhoneUploadResponse> phoneUpload(@Body PhoneUploadData data);

    @POST("/phone/download")
    Call<PhoneDownloadResponse> phoneDownload();
}
