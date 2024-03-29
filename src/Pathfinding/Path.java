package Pathfinding;


import java.util.ArrayList;

/**
 * 
 * @author Connor Rice
 */
public class Path implements Comparable{
    
    private ArrayList<Integer> pathIndicies;
    private float cost;
    private int curIndex;
    private boolean marked;
    
    public Path(ArrayList<Integer> pathNodes) {
        this.pathIndicies = pathNodes;
        curIndex = 0;
        marked = false;
    }
    
    public ArrayList<Integer> getGraphNodes() {
        return pathIndicies;
    }
    
    public int getLastNode() {
        return pathIndicies.get(pathIndicies.size()-1); // ASKMATTHEW: More efficient to keep internal tracker?
    }
    
    public void addNode(int newNode) {
        pathIndicies.add(newNode);
    }
    
    // ASKMATTHEW: can we do a (possibly threaded) this every time we addNode?
    public void updateCost(float estCost) {
        cost = pathIndicies.size() + estCost;
    }
    
    public float getCost() {
        return cost;
    }
    
    public Integer peek() {
        return pathIndicies.get(curIndex);
    }
    
    public boolean getEndReached() {
        return curIndex == pathIndicies.size();
    }
    
    public int getNextPathNode() {
        int index = pathIndicies.get(curIndex);
        curIndex++;
        return index;
    }
    
    public void setMarked() {
        marked = true;
    }
    
    public boolean getMarked() {
        return marked;
    }
    
    @Override
    public Path clone() {
        return new Path((ArrayList<Integer>) getGraphNodes().clone());
    }

    public int compareTo(Object o) {
        if (cost > ((Path)o).getCost()) {
            return 1;
        } else {
            return -1;
        }
    }
    
}
