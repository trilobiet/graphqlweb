package com.trilobiet.graphqlweb.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.trilobiet.graphqlweb.datamodel.File;

public class ViewHelpers {

	public static List<File> images(List<File> input) {
		
		List<File> images = new ArrayList<>(input);
		images.removeIf(f -> !isImage(f));
		return images;
	}
	
	public static List<File> attachments(List<File> input) {
		
		List<File> attachments = new ArrayList<>(input);
		attachments.removeIf(f -> isImage(f));
		return attachments;
	}
	
	public static boolean isImage(File file) {
		
		String imgExts = ".png .jpg .jpeg .webp .gif";
		boolean isImage = imgExts.contains(file.getExt());
		return isImage;		
	}
	
	public static String urlEncode(String input) {
		
		String r = input;
		
		try {
			r = URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			r = "could_not_encode_input";
		}
		
		return r;
	}
	
}
