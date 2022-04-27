package Utils;

public class BitUtils {
	
	/**
	 * Zeroes out the high bits to the new
	 * @param value - Value to be sterilized
	 * @return cleanedValue - Value with high bits zeroed
	 */
	public static Integer sterilizeBit(Integer value, int bitWidth) {
		int shiftlength = 32 - bitWidth;
		
		return ((value << shiftlength) >> shiftlength);
	}

}
