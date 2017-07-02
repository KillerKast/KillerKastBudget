package com.killerkast;

import org.junit.*;

import javax.validation.constraints.AssertTrue;

/**
 * Created by killerkast on 6/28/17.
 */
public class SanityTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("***Before Class is invoked");
    }

    @Before
    public void before(){

        System.out.println("_______________________________");
        System.out.println("\t Before is invoked");
    }

    @After
    public void after() {
        System.out.println("\t\t After is invoked");
    }

    @Test
    public void someTest() {
        int x = 1;
        int y = 2;
        Assert.assertFalse(x == y);
        System.out.println("\t\t someTest is invoked");
    }

    @Test
    public void someTest2() {
        System.out.println("\t\t someTest2 is invoked");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("***After Class is invoke");
    }
}
