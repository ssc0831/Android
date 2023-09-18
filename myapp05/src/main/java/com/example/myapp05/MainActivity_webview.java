package com.example.myapp05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapp05.databinding.ActivityMainWebviewBinding;

public class MainActivity_webview extends AppCompatActivity {
    private ActivityMainWebviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainWebviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.webview1.setWebViewClient(new CookWebViewClient());
        WebSettings webSettings = binding.webview1.getSettings();
        webSettings.setBuiltInZoomControls(true);

        binding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.webview1.loadUrl(binding.editUrl.getText().toString());
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.webview1.goBack();
            }
        });
    }
    class CookWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}