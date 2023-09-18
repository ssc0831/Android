package com.example.cafemanager.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cafemanager.CafeManagerClient;
import com.example.cafemanager.R;
import com.example.cafemanager.databinding.ActivityMenuBinding;
import com.example.cafemanager.order.Menu;
import com.example.cafemanager.shop.Shop;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    private final String TAG = "MenuActivity";
    private static final int REQUEST_IMAGE_PICK = 1;
    private String menuImage ="";
    private MenuService menuService;
    private ActivityMenuBinding binding;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences("ezorder", MODE_PRIVATE);
        String stShopId = preferences.getString("ezordershopId","");
        long shopId = Long.parseLong(stShopId);

        menuService = CafeManagerClient.getInstance().getMenuService();

        binding.btnFile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });
        binding.btnJoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + shopId);

                File imageFile = new File(getRealPathFromUri(selectedImageUri)); // Uri를 파일로 변환
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);

                Call<ResponseBody> call = menuService.uploadImage(imagePart);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody responseBody = response.body();
                        if(response.body() !=null){
                            try {
                                menuImage = responseBody.string();
                                Menu menu = new Menu();
                                menu.setMenuName(binding.edtMenuName.getText().toString());
                                menu.setPrice(Integer.parseInt(binding.edtPrice.getText().toString()));
                                menu.setMenuImg(menuImage);
                                menu.setShop(new Shop(shopId));
                                insertMenutoServer(menu);
                                Log.d(TAG, "onResponse: "+menuImage);
                                Toast.makeText(getApplicationContext(),"메뉴가 등록되었습니다",Toast.LENGTH_SHORT).show();
                                binding.edtMenuName.setText("");
                                binding.edtPrice.setText("");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }//[onCreateEnd]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            binding.ivMenuImg.setImageURI(selectedImageUri);
        }
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
    private void insertMenutoServer(Menu menu){
        Call<Void> call = menuService.saveMenu(menu);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: 메뉴등록");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}