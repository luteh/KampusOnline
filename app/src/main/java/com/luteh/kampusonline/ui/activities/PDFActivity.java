package com.luteh.kampusonline.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.AppConstant;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseActivity;

public class PDFActivity extends BaseActivity {

    private Intent intent;
    private Bundle bundle;

    @BindView(R.id.pdfView)
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
    }

    @Override
    protected void onInit() {
        super.onInit();
        intent = getIntent();
        bundle = intent.getExtras();
        pdfView.fromAsset(bundle.getString(AppConstant.KEY_PDF_ASSET))
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this, true))
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                        pdfView.fitToWidth();
                    }
                })
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .load();
    }
}
