package com.glodon.tool;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by lichongmac@163.com on 2019-09-25.
 */
public class DateUtilsTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void dateToTimeMillis() throws ParseException {
        assertEquals(1569340800000l, DateUtils.dateToTimeMillis("2019-09-25 00:00:00"));
    }

    @Test
    public void dateToTimeMillisWithBlank() throws ParseException {
        expectedEx.expect(ParseException.class);
        expectedEx.expectMessage("");
        DateUtils.dateToTimeMillis("");
    }

    @Test
    public void dateToTimeMillisWithNull() throws ParseException {
        expectedEx.expect(NullPointerException.class);
        DateUtils.dateToTimeMillis(null);
    }

    @Test
    public void dateToTimeMillisWithABC() throws ParseException {
        expectedEx.expect(ParseException.class);
        expectedEx.expectMessage("abc");
        DateUtils.dateToTimeMillis("abc");
    }

    @Test
    public void dateToTimeMillisWith_yyyy_MM_dd() throws ParseException {
        expectedEx.expect(ParseException.class);
        expectedEx.expectMessage("2019-09-25");
        DateUtils.dateToTimeMillis("2019-09-25");
    }


    @Test
    public void timeMillis2Date() {
        assertEquals("2019-09-25", DateUtils.timeMillis2Date(1569381164993l));
    }

    @Test
    public void getDefaultDate() {
        assertEquals(19,DateUtils.getDefaultDate().length());
    }

    @Test
    public void getDate() {
        assertEquals("2019-09-25 11:27:25",DateUtils.getDate("yyyy-MM-dd HH:mm:ss",1569382045819l));

    }
     @Test
    public void getDate_yyyy_MM_dd() {
        assertEquals("2019-09-25",DateUtils.getDate("yyyy-MM-dd",1569382045819l));

    }


}