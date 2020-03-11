package com.trilobiet.graphqlweb.datamodel;

import java.text.Normalizer;
import java.util.Comparator;

public interface NormalizedComparator<T> extends Comparator<T> {
	
	/*
	 * Normalize special (accented) characters
	 */
	default String normalized(String s) {
		
		if (s != null) return Normalizer.normalize(s, Normalizer.Form.NFD);
		else return "";
	}
}
