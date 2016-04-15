package com.example.muhammed.showcloudeprinters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://stackoverflow.com/questions/20511639/how-to-create-pdf-from-webview-in-android");
        doWebViewPrint();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

        }
    }

    private void doWebViewPrint() {
        // Create a WebView object specifically for printing
        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
            }
        });

//        // Generate an HTML document on the fly:
//        String htmlDocument = "<html><body><h1>Test Content</h1><p>Testing, " +
//                "testing, testing...</p></body></html>";
//        mWebView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);


//        mWebView.setPictureListener();

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
    }

    private void createWebPrintJob(WebView view) {
        String jobName = getString(R.string.app_name) + " Document";
// Get a PrintManager instance
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            printAdapter = view.createPrintDocumentAdapter(jobName);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            printAdapter = view.createPrintDocumentAdapter();
        }

        // Create a print job with name and adapter instance

        PrintJob printJob = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            printJob = printManager.print(jobName, printAdapter,
                    new PrintAttributes.Builder().build());
        }

    }

}
