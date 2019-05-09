package cn.luozc.unit_app.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellUtil {


      /**
        * 
        * @param shPath   需要执行的命令或脚本路径
        * @return
        */
    public static String excute(String shPath){
        String result="";
        try {
            Process ps = Runtime.getRuntime().exec(shPath);
            result = ps.waitFor() + "|";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ps.getInputStream(), "gbk"));
            String ls = "";
            while ((ls = bufferedReader.readLine()) != null) {
              result = result + ls;
            }
        }catch (Exception e) {
          e.printStackTrace();
        }
        return result;
    }
}