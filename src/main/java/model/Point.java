package model;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.logging.Logger;

@Entity
@Table(name = "dots")
public class Point implements Serializable {

    //    @GenericGenerator(name="seq" , strategy="increment")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "x")
    @NotNull
    private Double x;

    @Column(name = "y")
    @NotNull
    private Double y;

    @Column(name = "r")
    @NotNull
    private Double r;

    @NotNull
    @Column(name = "status")
    private boolean status;

    public Point() {

    }

    public Point(Double x, Double y, Double r, boolean status) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        if (x >= 0 && y <= 0) {
            this.status = y <= 0.5 * x + 0.5 * r;
        } else if (x < 0 && y > 0) {
            this.status = x * x + y * y <= r * r;
        } else if (x < 0 && y < 0) {
            this.status = Math.abs(x) < r && Math.abs(y) < r / 2;
        } else {
            this.status = false;
        }
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}