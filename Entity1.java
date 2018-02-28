import java.util.ArrayList;

public class Entity1 extends Entity
{

    private  static final ArrayList<Integer> neighbors = new ArrayList<>();
    private  int[] minCostEntity1 = new int[4];
    // Perform any necessary initialization in the constructor
    public Entity1()
    {
        System.out.println("\nInitializing entity 1 ");
        int[] initialCosts = new int[]{1, 0, 1, 999};   //Initial Costs declaration
                                            // Adds all neighbors of node 0
        neighbors.add(0);
        neighbors.add(2);

        //Link costs between each node is initialized and sent to the distance table.
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++) {
            for (int j = 0; j < 4; j++) {

                if (i == j) {
                    this.distanceTable[i][j] = initialCosts[i];
                }
                else {
                    this.distanceTable[i][j] = 999;
                }
            }
        }
        printDT();

        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++){
            minCostEntity1[i] = caluclateMinCostRecursive(this.distanceTable[i]);
        }
        //If next node changes, a new packet with new minimum cost values is created.
        //sent to all neighboring nodes.

        int k = 1;
        for (int j : neighbors){
            Packet p = new Packet(k, j, minCostEntity1);
            NetworkSimulator.toLayer2(p);
        }
    }

    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p)
    {

        int entityId = 1;                           //Name of the node is 1
        int source = p.getSource();                 //get the source ID
        int[] currentMinCost = new int[4];
        for (int m = 0; m < NetworkSimulator.NUMENTITIES; m++){
            currentMinCost[m] = p.getMincost(m);
        }
        System.out.println(String.format("Entity %d update is called, dest = %d, source = %d",
            entityId, p.getDest(), p.getSource()));
        System.out.println("costs: ");
        for (int z =0; z < currentMinCost.length; z++){
            System.out.println(currentMinCost[z]);
        }
        System.out.println("\n");

        boolean isUpdated = false;
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++) {
            int updatableValue = this.distanceTable[source][source] + currentMinCost[i];

            if (updatableValue < 999) {
                this.distanceTable[i][source] = updatableValue;
            } else {
                this.distanceTable[i][source] = 999;
            }
        }
            this.printDT();
            //calculate the minimum cost between node 1 and its neighboring nodes
            int[] cost;
            cost = minCostEntity1;
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++) {
                minCostEntity1[j] = caluclateMinCostRecursive(this.distanceTable[j]);
            }

            for (int k = 0; k < 4; k++) {
                if (cost[k] != minCostEntity1[k]) {
                    isUpdated = true;                   //Assert isUpdated to true.
                }
            }
        //After updating the costs, further the
        // distance table is printed with the new updated minimum costs.
            if (isUpdated) {
                for (int j : neighbors) {
                    Packet updatedPacket = new Packet(entityId, j, minCostEntity1);
                    NetworkSimulator.toLayer2(updatedPacket);
                }
            }
            else{
                System.out.println("no updates");
            }
        }





    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    }

    public void printDT()
    {
        System.out.println();
        System.out.println("         via");
        System.out.println(" D1 |   0   2");
        System.out.println("----+--------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 1)
            {
                continue;
            }

            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j += 2)
            {

                if (this.distanceTable[i][j] < 10)
                {
                    System.out.print("   ");
                }
                else if (this.distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print(" ");
                }

                System.out.print(this.distanceTable[i][j]);
            }
            System.out.println();
        }
    }

    //method to calculate the minimum cost
    protected static int caluclateMinCostRecursive ( int a[] ) {
        int min = a[0];
        for (int m: a){
            if (m < min){
                min = m;
            }
        }
        return min;
    }

    //Method to print the minimum costs for node 1
    public void printMinCost() {
        System.out.print("Minimum costs for Entity 1: ");
        for (int l = 0; l < 4; l++) {
            System.out.print(minCostEntity1[l]);
        }
        System.out.println("\n");
    }
}