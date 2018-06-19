package edu.csu2017fa314.T16.Model;

import java.util.ArrayList;

public class Leg { 
	public ArrayList<String> startInfo = new ArrayList<>();
	public ArrayList<String> endInfo = new ArrayList<>();
	public int distance = 0;
	public String startCode = "", startName = "", startLatitude = "", startLongitude = "", endCode = "", endName = "", endLatitude = "", endLongitude = "";
	
	public Leg(ArrayList<String> startInfo, ArrayList<String> endInfo, int distance) {
		this.startInfo = startInfo;
		this.endInfo = endInfo;
		this.distance = distance;
		getInformation();
	}

	//pulls code, name, latitude and longitude out of the ArrayLists so that they're easily accessible in the web code
	public void getInformation() {
		for(int i = 0; i < startInfo.size(); i++) {
			if(startInfo.get(i).toLowerCase().contains("code")) { startCode = startInfo.get(i); }
			else if(startInfo.get(i).toLowerCase().contains("airport name")) { startName = startInfo.get(i).substring(startInfo.get(i).indexOf(":")+1); }
			else if(startInfo.get(i).toLowerCase().contains("latitude")) { startLatitude = startInfo.get(i).substring(startInfo.get(i).indexOf(":")+1); }
			else if(startInfo.get(i).toLowerCase().contains("longitude")) { startLongitude = startInfo.get(i).substring(startInfo.get(i).indexOf(":")+1); }
		}
		for(int i = 0; i < endInfo.size(); i++) {
			if(endInfo.get(i).toLowerCase().contains("airport name")) { endName = endInfo.get(i).substring(endInfo.get(i).indexOf(":")+1); }
			else if(endInfo.get(i).toLowerCase().contains("latitude")) { endLatitude = endInfo.get(i).substring(endInfo.get(i).indexOf(":")+1); }
			else if(endInfo.get(i).toLowerCase().contains("longitude")) { endLongitude = endInfo.get(i).substring(endInfo.get(i).indexOf(":")+1); }
		}
	}

	//converts latitudes & longitudes from degree/minute/second (DMS) form to degree (D) only
	public static double DMStoD(String location){		
		//remove delimiters & convert to degree-only form
		location = location.replaceAll("[a-z|A-Z|:| ]", "");
		String [] locarray = location.split("°|'|\"");		
		if (locarray.length == 1) { return Double.parseDouble(locarray[0]); }
		else if (locarray.length == 2) { return (Double.parseDouble(locarray[0]) + (Double.parseDouble(locarray[1])/60.0)); }
		else if (locarray.length == 3) { return (Double.parseDouble(locarray[0]) + (Double.parseDouble(locarray[1])/60.0) + (Double.parseDouble(locarray[2])/3600.0)); }
		return -1;
	}

	public double getStartLatitude() {
		return DMStoD(startLatitude);
	}

	public double getStartLongitude() {
		return DMStoD(startLongitude);
	}

	public double getEndLatitude() {
		return DMStoD(endLatitude);
	}

	public double getEndLongitude() {
		return DMStoD(endLongitude);
	}

	public String getStartCode() {
		return startCode;
	}
	
	@Override
	public String toString() {
		return String.format("Start: %s\nEnd: %s\nDistance: %d\n", this.startName, this.endName, this.distance);
	}
}
