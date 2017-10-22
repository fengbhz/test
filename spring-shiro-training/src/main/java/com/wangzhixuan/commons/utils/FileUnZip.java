package com.wangzhixuan.commons.utils;

/**
 * Created by Administrator on 2017/8/23 0023.
         */
import java.io.*;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class FileUnZip {
    /**
     * 解压zip文件
     *
     * @param sourceFile,待解压的zip文件;
     *            toFolder,解压后的存放路径
     * @throws Exception
     **/

    public static String  zipToFile(String sourceFile, String toFolder) throws Exception {
        String fileName="";
        String toDisk = toFolder;// 接收解压后的存放路径
        ZipFile zfile = new ZipFile(sourceFile, "utf-8");// 连接待解压文件
        Enumeration zList = zfile.getEntries();// 得到zip包里的所有元素
        ZipEntry ze = null;
        boolean flag=true;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                System.out.println("打开zip文件里的文件夹:"+ ze.getName() +"skipped...");
                continue;
            }
            OutputStream outputStream = null;
            InputStream inputStream = null;
            try {
                // 以ZipEntry为参数得到一个InputStream，并写到OutputStream中
                outputStream = new BufferedOutputStream(new FileOutputStream(getRealFileName(toDisk, ze.getName())));
                inputStream = new BufferedInputStream(zfile.getInputStream(ze));
                int readLen = 0;
                while ((readLen = inputStream.read(buf, 0, 1024)) != -1) {
                    outputStream.write(buf, 0, readLen);
                }
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                // log.info("解压失败："+e.toString());
                throw new IOException("解压失败：" + e.toString());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {

                    }
                }
                while(flag&&null!=ze&&StringUtils.isNotBlank(ze.getName())){
                    fileName=ze.getName().toString().trim().split("/")[0];
                    flag=false;
                }

                if (outputStream != null) {
                        outputStream.close();
                }
                inputStream = null;
                outputStream = null;
            }

        }
        zfile.close();
        FileUnZip.deleteZip(sourceFile);
        return fileName;
    }

    /**
     *
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     *
     * @param zippath
     *            指定根目录
     *
     * @param absFileName
     *            相对路径名，来自于ZipEntry中的name
     *
     * @return java.io.File 实际的文件
     *
     */

    private static File getRealFileName(String zippath, String absFileName) {
        // log.info("文件名："+absFileName);
        String[] dirs = absFileName.split("/", absFileName.length());
        File ret = new File(zippath);// 创建文件对象
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                ret = new File(ret, dirs[i]);
            }
        }
        if (!ret.exists()) {// 检测文件是否存在
            ret.mkdirs();// 创建此抽象路径名指定的目录
        }
        ret = new File(ret, dirs[dirs.length - 1]);// 根据 ret 抽象路径名和 child
        // 路径名字符串创建一个新 File 实例
        return ret;
    }

    /**
     * 删除zip
     */
    public static void deleteZip(String path) {
        File file = new File(path);// 里面输入特定目录
            if (file.getName().endsWith("zip"))// 获得文件名，如果后缀为“”，这个你自己写，就删除文件
            {
                file.delete();// 删除文件}
            }
    }

}