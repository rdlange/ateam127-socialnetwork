package application;

/**
 * Undirected and unweighted graph implementation used by the Social Network visualizer.
 * 
 * Filename: Graph.java
 * Project: A-Team project (Social Network)
 * Authors: Robert Lange
 * Email: rdlange2@wisc.edu
 * Lecture: 001
 * Due: December 11th, 2019 (11:59pm) 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This is the graph implementation that is used by the social network. It
 * is an undirected, unweighted graph consisting of vertices. The vertices store
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

	// construct a new graph with no vertices in it
	public Graph() {
		this.vertexList = new ArrayList<GraphNode<String>>();
	}

	private class GraphNode<T> {
		// the data stored by this vertex
		private String data;
		// stores a list of adjacent vertices
		private List<GraphNode<String>> adjacencyList;
		// construct a vertex with value 'vertex' and an adjacency list.
		private GraphNode(String vertex) {
			this.data = vertex;
			this.adjacencyList = new ArrayList<GraphNode<String>>();
		}
	}

	/**
	 * Add new vertex to the graph. Does not add null vertices or vertices
	 * already in the graph.
	 */
	public void addVertex(String vertex) {
		// do nothing if the vertex is null
		if (vertex == null) {
			return;
		}
		// do nothing if the vertex is in the graph
		for (int i = 0; i < vertexList.size(); ++i) {
			if (vertexList.get(i) != null && vertexList.get(i).data.equals(vertex)) {
				return;
			}
		}
		// add the new vertex to the graph
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
			// vertex to the deleted vertex
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
		// remove the vertex if it is found
		if (vertexToRemove != null) {
			this.vertexList.remove(getVertexFromString(vertex));
			this.order--;
		}
	}

	/**
	 * Private helper method used to find a vertex from a provided string.
	 * Primarily used to remove vertices from the graph.
	 * 
	 * @param vertex - the vertex to look for in the graph
	 * @return the matching vertex
	 */
	private GraphNode<String> getVertexFromString(String vertex) {
		// check every vertex in the graph for the matching data
		for (GraphNode<String> node : vertexList) {
			if (node.data.equals(vertex)) {
				return node;
			}
		}
		// if the vertex is not found, return null
		return null;
	}

	/**
	 * Add the edge between to vertices in the graph (edge is undirected and
	 * unweighted). Add the vertices to the graph if they are not already present.
	 */
	public void addEdge(String vertex1, String vertex2) {
		// do not add an edge if either vertex is null
		if (vertex1 == null || vertex2 == null) {
			return;
		}
		GraphNode<String> vertexA = null;
		GraphNode<String> vertexB = null;
		// if vertexA does not exist, create it in the graph
		if (!this.getAllVertices().contains(vertex1)) {
			this.addVertex(vertex1);
		}
		// if vertexB does not exist, create it in the graph
		if (!this.getAllVertices().contains(vertex2)) {
			this.addVertex(vertex2);
		}
		// find vertex1 and vertex2 in the graph
		for (GraphNode<String> node : vertexList) {
			if (node.data.equals(vertex1)) {
				vertexA = node;
			}
			if (node.data.equals(vertex2)) {
				vertexB = node;
			}
		}
		// add the edge to the graph and increase the size
		if (!vertexA.adjacencyList.contains(vertexB)) {
			vertexA.adjacencyList.add(vertexB);
			vertexB.adjacencyList.add(vertexA);
			this.size++;
		}
	}

	/**
	 * Remove the edge between vertex1 and vertex2 from this graph. Do nothing if
	 * the edge does not exist between these vertices.
	 */
	public void removeEdge(String vertex1, String vertex2) {
		// get both vertices from the graph
		GraphNode<String> vertexA = this.getVertexFromString(vertex1);
		GraphNode<String> vertexB = this.getVertexFromString(vertex2);
		// only remove the edge and decrease the size if the two vertices exist in the
		// graph and an edge exists between the edges.
		if (vertexA != null && vertexB != null && vertexA.adjacencyList.contains(vertexB)) {
			vertexA.adjacencyList.remove(vertexB);
			vertexB.adjacencyList.remove(vertexA);
			this.size--;
		}
	}

	/**
	 * Returns a list of the vertices
	 * 
	 * @return an ArrayList of all the vertices
	 */
	public List<String> getAllVertices() {
		ArrayList<String> vertices = new ArrayList<String>();
		// store all the vertices in the graph in a HashSet
		for (GraphNode<String> node : vertexList) {
			vertices.add(node.data);
		}
		return vertices;
	}

	/**
	 * Get a list of every adjacent vertex to a given vertex
	 *
	 * @param vertex - the vertex to find adjacent vertices of
	 * @return a list of all vertices adjacent to this vertex, null if the vertex is not
	 *         present in the graph.
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		// store an array of adjacent vertices
		List<String> adjacent = new ArrayList<String>();
		// find the vertex in the graph by checking all the graph's vertices
		GraphNode<String> vertexMatch = this.getVertexFromString(vertex);
		
		// construct and return the list of adjacent vertices
		if (vertexMatch != null) {
			for (GraphNode<String> node : vertexMatch.adjacencyList) {
				adjacent.add(node.data);
			}
			return adjacent;
		}
		// if the vertex is not found, return null
		return null;
	}

	/**
	 * Returns the number of edges in the graph.
	 * 
	 * @return the number of edges in the graph.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the number of vertices in the graph.
	 */
	public int order() {
		return this.order;
	}
}
