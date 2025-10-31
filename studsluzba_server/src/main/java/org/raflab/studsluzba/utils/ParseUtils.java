package org.raflab.studsluzba.utils;

public class ParseUtils {
	
	/*
	 * dobija indeks oblika rn1923 i vraca niz stringova [RN,19,23]
	 */
	public static String[] parseIndeks(String indeksShort) {	
		if(indeksShort.length()<5) return null;
		String[] retVal = new String[3];		
		indeksShort = indeksShort.toUpperCase();
		StringBuilder sb = new StringBuilder();
		// stud program
		int i = 0;
		while(Character.isAlphabetic(indeksShort.charAt(i))){
			sb.append(indeksShort.charAt(i++));
		}
		if(i>=indeksShort.length()) return null;
		retVal[0] = sb.toString();
		sb.setLength(0);		
		// godina
		sb.append(indeksShort.charAt(i++));
		sb.append(indeksShort.charAt(i++));		
		retVal[1] = sb.toString();
		sb.setLength(0);		
		while(i<indeksShort.length()) {
			sb.append(indeksShort.charAt(i++));			
		}		
		retVal[2] = sb.toString();		
		return retVal;		
	}
	/*
	 * dobija email studenta na primer ppetrovic1220rn@raf.rs, a vraca indeks u obliku  [rn, 20, 12]
	 */
	
	public static String[] parseEmail(String studEmail) {
		if(!studEmail.endsWith("@raf.rs")) return null;
		String emailStr = studEmail.substring(0,studEmail.indexOf('@'));
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(Character.isAlphabetic(emailStr.charAt(i))){
			i++;
		}
		while(Character.isDigit(emailStr.charAt(i))) {
			sb.append(emailStr.charAt(i++));
		}
		String cifre = sb.toString();	
		String[] retVal = new String[3];
		retVal[2] = cifre.substring(0, cifre.length()-2);
		retVal[1] = cifre.substring(cifre.length()-2, cifre.length());
		retVal[0] = emailStr.substring(i);		
		return retVal;
	}

}
