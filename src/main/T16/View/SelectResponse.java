package edu.csu2017fa314.T16.View;

import edu.csu2017fa314.T16.Model.Leg;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectResponse {
    private String response = "select";
    private ArrayList<Leg> legs;

    public SelectResponse(ArrayList<Leg> legs) {
        this.legs = legs;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SelectResponse{" +
                "response='" + response + '\'' +
                ", legs=" + legs +
                '}';
    }
}
