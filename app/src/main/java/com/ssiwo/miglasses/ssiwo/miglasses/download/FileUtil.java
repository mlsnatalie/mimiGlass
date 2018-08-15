package com.ssiwo.miglasses.ssiwo.miglasses.download;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class FileUtil {  
    private static String SDPATH;  
    public FileUtil(){  
        //得到SD卡的目录，如：“sdcard/”  
        SDPATH=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;  
    }  
    /** 
     * 在SD卡的指定目录上创建文件 
     * @param fileName 
     */  
    public File createFile(String fileName){  
        File file=new File(SDPATH+fileName);  
        try {  
            file.createNewFile();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return file;  
    }  
    /** 
     * 在SD卡上创建指定名称的目录 
     * @param dirName 
     */  
    public File createDir(String dirName){  
        File file=new File(SDPATH+dirName+File.separator);  
        file.mkdir();  
        return file;  
    }  
    /** 
     * 判断指定名称的文件在SD卡上是否存在 
     * @param fileName 
     * @return 
     */  
    public boolean isExist(String dirName,String fileName){  
        File file=new File(SDPATH+dirName+fileName);  
        return file.exists();  
    }  
      
    /** 
     * 通过URL得到HttpURLConnection，通过HttpURLConnection得到InputStream 
     * @param urlStr 
     * @return 
     */  
    public InputStream getIS(String urlStr){  
        URL url=null;  
        HttpURLConnection urlConn=null;  
        InputStream is=null;  
        try {  
            url=new URL(urlStr);  
            urlConn=(HttpURLConnection)url.openConnection();  
            is=urlConn.getInputStream();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return is;  
    }  
    /** 
     * 由得到的输入流把下载的文件写入到SD卡的指定位置 
     * @param is 
     * @param dirName 
     * @param fileName 
     * @return 
     */  
    public File IS2SD(InputStream is,String dirName,String fileName){  
        OutputStream os=null;  
        File file=null;
        File dir = null;
        try {
            if (dir == null) {

                dir = createDir(dirName);
            }

            System.out.println(dir);
            file=createFile(dirName+fileName);  
            os=new FileOutputStream(file);  
            byte buffer[]=new byte[1024*4];  
            int temp=0;  
            while((temp=is.read(buffer))!=-1){  
                os.write(buffer, 0, temp);  
            }  
            os.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                os.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return file;  
    }  
    /**
     * 对下载的文件进行MD5完整性校验
     * @param packagePath
     * @param crc
     * @return
     */
    public  boolean verifyInstallPackage(String packagePath,String crc) {    
        try {
        MessageDigest sig = MessageDigest.getInstance("MD5");
           File packageFile = new File(packagePath);
           InputStream signedData = new FileInputStream(packageFile);
           byte[] buffer = new byte[4096];//每次检验的文件区大小
           long toRead = packageFile.length();
           long soFar = 0;
           boolean interrupted = false;
           while (soFar < toRead) {
             interrupted = Thread.interrupted();
           if (interrupted) break;
            int read = signedData.read(buffer);
               soFar += read;
               sig.update(buffer, 0, read);
           }
           byte[] digest = sig.digest();
           String digestStr = bytesToHexString(digest);//将得到的MD5值进行移位转换
           digestStr = digestStr.toLowerCase();
           crc = crc.toLowerCase();
           if (digestStr.equals(crc)) {//比较两个文件的MD5值，如果一样则返回true
               return true;
           }
 
        } catch (Exception e) {
        	// TODO: handle exception
        	return false;
        }
      return false;
    }
    
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        int i = 0;
        while (i < src.length) {
            int v;
            String hv;
            v = (src[i] >> 4) & 0x0F;
            hv = Integer.toHexString(v);
            stringBuilder.append(hv);
            v = src[i] & 0x0F;
            hv = Integer.toHexString(v);
            stringBuilder.append(hv);
            i++;
        }
        return stringBuilder.toString();
    }
      
}  


	