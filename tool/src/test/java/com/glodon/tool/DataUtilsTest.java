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
        assertEquals("\u000BOUT:0134329028", DataUtils.hexStr2Str("0B4F55543A30313334333239303238"));
    }

    @Test
    public void byte2Hex() {
    }

    @Test
    public void byteArrToHex() {
    }

    @Test
    public void byteArrToHex1() {
    }

    @Test
    public void byteOfASCIIToString() {
    }

    @Test
    public void hexToByteArr() {
    }

    @Test
    public void getDivLines() {
    }

    @Test
    public void twoByte() {
    }

    @Test
    public void sum() {
    }

    @Test
    public void str8ToInt() {
    }

    @Test
    public void str8ToInt1() {
    }
}