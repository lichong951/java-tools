package com.glodon.tool;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by zhangtianjie on 2018/9/7.
 */

public class Util {
    private static final String TAG = "Util";


    public static boolean hasNet() {
        return hasNet;
    }

    public static void setHasNet(boolean hasNet) {
        Util.hasNet = hasNet;
    }

    private static boolean hasNet = false;


    public static String bitmaptoString(Bitmap bitmap) {  //图片转base64
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    public static String bitmaptoStringlow(Bitmap bitmap) {  //图片转base64
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    public static ArrayList<String> getFiles(String path) {


        File filedir = new File(path);
        if (!filedir.exists()) {                //如果不存在，那就建立这个文件夹
            return null;
        }

        ArrayList<String> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {

                files.add(tempList[i].getName());
            }

        }
        return files;
    }


    public static void deleteSingleFileImg(String filePath) {


        File filedir = new File(filePath);
        if (!filedir.exists()) {                //如果不存在，那就建立这个文件夹
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {                //如果不存在，那就建立这个文件夹
            return;
        }

        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                L.d(TAG, "Delete删除单个文件" + filePath + "成功！");

            }
        }
    }



    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        return cachePath;
    }

//    /*
//     * 数据写入
//     */
//    public static boolean writeResponseBodyToDisk(Context context, ResponseBody body, String saveName) {
//        try {
//
//            //  String path = getDiskCacheDir(context) + File.separator + savaName;
//
//            String sdCardDir = Constant.upgradePath;
//            File dirFile = new File(sdCardDir);  //目录转化成文件夹
//            if (!dirFile.exists()) {                //如果不存在，那就建立这个文件夹
//                dirFile.mkdirs();
//            }
//
//
//            String path = Constant.upgradePath + saveName;
//            L.d(TAG, "path:" + path);
//            File futureStudioIconFile = new File(path);
//
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//
//            try {
//                byte[] fileReader = new byte[4096];
//
//                long fileSize = body.contentLength();
//                long fileSizeDownloaded = 0;
//
//                inputStream = body.byteStream();
//                outputStream = new FileOutputStream(futureStudioIconFile);
//
//                while (true) {
//                    int read = inputStream.read(fileReader);
//
//                    if (read == -1) {
//                        break;
//                    }
//
//                    outputStream.write(fileReader, 0, read);
//
//                    fileSizeDownloaded += read;
//
//                    L.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
//                }
//
//                outputStream.flush();
//
//                return true;
//            } catch (IOException e) {
//                return false;
//            } finally {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            }
//        } catch (IOException e) {
//            return false;
//        }
//    }


    /*
     * 获取应用版本号
     */
    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    /*
     * 获取应用版本名
     */

    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static boolean isRooted() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getInputStream();
            outputStream.write("id\n".getBytes());
            outputStream.flush();
            outputStream.write("exit\n".getBytes());
            outputStream.flush();
            process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            if (s == null) {
                L.e(TAG, "s==null 没有root权限");
            } else {
                if (s.contains("uid=0")) return true;
            }

        } catch (IOException e) {
            L.e(TAG, "没有root权限");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null)
                process.destroy();
        }
        return false;
    }


    public static int startApp() {
        //am start -n
        String[] args = new String[]{"am", "start", "-n", "com.glodon.gdb.gate/.activity.WelcomeActivity"};

        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();

        int result = 0;

        try {
            process = processBuilder.start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }

            String e;
            while ((e = errorResult.readLine()) != null) {
                errorMsg.append(e);
            }


        } catch (IOException e) {
            e.printStackTrace();
            result = 2;
        } catch (Exception e) {
            result = 2;
            e.printStackTrace();
        } finally {
            if (successResult != null) {
                try {
                    successResult.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }

            if (errorResult != null) {
                try {
                    errorResult.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
            if (process != null) {
                process.destroy();
            }

        }
        if (successMsg.toString().toLowerCase().contains("success")) {
            result = 0;
        } else {
            result = 2;
        }
        L.d(TAG, "successMsg:" + successMsg + " errorMsg:" + errorMsg);
        return result;

    }

    public static int installBySlient(Context context, String filePath) {
        int result = 0;
        try {
            L.i(TAG, "installBySlient , filePath : " + filePath);
            File file = new File(filePath);
            if (filePath == null || filePath.length() == 0
                    || (file = new File(filePath)) == null
                    || file.length() <= 0 || !file.exists() || !file.isFile()) {
                return result;
            }

            String[] args = {"pm", "install", "-i ", context.getPackageName(), "-r", filePath};
            ProcessBuilder processBuilder = new ProcessBuilder(args);

            Process process = null;
            BufferedReader successResult = null;
            BufferedReader errorResult = null;
            StringBuilder successMsg = new StringBuilder();
            StringBuilder errorMsg = new StringBuilder();

            try {
                process = processBuilder.start();
                successResult = new BufferedReader(new InputStreamReader(
                        process.getInputStream()));
                errorResult = new BufferedReader(new InputStreamReader(
                        process.getErrorStream()));
                String s;

                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }

                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
                L.d(TAG, "s : " + s);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (successResult != null) {
                        successResult.close();
                    }
                    if (errorResult != null) {
                        errorResult.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (process != null) {
                    process.destroy();
                }
            }

            if (successMsg != null && (successMsg.toString().contains("Success")
                    || successMsg.toString().contains("success"))) {
                result = 1;
            }

            L.d(TAG, "installBySlient complete :" + "successMsg:" + successMsg + ", ErrorMsg:" + errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void install1(String filePath) {
        //  return execCommand("pm","install","-f","com.glodon.glm.gateface","--user","0",filePath);

        String[] pm = new String[]{"pm", "install", "-f", "com.glodon.glm.gateface", "--user", "0", filePath};

        execInstallCommand(pm);
    }


    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";

    public static void execInstallCommand(String[] commands) {
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;

        DataOutputStream os = null;
        try {

            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                // donnot use os.writeBytes(commmand), avoid chinese charset error
                os.write(command.getBytes());
                os.writeBytes(COMMAND_LINE_END);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();
            process.waitFor();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "execCommand: ", e);
            }

            if (process != null) {
                process.destroy();
            }
        }
    }

    /*
     * m命令可以通过adb在shell中执行，同样，我们可以通过代码来执行
     */
    public static String execCommand(String... command) {
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        String result = "";

        try {
            process = new ProcessBuilder().command(command).start();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }

            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            result = new String(baos.toByteArray());

            if (inIs != null)
                inIs.close();
            if (errIs != null)
                errIs.close();
            process.destroy();
        } catch (IOException e) {

            result = e.getMessage();
        }

        return result;
    }

    public static int install(String filePath) {
        L.d(TAG, "start to install apk");
        L.d(TAG, filePath);

        File file = new File(filePath);
        if (filePath.isEmpty() || file.length() <= 0 || !file.exists() || !file.isFile() || !file.getName().endsWith("apk")) {
            return 1;
        }


        String[] args = new String[]{"pm", "install", "-r", filePath};

        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();

        int result = 0;

        try {
            process = processBuilder.start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }

            String e;
            while ((e = errorResult.readLine()) != null) {
                errorMsg.append(e);
            }


        } catch (IOException e) {
            e.printStackTrace();
            result = 2;
        } catch (Exception e) {
            result = 2;
            e.printStackTrace();
        } finally {
            if (successResult != null) {
                try {
                    successResult.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }

            if (errorResult != null) {
                try {
                    errorResult.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
            if (process != null) {
                process.destroy();
            }

        }
        if (successMsg.toString().toLowerCase().contains("success")) {
            result = 0;
            //android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            result = 2;
        }
        L.d(TAG, "successMsg:" + successMsg + " errorMsg:" + errorMsg);
        return result;
    }


    public static void runShellCommand(String command) {
        Process process = null;
        BufferedReader bufferedReader = null;
        StringBuilder mShellCommandSB = new StringBuilder();
        L.d("wenfeng", "runShellCommand :" + command);
        mShellCommandSB.delete(0, mShellCommandSB.length());
        String[] cmd = new String[]{"/system/bin/sh", "-c", command}; //调用bin文件
        try {
            byte b[] = new byte[1024];
            process = Runtime.getRuntime().exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                mShellCommandSB.append(line);
            }
            L.d("wenfeng", "runShellCommand result : " + mShellCommandSB.toString());
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }


    public static boolean silentInstall(String apkPath) {

        if (!isRooted()) {
            L.d(TAG, "没有ROOT权限，不能使用秒装");
            return false;
        }


        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            // 申请su权限
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // 执行pm install命令exitex

            dataOutputStream.writeBytes((String) "chmod 777 " + apkPath + "\n");
            /*dataOutputStream.writeBytes((String) "export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n");


            String command = "pm install -r " + apkPath + "\n";
            dataOutputStream.writeBytes(command);*/

            dataOutputStream
                    .writeBytes("pm install -r "
                            + apkPath);
            // dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));

            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            int value = process.waitFor();
            if (value == 0) {
                L.d(TAG, "安装成功！");

                // 失败
            } else if (value == 1) {
                L.d(TAG, "安装失败！");

                // 未知情况
            } else {
                L.d(TAG, "未知情况！");

            }


            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            // 读取命令的执行结果
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            L.d(TAG, "install msg is " + msg);
            // 如果执行结果中包含Failure字样就认为是安装失败，否则就认为安装成功

            if (msg.contains("not allowed to su")) {
                return false;
            }
            if (msg.contains("denied")) {
                return false;
            }
            if (!msg.contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            L.d(TAG, e.getMessage());
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                L.d(TAG, e.getMessage());
            }
        }
        return result;

    }


    private static boolean ifDeviceRoot() {
        boolean result = false;

        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                result = false;
                return result;
            } else {
                result = true;
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int excute(String cmdStr) {
        if (ifDeviceRoot()) {    //已root

            DataOutputStream dos = null;
            Process process = null;

            try {
                //得到Process对象,这里执行su命令是为了获取root权限.如果没有root权限就无法进行静默安装了.
                process = Runtime.getRuntime().exec("su");
                dos = new DataOutputStream(
                        (OutputStream) process.getOutputStream());
                // 部分手机Root之后Library path 丢失，导入path可解决该问题
                dos.writeBytes((String) "export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n");
                cmdStr = String.valueOf(cmdStr);
                dos.writeBytes((String) (cmdStr + "\n"));
                dos.flush();
                dos.writeBytes("exit\n");
                dos.flush();
                //阻塞进程,等待安装完成
                process.waitFor();
                //值为0表示进程正常终止,当返回0 表示静默安装已结束
                //若返回其他值代表失败,通常表示大于0的对应的未终止异常线程数,比如1就是有1个进程未终止
                int result = process.exitValue();
                return (Integer) result;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            } finally {
                try {
                    if (dos != null) {
                        dos.close();
                    }
                    process.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            //设备没有root,则调用系统的应用安装页面(请参考上篇文章)

            return 0;
        }

    }


    private static boolean isRoot() {
        boolean bool = false;
        try {
            bool = new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;

    }


    public static void smartInstall(Context context, String apkPath) {

        L.d(TAG, "smartInstall()");
        Uri uri = Uri.fromFile(new File(apkPath));
        Intent localIntent = new Intent(Intent.ACTION_VIEW);
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(localIntent);
    }


    /**
     * 设备唯一id
     */


    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        //去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    //文件结尾
                    break;
                }
            }
        } catch (Exception ex) {
            //赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }


    public static String getDateToString(long milSecond) {

        String pattern = "yyyy-MM-dd HH:mm:ss";
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) // drawable 转换成bitmap
    {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        return bitmap;
    }


    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            L.d(TAG, "本软件的版本号：" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static boolean isKeyboardOpen(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive(editText);

        return isOpen;

    }

    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }

}


