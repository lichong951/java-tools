package com.glodon.tool;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lichongmac@163.com on 2019-09-19.
 */
public class CheckNullTest {

    @Test
    public void isNull() {
        assertEquals(true, CheckNull.isNull(null));
    }

    @Test
    public void isBlank() {
        assertEquals(true, CheckNull.isNull(""));
    }

    @Test
    public void getSelf() {
        assertEquals("1", CheckNull.getSelfOrBlank("1"));

    }

    @Test
    public void getBlankWithBlank() {
        assertEquals("", CheckNull.getSelfOrBlank(""));

    }

    @Test
    public void getBlankWithNull() {
        assertEquals("", CheckNull.getSelfOrBlank(null));

    }


    @Test
    public void isNotNullWithBlank() {
        assertEquals(false, CheckNull.isNotNull(""));


    }

    @Test
    public void isNotNullWithNull() {
        assertEquals(false, CheckNull.isNotNull(null));

    }

 @Test
    public void isNotNullWithAnyString() {
        assertEquals(true, CheckNull.isNotNull("00"));

    }


}