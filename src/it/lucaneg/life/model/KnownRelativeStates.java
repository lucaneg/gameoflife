package it.lucaneg.life.model;

import it.lucaneg.life.model.State.Coordinates;

public class KnownRelativeStates {
	public static final Coordinates[] BLOCK = new Coordinates[] { 
			new Coordinates(0, 0), 
			new Coordinates(1, 0),
			new Coordinates(0, 1), 
			new Coordinates(1, 1) 
			};
	
	public static final Coordinates[] BEE_HIVE = new Coordinates[] { 
			new Coordinates(1, 0),
			new Coordinates(2, 0),
			new Coordinates(0, 1),
			new Coordinates(3, 1),
			new Coordinates(1, 2),
			new Coordinates(2, 2)
			};
	
	public static final Coordinates[] LOAF = new Coordinates[] { 
			new Coordinates(1, 0),
			new Coordinates(2, 0),
			new Coordinates(0, 1),
			new Coordinates(3, 1),
			new Coordinates(1, 2),
			new Coordinates(3, 2),
			new Coordinates(2, 3)
			};
	
	public static final Coordinates[] BOAT = new Coordinates[] { 
			new Coordinates(0, 0), 
			new Coordinates(1, 0),
			new Coordinates(0, 1), 
			new Coordinates(1, 2),
			new Coordinates(2, 1)
			};
	
	public static final Coordinates[] TUB = new Coordinates[] { 
			new Coordinates(1, 0),
			new Coordinates(0, 1), 
			new Coordinates(1, 2),
			new Coordinates(2, 1)
			};
	
	public static final Coordinates[] BLINKER = new Coordinates[] { 
			new Coordinates(0, 1), 
			new Coordinates(1, 1),
			new Coordinates(2, 1)
			};
	
	public static final Coordinates[] TOAD = new Coordinates[] { 
			new Coordinates(1, 1), 
			new Coordinates(2, 1),
			new Coordinates(3, 1),
			new Coordinates(0, 2),
			new Coordinates(1, 2),
			new Coordinates(2, 2)
			};
	
	public static final Coordinates[] BEACON = new Coordinates[] { 
			new Coordinates(0, 0), 
			new Coordinates(1, 0),
			new Coordinates(0, 1), 
			new Coordinates(1, 1),
			new Coordinates(2, 2), 
			new Coordinates(2, 3),
			new Coordinates(3, 2), 
			new Coordinates(3, 3)
			};
	
	public static final Coordinates[] PULSAR = new Coordinates[] { 
			new Coordinates(4, 2), 
			new Coordinates(5, 2),
			new Coordinates(6, 2), 
			new Coordinates(10, 2),
			new Coordinates(11, 2), 
			new Coordinates(12, 2),
			new Coordinates(2, 4), 
			new Coordinates(7, 4), 
			new Coordinates(9, 4), 
			new Coordinates(14, 4), 
			new Coordinates(2, 5), 
			new Coordinates(7, 5), 
			new Coordinates(9, 5), 
			new Coordinates(14, 5), 
			new Coordinates(2, 6), 
			new Coordinates(7, 6), 
			new Coordinates(9, 6), 
			new Coordinates(14, 6), 
			new Coordinates(4, 7), 
			new Coordinates(5, 7), 
			new Coordinates(6, 7), 
			new Coordinates(10, 7), 
			new Coordinates(11, 7), 
			new Coordinates(12, 7), 
			new Coordinates(4, 9), 
			new Coordinates(5, 9), 
			new Coordinates(6, 9), 
			new Coordinates(10, 9), 
			new Coordinates(11, 9), 
			new Coordinates(12, 9), 
			new Coordinates(2, 10), 
			new Coordinates(7, 10), 
			new Coordinates(9, 10), 
			new Coordinates(14, 10), 
			new Coordinates(2, 11), 
			new Coordinates(7, 11), 
			new Coordinates(9, 11), 
			new Coordinates(14, 11), 
			new Coordinates(2, 12), 
			new Coordinates(7, 12), 
			new Coordinates(9, 12), 
			new Coordinates(14, 12), 
			new Coordinates(4, 14), 
			new Coordinates(5, 14), 
			new Coordinates(6, 14), 
			new Coordinates(10, 14), 
			new Coordinates(11, 14), 
			new Coordinates(12, 14)			
			};
	
	public static final Coordinates[] PENTA_DECATHLON = new Coordinates[] { 
			new Coordinates(4, 5), 
			new Coordinates(5, 5),
			new Coordinates(6, 4),
			new Coordinates(6, 6),
			new Coordinates(7, 5),
			new Coordinates(8, 5),
			new Coordinates(9, 5), 
			new Coordinates(10, 5),
			new Coordinates(11, 4),
			new Coordinates(11, 6),
			new Coordinates(12, 5), 
			new Coordinates(13, 5)
			};
	
	public static final Coordinates[] GLIDER = new Coordinates[] { 
			new Coordinates(1, 3), 
			new Coordinates(2, 3),
			new Coordinates(3, 3),
			new Coordinates(3, 2),
			new Coordinates(2, 1)
			};
	
	public static final Coordinates[] LWSS = new Coordinates[] { 
			new Coordinates(1, 1), 
			new Coordinates(4, 1),
			new Coordinates(5, 2),
			new Coordinates(1, 3),
			new Coordinates(5, 3),
			new Coordinates(2, 4),
			new Coordinates(3, 4),
			new Coordinates(4, 4),
			new Coordinates(5, 4),
			};
	
	public static final Coordinates[] MWSS = new Coordinates[] { 
			new Coordinates(3, 1), 
			new Coordinates(1, 2),
			new Coordinates(5, 2),
			new Coordinates(6, 3),
			new Coordinates(1, 4),
			new Coordinates(6, 4),
			new Coordinates(2, 5),
			new Coordinates(3, 5),
			new Coordinates(4, 5),
			new Coordinates(5, 5),
			new Coordinates(6, 5)
			};
	
	public static final Coordinates[] HWSS = new Coordinates[] { 
			new Coordinates(3, 1), 
			new Coordinates(4, 1), 
			new Coordinates(1, 2),
			new Coordinates(6, 2),
			new Coordinates(7, 3),
			new Coordinates(1, 4),
			new Coordinates(7, 4),
			new Coordinates(2, 5),
			new Coordinates(3, 5),
			new Coordinates(4, 5),
			new Coordinates(5, 5),
			new Coordinates(6, 5),
			new Coordinates(7, 5)
			};
	
	public static final Coordinates[] R_PENTOMINO = new Coordinates[] { 
			new Coordinates(1, 2), 
			new Coordinates(2, 1), 
			new Coordinates(2, 2),
			new Coordinates(2, 3),
			new Coordinates(3, 1)
			};
	
	public static final Coordinates[] DIEHARD = new Coordinates[] { 
			new Coordinates(1, 2), 
			new Coordinates(2, 2), 
			new Coordinates(2, 3),
			new Coordinates(6, 3),
			new Coordinates(7, 1),
			new Coordinates(7, 3),
			new Coordinates(8, 3)
			};
	
	public static final Coordinates[] ACORN = new Coordinates[] { 
			new Coordinates(1, 3), 
			new Coordinates(2, 1), 
			new Coordinates(2, 3),
			new Coordinates(4, 2),
			new Coordinates(5, 3),
			new Coordinates(6, 3),
			new Coordinates(7, 3)
			};
}
