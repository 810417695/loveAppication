package ipd.com.love.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * ʱ�乤����
 * 
 * @author yang
 * 
 */
public class MyTimeUtils {

	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;
	private static final long ONE_WEEK = 604800000L;

	private static final String ONE_SECOND_AGO = "��ǰ";
	private static final String ONE_MINUTE_AGO = "����ǰ";
	private static final String ONE_HOUR_AGO = "Сʱǰ";
	private static final String ONE_DAY_AGO = "��ǰ";
	private static final String ONE_MONTH_AGO = "��ǰ";
	private static final String ONE_YEAR_AGO = "��ǰ";

	public static final String DATA_STYLE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ���ض�ʱ���ַ�����ʽyyyy-MM-dd HH:mm:ss
	 */

	@SuppressLint("SimpleDateFormat")
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DATA_STYLE);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ����תyyyy-MM-dd HH:mm:ss��ʽ��ʱ��
	 * 
	 * @param l
	 *            ����ֵ
	 * @return ���ض�ʱ���ַ�����ʽyyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStringDate(long l) {
		Date currentTime = new Date(l);
		// currentTime.setTime(l);
		SimpleDateFormat formatter = new SimpleDateFormat(DATA_STYLE);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss ת�� ��Ӧʱ�����ֵ
	 * 
	 * @param time
	 *            yyyy-MM-dd HH:mm:ss��ʽ��ʱ��
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Long getLongTime(String time) {

		SimpleDateFormat formatter = new SimpleDateFormat(DATA_STYLE);
		try {
			Date date = formatter.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���ؼ���ǰ ����ǰ
	 * 
	 * @param time
	 *            "yyyy-MM-dd HH:mm:ss"��ʽ��ʱ��
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String format(String time) {
		SimpleDateFormat format = new SimpleDateFormat(DATA_STYLE);
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		long delta = new Date().getTime() - date.getTime();
		if (delta < 1L * ONE_MINUTE) {
			long seconds = toSeconds(delta);
			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
		}
		if (delta < 45L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}
		if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}
		if (delta < 48L * ONE_HOUR) {
			return "����";
		}
		if (delta < 30L * ONE_DAY) {
			long days = toDays(delta);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		}

		if (delta < 12L * 4L * ONE_WEEK) {
			long months = toMonths(delta);
			return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
		} else {
			long years = toYears(delta);
			return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
		}
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}

}