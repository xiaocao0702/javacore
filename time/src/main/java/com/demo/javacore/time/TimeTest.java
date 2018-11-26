package com.demo.javacore.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Created by cao on 2018/11/23.
 * Java 8 提供的日期时间 API都在java.time包下，这个包涵盖了所有处理
 * 日期(date)，时间(time)，
 * 日期/时间(datetime)，时区（zone)，时刻（instant），
 * 间隔（duration）与时钟（clock）的操作
 */
public class TimeTest {
    public static void main(String[] args) {
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 将当前日期时间减去两天
        LocalDateTime dateTime2 = now.minusDays(2);
        System.out.println(dateTime2);

        // 将当前日期时间加上五天
        LocalDateTime dateTime3 = now.plusDays(5);
        System.out.println(dateTime3);

        // 输出当前日期时间的年份
        System.out.println(now.getYear());

        // 构造一个指定日期时间的对象
        LocalDateTime dateTime = LocalDateTime.of(2016, 10, 23, 8, 20);
        System.out.println(dateTime);

        System.out.println("------时间戳-------");
        System.out.println("时间戳对应的是java.time.Instant");

        // 获取当前时间的时间戳
        Instant instant = Instant.now();
        // 因为中国在东八区，所以这句输出的时间跟我的电脑时间是不同的
        System.out.println(instant);

        // 既然中国在东八区，则要偏移8个小时，这样子获取到的时间才是自己电脑的时间
        OffsetDateTime dateTime22 = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(dateTime22);

        // 转换成毫秒，如果是当前时间的时间戳，结果跟System.currentTimeMillis()是一样的
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        System.out.println("------间隔-------");
        System.out.println("java.time.Duration用于计算两个“时间”间隔\n" +
                "java.time.Period用于计算两个“日期”间隔");
        LocalTime start = LocalTime.now();
        try {
            //让线程睡眠3s
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        LocalTime end = LocalTime.now();
        //获取end和start的时间间隔
        Duration duration = Duration.between(start, end);

        //可能会输出PT3S或者输出PT3.001S，至于多出来的0.001秒其实就是除去线程睡眠时间执行计算时间间隔那句代码消耗的时间
        System.out.println(duration);
        //起始时间指定为2015年3月4日
        LocalDate start2 = LocalDate.of(2015, 3, 4);
        //终止时间指定为2017年8月23日
        LocalDate end2 = LocalDate.of(2017, 8, 23);

        Period period = Period.between(start2, end2);
        //输出P2Y5M19D，Y代表年，M代表月，D代表日，说明start和end的日期间隔是2年5个月19天
        System.out.println(period);


        System.out.println("------格式转换-------");
        System.out.println("java.time.format.DateTimeFormatter");
        // 获取预定义的格式，DateTimeFormatter类预定了很多种格式
        DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
        // 获取当前日期时间
        LocalDate today = LocalDate.now();
        // 指定格式化器格式日期时间
        String strNow = today.format(dtf);
        System.out.println(strNow);

        // 自定义格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String strNow2 = today.format(formatter);
        System.out.println(strNow2);

        // 将字符串转换成日期
        LocalDate date = LocalDate.parse(strNow2, formatter);
        System.out.println(date);

        System.out.println("------时区-------");
        System.out.println("java.time.ZonedDateTime");
        System.out.println("java.time.ZoneId");
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);

        //获取当前时区的日期时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        //获取美国洛杉矶时区的日期时间
        ZonedDateTime USANow = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(USANow);


    }
}
