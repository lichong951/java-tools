package com.example.lichong.myapplication;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * Created by lichong on 2017/5/16.
 */

public class ListTest {

    @Test
    public void verifyBehaviour(){
        //mock creation
        List mockedList=mock(List.class);

        //using mock object - it does not throw any "unexpected interacti"
        mockedList.add("one");
        mockedList.clear();

        //selective,explicit,highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testMockito(){
        //you can mock concrete classes,not only interfaces
        LinkedList mockedList=mock(LinkedList.class);
        //stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");
        //the following prints "first"
        System.out.println(mockedList.get(0));

        //the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }
}
