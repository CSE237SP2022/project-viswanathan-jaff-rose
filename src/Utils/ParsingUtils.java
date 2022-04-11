package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingUtils {

	public static Integer regexParseAssemblyConstant(String potentialConstant) throws Exception {
	    Pattern pattern = Pattern.compile("(0[xX])([0-9a-fA-F]{1,2})|(0[bB])([0-1]{1,8})|(0[oO])([0-7]{1,3})|([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(potentialConstant);
	    if(!matcher.find()) {
	    	throw new Exception("Invalid Constant '"+ potentialConstant +"' Specified");
	    }
	    
	    Integer parsedConstant = new Integer(0xFFFFFFFF);
	    
	    if(matcher.group(1) != null) {
	    	parsedConstant =  Integer.parseInt(matcher.group(2), 16);
	    }
	    
	    if(matcher.group(7) != null) {
	    	parsedConstant =  Integer.parseInt(matcher.group(7), 10);
	    }
	    
	    if(matcher.group(3) != null) {
	    	parsedConstant =  Integer.parseInt(matcher.group(4), 2);
	    }
	    
	    if(matcher.group(5) != null) {
	    	parsedConstant =  Integer.parseInt(matcher.group(6), 8);
	    }
	    
	    System.out.println(((parsedConstant << 24) >> 24));
	    
	    parsedConstant = ((parsedConstant << 24) >> 24);
	    
	    if( parsedConstant > 255) {
	    	throw new Exception("Invalid Constant '"+ potentialConstant +"' Specified");
	    }
	    
	    return parsedConstant;
	 
	}
	

}
