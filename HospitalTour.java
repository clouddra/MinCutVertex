import java.util.*;
import java.lang.Math ; 

// A0072292H
// Chong Yun Long

class HospitalTour {
	private int V; // number of vertices in the graph (number of rooms in the hospital)
	private Vector < Vector < IntegerPair > > AdjList; // the graph (the hospital)
	private Vector < Integer > RatingScore; // the weight of each vertex (rating score of each room)

	private Vector < Integer > color ; // color of each vertex(unvisited, visited, explored)
	private Vector < Integer > nodeOrder ;
	public final int unvisited = 0 ;
	public final int visited = 1 ;  // traversed once
	public final int explored = 2 ; // traversed itself and all neighbors
	public int traverseOrder ;
	int answer;


	public HospitalTour() {  }

	int Query() {
		// Reinitialize all variables
		answer = -1;
		traverseOrder = 0;
		color = new Vector<Integer>();
		nodeOrder = new Vector<Integer>();
		nodeOrder.setSize(V);
		for (int i = 0; i < V; i++)
			color.add(unvisited);

		dfs(0, -1);

		return answer;
	}

	void minCutVertex(int current) 
	{
		// not source or source with >1 children
		if (current!=0 || (current==0 && AdjList.get(current).size() > 1)) {
			if (answer < 0) 
				answer = RatingScore.get(current) ;
			
			else
				answer = Math.min(RatingScore.get(current), answer);
		}
	}
	int dfs(int current, int previous) {
		color.set(current, visited) ;
		nodeOrder.set(current, traverseOrder) ;
		int lowPoint = traverseOrder++;	

		for (int i=0 ; i < AdjList.get(current).size() ; i++){
			int next = AdjList.get(current).get(i).first() ;
			if (next!=previous)
			{
				
				if (color.get(next)==unvisited){				
					int result = dfs(next, current) ;

					if (result == nodeOrder.get(current) && current!=0)   // start of a cycle that is not root
						minCutVertex(current) ;
					if (result > nodeOrder.get(current))	// vertex of a cut edge = cut vertex
						minCutVertex(current) ;
					

					lowPoint = Math.min(result, lowPoint) ;

				}
				else  // either visited or fully explored
					lowPoint = Math.min(nodeOrder.get(next), lowPoint) ;
			}
		}

		color.set(current, explored) ;
		return lowPoint ;

	}
	// --------------------------------------------

	void run() {
		// do not alter this method
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt();

			// read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
			RatingScore = new Vector < Integer > ();
			for (int i = 0; i < V; i++)
				RatingScore.add(sc.nextInt());

			// clear the graph and read in a new graph as Adjacency List
			AdjList = new Vector < Vector < IntegerPair > >();
			for (int i = 0; i < V; i++) {
				AdjList.add(new Vector<IntegerPair>());

				int k = sc.nextInt();
				while (k-- > 0) {
					int j = sc.nextInt();
					AdjList.get(i).add(new IntegerPair(j, 1)); // edge weight is always 1 (the weight is on vertices now)
				}
			}

			System.out.println(Query());
		}
	}

	public static void main(String[] args) {
		// do not alter this method
		HospitalTour ps3 = new HospitalTour();
		ps3.run();
	}
}
