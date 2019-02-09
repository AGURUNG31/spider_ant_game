// GridBoardPosition provides an abstraction for valid chess position on a square chess board of square BOARD_SIZE
// Can be setup using algebraic chess notation
class GridBoardPosition
{
	//Maps the integer horizontal co-ordinates to constant letter representations from algebra co-ordinates
	private enum AlgebraicXCoordinate {
		A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

		private int xCoordinate;

		//Map each enum value to an integer representing the x-coordinate
		private AlgebraicXCoordinate(final int coordinate)
		{
			this.xCoordinate = coordinate;
		}

		public int getValue()
		{
			return this.xCoordinate;
		}

		public static AlgebraicXCoordinate fromInt(int coordinate) 
		{
		    for (AlgebraicXCoordinate algebraCoordinate : AlgebraicXCoordinate.values()) 
		    {
		        if (coordinate == algebraCoordinate.getValue()) 
		        {
		        	return algebraCoordinate;
		        }
		    }

		    throw new IllegalArgumentException("Invalid position co-ordinate for algebraic chess notation.");
		}
	}

	public int x; //horizontal coordinate
	public int y; //vertical coordinate

	public static int BOARD_SIZE = 8; //size of one side of the square chessboard

	//Throws IllegalArgumentException if invalid co-ordinate range
	public GridBoardPosition(int xCoordinate, int yCoordinate) throws IllegalArgumentException
	{
		if (isValidCoordinate(xCoordinate,yCoordinate))
		{
			x = xCoordinate;
			y = yCoordinate;
		} else {
			throw new IllegalArgumentException("Invalid position co-ordinate. GridBoardView size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}
	}

	//Constructor for a position coordinate from algebraic chess notation (eg. A8 => x=1,y=8 , B2 => x=2,y=2 ...)
	//Throws IllegalArgumentException if an invalid algebraic chess notation is provided
	public GridBoardPosition(String algebraCoordinate) throws IllegalArgumentException
	{
		if (algebraCoordinate.length() == 2)
		{
			try {
				x = AlgebraicXCoordinate.valueOf(algebraCoordinate.substring(0,1).toUpperCase()).getValue(); //Use AlgebraicXCoordinate enum
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid horizontal algebraic co-ordinate. GridBoardView size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

			try {	
				y = Integer.parseInt(algebraCoordinate.substring(1));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid vertical position co-ordinate. GridBoardView size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

			if (!isValidCoordinate(x,y))
			{
				throw new IllegalArgumentException("Invalid algebraic position co-ordinate. GridBoardView size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

		} else {
			throw new IllegalArgumentException("Invalid algebraic position co-ordinate. GridBoardView size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}
	}

	//Is the coordinate a valid position on the defined board size
	public static boolean isValidCoordinate(int xCoordinate, int yCoordinate)
	{
		if (xCoordinate >= 1 && xCoordinate <= BOARD_SIZE && yCoordinate >= 1 && yCoordinate <= BOARD_SIZE)
		{
			return true;
		} else {
			return false;
		}
	}

	//When does one GridBoardPosition equal another GridBoardPosition
	//Required method to override for use with 'contains' in collections
	//
	//Returns boolean
	public boolean equals(Object other)
	{
		GridBoardPosition another = (GridBoardPosition)other;
		return another.x == this.x && another.y == this.y;
	}

	//Allows for storage of class in Hash based collections
	//Builds a unique int as the hashcode based on concatenating the string representation of each part of the coordinate
	//
	//Returns int that is unique for each GridBoardPosition
	public int hashCode()
	{
		String xString = String.valueOf(this.x);
		String yString = String.valueOf(this.y);
		String stringCoordinate = xString + yString;
		return Integer.parseInt(stringCoordinate);
	}

	//Return the algebraic notation of the object's chess co-ordinate
	public String toString()
	{
		String xCoordinate = AlgebraicXCoordinate.fromInt(this.x).name();
		String yCoordinate = String.valueOf(this.y);
		return (xCoordinate + yCoordinate);
	}

}