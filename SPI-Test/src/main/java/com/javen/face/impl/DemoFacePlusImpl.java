package com.javen.face.impl;

import com.javen.face.DemoFace;

public class DemoFacePlusImpl implements DemoFace {
    public String plus(int a, int b) {
        int res = a + b;
        return this.getClass().getName() + "  :  " + res;
    }
}
