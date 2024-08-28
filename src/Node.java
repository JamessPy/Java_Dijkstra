import java.util.ArrayList;

public class Node {
	boolean start = false;
    boolean end = false;
    boolean visited = false; 
    public String name;
    int start_cost;
    
    Node prev;
    public ArrayList<Node> others;

    public boolean isstart() {
        return start;
    }

    public void setstart(boolean start) {
        this.start = start;
    }

    public boolean isend() {
        return end;
    }

    public void setend(boolean end) {
        this.end = end;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getstart_cost() {
        return start_cost;
    }

    public void setstart_cost(int start_cost) {
        this.start_cost = start_cost;
    }

    public void add_other(Node node_add){  
        this.others.add(node_add);
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node(String name, boolean start, boolean end){
        this.name = name;
        this.start = start;
        this.end = end;
        this.others = new ArrayList<>();
        if(this.isstart()){
            this.start_cost = 0;
        }else{
            this.start_cost = 1000;
        }
    }
    Node node1;
    Node node2;
    int cost;

    public void Bridge(Node node1, Node node2, int cost){
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;

        if(!node1.others.contains(node2)){
            node1.add_other(node2);
        }
        if(!node2.others.contains(node1)){
            node2.add_other(node1);
        }


    }

    public int getDistance(){
        return cost;
    }
    public boolean is_proper_edge(String namenode1, String name2){ //returns whether this edge holds the two nodes passed in or not. This allows us to loop through all the edges to find proper costs
        boolean result = false;
        if((namenode1 == node1.getName() && name2 == node2.getName()) ||( namenode1 == node2.getName() && name2 == node1.getName())){
            result = true;
        }
        return result;
    }

}
