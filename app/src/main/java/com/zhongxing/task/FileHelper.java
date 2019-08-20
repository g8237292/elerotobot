package com.zhongxing.task;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.ziguangcn.amazonsdk.MyTimberLog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xulun on 2019/6/21.
 */

public class FileHelper {
    private static final String TAG = "FileHelper";

    public static void saveExternalFile(Context context, String fileName, String content){
        try {
            File dir = context.getExternalFilesDir(null);
            File myExternalFile = new File(dir, fileName);
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(content.getBytes());
            fos.close();
            Log.d(TAG, "saveExternalFile: Write task to"+myExternalFile.getAbsolutePath());
        }catch (IOError e) {
            Log.e(TAG, "saveExternalFile: " + e.getMessage());
        }catch (Exception ex){
            Log.e(TAG, "saveExternalFile: "+ ex );
        }
    }

    public static String loadExternalFile(Context context, String packageName, String externalFile){
        try {
            String myData = "";
            File myExternalFile;
            if(packageName.isEmpty()){
                myExternalFile =  new File(context.getExternalFilesDir(null),externalFile);
            }else {
                myExternalFile = new File(new File(context.getExternalFilesDir(null)
                        .getParent()).getParent() + "/" + packageName + "/files/", externalFile) ;
            }
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String strLine;
            while((strLine = br.readLine()) != null){
                myData  = myData + strLine;
            }
            dis.close();
            return myData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        Log.d(TAG, "isExternalStorageAvailable: " +extStorageState);
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        Log.d(TAG, "isExternalStorageReadOnly: " + extStorageState);
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean saveTextFile(Context context, String filename, String content){
        Log.d(TAG, "saveTextFile: " + content);
        if (FileHelper.isExternalStorageAvailable() && !FileHelper.isExternalStorageReadOnly()){
            Log.i(TAG, "saveTextFile: "+"sending task to robotium application...");
            FileHelper.saveExternalFile(context, filename, content);
            return true;
        }
        return false;
    }
}
