package org.raflab.studsluzba.utils;

import java.util.List;

public class ParseUtils {
	
	/*
	 * dobija indeks oblika rn1923 i vraca niz stringova [RN,19,23]
	 */
	public static List<String> parseIndeks(String indeksShort) {
		if(indeksShort.length()<5) return null;

        StringBuilder oznaka = new StringBuilder();
        int i = 0;
        while(i < indeksShort.length()){
            char c = indeksShort.charAt(i++);
            if(!Character.isAlphabetic(c))
                break;
            oznaka.append(c);
        }

        if(i > indeksShort.length() - 2)
            return null;
        String broj = indeksShort.substring(i - 1, indeksShort.length() - 2);

        String godina = "20" + indeksShort.substring(indeksShort.length() - 2);
		return List.of(broj, godina, oznaka.toString().toUpperCase());
	}
}
