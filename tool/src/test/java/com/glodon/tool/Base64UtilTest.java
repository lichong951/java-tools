package com.glodon.tool;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lichongmac@163.com on 2019-09-19.
 */
public class Base64UtilTest {

    @Test
    public void encode() {
        assertEquals("AQI=",Base64Util.encode(new byte[]{(byte)1,(byte)2}));
    }
}