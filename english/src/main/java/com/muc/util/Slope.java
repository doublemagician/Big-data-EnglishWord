package com.muc.util;

import java.util.ArrayList;

/*
* @Author WangYun
* @Date 2020/6/14
* @Desc 计算斜率得工具类
* */
public class Slope {
    public static double getslope(ArrayList<int[]> points)
    {
        int length=points.size();
        float sum=0;
        int times=0;
        for(int i=0;i<length-1;i++)
            for(int j=i+1;j<length;j++)
            {
                try {
                    int[] point1 = points.get(i);
                    int[] point2 = points.get(j);

                    double temp = (Float.valueOf(point1[1]) - Float.valueOf(point2[1])) / (Float.valueOf(point1[0])/10 - Float.valueOf(point2[0])/10);
                    sum += temp;
                    times++;
                }catch (Exception e)
                {
                    continue;
                }
            }
        return sum/times*points.size();
    }
}
