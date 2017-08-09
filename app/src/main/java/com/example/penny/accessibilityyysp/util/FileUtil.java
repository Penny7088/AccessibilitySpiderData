package com.example.penny.accessibilityyysp.util;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created on 2017/7/8 0008.
 * by penny
 */

public class FileUtil {

    public static boolean writeFile(String pS) {
        File sd = Environment.getExternalStorageDirectory();
        String path = sd.getPath() + File.separator + "ELMCate" + File.separator;
        try {
            Log.d("==writeFile===",path);
            File file = new File(Environment.getExternalStorageDirectory(),
                    "elmdata.txt");
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(pS+"\n");
            out.close();
            Log.d("===================","写文件成");
            return true;
        } catch (IOException e) {
            Log.d("===================","写文件失败");
            e.printStackTrace();
            return false;
        }
    }
}