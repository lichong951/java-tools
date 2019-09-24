package com.glodon.tool;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lichongmac@163.com on 2019-09-23.
 */
public class DataUtilsTest {

    @Test
    public void isOdd() {
        assertEquals(1, DataUtils.isOdd(1));
    }

    @Test
    public void isEven() {
        assertEquals(0, DataUtils.isOdd(2));
    }

    @Test
    public void hexToInt() {
        assertEquals(1, DataUtils.HexToInt("1"));

    }

    @Test
    public void hexToIntWith() {
        assertEquals(15, DataUtils.HexToInt("F"));
    }


    @Test
    public void intToHex() {
        assertEquals("1", DataUtils.IntToHex(1));
    }

    @Test
    public void hexToByte() {
        assertEquals((byte) 0x0, DataUtils.HexToByte("00"));
    }

    @Test
    public void hexToByteWith1() {
        assertEquals((byte) 0x1, DataUtils.HexToByte("1"));
    }

    @Test
    public void hexStr2Str() {
        assertEquals("\u000BOUT:0134329028", DataUtils.hexStr2Str("0B4F55543A30313334333239303238"
        ));
    }

    @Test
    public void byte2Hex() {
        assertEquals("01", DataUtils.Byte2Hex((byte) 0x1));

    }

    @Test
    public void byteArrToHex() {
        assertEquals("01 01 ", DataUtils
                .ByteArrToHex(new byte[]{(byte) 0x1, (byte) 0x1}));

    }

    @Test
    public void byteArrToHex1() {
        assertEquals("0101", DataUtils
                .ByteArrToHex(new byte[]{(byte) 0x1, (byte) 0x1}, 0, 2));
    }

    @Test
    public void byteOfASCIIToString() {
        assertEquals(1,
                DataUtils.byteOfASCIIToString(new byte[]{(byte) 3, (byte) 4}, 0, 1).length());
    }

    @Test
    public void hexToByteArr() {
        assertEquals(0, DataUtils.HexToByteArr("").length);
    }

    @Test
    public void hexToByteArrWith1() {
        assertEquals(1, DataUtils.HexToByteArr("1").length);
    }

    @Test
    public void getDivLines() {
        assertEquals(6, DataUtils.getDivLines("123456", 1).size());

    }

    @Test
    public void getDivLinesWithBlank() {
        assertEquals(0, DataUtils.getDivLines("", 1).size());

    }

    @Test(expected = NullPointerException.class)
    public void getDivLinesWithNullThrowNullPointerException() {
        assertEquals(0, DataUtils.getDivLines(null, 1).size());

    }

    @Test
    public void twoByte() {
        assertEquals("1234", DataUtils.twoByte("12345678"));
    }

    @Test
    public void sum() {
        assertEquals("123456780114", DataUtils.sum("12345678"));
    }

    @Test
    public void str8ToInt() {
        assertEquals(502, DataUtils.str8ToInt("12345678"));

    }

    @Test
    public void str8ToInt1() {
        assertEquals(3, DataUtils.str8ToInt(new String[]{"1","1"}));
    }
}