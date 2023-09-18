package com.example.cafemanager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.Manifest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cafemanager.databinding.ActivityMainBinding;
import com.example.cafemanager.shop.Shop;
import com.example.cafemanager.shop.ShopService;
import com.example.cafemanager.user.User;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private Uri selectedImageUri;
    private ShopService shopService;
    private String shopImage ="";
    private static final int REQUEST_IMAGE_PICK = 1;
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Notifications permission granted",Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(this, "FCM can't post notifications without POST_NOTIFICATIONS permission",
                            Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_MEDIA_IMAGES) ==
                PackageManager.PERMISSION_GRANTED) {
        }else{
            requestPermissionLauncher.launch(
                    Manifest.permission.READ_MEDIA_IMAGES);
        }

        shopService = CafeManagerClient.getInstance().getShopService();

        binding.btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });

        binding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이미지를 서버로 업로드하고 서버에서 반환한 경로를 받아오는 메서드
                // Retrofit을 사용하여 이미지를 서버로 업로드하고 경로를 받아옴
                // 서버에서는 업로드한 이미지를 저장하고 이미지 파일 경로를 생성함
                // 반환된 경로를 리턴
                // Uri를 실제 파일 경로로 변환하는 메서드
                File imageFile = new File(getRealPathFromUri(selectedImageUri)); // Uri를 파일로 변환
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);
                // Retrofit을 사용하여 이미지 업로드 API 호출
                Call<ResponseBody> call = shopService.uploadImage(imagePart);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody responseBody = response.body();
                        if(response.body() !=null){
                            try {
                                Shop shop = new Shop();
                                shopImage = responseBody.string();
                                shop.setLatitude(Double.parseDouble(binding.edtLat.getText().toString()));
                                shop.setLongitude(Double.parseDouble(binding.edtLon.getText().toString()));
                                shop.setShopName(binding.edtName.getText().toString());
                                shop.setShopImg(shopImage);
                                shop.setUser(new User(binding.edtId.getText().toString(),
                                        binding.edtPw.getText().toString()));
                                insertShoptoServer(shop);
                                Toast.makeText(getApplicationContext(),"가게가 등록되었습니다", Toast.LENGTH_SHORT).show();
                                //초기화
                                binding.edtId.setText("");
                                binding.edtLat.setText("");
                                binding.edtLon.setText("");
                                binding.edtName.setText("");
                                binding.edtPw.setText("");

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t);
                    }
                });
            }
        });
    }//[oncreateEnd]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            binding.ivShopImg.setImageURI(selectedImageUri);
        }
        Log.d(TAG, "onActivityResult: "+shopImage);
    }


    private String getRealPathFromUri(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
    private void insertShoptoServer(Shop shop){
        Call<Void> call = shopService.insertShop(shop);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: shop 등록");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}