package com.luteh.kampusonline.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.luteh.kampusonline.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by Luthfan Maftuh on 08/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class Common {

    private static final String TAG = "Common";

    public static String currentUID = null;

    public static List<String> semesterLists = new ArrayList<>();
    public static List<String> semesterListCollectionNames = new ArrayList<>();
    public static List<String> ujianSemesterList = new ArrayList<>();
    public static List<String> ujianSemesterListChildNames = new ArrayList<>();
    public static List<String> susulanSemesterList = new ArrayList<>();
    public static List<String> susulanSemesterListChildNames = new ArrayList<>();

    private static AlertDialog.Builder builder;
    private static AlertDialog dialog;
    private static android.app.AlertDialog waitingDialog;

    public static boolean isFrsDialogShowed = false;

    public static void showProgressBar(Context context) {
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if (!dialog.isShowing()) dialog.show();
    }

    public static void dismissProgressBar() {
        if (dialog.isShowing()) dialog.dismiss();
    }

    public static void showSpotsProgress(Context context) {
        showSpotsProgress(context, context.getResources().getString(R.string.title_message_loading));
    }

    public static void showSpotsProgress(Context context, String message) {
        waitingDialog = new SpotsDialog.Builder()
                .setContext(context)
                .setMessage(message)
                .setCancelable(false)
                .setTheme(R.style.CustomSpotsDialog)
                .build();
        if (!waitingDialog.isShowing()) {
            waitingDialog.show();
        }
    }

    public static void dismissSpotsProgress() {
        if (waitingDialog.isShowing())
            waitingDialog.dismiss();
    }

    public static void showSuccessMessage(Context context, String message) {
        FancyToast.makeText(context, message, 0, FancyToast.SUCCESS, false).show();
    }

    public static void showInfoMessage(Context context, String message) {
        FancyToast.makeText(context, message, 1, FancyToast.INFO, false).show();
    }

    public static void showErrorMessage(Context context, String message) {
        FancyToast.makeText(context, message, 1, FancyToast.ERROR, false).show();
    }

    public static boolean isValidEmail(EditText editText) {
        return (!TextUtils.isEmpty(editText.getText()) && Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches());
    }

    public static boolean isEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Convert view group layout to image
     *
     * @param viewGroup The view group using reference *
     */
    public static void layoutToImage(ViewGroup viewGroup, String fileName) throws FileNotFoundException {
//        String currentTime = String.valueOf(new Date().getTime());
        // convert view group to bitmap
        viewGroup.setDrawingCacheEnabled(true);
        viewGroup.buildDrawingCache();
        Bitmap bm = viewGroup.getDrawingCache();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String imgPathName = Environment.getExternalStorageDirectory() + File.separator + fileName + ".jpg";

        File f = new File(imgPathName);
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageToPDF(fileName, imgPathName);
    }

    /**
     * Convert image to PDF file
     */
    private static void imageToPDF(String fileName, String imgPathName) throws FileNotFoundException {
        try {
            Document document = new Document();
            String dirpath = android.os.Environment.getExternalStorageDirectory().toString();
            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/" + fileName + ".pdf")); //  Change pdf's name.
            document.open();
            Image img = Image.getInstance(imgPathName);
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / img.getWidth()) * 100;
            img.scalePercent(scaler);
            img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            document.add(img);
            document.close();
//            Toast.makeText(this, "PDF Generated successfully!..", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "imageToPDF: Directory Path : " + dirpath);
            Log.d(TAG, "imageToPDF: Filename : " + imgPathName);
        } catch (Exception e) {
            Log.e(TAG, "imageToPDF: " + e.getMessage());
        }
    }
}
