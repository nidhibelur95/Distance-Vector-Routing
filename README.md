# Distance-Vector-Routing
PROGRAM DESIGN:
•	This is a programming assignment to implement a distributed asynchronous distance vector routing algorithm for the given network topology.
•	This implementation is done using JAVA programming language.
•	The Bellman Ford Algorithm is used to implement distance vector routing algorithm in this project.
•	This program finds the minimum distance to all nodes in a graph for each node. 
•	Each node holds the minimum distance to every other node.
•	The nodes respond to change and will update minimum paths if a new one is found. In case of an update, a node will inform all its direct neighbors about it.
•	The distance vector routing algorithm is executed in an emulated environment. 
•	Main file – Project.java and other class files - Entity.java, Event.java, EventList.java, EventListImpl.java, NetworkSimulator.java, Packet.java and Subclasses Entity0.java, Entity1.java, Entity2.java, Entity3.java are needed for the routing algorithm to be executed. 
•	NetworkSimulator.java represents an emulated network environment. 
•	It produces the network layer 2, and the transmission and delivery between connected nodes. At the start of the emulated environment it calls on Entity0.java, Entity1.java, Entity2.java, Entity3.java
•	The emulated environment calls the Entity (0,1,2,3) subclasses connected to the 4 nodes Each of the nodes contain the following subclasses:
o	Entity0(), Entity1(), Entity2(), Entity3() : These constructors will initialize the corresponding nodes with link costs and adds neighboring nodes. If links change, new packets are created and sent to all neighboring nodes.
o	Update (packet p) : In the update() method, the neighboring nodes will receive the new packets from the source node and these values are updated within each of the destination nodes. 

