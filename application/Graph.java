package application;

/**
 * Undirected and unweighted graph implementation used by the Social Network visualizer.
 * 
 * Filename: Graph.java
 * Project: A-Team project (Social Network)
 * Authors: Robert Lange, Yu Long, Joe Hershey, Kevin Xiao, Lukas Her
 * Email: rdlange2@wisc.edu, long27@wisc.edu, joehershey@wisc.edu, klxiao@wisc.edu, lnher2@wisc.edu
 * Lecture: 001
 * Due: December 11th, 2019 (11:59pm) 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * This is the graph implementation that is used by the social network. It
 * is an undirected directed, unweighted graph consisting of vertices. The vertices store
 * strings and adjacency lists.
 */
public class Graph {

	// the vertexList stores a list of all vertices currently in the graph
	private List<GraphNode<String>> vertexList;
	// the order of the graph is the number of vertices that exist in the graph
	private int order;
	// the size of the graph is the number of edges that exist between nodes in the
	// graph
	private int size;

	/*
	 * Default no-argument constructor
	 */
	public Graph() {
		this.vertexList = new ArrayList<GraphNode<String>>();
	}

	private class GraphNode<T> {
		// the data stored by this node
		private String data;
		// the adjacencyList contains a list of adjacent vertices
		private List<GraphNode<String>> adjacencyList;
		// construct a vertex with the value 'vertex' and an adjacency list.
		private GraphNode(String vertex) {
			this.data = vertex;
			this.adjacencyList = new ArrayList<GraphNode<String>>();
		}
	}

	/**
	 * Add new vertex to the graph. Does not add null vertices or vertices
	 * already in the graph.
	 *
	 * @param vertex - The data to store in the new vertex
	 */
	public void addVertex(String vertex) {
		// do not add the vertex if it is null
		if (vertex == null) {
			return;
		}
		// if the vertex already exists in the graph, do not add it to the graph
		for (int i = 0; i < vertexList.size(); ++i) {
			if (vertexList.get(i) != null && vertexList.get(i).data.equals(vertex)) {
				return;
			}
		}
		// construct a new graph node containing the non-null vertex and add it to the
		// vertexList.
		// then increase the order (number of vertices) of the graph.
		GraphNode<String> S = new GraphNode<String>(vertex);
		vertexList.add(S);
		this.order++;
	}

	/**
	 * Remove a vertex and its edges from the graph.
	 * 
	 * @param vertex - the vertex to remove from the graph
	 */
	public void removeVertex(String vertex) {
		// remove any edges connecting to the removed vertex by checking the edges of
		// each node
		for (GraphNode<String> currentVertex : vertexList) {
			// check all the edges in the graph and delete any edges going from an adjacent
			// vertex to the
			// deleted vertex
			for (int i = 0; i < currentVertex.adjacencyList.size(); ++i) {
				if (currentVertex.adjacencyList.get(i).data.equals(vertex)) {
					currentVertex.adjacencyList.remove(i);
					this.size--;
				}
			}
			// delete any edges going from this vertex to any other vertices
			if (this.getAdjacentVerticesOf(vertex) != null) {
				for (String adjacent : this.getAdjacentVerticesOf(vertex)) {
					this.removeEdge(vertex, adjacent);
				}
			}
		}
		// remove the vertex from the graph (if it exists in the graph)
		GraphNode<String> vertexToRemove = getVertexFromString(vertex);
		// if the vertex was found, remove it from the vertexList and decrease the
		// number of vertices in the graph.
		if (vertexToRemove != null) {
			this.vertexList.remove(getVertexFromString(vertex));
			this.order--;
		}
	}

	/**
	 * Private helper method used to find a graph node from a provided string.
	 * Primarily used in remove to remove a vertex from the graph.
	 * 
	 * @param vertex - the vertex string to find in the matching node
	 * @return a reference to the node with the matching value, null if this node is
	 *         not in the graph
	 */
	private GraphNode<String> getVertexFromString(String vertex) {
		for (GraphNode<String> currentVertex : vertexList) {
			if (currentVertex.data.equals(vertex)) {
				return currentVertex;
			}
		}
		// if the vertex node is not found in the graph, return null
		return null;
	}

	/**
	 * Add the edge between two vertices in the graph. (edge is undirected and
	 * unweighted). If either vertex does not exist in the graph, add it to the
	 * graph and then insert the edge.
	 *
	 * @param vertex1 - the first vertex to add the new edge to
	 * @param vertex2 - the second vertex to add the new edge to
	 */
	public void addEdge(String vertex1, String vertex2) {
		// do not add an edge between the vertices if either one is null
		if (vertex1 == null || vertex2 == null) {
			return;
		}
		// keep a reference to the starting vertex (where the edge comes from) and the
		// ending vertex
		// (where the edge goes to)
		GraphNode<String> startVertex = null;
		GraphNode<String> endVertex = null;
		// if vertex1 does not exist, create it in the graph
		if (!this.getAllVertices().contains(vertex1)) {
			this.addVertex(vertex1);
		}
		// if vertex2 does not exist, create it in the graph
		if (!this.getAllVertices().contains(vertex2)) {
			this.addVertex(vertex2);
		}
		// set the startVertex and endVertex references
		for (GraphNode<String> node : vertexList) {
			if (node.data.equals(vertex1)) {
				startVertex = node;
			}
			if (node.data.equals(vertex2)) {
				endVertex = node;
			}
		}
		// if the startVertex does not already contain an edge pointing to the
		// endVertex, add the edge
		// and increase the size of the graph
		if (!startVertex.adjacencyList.contains(endVertex)) {
			startVertex.adjacencyList.add(endVertex);
			endVertex.adjacencyList.add(startVertex);
			this.size++;
		}
	}

	/**
	 * Remove the edge between vertex1 and vertex2 from this graph. Do nothing if
	 * the edge does not exist between these vertices.
	 */
	public void removeEdge(String vertex1, String vertex2) {
		// get references to both vertices
		GraphNode<String> firstVertex = this.getVertexFromString(vertex1);
		GraphNode<String> secondVertex = this.getVertexFromString(vertex2);
		// only remove the edge and decrease the size if the two vertices exist in the
		// graph and an edge exists from vertex1 to vertex2.
		if (firstVertex != null && firstVertex.adjacencyList.contains(secondVertex)) {
			firstVertex.adjacencyList.remove(secondVertex);
			secondVertex.adjacencyList.remove(firstVertex);
			this.size--;
		}
	}

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return a HashSet containing all the vertices in the graph
	 */
	public List<String> getAllVertices() {
		List<String> vertices = new ArrayList<String>();
		// store all the vertices in the graph in a HashSet (HashSet is chosen for time
		// efficiency)
		for (GraphNode<String> node : vertexList) {
			vertices.add(node.data);
		}
		return vertices;
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 *
	 * @param vertex - the vertex to find adjacent vertices of
	 * @return a list of vertices adjacent to this vertex, null if the vertex is not
	 *         in the graph
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		// store an array of vertices adjacent to this one
		List<String> adjacentVertices = new ArrayList<String>();
		// store a reference to the vertex to get adjacent vertices of
		GraphNode<String> vertexMatch = null;
		// find the matching vertex in the graph by checking all the graph's vertices
		for (GraphNode<String> node : vertexList) {
			if (node.data.equals(vertex)) {
				vertexMatch = node;
				break;
			}
		}
		// if the matching vertex was found, construct and return the list of adjacent
		// vertices
		if (vertexMatch != null) {
			for (GraphNode<String> node : vertexMatch.adjacencyList) {
				adjacentVertices.add(node.data);
			}
			return adjacentVertices;
		}
		// if the vertex is not in the graph, return null
		return null;
	}

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the size of this graph (the number of edges).
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the order of this graph (the number of vertices).
	 */
	public int order() {
		return this.order;
	}
}
