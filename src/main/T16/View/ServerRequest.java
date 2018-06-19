package edu.csu2017fa314.T16.View;

public class ServerRequest {
    private String request = "";
    private String description = "";
    private boolean miles;
    private int op;

    public ServerRequest(String request, String description, boolean miles, int op) {
        this.request = request;
        this.description = description;
        this.miles = miles;
        this.op = op;
    }

    public String getRequest() {
        return request;
    }

    public void setQuery(String request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public boolean getUnits() {
    	return miles;
    }

    public int getOp() {
    	return op;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request='" + request + '\'' +
                ", description='" + description + '\'' +
                ", miles='" + miles + '\'' +
                '}';
    }
}
