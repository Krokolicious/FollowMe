package com.example.followme.Routing;
import java.util.ArrayList;
import java.util.Stack;

import android.util.Log;


public class RouteManager {
	private boolean[][] adjacencyMatrix;
	private double[][] adjacencyWeightMatrix;
	public Node[] nodeRegister;
	public MapDataHandler mapDataHandler;
	
	/*
	 * The route handler, every route handler comes with its own dataHandler.
	 * This class is mainly used for finding routes of various types
	 */
	public RouteManager(){
		mapDataHandler = new MapDataHandler();	
		Log.wtf("TAG", "Okee nu gebeurt er niets meer Exiting RM.");
		//nodeRegister = mapDataHandler.nodeRegister.getNodeRegister();
		//adjacencyWeightMatrix = mapDataHandler.nodeRegister.getWeightMatrix();
	}
	/*
	 * Finds the shortest route between nodes
	 * 
	 * @param _startNode	this is the starting node 
	 * @param _endNode	this is the end node.
	 */
	public void findRoute(Node _startNode, Node _endNode){
		//**VERY EXPERIMENTAL**
		int limiter = 0; // need to create a valid limiter.
		RouteHandler routeHandler = new RouteHandler();
		calculateRoutes(adjacencyWeightMatrix, _startNode, _endNode, new Node[]{_startNode}, limiter, 0, routeHandler);
		Route[] routeArray = routeHandler.returnRouteRay();
		if(routeArray != null){
			findOptimalRoute(routeArray, "short");
		}else{
			// We have have found no routes this means that we need to start moving the limiter up.
			calculateRoutes(adjacencyWeightMatrix, _startNode, _endNode, new Node[]{_startNode}, limiter*3, 0, routeHandler);
			if(routeArray != null){
				findOptimalRoute(routeArray, "short");
			}else{
				//We still havent found anything We will shove the limiter for the last time.
				calculateRoutes(adjacencyWeightMatrix, _startNode, _endNode, new Node[]{_startNode}, limiter*6, 0, routeHandler);
				if(routeArray != null){
					findOptimalRoute(routeArray, "short");
				}else{
					//We still havent found anything we will have to cancel the search.
				}
			}
		}
	}
	
	/*
	 * This is the function that actually calculates the routes
	 * 
	 * @param _a This is the matrix that is used as criteria for our navigation, this can be an adjacency matrix, or a weight matrix.
	 * @param _s	This is the node we are checking from
	 * @param _e this is the node we are going to finally
	 * @param _visistedNodes	this is the parameter that is useful for the recursion, so we dont pass the same node twice.
	 * @param _limit	This is the parameter that functions as the limiter, generally it is set to 3 times the global length from start to end.
	 * @param _weight	This is how 'Heavy' the current route is (measures distance or other values set in the 'a' matrix
	 * @param _routeHandler	This is the Routehandler which gathers succesful routes for us. This variable should be instantiated elsewhere.	
	 */
	
	private void calculateRoutes(double[][] _a, Node _s, Node _e, Node[] _visitedNodes, double _limit, double _weight, RouteHandler _routeHandler){ 
        boolean dupeConditional = false; 
        boolean hitEnd = false; 
    	double routeWeight = _weight;
        for(int i = 0; i < _a.length; i ++){ 
			hitEnd = false; //sometimes Java feels like multi-threading for loops, this basically a safety net for when something manages to fuck up majestically
            dupeConditional = false; 
            for(int j = 0; j < _visitedNodes.length; j++){ 
                //Check if you have visited the node you are about to visit before 
                //If this is true we set the dupeConditional to true 
                if(_visitedNodes[j].UID == i) dupeConditional = true; 
            } 
            //if we aren't checking a duped node right now we continue to the main part 
            if(!dupeConditional){
                //We can visit the 'i' node now so we can move into that node. 
                if(_a[i][_s.UID] != 0){ // We have found something. 
                	routeWeight += _a[i][_s.UID];
					if(i == _e.UID){//we have found the the end node. We are ignoring the weight limiter for this scenario. (To generate more hits when the limit was a bit on the low side)
						hitEnd = true;
						break; // we have found that our connection leads to the end node we are breaking the loop 
					}
					if (routeWeight > _limit) break; //The weight exceeded the limit thus exiting the loop and steering toward the end of the function before the hitEnd boolean was set to true
                    								//we make a new dupeRegister for the next function 
                    Node[] newVisitedNodes = new Node[_visitedNodes.length+1]; 
                      
                    //we fill the new dupeRegister with the old dupeRegister 
                    for(int z = 0; z < _visitedNodes.length; z++){ 
                        newVisitedNodes[z] = _visitedNodes[z]; 
                    }
                    //We have finished filling the new register with the old registers value and have left a slot open 
                    //we add the node we are visiting to the current dupe register 
                    newVisitedNodes[newVisitedNodes.length-1] = _s;// we fill the empty slot with the newly visited node. 
                    //we restart the function with the new node and give all of our limiters. 
                    calculateRoutes(_a, nodeRegister[i], _e, newVisitedNodes, _limit, routeWeight, _routeHandler); //This starts searching for possible next nodes 
                } 
                  
            } 
            //If we are checking a duped node we start again till we reach the end. 
        } 
        //We still have to implement the limiter and handling for it. 
            if(hitEnd)_routeHandler.createAndAddRoute(_visitedNodes, _e, routeWeight);  
          /*
		  *We have arrived at the destination, causing the loop to break. while hitEnd is still valid.
		  *logically if we haven't found anything that wasn't previously visited before and we can't find any connections it was a dead end
		  *However if we found our destination between the possible destinations hitEnd would be true, thus sending the whole route to the routeHandler
		  *This way we handle generating the routes and finding them at the same time.
		  */
    }
	
	/*
	 * Simple sorting algorithm that allows sorting the array, by a type defined
	 * @param _routeArray	This is the array we are searching through for our routes.
	 * @param _type 			This is a string that tells us what kind of shorting we want to do, currently supported "short", later on to implemented are "sightseeing" and other values.
	 * @return returns the shortest route possible. 
	 */
	private Route findOptimalRoute(Route[] _routeArray, String _type){
		int storage = 0;
		if(_type == "short"){
		double minValue = _routeArray[0].weight;  
			for(int i=1;i<_routeArray.length;i++){  
			  if(_routeArray[i].weight < minValue){  
			  	minValue = _routeArray[i].weight; 
			  	storage = i;
				}  
			}
		}
		return _routeArray[storage];
	}
	
}
