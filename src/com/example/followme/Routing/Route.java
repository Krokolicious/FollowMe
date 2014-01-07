package com.example.followme.Routing;
import java.util.ArrayList;


public class Route {
	private ArrayList<Node> nodeList;
	public double weight = 0;
	/*
	 * simple container class.
	 * @param _startNode to prevent spamming of empty routes the minimum requirement is to have a starting node in a route.
	 */
	public Route(Node _startNode){
		nodeList = new ArrayList<Node>();
		addNode(_startNode);
	}
	
	/*
	 * simple function to add a node to route.
	 */
	public void addNode(Node _node){
		nodeList.add(_node);
	}
	
	
	
}
