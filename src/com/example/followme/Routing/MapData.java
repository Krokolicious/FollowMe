package com.example.followme.Routing;

import android.util.Log;

import com.example.followme.Routing.Node;

public class MapData implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float adjacencyVersionID;
	public float mapVersionID;
	private boolean[][] adjacencyMatrix; 
	public int[][] adjancencyIntMatrix;
	private double[][] weightMatrix;
	private Node[] nodeRegister;	
	private boolean naked;
	
	/*
	 * Map data class, should only be instantantiated when updating or first run of the app.
	 */
	public MapData(){
		naked = true;
		Log.wtf("TAG", "JAAA FINISHEEEDD");
	}
	
	/*
	 * adds a node to the mapData
	 * @param _node the node to be added.
	 */
	public void addNode(Node _node){
		if(naked == true){
			nodeRegister = new Node[1];
			nodeRegister[0] = _node;
		}else{
			Node[] newNodeRegister = new Node[nodeRegister.length+1];
			for(int i = nodeRegister.length-1; i < nodeRegister.length;i++){
				newNodeRegister[i] = nodeRegister[i];
			}
			newNodeRegister[nodeRegister.length] = _node;
			nodeRegister = newNodeRegister;
		}
	}
	
	/*
	 * Returns the nodeRegister.
	 * @return the noderegister that has to be returned.
	 */
	public Node[] getNodeRegister(){
		return nodeRegister;
	}
	
	/*
	 * setter for the adjacencyMatrix
	 * @param _adj a double layered boolean array to be set as the adjacency matrix
	 */
	public void setAdjacencyMatrix(boolean[][] _adj){
		adjacencyMatrix = _adj;
	}
	/*
	 * getter for the adjacencyMatrix.
	 * @return returns a double layered boolean array
	 */
	public boolean[][] getAdjacencyMatrix(){
		return adjacencyMatrix;
	}	
	/*
	 * setter for the weight Matrix
	 * @param _adj double layered double array to be set as weight matrix
	 */
	public void setWeightMatrix(double[][] _adj){
		weightMatrix = _adj;
	}
	/*
	 * getter for the weight matrix.
	 * @return returns a double layered double array.
	 */
	public double[][] getWeightMatrix(){
		return weightMatrix;
	}	
	
}
