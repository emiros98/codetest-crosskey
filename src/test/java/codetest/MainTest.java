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
        double a = 100.555;
        double b = 3.001;
        double c = 50.0;
        assertEquals(100.56, m.round(a), 0.0);
        assertEquals(3.0, m.round(b), 0.0);
        assertEquals(50.0, m.round(c), 0.0);
    }
}