import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LoopChecker
{
    private Board board;
    
    public LoopChecker(Board board)
    {
    	this.board = board;
    }
    
  
  /** Given a cluster of nodes, determines if they are a loop
    * If cluster-size is k, and the graph of nodes adjacent to the cluster
   * has j nodes and l edges then this method has complexity O( 6k^2 + j + l)
   * @cluster Cluster of colored nodes
   */
  public boolean isLoop(Cluster cluster)
  {
    
	ArrayList<Position> clusterNodes = cluster.getNodes();
    ArrayList<Position> adjacentNodes = new ArrayList<Position>();
    
    //Goes through every node in the cluster, finds adjacent nodes that aren't in the cluster and adds them to a list
    for(Position position : clusterNodes)
    {
    	ArrayList<Position> localAdjacentNodes = position.getAdjacents(board);
    	
    	//Assumes nodes adjacent to cluster are of a different colour or empty
    	for(Position node : localAdjacentNodes)
    	{
    		//If the nodes haven't already been added to the list of nodes adjacent to the cluster and they're not part of the cluster
    	       if( !adjacentNodes.contains(node)
    	        && !clusterNodes.contains(node))
    	       {
    	         adjacentNodes.add(node);
    	       }
    	}
     
    }
    
    //If all nodes are one component then the empty nodes inside and outside the 'loop' are connected
    //and thus there is no loop.
    if(isOneComponent(adjacentNodes))
      return false;
    else
      return true;
      
  }
  
  /**
   * Checks if a given list of nodes are one component i.e. all nodes are reachable from all other nodes
   * This method has complexity O(j+l) where j is the number of nodes and l is the number of 'edges' between
   * them (bfs complexity)
   */
  public boolean isOneComponent(ArrayList<Position> adjacentNodes)
  {
     Position currentNode = adjacentNodes.get(0);
     int index = adjacentNodes.indexOf(currentNode);
     adjacentNodes.remove(index);
     Queue<Position> queue=new LinkedList<Position>();
     queue.add(currentNode);
     
     //Checks all nodes surrounding current node. If any of them are in the list of adjacent nodes, they are added
     // to the queue and removed from the list of adjacent nodes. Once the queue is empty, then all reachable nodes 
     //have been explored.
     while(!queue.isEmpty())
     {
       currentNode = queue.remove();
       ArrayList<Position> localAdjacentNodes = currentNode.getAdjacents(board);
       
       for(Position nextNode : localAdjacentNodes)
       {
    	   if(adjacentNodes.contains(nextNode))
           {
             queue.add(nextNode);
             index = adjacentNodes.indexOf(currentNode);
             adjacentNodes.remove(index);
           }
       }
       
     }
     
     //All reachable nodes have been explored. If the list of adjacent nodes is not empty then some nodes
     //were not reachable and there is more than one component
     if(adjacentNodes.size() == 0)
       return true;
     else
       return false;
  }
}