/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivanovpv.timeseries.speedtest;

/**
 *
 * @author User
 */
public  class Scaling {
    private static final int TOTAL_ROWS = 100000;
    
    public static int[] scalingRows( int[] rows, int trend_rows) {
        int[] local_rows = new int[TOTAL_ROWS];
        for(int i=0; i<TOTAL_ROWS; i++) {
        local_rows[i] = (rows[i] % trend_rows);
        }
        return local_rows;
    }
    public static int[] scalingParams( int[] params, int trend_params) {
        int[] local_params = new int[TOTAL_ROWS];;
        for(int i=0; i<TOTAL_ROWS; i++) {
         local_params[i] = (params[i] % trend_params);
        }
        return local_params;
    }
    
}
