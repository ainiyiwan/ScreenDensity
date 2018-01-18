package com.example.zy.screendensity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeXmlDouble {

    //文件夹样式values-w{0}dp
    private final static String rootPaths = "C:\\Users\\Administrator\\Desktop\\layout\\values-w{0}dp\\";

    //基准宽度360f
    private final static double dx = 360f;

    //基准样式<dimen name="base1dp">1.00dp</dimen>
    private final static String XTemplate = "<dimen name=\"base{0}dp\">{1}dp</dimen>\n";

    public static void main(String[] args) {
        makeString(320);
        makeString(360);
        makeString(384);
    }

    public static void makeString(int x) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        double cellx = x / dx;//360/360基准数
        //打印多少个dp
        for (int i = 1; i < 100; i++) {
            sb.append(XTemplate.replace("{0}", i + "").replace("{1}",
                    formatDouble1(cellx * i) + ""));
        }
        sb.append("</resources>");

        String path = rootPaths.replace("{0}", x + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "BaseDimen.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100.00f;
    }

    /**
     * 保留两位小数，四舍五入的一个老土的方法
     * @param d
     * @return
     */
    public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }

}