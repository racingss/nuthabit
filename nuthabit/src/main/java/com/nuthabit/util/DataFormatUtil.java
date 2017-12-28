package com.nuthabit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	}
}
