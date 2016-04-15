package com.example.muhammed.showcloudeprinters;

import android.content.Context;
import android.print.PrintDocumentAdapter;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by muhammed on 4/12/2016.
 */
public class MyWebView extends WebView {
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public PrintDocumentAdapter createPrintDocumentAdapter(String name) {
        return new MyAdapter();
    }
}
