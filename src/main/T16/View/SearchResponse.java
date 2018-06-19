package edu.csu2017fa314.T16.View;

import edu.csu2017fa314.T16.Model.Location;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchResponse {
    private String response = "search";
    private ArrayList<Location> locations = new ArrayList<>();

    public SearchResponse(ArrayList<Location> locations) {
        this.locations = locations;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "response='" + response + '\'' +
                ", locations=" + locations +
                '}';
    }
}
