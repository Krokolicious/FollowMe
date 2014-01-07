package com.example.followme.Routing;

public class Node {
	public int UID;
	public double lat;
	public double lng;
	
	
	/*
	 * Simple node class.
	 * @param _UniqueID	the id of the node
	 * @param _lat	the latitude of the node
	 * @param _lng	the longitude of the node
	 */
	public Node(int _UniqueID, double _lat, double _lng){
		UID= _UniqueID;
		lat = _lat;
		lng = _lng;
	}
	
	public int getUID(){
		return UID;
	}
	
	public double getLat(){
		return lat;
	}
	
	public double getLng(){
		return lng;
	}
	
	public void setLng(double _lng){
		lng = _lng;
	}
	
	public void setLat(double _lat){
		lat = _lat;
	}
	
	public void setUniqueID(int _UniqueID){
		UID = _UniqueID;
	}
	
}
