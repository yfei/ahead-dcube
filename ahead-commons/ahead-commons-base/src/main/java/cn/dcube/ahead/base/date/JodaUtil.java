package cn.dcube.ahead.base.date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @date：2021-12-21 10:46<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
public class JodaUtil {

    public static final String YYYYMMDD = "yyyy-MM-dd";

    public static final String YYYYMMDD000000 = "yyyy-MM-dd 00:00:00";

    public static final String YYYYMMDD235959 = "yyyy-MM-dd 23:59:59";

    public static final String YYYYMM = "yyyy-MM";

    public static final String STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static final String STANDARD_DATETIME = "yyyy/MM/dd HH:mm:ss";

    public static final String FORMAT_YYMMDDHHmm00 = "yyyy-MM-dd HH:mm:00";

    public static final String STANDARD_0 = "yyyy-MM-dd HH:mm:ss.0";

    public static final String STANDARD_MS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String FORMAT_YYMMDD = "yyyyMMdd";

    public static final String FORMAT_YYMM = "yyyyMM";

    public static final String FORMAT_WEEK = "EE";

    /**
     * 中国周一是一周的第一天
     */
    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;

    /**
     * 每天的毫秒数
     */
    private final static long DAY_IN_MILLISECOND = 1000 * 3600 * 24;

    /**
     * 1天的毫秒数
     */
    private final static long _24Hours = 24 * 60 * 60 * 1000;

    private static final Logger logger = LoggerFactory.getLogger(JodaUtil.class);

    public static final DateTimeFormatter standardFormatter = DateTimeFormat.forPattern(STANDARD);

    /**
     * @param strDate      输入的字符串类型的日期时间
     * @param inputFormat  输入的日期类型字符串格式类型
     * @param outputFormat 输出的日期时间类型字符串格式类型
     * @return 经过转换后的时间字符串
     */
    public static String formatDate(String strDate, String inputFormat, String outputFormat) {
        return formatStrDate(strDate, inputFormat, Locale.getDefault()).toString(outputFormat);
    }

    /**
     * @param strDate     输入的字符串类型的日期时间
     * @param inputFormat 输入的日期类型字符串格式类型
     * @return
     */
    public static String formatDate(String strDate, String inputFormat) {
        return formatStrDate(strDate, inputFormat, Locale.getDefault()).toString(STANDARD);
    }

    /**
     * @param inputStrDate     输入的字符串类型的日期时间
     * @return
     */
    public static String formatDateToYYYYMM(String inputStrDate) {
        return formatStrDate(inputStrDate, STANDARD, Locale.getDefault()).toString(FORMAT_YYMM);
    }

    public static String formatDateToDateSimple(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD);
    }

    /**
     * @param strDate     输入的字符串类型的日期时间
     * @param inputFormat 输入的日期类型字符串格式类型
     * @param locale      区域
     * @return 经过归一化后的日期时间字符串
     */
    public static String formatDate(String strDate, String inputFormat, Locale locale) {
        return formatStrDate(strDate, inputFormat, locale).toString(STANDARD);
    }

    /**
     * @param strDate     输入的字符串类型的日期时间
     * @param inputFormat 输入的日期类型字符串格式类型
     * @param locale      区域
     * @return 经过归一化后的日期时间字符串
     */
    public static DateTime formatStrDate(String strDate, String inputFormat, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        DateTime time = DateTimeFormat.forPattern(inputFormat).withLocale(locale).parseDateTime(strDate);
        if (inputFormat.indexOf("y") < 0) {
            // 没有年份,认为是当前年
            time = new DateTime(DateTime.now().getYear(), time.getMonthOfYear(), time.getDayOfMonth(),
                    time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute());
        }

        return time;
    }

    /**
     * 日期转成格式化的时间字符串
     *
     * @param date         时间对象
     * @return 转换后的符合指定格式的时间字符串
     */
    public static String formatDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD);
    }

    /**
     * 日期转成格式化的时间字符串
     *
     * @param date         时间对象
     * @param outputFormat 时间格式
     * @return 转换后的符合指定格式的时间字符串
     */
    public static String formatDate(Date date, String outputFormat) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(outputFormat);
    }

    /**
     * 将日期格式的时间转换为字符串形式
     *
     * @param date 日期格式的时间
     * @return 字符串形式的时间
     */
    public static String dateToStr(Date date) {
        return formatDate(date, STANDARD);
    }

    /**
     * 设置日期中某些位为0
     *
     * @param date        日期对象
     * @param inputFormat yyyy-MM-dd HH:MM:00
     * @return 经过设置过的日期对象
     */
    public static Date date(Date date, String inputFormat) {
        DateTime dateTime = new DateTime();
        String strDate = dateTime.toString(inputFormat);
        Date outputDate = DateTimeFormat.forPattern(inputFormat).parseDateTime(strDate).toDate();

        return outputDate;
    }

    /**
     * @param dateTime
     * @return
     */
    public static long strDateTimeToLong(String dateTime) {
        return standardStrDateTime2Date(dateTime).toDate().getTime();
    }

    /**
     * yyyy-MM-dd HH:mm:ss 格式转成long类型的毫秒数
     *
     * @param strDateTime
     * @return
     */
    public static DateTime standardStrDateTime2Date(String strDateTime) {
        return standardFormatter.parseDateTime(strDateTime).toDateTime();
    }

    /**
     * 不对输入的字符串进行正则匹配，使用yyyy-MM-dd HH:mm:ss 进行转换
     *
     * @param strDate 输入的日期格式字符串
     * @return 根据标准日期格式转换后的日期字符串
     */
    public static Date parseStrToDateSimple(String strDate) {
        Date date = standardStrDateTime2Date(strDate).toDate();

        return date;
    }

    /**
     * 不对输入的字符串进行正则匹配，使用yyyy-MM-dd HH:mm:ss 进行转换
     *
     * @param strDate 输入的日期格式字符串
     * @return JodaTime对象。根据标准日期格式转换后的日期字符串
     */
    public static DateTime parseStrToDateTimeSimple(String strDate) {
        DateTime date = standardStrDateTime2Date(strDate);

        return date;
    }

    /**
     * 根据输入的日期型字符串和时间格式进行转换成日期对象
     *
     * @param strDate     输入的日期格式字符串
     * @param inputFormat 日期格式字符串类型
     * @return 转换后的时间类型
     */
    public static Date parseStrToDateSimple(String strDate, String inputFormat) {
        Date date = DateTimeFormat.forPattern(inputFormat).parseDateTime(strDate).toDate();

        return date;
    }

    /**
     * 根据输入的日期型字符串和时间格式进行转换成日期对象
     *
     * @param strDate     输入的日期格式字符串
     * @param inputFormat 日期格式字符串类型
     * @param locale      地区
     * @return 转换后的时间类型
     */
    public static Date parseStrToDateSimple(String strDate, String inputFormat, Locale locale) {
        Date date = DateTimeFormat.forPattern(inputFormat).withLocale(locale).parseDateTime(strDate).toDate();

        return date;
    }

    /**
     * 将长整型的时间转化为标准格式，例如 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatLongTime(long time) {
        DateTime dateTime = new DateTime(sec2ms(time));

        return dateTime.toString(STANDARD);
    }

    /**
     * 今天最后1秒
     *
     * @return yyy-MM-dd 23:59:59
     */
    public static long todayLastSecond() {
        String time = DateTime.now().toString("yyyy-MM-dd") + " 23:59:59";
        long result = parseStrToDateTimeSimple(time).getMillis();

        return result;
    }

    /**
     * 格式化毫秒级别的时间串<br>
     * 将长整型的时间转化为标准格式，例如 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatLongMSTime(long time) {
        DateTime dateTime = new DateTime(time);

        return dateTime.toString(STANDARD);
    }

    public static String formatLongMSTime4OS(long time) {
        DateTime dateTime = new DateTime(time);

        return dateTime.toString(STANDARD_DATETIME);
    }

    /**
     * 将长整型的时间转化为指定格式，例如 yyyyMMddHH
     *
     * @param time
     * @param format
     * @return
     */
    public static String formatLongTime(long time, String format) {
        DateTime dateTime = new DateTime(sec2ms(time));

        return dateTime.toString(format);
    }

    /**
     * 获取当前日期时间,秒数是0 例如: yyyy-MM-dd HH:mm:00
     */
    public static Date currentDateTimeByZeroSec() {
        DateTime dateTime = new DateTime();
        int a = dateTime.getSecondOfMinute();
        Date resultDate = dateTime.minusSeconds(a).toDate();

        return resultDate;
    }

    /**
     * 获取字符串格式的当前时间
     *
     * @return 2015-05-12 18:35:12
     */
    public static String currentDateTime() {
        DateTime dateTime = new DateTime();

        return dateTime.toString(STANDARD);
    }

    public static String currentDateTimeInMS() {
        DateTime dateTime = new DateTime();

        return dateTime.toString(STANDARD_MS);
    }


    public static String currentDateTime(String type) {
        DateTime dateTime = new DateTime();

        return dateTime.toString(type);
    }


    /**
     * 获取字符串格式的当前时间
     *
     * @return 2015-05-12 18:35:12
     */
    public static String currentDateTimeYYYYMMDD235959() {
        DateTime dateTime = new DateTime();

        return dateTime.toString(YYYYMMDD235959);
    }

    /**
     * 获取当前年月日的字符串形式 日期"XXXX-XX-XX"
     *
     * @return 例如:2016-12-27
     */
    public static String currentStrDate() {
        LocalDate now = new LocalDate();

        return now.toString();
    }

    public static DateTime currentDateTimeObject() {
        DateTime dateTime = new DateTime();

        return dateTime;
    }

    /**
     * 获取当前年
     *
     * @return 当前的年份
     */
    public static int currentYear() {
        DateTime dateTime = new DateTime();

        return dateTime.getYear();
    }

    public static int currentMonth() {
        DateTime dateTime = new DateTime();

        return dateTime.getMonthOfYear();
    }

    public static int currentDay() {
        DateTime dateTime = new DateTime();

        return dateTime.getDayOfMonth();
    }

    public static String currentYearMonth() {
        DateTime dateTime = new DateTime();

        return dateTime.toString(YYYYMM);
    }

    public static String currentByFormat(String format) {
        DateTime dateTime = new DateTime();

        return dateTime.toString(format);
    }

    /**
     * 获取当前日期对象
     *
     * @return 当前日期对象
     */
    public static Date currentDate() {
        LocalDate now = new LocalDate();

        return now.toDate();
    }

    /**
     * 获取字符串格式的当前时间
     *
     * @return 2015-05-12 18:35:12
     */
    public static String currentDate(String format) {
        DateTime dateTime = new DateTime();

        return dateTime.toString(format);
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        StringBuffer sb = new StringBuffer();

        sb.append(days);
        sb.append(" days");
        sb.append(" ");

        if (hours < 10) {
            sb.append("0");
        }
        sb.append(hours);
        sb.append(":");

        if (minutes < 10) {
            sb.append("0");
        }
        sb.append(minutes);
        sb.append(":");
        if (seconds < 10) {
            sb.append("0");
        }
        sb.append(seconds);

        return sb.toString();
    }

    /**
     * 把秒转换成毫秒
     *
     * @param time
     * @return
     */
    private static long sec2ms(long time) {
        // 时间单位是秒,转换成毫秒. 2014-08-13 free waf触发该情况
        if (time >= 1000000000L && time < 1000000000000L) {
            time *= 1000;
        }
        return time;
    }

    /**
     * 日期A晚于日期B<br>
     * java 的SimpleDateFormat类线程不安全<br>
     * 会抛出异常: java.lang.ArrayIndexOutOfBoundsException: 5805585
     *
     * @param dateA 日期时间A
     * @param dateB 日期时间B
     * @return true:A日期晚于B日期; false:A日期早于B日期
     * @throws Exception
     */
    public static boolean compareADateAfterB(String dateA, String dateB) throws Exception {
        boolean blnResult = false;

        try {
            if (null != dateA && !("".equals(dateA.trim())) && null != dateB && !("".equals(dateB.trim()))) {
                Date dA = parseStrToDateSimple(dateA);
                Date dB = parseStrToDateSimple(dateB);

                blnResult = dA.after(dB);
            }
        } catch (Exception e) {
            logger.error("dateA<" + dateA + "> dateB<" + dateB + ">", e);
        }

        return blnResult;
    }

    /**
     * 验证时间是否超过1天或者晚于1天
     *
     * @param standardData
     */
    public static String verifyDate(String standardData) {
        String checkedDateTime = standardData;

        long millisTime = JodaUtil.standardStrDateTime2Date(standardData).getMillis();
        if (is2050YearBug(millisTime)) {
            checkedDateTime = currentDateTime();
        }

        if (is1970YearBug(millisTime)) {
            checkedDateTime = currentDateTime();
        }

        return checkedDateTime;
    }

    /**
     * 1970问题，时间比当前时间晚，例如现在是2014年，但是晚了1970年
     *
     * @return =true:超前的异常时间; false:正常的时间
     */
    public static boolean is1970YearBug(long inputTime) {
        boolean result = false;

        if ((System.currentTimeMillis() - inputTime) > _24Hours) {
            result = true;
        }

        return result;
    }

    /**
     * 2053问题，开始时间比当前时间早提前，例如现在是2014年，但是提前到了2053年
     *
     * @return =true:超前的异常时间; false:正常的时间
     */
    public static boolean is2050YearBug(long inputTime) {
        boolean result = false;

        if ((inputTime - System.currentTimeMillis()) > _24Hours) {
            result = true;
        }

        return result;
    }

    /**
     * 格式化已知格式时间字符串
     *
     * @param dateValue
     * @param format    输入时间字符串格式
     * @param locale    本地
     * @return 格式化后的时间格式字符串
     */
    public static String formatDateWithCheck(String dateValue, String format, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        DateTimeFormatter df = DateTimeFormat.forPattern(format).withLocale(locale);
        DateTime time = DateTime.parse(dateValue, df);
        if (format.indexOf("y") < 0) { // 日期格式模板中没有年份,认为是当前年
            time = new DateTime(DateTime.now().getYear(), time.getMonthOfYear(), time.getDayOfMonth(),
                    time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute());
        }

        String formatedTime = time.toString(STANDARD);

        return formatedTime;
    }

    /**
     * 格式化long类型的时间
     *
     * @param time
     * @return
     */
    public static String formatLongTimeWithCheck(long time) {
        DateTime dateTime = new DateTime(sec2ms(time));
        String formatedTime = dateTime.toString(STANDARD);

        long millisTime = dateTime.getMillis();

        // 2053问题，开始时间比当前时间早提前，例如现在是2014年，但是提前到了2053年
        if (is2050YearBug(millisTime)) {
            formatedTime = currentDateTime();
        }

        if (is1970YearBug(millisTime)) {
            formatedTime = currentDateTime();
        }

        return formatedTime;
    }

    /**
     * 比输入时间晚1天的时间
     *
     * @param strDate   输入时间
     * @param newFormat 输出时间格式串
     * @return 晚一天的时间串
     */
    public static String getAfterOneDay(String strDate, String newFormat) {
        return getAfterDay(strDate, STANDARD, 1, JodaUtil.YYYYMMDD);
    }

    /**
     * 获取当前日期时间的后一天
     *
     * @param strDate   输入的日期时间
     * @param format    输入日期时间串的格式
     * @param num       比输入的时间晚的天数
     * @param newFormat 输出的日志格式串
     * @return 比当前时间晚一天的日期时间
     */
    public static String getAfterDay(String strDate, String format, int num, String newFormat) {
        DateTime time = DateTimeFormat.forPattern(format).parseDateTime(strDate);

        return time.plusDays(num).toString(newFormat);
    }

    /**
     * 比较两个时间的大小(去掉小时,分钟和秒后进行比较)
     *
     * @param strDate1     日期时间对象
     * @param strDate2           日期时间对象
     * @param outputFormat 根据给定的格式进行比较<br>
     *                     例如: YYYYMMDD 过滤掉小时,分,秒后进行比较
     * @return 大于为 1 / 等于 0 / 小于-1
     */
    public static int compareDate(Date strDate1, Date strDate2, String outputFormat) {
        String tempStrDate1 = formatDate(strDate1, outputFormat);
        String tempStrDate2 = formatDate(strDate2, outputFormat);

        // 过滤掉某些数值后进行比较
        long time1 = formatStrDate(tempStrDate1, outputFormat, Locale.getDefault()).getMillis();
        long time2 = formatStrDate(tempStrDate2, outputFormat, Locale.getDefault()).getMillis();

        if (time1 == time2) {
            return 0;
        }

        return time1 > time2 ? 1 : -1;
    }

    /**
     * 获取给定时间所在月的最后1天的日期
     *
     * @param sDate1 给定时间
     * @return 给定时间最后1天的日期
     */
    public static Date getLastDayOfMonth(Date sDate1) {
        LocalDate now = new LocalDate(sDate1);
        LocalDate lastDayOfPreviousMonth = now.dayOfMonth().withMaximumValue();

        return lastDayOfPreviousMonth.toDate();
    }

    /**
     * 获取某段时间之前的时间点的字符串形式
     *
     * @param strDate      基准时间
     * @param timeDistance 时间段 （单位：毫秒）
     * @return 某段时间之前的时间点的字符串形式
     */
    public static String getEarlyDate(String strDate, int timeDistance) {
        String earlyDateStr = null;
        Date date = JodaUtil.parseStrToDateSimple(strDate);
        DateTime dateTime = new DateTime(date);
        earlyDateStr = dateTime.minus(timeDistance).toString(JodaUtil.STANDARD);

        return earlyDateStr;
    }

    /**
     * 获取某段时间之前的时间点的字符串形式
     *
     * @param date      基准时间
     * @param timeDistance 时间段 （单位：毫秒）
     * @return 某段时间之前的时间点的字符串形式
     */
    public static String getEarlyDate(Date date, int timeDistance) {
        String earlyDateStr = null;
        DateTime dateTime = new DateTime(date);
        earlyDateStr = dateTime.minus(timeDistance).toString(JodaUtil.STANDARD);

        return earlyDateStr;
    }

    /**
     * 下月第一天
     *
     * @param date 指定的时间对象
     * @return 指定时间的下个月第一天时间对象
     */
    public static Date getNextMonthFirst(Date date) {
        DateTime dateTime = new DateTime(date);
        int day = dateTime.dayOfMonth().get() - 1;
        Date resultDate = dateTime.plusMonths(1).minusDays(day).toDate();

        return resultDate;
    }

    /**
     * 得到两个日期间的间隔天数<br>
     * (如果指定的format格式精确到时、分、秒，不足一天会舍去的, <br>
     * 比如2013-10-11 10:20:00 与 2013-10-11 08:20:00 会得到相差0天)
     *
     * @param date1 输入的第一个时间
     * @param date2 输入的第二个时间
     * @return 忽略时分秒后进行比较
     */
    public static long getDistanceDay(Date date1, Date date2) {
        if (date1 == null) {
            throw new RuntimeException("date1 参数不正确");
        }
        if (date2 == null) {
            throw new RuntimeException("date2 参数不正确");
        }

        DateTime dateTime1 = new DateTime(date1);
        DateTime dateTime2 = new DateTime(date2);

        // 忽略时,分,秒进行比较
        Date d1 = dateTime1.minusHours(dateTime1.getHourOfDay()).minusMinutes(dateTime1.getMinuteOfHour())
                .minusSeconds(dateTime1.getSecondOfMinute()).toDate();
        Date d2 = dateTime2.minusHours(dateTime2.getHourOfDay()).minusMinutes(dateTime2.getMinuteOfHour())
                .minusSeconds(dateTime2.getSecondOfMinute()).toDate();

        return Math.abs(d1.getTime() - d2.getTime()) / DAY_IN_MILLISECOND;
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @return 当前时间所在一周的周一的日期
     */
    public static Date getMondayOfWeek() {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfWeek().withMinimumValue().dayOfWeek().getDateTime().toDate();
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @return 当前时间所在一周的周一的日期
     */
    public static String getMondayOfWeek2String() {
        DateTime dateTime = new DateTime();
        Date date = dateTime.dayOfWeek().withMinimumValue().dayOfWeek().getDateTime().toDate();

        return formatDate(date, YYYYMMDD000000);
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @param date 当前日期时间
     * @return 当前时间所在一周的周一的日期
     */
    public static Date getMondayOfWeek(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.dayOfWeek().withMinimumValue().dayOfWeek().getDateTime().toDate();
    }

    /**
     * 相对于参数日期一月前的日期
     *
     * @return
     */
    public static Date getBeforeMonthDate(Date date) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);
        // 取得本月所过天数
        int curMonthPassDays = getPassDayOfMonth(date);
        // 取得当前月天数
        // int currentDays = Calendar.DAY_OF_MONTH;
        // 上月
        lastDate.add(Calendar.MONTH, -1);
        // 取得上月天数
        int lastMonthDays = lastDate.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (curMonthPassDays > lastMonthDays) {
            lastDate.set(Calendar.DATE, lastMonthDays);
        } else {
            lastDate.set(Calendar.DATE, curMonthPassDays);
        }

        return lastDate.getTime();
    }

    /**
     * 获取从当前时间开始前N个月的年份和月份
     *
     * @param date         制定日期或者当前时间
     * @param beforeNMonth 包括当前月份开始前N个月
     * @return 返回内容例如:201701 201702 201703 201704
     */
    public static Set<String> beforeNMonth(Date date, int beforeNMonth) {
        Set<String> result = new TreeSet<String>();
        DateTime dateTime = new DateTime(date);

        result.add(currentDate(FORMAT_YYMM));
        for (int i = 1; i < beforeNMonth; i++) {
            dateTime = dateTime.minusMonths(1);
            result.add(dateTime.toString(FORMAT_YYMM));
        }

        return result;
    }

    /**
     * 取得月已经过的天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 是否是本月第一天
     *
     * @param date
     * @return
     */
    public static boolean isMonthFirstDay(String date) {
        // 当天日期
        Date curDate = JodaUtil.formatStrDate(date, YYYYMMDD, Locale.getDefault()).toDate();
        if (getIntervalDaysByDate(curDate, getFirstDateOfMonth(curDate)) == 0) {
            return true;
        }

        return false;
    }

    /**
     * 计算两个时间之间差几天
     *
     * @param startday 开始时间
     * @param endday   结束时间
     * @return 相差天数
     */
    public static int getIntervalDaysByDate(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;

        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * 上月第一天
     *
     * @param date
     * @return
     */
    public static Date getBeforeMonthFirst(Date date) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);
        lastDate.set(Calendar.DATE, 1); // 设为当前月的1号
        lastDate.add(Calendar.MONTH, -1);

        return lastDate.getTime();
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.dayOfMonth().withMinimumValue().dayOfMonth().getDateTime().toDate();
    }

    public static Date getFirstDateOfMonth() {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfMonth().withMinimumValue().dayOfMonth().getDateTime().toDate();
    }

    /**
     * 获取当前月第一天的0点0分0秒
     *
     * @return 2018-08-01 00:00:00
     */
    public static String getFirstDateOfMonth2String() {
        DateTime dateTime = new DateTime();
        String result = dateTime.dayOfMonth().withMinimumValue().dayOfMonth().getDateTime().toString(YYYYMMDD000000);

        return result;

    }

    /**
     * 获取指定日期前n天的日期集合
     *
     * @param date 指定日期
     * @param days 天数
     * @return 倒序
     */
    public static List<String> getBeforeDayList(Date date, int days) {
        List<String> dayList = new ArrayList<String>();

        DateTime dateTime = new DateTime(date);

        for (int i = days; i > 0; i--) {
            String before = dateTime.minusDays(i).toString(YYYYMMDD);
            dayList.add(before);
        }

        return dayList;
    }

    /**
     * 获取指定时间之前N天的时间
     *
     * @param date
     * @param n
     * @return
     */
    public static String getBeforeDay2String(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(n).toString(STANDARD);
    }

    /**
     * 根据日期取上一个工作日
     *
     * @param date
     * @return
     */
    public static Date getBeforeDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(1).toDate();
    }

    public static Date getAfterDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(1).toDate();
    }

    /**
     * 根据日期取上一个工作日
     *
     * @param date
     * @return
     */
    public static String getBeforeDay2String(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(1).toString(STANDARD);
    }

    public static Date getAfterXDay(Date date, int x) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(x).toDate();
    }

    /**
     * 根据日期获取当年第一天
     */
    public static Date getYearFirstDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.dayOfYear().withMinimumValue().dayOfYear().getDateTime().toDate();
    }

    /**
     * 根据日期获取当年第一天
     */
    public static String getYearFirstDay2String(Date date) {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfYear().withMinimumValue().dayOfYear().getDateTime().toString(STANDARD);
    }

    /**
     * 根据日期获取当年第一天
     */
    public static Date getYearFirstDay() {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfYear().withMinimumValue().dayOfYear().getDateTime().toDate();
    }

    /**
     * 根据日期获取当年第一天
     */
    public static String getYearFirstDay2String() {
        DateTime dateTime = new DateTime();
        Date date = dateTime.dayOfYear().withMinimumValue().dayOfYear().getDateTime().toDate();

        return formatDate(date, YYYYMMDD000000);

    }

    /**
     * 获取指定日期时周几
     *
     * @param date
     * @return
     */
    public static String getWeekNumOfDate(Date date) {

        String[] weeks = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 根据日期取得对应一周前日期
     *
     * @param date
     * @return
     */
    public static Date getBeforeWeekDate(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.add(Calendar.DATE, -7);
        return monday.getTime();
    }

    /**
     * 是否是本季度第一天
     *
     * @param date
     * @return
     */

    public static boolean isSeasonFirstDay(String date) {
        // 当天日期
        Date curDate = JodaUtil.parseStrToDateSimple(date);
        if (getIntervalDaysByDate(curDate, getFirstDateOfSeason(curDate)) == 0) {
            return true;
        }

        return false;
    }


    /**
     * 取得季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfSeason(Date date) {
        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    public static Date getFirstDateOfSeasonByCurrentDate() {
        LocalDate local = new LocalDate();
        Date date = local.toDate();

        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) { // 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) { // 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) { // 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) { // 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }

        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }

        return season;
    }

    /**
     * 根据日期获取去年第一天
     */
    public static Date getBeforeYearFirstDay(Date date) {
        Calendar monday = Calendar.getInstance();
        // DAY_OF_YEAR
        monday.setTime(date);
        monday.set(Calendar.DAY_OF_YEAR, 1);
        monday.add(Calendar.YEAR, -1);

        return monday.getTime();
    }

    /**
     * 是否是本年第一天
     *
     * @param date
     * @return
     */
    public static boolean isYearFirstDay(String date) {
        // 当天日期
        Date curDate = JodaUtil.parseStrToDateSimple(date);
        Calendar monday = Calendar.getInstance();
        monday.setTime(curDate);
        int day = monday.get(Calendar.DAY_OF_YEAR);
        if (day == 1) {
            return true;
        }

        return false;
    }

    /**
     * 取得上一季度第一天
     *
     * @param date
     * @return
     */
    public static Date gerFirstDayOfBeforeSession(Date date) {
        Date firstDate = getFirstDateOfSeason(getSeasonDate(date)[0]);
        Calendar beforDate = Calendar.getInstance();
        beforDate.setTime(firstDate);
        // 设为当前月的1号
        beforDate.set(Calendar.DATE, 1);
        beforDate.add(Calendar.MONTH, -3);

        return beforDate.getTime();
    }

    /**
     * 取得月第一天
     *
     * @param year  指定的年份
     * @param month 指定的月份
     * @return XX年XX月第一天的时间日期类型对象
     */
    public static Date getFirstDateOfMonthByMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    /**
     * 获取指定月份第一天的零点
     *
     * @param year  指定年
     * @param month 指定月份
     * @return
     */
    public static String getFirstDay(int year, int month) {
        DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(1);

        return dateTime.toString(YYYYMMDD000000);
    }

    /**
     * 取得月最后一天
     *
     * @param year  指定的年份
     * @param month 指定的月份
     * @return XX年XX月第最后一天的时间日期类型对象
     */
    public static Date getLastDateOfMonthByMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    /**
     * 计算两个时间之间差的毫秒数
     *
     * @param startday 开始时间
     * @param endday   结束时间
     * @return 相差天数
     */
    public static long getIntervalSecondByDate(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();

        return el - sl;
    }

    /**
     * 计算两个时间之间"0天,0小时,0分,1秒"
     *
     * @param startday 开始时间
     * @param endday   结束时间
     * @return 相差天数
     */
    public static String getIntervalTimeByDate(Date startday, Date endday) {
        long a = getIntervalSecondByDate(startday, endday);
        long s = a / 1000; // 总秒数
        long _s = s % 60; // 具体秒数
        long m = s / 60; // 总分钟数
        long _m = m % 60; // 具体分钟数
        long h = m / 60;
        long _h = h % 24;
        long d = h / 24;

        return new StringBuilder().append(d).append("天,").append(_h).append("小时,").append(_m).append("分,").append(_s)
                .append("秒").toString();
    }

    /**
     * 获取指定日期时周几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.dayOfWeek().getAsShortText(Locale.CHINESE);
    }

    /**
     * 获取指定日期时周几
     *
     * @return
     */
    public static String getWeekOfDate2String() {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfWeek().getAsShortText(Locale.CHINESE);
    }

    /**
     * 获得当前日期的周几数字，周一--1
     *
     * @return
     */
    public static int getWeekOfDate() {
        DateTime dateTime = new DateTime();
        return dateTime.dayOfWeek().get();
    }

    /**
     * 根据日期取得对应上周周一00:00:00
     *
     * @return
     */
    public static String getBeforeMondayOfWeek2String() {
        Date currentWeek = getMondayOfWeek();
        Calendar monday = Calendar.getInstance();
        monday.setTime(currentWeek);
        monday.add(Calendar.DATE, -7);

        return formatDate(monday.getTime(), YYYYMMDD000000);
    }


    /**
     * 日期相加 -1 上一天 0 本天 1 下一天
     *
     * @param date 日期
     * @param hour  小时数
     * @return 返回相加后的日期
     */
    public static final Date addDate(Date date, long hour) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime() + hour * 3600 * 1000);

        return c.getTime();
    }

    /**
     * 取得季度最后一天
     *
     * @param date 当前指定的时间
     * @return 指定时间所在季度最后一天时间对象
     */
    public static Date getLastDateOfSeason(Date date) {
        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    /**
     * 取得月最后一天
     *
     * @param date 当前指定时间
     * @return 当前时间所在月的最后一天时间对象
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    /**
     * 获取两个日期相差月的个数
     *
     * @param beforedate
     * @param afterdate
     * @return
     */
    public static long getDistinceMonth(Date beforedate, Date afterdate) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(beforedate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(afterdate);

        return (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }

    /**
     * 获取某段时间之前的时间点
     *
     * @param date      基准时间
     * @param timeDistance 时间段 （单位：毫秒）
     * @return 某段时间之前的时间点
     */
    public static Date getEarlyDatetime(Date date, int timeDistance) {
        Date earlyDate = new Date(date.getTime() - timeDistance);
        return earlyDate;
    }

    /**
     * 比较当前时间是否在该时间段内，在该时间段内为true，否则为false
     *
     * @param startTime 09:00:00
     * @param endTime
     * @return
     */
    public static boolean timeCompare(String startTime, String endTime) {
        boolean flag = false;

        if (startTime == null || endTime.trim().length() == 0) {
            endTime = startTime;
        }

        String strbDateTime = currentStrDate() + " " + startTime + ":00";
        String streDateTime = currentStrDate() + " " + endTime + ":00";

        Date startDateTime = parseStrToDateSimple(strbDateTime);
        Date endDateTime = parseStrToDateSimple(streDateTime);
        Date current = new Date();

        flag = current.after(startDateTime) && current.before(endDateTime);

        return flag;
    }


}
