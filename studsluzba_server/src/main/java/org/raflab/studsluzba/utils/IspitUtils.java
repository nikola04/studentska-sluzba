package org.raflab.studsluzba.utils;

public class IspitUtils {
    public static int calculateOcena(double brojPoena){
        if(brojPoena > 90) return 10;
        if(brojPoena > 80) return 9;
        if(brojPoena > 70) return 8;
        if(brojPoena > 60) return 7;
        if(brojPoena > 50) return 6;
        return 5;
    }
}
