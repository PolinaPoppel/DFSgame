
import java.util.ArrayList;

public class SixPuzzle {

    
    private static final int goal[]    = {0,1,2,3,4,5};
    
    
    public static void main(String[] args) {
        
        
        play();
        
        
    }
    
    public static void play(){
    
        int initial[] = {1,4,2,5,3,0};
        DFS(initial);
        //printPath(DFS(initial));
    }
    
    public static void printPath(ArrayList<int[]>  path){
        
        if (path==null){
            System.out.println("null");
        } else {
            for (int i= 0; i < path.size(); i++){
                                
                System.out.println("Step " + i + ": ");
                for(int j = 0; j< path.get(i).length; j++){
                    System.out.print(path.get(i)[j] + " ");
                    if (j == 2) System.out.println(); //next row
                }
                System.out.println();
                System.out.println();
            }
        }
    }
    public static void printTiles(int[] tiles){
        for(int j = 0; j< tiles.length; j++){
                    System.out.print(tiles[j] + " ");
                    //if (j == 2) System.out.println(); //next row
                }
                System.out.println();
                System.out.println();
    }
    public static boolean checkSolution(int[] test) {
    
        for (int i = 0; i < 6; i++)if (test[i]!=goal[i]) return false;
        return true;
        
    }
    
    public static ArrayList<int[]>  DFS (int[] initial) {
    
        System.out.println("\nQ1.c Depth First Search");
        
        ArrayList<int[]> path = new ArrayList<>();
        ArrayList<int[]> stack = new ArrayList<>();
        
        stack.add( 0 , initial);

        while(!stack.isEmpty()){
            
            // pop from stack
            int current[] = new int[6];
            System.arraycopy(stack.get(0), 0, current, 0, 6);
            stack.remove(0);
            
            if (!visited(path, current)){ // check if node has been visited
            
                // set node to explored ie add it to arraylist of explored nodes
                int next[] = new int[6];
                System.arraycopy(current, 0, next, 0, 6);
                path.add(next);
                printTiles(next);
                
                if (checkSolution(next)) return path;
                
                // get position of free tile
                int zeroPos = 0;
                for (int i = 0; i<6; i++) if (next[i] == 0) zeroPos = i;
                
                
                // traverse neighbours
                int downNeighbour[] = new int[6];
                int rightNeighbour[]= new int[6];
                int leftNeighbour[] = new int[6];
                int upNeighbour[]   = new int[6];
                int tileVals[] = new int[4];
                
                // move 0 up
                if (zeroPos > 2) {
                    System.arraycopy(next, 0, upNeighbour, 0, 6);
                
                    upNeighbour[zeroPos] = next[zeroPos - 3];
                    upNeighbour[zeroPos - 3] = 0;
                    tileVals[0] = upNeighbour[zeroPos];
                    
                    //stack.add(0,upNeighbour);
                } else tileVals[0] = 0;
                
                // move 0 down
                if (zeroPos < 3) {
                    System.arraycopy(next, 0, downNeighbour, 0, 6);
                    
                    downNeighbour[zeroPos] = next[zeroPos + 3];
                    downNeighbour[zeroPos + 3] = 0;
                    tileVals[1] = downNeighbour[zeroPos];
                    //stack.add(0,downNeighbour);
                } else tileVals[1] = 0;
                
                // move 0 left
                if (zeroPos != 0 && zeroPos != 3) {
                    System.arraycopy(next, 0, leftNeighbour, 0, 6);
                
                    leftNeighbour[zeroPos] = next[zeroPos - 1];
                    leftNeighbour[zeroPos -1] = 0;
                    tileVals[2] = leftNeighbour[zeroPos];
                    //stack.add(0,leftNeighbour);
                } else tileVals[2] = 0;
                
                // move 0 right
                if (zeroPos != 2 && zeroPos != 5) {
                    System.arraycopy(next, 0, rightNeighbour, 0, 6);
                
                    rightNeighbour[zeroPos] = next[zeroPos +1];
                    rightNeighbour[zeroPos+1] = 0;
                    tileVals[3] = rightNeighbour[zeroPos];
                    //stack.add(0,rightNeighbour);
                } else tileVals[3] = 0;
                
                while (true) {
                    int max = 0;
                    for (int i = 0; i<4; i++) {
                        
                        if (tileVals[i] > max) {
                            max = tileVals[i];
                        }
                        
                        
                    }
                    if (max == 0) break;
                    else {
                        if (tileVals[0] == max) {
                            System.out.println("adding up to stack");
                            stack.add(0,upNeighbour);
                            tileVals[0] = 0;
                        } else if (tileVals[1] == max) {
                            System.out.println("adding down to stack");
                            stack.add(0,downNeighbour);
                            tileVals[1] = 0;
                        } else if (tileVals[2] == max) {
                            System.out.println("adding left to stack");
                            stack.add(0,leftNeighbour);
                            tileVals[2] = 0;
                        } else if (tileVals[3] == max) {
                            System.out.println("adding right to stack");
                            stack.add(0,rightNeighbour);
                            tileVals[3] = 0;
                        }
                    
                    
                    }
                    
                }
                
            }      
              
            //break;
            
        }

        return path;
    }
    
    // returns true if current matches an array in explored
    public static boolean visited (ArrayList<int[]> explored, int[] current){
    
        for (int[] element : explored) {
            boolean match = true;
            for (int j = 0; j < 6; j++) if (element[j]!=current[j]) match = false;
            if (match) return true;
        }     
        
        return false;
        
    }
    
}
