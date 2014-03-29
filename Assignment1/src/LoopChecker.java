package hello;

public class LoopChecker
{

    //Used for checking adjacent spots on grid
    //For example, given grid location (x,y): (x+x_incrementor[0],y+y_incrementor[0]) is the first adjacent node
    //and (x+x_incrementor[5],y+y_incrementor[5]) is the last adjacent node
    int x_incrementor = {-1,-1,0,1,1,0};
    int y_incrementor = {-1,0,1,1,0,-1};
    
  
  /** Given a cluster of nodes, determines if they are a loop
    * If cluster-size is k, and the graph of nodes adjacent to the cluster
   * has j nodes and l edges then this method has complexity O( 6k + j + l)
   * @cluster Cluster of colored nodes
   */
  public boolean isLoop(ArrayList<Pair> cluster, char colour)
  {
    
    ArrayList<Pair> adjacentNodes = new ArrayList<Pair>();
    
    for(Pair pair : cluster)
    {
      //Assume game grid is represented as an arraylist of arraylists of chars
     for(int i=0; i<x_incrementor.length()); i++)
     {
       int x = pair.getFirst() + x_incrementor[i];
       int y = pair.getSecond() + y_incrementor[i];
       Pair adjacentPair = new Pair(x,y);
       
       //If the nodes haven't already been added to the list of adjacent nodes and they're not part of the cluster
       if( !adjacentNodes.contains(adjacentPair)
        && !cluster.contains(adjacentPair))
       {
         adjacentNodes.add(adjacentPair);
       }
       
     } 
     
    }
    
    //If all nodes are one component then the empty nodes inside the loop and outside the loop are connected
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
  public boolean isOneComponent(ArrayList<Pair> adjacentNodes)
  {
     Pair currentNode = adjacentNodes[0];
     int index = adjacentNodes.indexOf(currentNode);
     adjacentNodes.remove(index);
     Queue<Pair> queue=new LinkedList<Pair>();
     queue.add(currentNode);
     
     //Checks all nodes surrounding current node. If any of them are in the list of adjacent nodes, they are added
     // to the queue and removed from the list of adjacent nodes. Once the queue is empty, then all reachable nodes 
     //have been explored.
     while(!queue.isEmpty())
     {
       currentNode = queue.remove();
       
       for(int i=0; i<x_incrementor.length(); i++)
       {
          int x = currentNode.getFirst() + x_incrementor[i];
          int y = currentNode.getSecond() + y_incrementor[i];
          Pair nextNode = new Pair(x,y);
          
          if(adjacentNodes.contains(nextNode))
          {
            queue.add(nextNode);
            int index = adjacentNodes.indexOf(currentNode);
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