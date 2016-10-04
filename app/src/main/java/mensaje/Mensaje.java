package mensaje;

import java.io.Serializable;

/**
 * Created by CAMILO on 03/10/2016.
 */
public class Mensaje implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int x,y,tam,r,g,b;

    public Mensaje(int x, int y, int tam, int r, int g , int  b ){
        this.x=x;
        this.y=y;
        this.tam=tam;
        this.r=r;
        this.g=g;
        this.b=b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTam() {
        return tam;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
