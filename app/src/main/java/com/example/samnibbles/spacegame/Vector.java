package com.example.samnibbles.spacegame;

public class Vector {

    protected double x;
    protected double y;

    // Constructor methods ....

    public Vector() {
        x = y = 0.0;
    }

    public Vector( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    // Convert vector to a string ...

    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }

    // Compute magnitude of vector ....

    public double length() {
        return Math.sqrt ( x*x + y*y );
    }

    // Sum of two vectors ....

    public Vector add( Vector v1 ) {
        Vector v2 = new Vector( this.x + v1.x, this.y + v1.y );
        return v2;
    }

    // Subtract vector v1 from v .....

    public Vector sub( Vector v1 ) {
        Vector v2 = new Vector( this.x - v1.x, this.y - v1.y );
        return v2;
    }

    public Vector div( double scale ) {
        Vector v2 = new Vector(this.x / scale, this.y / scale);
        return v2;
    }

    public Vector mul( double scale ) {
        Vector v2 = new Vector(this.x * scale, this.y * scale);
        return v2;
    }

    // Scale vector by a constant ...

    public Vector scale( double scaleFactor ) {
        Vector v2 = new Vector( this.x*scaleFactor, this.y*scaleFactor );
        return v2;
    }

    // Normalize a vectors length....

    public Vector normalize() {
        Vector v2 = new Vector();

        double length = Math.sqrt( this.x*this.x + this.y*this.y );
        if (length != 0) {
            v2.x = this.x/length;
            v2.y = this.y/length;
        }

        return v2;
    }

    // Dot product of two vectors .....

    public double dotProduct ( Vector v1 ) {
        return this.x*v1.x + this.y*v1.y;
    }
}

