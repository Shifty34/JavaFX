package com.example.assignment_2;
import javafx.scene.paint.Color;



enum myColor{
    MAROON(128,0,0,255),
    MEDIUMVIOLETRED(199,21,133,255),
    DARKORANGE(255,140,0,255),
    GREEN(0,128,0,255),
    GOLD(255,215,0,255);

    private int r;
    private int g;
    private int b;
    private int a;
    private int argb;

    myColor(int r, int g, int b, int l){

        setR(r);
        setG(g);
        setB(b);
        setA(l);
        setARGB(r, g, b,l);
    }

    private void setB(int b) {
        if(b >= 0 && b<= 255){
            this.b = b;
        }
    }

    private void setG(int g) {
        if(g >= 0 && g<= 255){
            this.g = g;
        }
    }

    private void setR(int r) {
        if(r >= 0 && r<= 255){
            this.r = r;
        }
    }

    private void setARGB(int r, int g, int b, int l){
        this.argb = (r << 24) & 0xFF000000 |
                (g << 16) & 0x00FF0000|
                (b << 8) & 0x0000FF00|
                l;
        //opacity is always max
    }

    //Getter Functions
    public int getG() {
        return g;
    }
    public int getR() {
        return r;
    }
    public int getB() {
        return b;
    }
    public int getArgb() {
        return argb;
    }

    public String getHexColor(){

        return "0x" + Integer.toHexString(argb).toUpperCase();
    }

    public void setA(int a) {
        this.a = a;
    }

    public Color getRealColor(){
        return Color.valueOf(this.getHexColor());
    }



}
