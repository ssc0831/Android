package com.example.ezorder;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//중요


import com.bumptech.glide.Glide;
import com.example.ezorder.databinding.ActivityMainBinding;
import com.example.ezorder.order.OrderActivity;
import com.example.ezorder.order.Shop;
import com.example.ezorder.shop.ShopService;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "MainActivity";
    public static double CURRENT_LOC_LAT;
    public static double CURRENT_LOC_LON;
    private MapView mapView;
    private TextView txtName;
    private ImageView ivShop;
    private static NaverMap naverMap;
    private ArrayList<Shop> shops = new ArrayList<>();
    private ArrayList<Marker> markers = new ArrayList<>();
    private long recentShopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        //네이버 지도
        mapView = findViewById(R.id.map_view);



        txtName = findViewById(R.id.txtName);
        ivShop = findViewById(R.id.ivShop);

        //test 현재위치
        CURRENT_LOC_LAT = 35.1560157;
        CURRENT_LOC_LON = 129.0594088;


        //shop 전체 list 불러오기
        Call<List<Shop>> call = EzOrderClient.getInstance().getShopService().findAll();
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                for (Shop shop : response.body()) {
                    shops.add(shop);
                    binding.linearLayoutshop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                            intent.putExtra("shopId", recentShopId);
                            startActivity(intent);
                        }
                    });
                }
                mapView.onCreate(savedInstanceState);
                mapView.getMapAsync(MainActivity.this);
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {

            }
        });

        //가게 클릭


    }//[onCreate End]

    //지도
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        //배경 지도 선택
        naverMap.setMapType(NaverMap.MapType.Basic);


        //건물 표시
        naverMap.setLayerGroupEnabled(naverMap.LAYER_GROUP_BUILDING, true);

        //위치 및 각도 조정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(CURRENT_LOC_LAT, CURRENT_LOC_LON),   // 위치 지정
                15,                                     // 줌 레벨
                0,                                       // 기울임 각도
                0                                     // 방향
        );
        naverMap.setCameraPosition(cameraPosition);

        //CustomMarker customMarker = new CustomMarker(shop1,CustomMarker.CURRENT);
        //로컬(현위치)
        for (Shop shop : shops) {
            Marker marker = new Marker();
            markers.add(marker);
            setMarker(marker, shop.getLatitude(), shop.getLongitude(), shops.indexOf(shop));
        }
    }

    //지도 마커 set
    private void setMarker(Marker marker,
                           double lat, double lng) {
        setMarker(marker, lat, lng, 0);
    }

    private void setMarker(double lat, double lng) {
        setMarker(new Marker(), lat, lng, 0);
    }

    //제일 많이쓰일듯
    private void setMarker(double lat, double lng, int shopID) {
        setMarker(new Marker(), lat, lng, shopID);
    }

    private void setMarker(Marker marker,
                           double lat, double lng,
                           int shopID) {

        marker.setIcon(MarkerIcons.GRAY);

        marker.setCaptionText(shops.get(shopID).getShopName());
        //마커의 투명도
        marker.setAlpha(0.8f);
        //마커 위치
        marker.setPosition(new LatLng(lat, lng));
        //마커 우선순위
        //marker.setZIndex(zIndex);
        //마커 표시
        marker.setMap(naverMap);

        marker.setOnClickListener(overlay -> {
            txtName.setText(marker.getCaptionText());
            //######추후 수정해야함 db에서 받아온 이미지 뿌려주기
            String imgName = shops.get(shopID).getShopImg();
            Log.d(TAG, "setMarker:");
            String imgUrl = "http://10.100.102.20:8044/image/"+imgName;
            Log.d(TAG, "setMarker: "+imgName);
            Glide.with(getApplicationContext()).load(imgUrl).into(ivShop);

            for (Marker m : markers) {
//                if (shopID==0) m.setIcon(MarkerIcons.RED);
//                else
                m.setIcon(MarkerIcons.GRAY);
            }
            marker.setIcon(MarkerIcons.BLACK);
            recentShopId = shops.get(shopID).getShopId();
            return true;
        });
    }
    //[마커set 종료]

    //생명주기 map관리
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

/*현재 위치 받아오는 방법
LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Toast.makeText(getApplicationContext(),
                "경도: "+longitude+ " / 위도: "+latitude,
                Toast.LENGTH_SHORT).show();
 */