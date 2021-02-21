package com.javen.spi.impl;

import com.javen.face.DemoFace;

public class DemoSpiPlusImpl implements DemoFace {
    public String plus(int a, int b) {
        int res = a + b;
        return this.getClass().getName() + "  :  " + res;
    }
}
