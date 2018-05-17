package shenzhen.md5算法;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * created by dailf on 2018/5/11
 *
 * @author dailf
 */
public class md5 {
    public static void main(String[] args) {
        System.out.println(getMD5("captcha.jpg"));
    }
    //对文件进行MD5摘要
    public static String getMD5(String path){

        String pathName = path;
        String md5= "";
        try {
//            File file = new File(pathName);
//            FileInputStream ins = new FileInputStream(file);
//            FileChannel ch = ins.getChannel();
//            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length());
                MessageDigest md = MessageDigest.getInstance("MD5");

//            md.update(byteBuffer);
//            ins.close();
            md5 = toHexString(md.digest("lalalala".getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
       }
        return md5;
    }
    final static char hex[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String toHexString(byte[] tmp){
        String s;
        char str[] = new char[tmp.length*2];
        int k =0;
        for (int i = 0; i < tmp.length; i++) {
            byte byte0 = tmp[i];
            str[k++] = hex[byte0>>>4&0xf];
            str[k++] = hex[byte0&0xf];
        }
        s=new String(str);
        return s;
    }
}
