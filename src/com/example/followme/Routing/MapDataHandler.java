package com.example.followme.Routing;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.example.followme.Routing.MapData;
import com.example.followme.Routing.Node;

import android.util.Log;

public class MapDataHandler {
	public MapData nodeRegister;
	private String sqlURL = "jdbc:mysql://oege.ie.hva.nl/;databaseName=zkonijnn001;user=konijnn001;password=w4Hda8MsV7tfc7BV";
	
	public MapDataHandler(){
		Log.wtf("TAG", "MPH INITIATED");
		nodeRegister = new MapData();
		
		if(!checkFirstRun()){
			Log.wtf("Text", "FIRSTRUN INITIATED");
			//nodeRegister = fetchMapDataFromSQLServer(sqlURL);
			//nodeRegister.setAdjacencyMatrix(fetchAdjacencyMatrixDataFromSQLServer(sqlURL));
			//nodeRegister.adjancencyIntMatrix = boolArrayToIntArray(nodeRegister.getAdjacencyMatrix());
			//nodeRegister.setWeightMatrix(generateWeightMatrix(nodeRegister.adjancencyIntMatrix, nodeRegister.getNodeRegister()));
			//writeToFile(nodeRegister, "MapData");
		}else{
			Log.wtf("TAG", "THIS IS A SECOND RUN");
			nodeRegister = fetchNodeDataFromFile();
		}
	}
	
	private boolean checkFirstRun(){
		boolean returned = false;
		File storedMapData = new File("MapData");
		returned = storedMapData.exists();
		return returned;
	}
	
	private MapData fetchNodeDataFromFile(){
		MapData nodeReg = null;
		File storedMapData = new File("MapData");
		
		try {
			FileInputStream fileIn = new FileInputStream(storedMapData);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			nodeReg = (MapData) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return nodeReg;
	}
	
	private void fetchNodeDataFromSQL(String _url){
		
	}
	
	private void writeToFile(Object _object, String _name){
		try{
		File objectFile = new File(_name);
		FileOutputStream output = new FileOutputStream(objectFile);
		ObjectOutputStream out = new ObjectOutputStream(output);
		
		out.writeObject(_object);
		out.flush();
		out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Node[] returnNodeRegister(){
		return nodeRegister.getNodeRegister();
	}
	
	private double[][] generateWeightMatrix(int[][] _adjacencyIntMatrix, Node[] _nodeRegister){
		double[][] weightMatrix = new double[_adjacencyIntMatrix.length][_adjacencyIntMatrix.length];
		
		for(int i = 0; i < _adjacencyIntMatrix.length; i++){
			for(int j = 0; j < _adjacencyIntMatrix.length; j++){
				if(_adjacencyIntMatrix[i][j] == 1){
					weightMatrix[i][j] = calculateDistance(_nodeRegister[i].lat, _nodeRegister[i].lng, _nodeRegister[j].lng, _nodeRegister[j].lat);
				}
			}
		}
		
		return weightMatrix;
	}
	
	private double calculateDistance(double _lat1, double _lng1, double _lat2, double _lng2){
		return Math.sqrt((_lat1*_lat1)+(_lng1*_lng1)) - Math.sqrt((_lng2*_lng2)+(_lat2*_lat2));
	}
	
	
	
}
