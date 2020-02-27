package codetest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    Main m;

    @Before
    public void setUp() throws Exception {
        m = new Main();
    }

    @Test
    public void testCalculate(){
        assertEquals(43.8713897340686, m.calculate(1000.0, 5.0, 2.0), 0.0);
    }

    @Test
    public void testPow(){
        assertEquals(8, m.pow(2,3), 0.0);
    }

    @Test
    public void testRound(){
        double a = 5.005;
        double b = 1.236;
        double c = 50.0;
        double d = 0.121;
        assertEquals(5.01, m.round(a), 0.0);
        assertEquals(1.24, m.round(b), 0.0);
        assertEquals(50.0, m.round(c), 0.0);
        assertEquals(0.12, m.round(d), 0.0);
    }
}