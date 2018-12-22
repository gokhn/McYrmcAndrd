package mac.yorum.android.app.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class Converter {
	
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}
	
	public static float convertPixelsToDp(float px, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;
	}
	
	public static String calendarToString(Calendar calendar)
	{
		String result = "";
		try 
		{
			DateFormat resultDateFormat = new SimpleDateFormat("MM.dd.yyyy");
			result = resultDateFormat.format(calendar.getTime());
		}
		catch (Exception e) {
			return result;
		}
		return result;
	}
	
	public static String stringToShortDate(String date)
	{

		String result = "";

		if (date == null || date.isEmpty() || date.equals("null"))
			return result;

			try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			DateFormat resultFormat = new SimpleDateFormat("dd.MM.yyyy");
			result = resultFormat.format(dateFormat.parse(date.trim()));
		}
		catch (ParseException e) {
			try
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				DateFormat resultFormat = new SimpleDateFormat("dd.MM.yyyy");
				result = resultFormat.format(dateFormat.parse(date.trim()));
			}
			catch(ParseException ex)
			{
				return result;
			}
			return result;
		}
		return result;
	}
	
	public static String stringToLongDate(String date)
	{
		String result = "";


		if (date == null || date.isEmpty() || date.equals("null"))
			return result;

		try 
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			DateFormat resultFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			result = resultFormat.format(dateFormat.parse(date.trim()));
		}
		catch (ParseException e) {
			try
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				DateFormat resultFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				result = resultFormat.format(dateFormat.parse(date.trim()));
			}
			catch(ParseException ex)
			{
				return result;
			}
			return result;
		}
		return result;
	}
	
	public static String diffDay(String startDate, String endDate)
	{
		String result = "";
		Date dateStart = parseDate(startDate.trim());
		Date dateEnd = parseDate(endDate.trim());
		if(dateStart == null || dateEnd == null) return result;
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarStart.setTime(dateStart);
		calendarEnd.setTime(dateEnd);
		return String.valueOf(diffDay(calendarStart, calendarEnd));
	}
	
	public static long diffDay(Calendar calendarStart, Calendar calendarEnd)
	{
		long diff = calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis();
		long diffdays = (diff / (24 * 60 * 60 * 1000)) + 1;
		Log.e("diffday", String.valueOf(diffdays));
		return diffdays;
	}
	
	public static long diffMinute(Calendar calendarStart, Calendar calendarEnd)
	{
		long diff = calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis();
		long diffdays = (diff / (60 * 1000)) + 1;
		Log.e("diffday", String.valueOf(diffdays));
		return diffdays;
	}
	
	public static long diffMinute(long start, long end)
	{
		long diff = end - start;
		long diffMinutes = (diff / (60 * 1000)) + 1;
		return diffMinutes;
	}
	
	public static Date parseDate(String dateString)
	{
		Date date = null;
		try 
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			date = dateFormat.parse(dateString.trim());
		}
		catch (ParseException e) {
			try
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				date = dateFormat.parse(dateString.trim());
			}
			catch(ParseException ex)
			{
				return date;
			}
			return date;
		}
		return date;
	}
	
	public static String encodeUrl(String url) {
		url = url.replace("%", "%25");
		url = url.replace(",", "%2C");
		url = url.replace(";", "%3B");
		url = url.replace("=", "%3D");
		url = url.replace("/", "%2F");
		url = url.replace("&", "%26");
		url = url.replace("+", "%2B");
		url = url.replace("@", "%40");
		url = url.replace("$", "%24");
		url = url.replace("/", "%2F");
		url = url.replace("?", "%3F");
		url = url.replace("#", "%23");
		url = url.replace(" ", "%20");
		url = url.replace("<", "%3C");
		url = url.replace(">", "%3E");
		url = url.replace(">", "%3E");
		url = url.replace(">", "%3E");
		url = url.replace(">", "%3E");
		url = url.replace(">", "%3E");
		url = url.replace("{", "%7B");
		url = url.replace("}", "%7D");
		url = url.replace("|", "%7C");
		url = url.replace("\\", "%5C");
		url = url.replace("^", "%5E");
		url = url.replace("~", "%7E");
		url = url.replace("[", "%5B");
		url = url.replace("]", "%5D");
		url = url.replace("`", "%60");
		url = url.replace("\"", "%22");
		return url;
	}
	
	public static String convertToUnicodeText(String text)
	{
		text = text.replace("�", "c");
		text = text.replace("�", "C");
		text = text.replace("�", "g");
		text = text.replace("�", "G");
		text = text.replace("�", "i");
		text = text.replace("�", "I");
		text = text.replace("�", "o");
		text = text.replace("�", "O");
		text = text.replace("�", "s");
		text = text.replace("�", "S");
		text = text.replace("�", "u");
		text = text.replace("�", "U");
		return text;
	}
	
	public static String convertToUnicodeText2(String text)
	{
		text = text.replace("�", "\u00e7");
		text = text.replace("�", "\u00c7");
		text = text.replace("�", "\u00f0");
		text = text.replace("�", "\u00D0");
		text = text.replace("�", "\u0131");
		text = text.replace("�", "\u0130");
		text = text.replace("�", "\u00f6");
		text = text.replace("�", "\u00d6");
		text = text.replace("�", "\u015f");
		text = text.replace("�", "\u015e");
		text = text.replace("�", "\u00fc");
		text = text.replace("�", "\u00dc");
		return text;
	}
	
	public static double round(double d)
	{
		double result = 0;
		return result;
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String sapDateToString(String date)
	{
		String result = "";
		try 
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat resultDateFormat = new SimpleDateFormat("dd MMM yyyy");
			result = resultDateFormat.format(dateFormat.parse(date.trim()));
		}
		catch (ParseException e)
		{
			return "----";
		}
		return result;
	} 
	
	@SuppressLint("SimpleDateFormat")
	public static String sapTimeToString(String time)
	{
		String result = time.substring(0, 5);
		return result;
	} 
	
	
}
