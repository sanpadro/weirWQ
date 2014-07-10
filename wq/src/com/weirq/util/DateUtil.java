package com.weirq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String longToString(String dateFormat,Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    Date date= new Date(millSec);
	    return sdf.format(date);
	}
}
