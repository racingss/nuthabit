package com.nuthabit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataFormatUtil {
	static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	public static DataResult getDayOfMonth(int i, int j) {
		Locale.setDefault(Locale.CHINESE);

		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		calendar.set(Calendar.DAY_OF_WEEK, j);
		
		
		
		if (firstDayOfWeek > j)
			i = i - 1;
		calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, i);
		DataResult r = new DataResult();

		if (calendar.get(Calendar.MONTH) != month)
			r.thisMonth = false;
		r.formatDate = formater.format(calendar.getTime());
		r.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return r;
	}

	/**
	 * 获得下个月最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfNextMonth() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 获得上个月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfLastMonth() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 获得所在年月的第一天和最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[] getMonthDayRange(int year, int month) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		String[] range = new String[2];
		range[0] = dft.format(calendar.getTime());
		calendar.set(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		range[1] = dft.format(calendar.getTime());

		return range;
	}

	public static void main(String arg[]) {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 7; j++) {
				DataResult r = getDayOfMonth(i, j);
				if (r.dayOfMonth < 10)
					System.out.print(" "+r.dayOfMonth + " ");
				else
					System.out.print(r.dayOfMonth + " ");
			}
			System.out.println();
		}
		
		String[] range = DataFormatUtil.getMonthDayRange(2018, 1);
		System.out.println(range[0] + "|" + range[1]);
	}
}
