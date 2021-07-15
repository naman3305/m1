package test.java.com.myproject;

import org.junit.Test;

import main.java.com.myproject.sum;

import org.junit.Assert;

public class sumTest {

    //testing getSize
    @Test
    public void test1() {
        sum object = new sum();
        Assert.assertEquals(5, object.add(2,3));
        Assert.assertEquals(9, object.add(6,3));
        Assert.assertEquals(11, object.add(7,4));
        Assert.assertEquals(15, object.add(6,9));
    }
}
