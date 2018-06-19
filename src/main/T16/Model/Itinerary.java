package edu.csu2017fa314.T16.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Itinerary {

	public ArrayList<String[]> locations = new ArrayList<>();
	public ArrayList<String[]> sorted = new ArrayList<>();
	public ArrayList<Leg> trip = new ArrayList<>();
	public int[][] table;
	public int distance = 0, latitude = -1, longitude = -1;
	public boolean miles = true;
	public int op = 3;
 
	public Itinerary(ArrayList<String[]> locations, boolean miles, int op) {
		this.locations = locations;
		this.miles = miles;
		this.op = op;
		table = new int[this.locations.size()][this.locations.size()];
		getIndices();
		buildTable();
		chooseTrip();
		buildTrip();
	}

	//finds the index of the latitude and longitude for every destination (String array) in the locations ArrayList
	public void getIndices() {
		for(String[] s : locations) {
			for(int i = 0; i < s.length; i++) {
				if(s[i] != null) {
					if(s[i].toLowerCase().contains("latitude")) { latitude = i; }
					else if(s[i].toLowerCase().contains("longitude")) { longitude = i; }
				}
			}
		}
	}   

	public int getDistance() {
		return distance;
	}

	public int[][] getTable() {
		return table;
	}

	public ArrayList<Leg> getTrip() {
		return trip;
	}

	public String getLatitude(String[] destination) {
		return destination[latitude].substring(destination[latitude].indexOf(":")+1, destination[latitude].length());
	}

	public String getLongitude(String[] destination) {
		return destination[longitude].substring(destination[longitude].indexOf(":")+1, destination[longitude].length());
	}

	//puts all location information into an ArrayList for the Leg object
	public ArrayList<String> getInfo(String[] destination) {
		ArrayList<String> info = new ArrayList<>();
		for(int i = 0; i < destination.length; i++) {
			if(destination[i] != null) { info.add(destination[i]); }
		}
		return info;
 	}

	//calls greatCircle but takes cleaner arguments
	public int neighborDistance(String[] start, String[] end) {
		return greatCircle(DMStoD(getLatitude(start)), DMStoD(getLatitude(end)), DMStoD(getLongitude(start)), DMStoD(getLongitude(end)));		
	}		

	//calculates distance between two points 
	public int greatCircle(double startLatitude, double finishLatitude, double startLongitude, double finishLongitude){
		//degree-to-radian conversions
		startLatitude *= (Math.PI/180.0);
		finishLatitude *= (Math.PI/180.0);
		startLongitude *= (Math.PI/180.0);
		finishLongitude *= (Math.PI/180.0);

		//compute great circle distance
		double n = Math.sqrt(Math.pow(Math.cos(finishLatitude)* Math.sin(finishLongitude - startLongitude), 2) + Math.pow(Math.cos(startLatitude) * Math.sin(finishLatitude) - Math.sin(startLatitude) * Math.cos(finishLatitude)* Math.cos(finishLongitude - startLongitude),2));
		double d = Math.sin(startLatitude) * Math.sin(finishLatitude) + Math.cos(startLatitude) * Math.cos(finishLatitude) * Math.cos(finishLongitude - startLongitude);
		float temp = (float)-1.0;
		if(miles) { temp = (float) (3958.7613 *  Math.atan2(n,d)); }
		else { temp = (float) (6371.0088 *  Math.atan2(n,d)); }
		return Math.round(temp);
	}

	//converts degree/minute/second latitude & longitude Strings to degree latitude & longitude doubles
	public static double DMStoD(String location){			
		//remove delimiters & convert to degree-only form
		location = location.replace(" ","");
		location = location.replaceAll("[a-z|A-Z]", "");
		String [] locarray = location.split("°|'|\"");		
		if (locarray.length == 1) { return Double.parseDouble(locarray[0]); }
		else if (locarray.length == 2) { return (Double.parseDouble(locarray[0]) + (Double.parseDouble(locarray[1])/60.0)); }
		else if (locarray.length == 3) { return (Double.parseDouble(locarray[0]) + (Double.parseDouble(locarray[1])/60.0) + (Double.parseDouble(locarray[2])/3600.0)); }
		return -1;
	}

	//builds the distance table based on the order of the locations ArrayList
	public void buildTable() {
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				table[i][j] = neighborDistance(locations.get(i), locations.get(j));
			}
		}
	}

	//calculates the total distance of a trip
	public int distance(int[] trip) {
		int dist = 0;
		for(int i = 0; i < trip.length-1; i++) {
			dist += table[trip[i]][trip[i+1]];			
		}
		return dist;
	}

	//finds the shortest possible trip applying all optimization algorithms
	public void chooseTrip() {
		int[] shortest = new int[locations.size()+1];
		int best = Integer.MAX_VALUE;
		//if the user asked for some degree of trip optimization
		if(op != 0) {
			for(int i = 0; i < locations.size(); i++) {
				int[] nn = nearestNeighbor(i);
				int nnDist = distance(nn);
				if(nnDist < best) { best = nnDist; shortest = nn; }
				if(op == 2) {
					int[] o2 = twoOpt(nn);
					int o2Dist = distance(o2);
					if(o2Dist < best) { best = o2Dist; shortest = o2; }
				} if(op == 3) {
					int[] o3 = threeOpt(nn);
					int o3Dist = distance(o3);
					if(o3Dist < best) { best = o3Dist; shortest = o3; }
				}
			}		
		}
		//otherwise, build un-optimized trip based on current order of destinations
		else {
			for(int i = 0; i < locations.size(); i++) {
				shortest[i] = i;
			}
			shortest[shortest.length-1] = 0;
		}
		distance = distance(shortest);
		for(int i = 0; i < shortest.length; i++) {
			sorted.add(locations.get(shortest[i]));	
		}
	}

	//calculates a nearest neighbor trip based on a starting index
	public int[] nearestNeighbor(int startIndex) {
		int[] nn = new int[locations.size()+1];
		for(int i = 0; i < locations.size(); i++) {
			nn[i] = i;
		}
		nn[0] = startIndex;
		nn[startIndex] = 0;
		nn[nn.length-1] = startIndex;
		for(int i = 0; i < nn.length - 2; i++) {
			int distance = Integer.MAX_VALUE;
			int saved = -1;
			for(int j = i+1; j < nn.length - 1; j++) {
				if(table[nn[i]][nn[j]] < distance) {
					distance = table[nn[i]][nn[j]];
					saved = j;
				}
			}
			int temp = nn[saved];
			nn[saved] = nn[i+1];
			nn[i+1] = temp;
		}
		return nn;
	}


	public int[] opt2Swap(int[] route, int i, int k) { // swap in place
		int [] nn = Arrays.copyOf(route, route.length);
		while(i < k) {
			int temp = nn[i];
			nn[i] = nn[k];
			nn[k] = temp;
			i++; 
			k--;
		}
		return nn;
	}

	public int[] twoOpt(int[] nn) {
		boolean improvement = true;
		while(improvement) {
			improvement = false;
			for (int i = 0; i < nn.length-3; i++) {
				for (int k = i + 2; k < nn.length-1; k++) {
					int delta = -table[nn[i]][nn[i+1]]-table[nn[k]][nn[k+1]]+table[nn[i]][nn[k]]+table[nn[i+1]][nn[k+1]];
					if (delta < 0) { //improvement?
						nn = opt2Swap(nn, i+1, k);
						improvement = true;
					}
				}
			}
		}
		return nn;
	}

	public int[] opt3Swap(int[] route, int startA, int endA, int startB, int endB) {
		int[] nn = new int[route.length];
		int c = 0;
		for(int i = 0; i < startA; i++) {
			nn[c] = route[i];
			c++;
		}
		for(int i = startB; i <= endB; i++) {
			nn[c] = route[i];
			c++;
		}
		for(int i = endA+1; i < startB; i++) {
			nn[c] = route[i];
			c++;
		}
		for(int i = startA; i <= endA; i++) {
			nn[c] = route[i];
			c++;
		}
		for(int i = endB+1; i < route.length; i++) {
			nn[c] = route[i];
			c++;
		}
		return nn;
	}

	public int[] threeOpt(int[] nn) {
		boolean improvement = true;
		while(improvement) {
			improvement = false;
			for(int i = 1; i < nn.length-3; i++) {
				for(int j = i+1; j < nn.length-2; j++) {
					for(int k = j+1; k < nn.length-1; k++) {
						int best = 0;
						int delta = -table[nn[i]][nn[i+1]]-table[nn[j]][nn[j+1]]-table[nn[k]][nn[k+1]];
						int c1 = table[nn[i]][nn[j]]+table[nn[i+1]][nn[j+1]]+table[nn[k]][nn[k+1]] + delta;
						int c2 = table[nn[i]][nn[i+1]]+table[nn[j]][nn[k]]+table[nn[j+1]][nn[k+1]] + delta;
						int c3 = table[nn[i]][nn[k]]+table[nn[j+1]][nn[j]]+table[nn[i+1]][nn[k+1]] + delta;
						int c4 = table[nn[i]][nn[j]]+table[nn[i+1]][nn[k]]+table[nn[j+1]][nn[k+1]] + delta;
						int c5 = table[nn[i]][nn[k]]+table[nn[j+1]][nn[i+1]]+table[nn[j]][nn[k+1]] + delta;
						int c6 = table[nn[i]][nn[j+1]]+table[nn[k]][nn[j]]+table[nn[i+1]][nn[k+1]] + delta;
						int c7 = table[nn[i]][nn[j+1]]+table[nn[k]][nn[i+1]]+table[nn[j]][nn[k+1]] + delta;
						if(c1 < best) { best = c1; improvement = true; }
						if(c2 < best) { best = c2; improvement = true; }
						if(c3 < best) { best = c3; improvement = true; }
						if(c4 < best) { best = c4; improvement = true; }
						if(c5 < best) { best = c5; improvement = true; }
						if(c6 < best) { best = c6; improvement = true; }
						if(c7 < best) { best = c7; improvement = true; }
						//case 1
						if(best == c1) { nn = opt2Swap(nn,i+1,j); }
						//case 2
						else if(best == c2) { nn = opt2Swap(nn,j+1,k); }
						//case 3
						else if(best == c3) { nn = opt2Swap(nn,i+1,k); }
						//case 4
						else if(best == c4) { nn = opt2Swap(nn,i+1,j); nn = opt2Swap(nn,j+1,k); }
						//case 5
						else if(best == c5) { nn = opt2Swap(nn,j+1,k); nn = opt3Swap(nn,i+1,j,j+1,k); }
						//case 6
						else if(best == c6) { nn = opt2Swap(nn,i+1,j); nn = opt3Swap(nn,i+1,j,j+1,k); }
						//case 7
						else if(best == c7){ nn = opt3Swap(nn,i+1,j,j+1,k); }
					}
				}
			}
		}
		return nn;
	}

	//creates the array of Leg objects
	public void buildTrip() {
		for(int i = 0; i < sorted.size() - 1; i++) {
			trip.add(new Leg(getInfo(sorted.get(i)), getInfo(sorted.get(i+1)), neighborDistance(sorted.get(i), sorted.get(i+1))));
		}
	}
}
