package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingUtils {

	public static Byte regexParseAssemblyConstant(String potentialConstant) throws Exception {
	    Pattern pattern = Pattern.compile("(0[xX])([0-9a-fA-F]{1,2})|(0[bB])([0-1]{1,8})|(0[oO])([0-7]{1,3})|([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(potentialConstant);
	    if(!matcher.find()) {
	    	throw new Exception("Invalid Constant '"+ potentialConstant +"' Specified");
	    }
	    
	    System.out.print("Group 0: " + matcher.group(0));
	    System.out.print(" Group 1: " + matcher.group(1));
	    System.out.println(" Group 2: " + matcher.group(2));
	    
	    if(matcher.group(1) != null) {
	    	return Byte.parseByte(matcher.group(2), 16);
	    }
	    
	    if(matcher.group(7) != null) {
	    	return Byte.parseByte(matcher.group(7), 10);
	    }
	    
	    if(matcher.group(3) != null) {
	    	return Byte.parseByte(matcher.group(4), 2);
	    }
	    
	    if(matcher.group(5) != null) {
	    	return Byte.parseByte(matcher.group(6), 8);
	    }
	    
	    throw new Exception("Invalid Constant '"+ potentialConstant +"' Specified");
	 
	}

}
