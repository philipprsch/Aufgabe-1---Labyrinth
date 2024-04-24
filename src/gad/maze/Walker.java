package gad.maze;

public class Walker {
	private final boolean[][] maze;
	public Result result;
	private int[] orient = {0, 1};
	private final int[] start_pos = {1,0};
	private int[] pos = start_pos;
	private final int[] end_pos;

	private final SquareMatrix rightRot = new SquareMatrix(new int[][] {{0,1},{-1,0}});
	private final SquareMatrix leftRot = new SquareMatrix(new int[][] {{0,-1},{1,0}});
	public Walker(boolean[][] maze, Result result) {
		this.maze = maze;
		this.result = result;
		this.end_pos = new int[] {this.maze.length -1, this.maze[0].length-2};
		result.addLocation(this.pos[0], this.pos[1]);
	}
	private void walkForward() throws Exception {
		this.pos = SquareMatrix.addVectors(this.pos, this.orient);
		System.out.println("New Position: "+this.pos[0]+", "+this.pos[1]);
		result.addLocation(this.pos[0], this.pos[1]);
	}
	private int[] rightVector(){
		return this.rightRot.multiplyVector(this.orient);
	}
	private void turnRight() {
		this.orient = this.rightVector();
	}
	private void turnLeft() {
		this.orient = this.leftRot.multiplyVector(this.orient);
	}
	private boolean rightWall() throws Exception {
		int[] rightField = SquareMatrix.addVectors(this.pos, this.rightVector());
		return maze[rightField[0]][ rightField[1]];
	}
	private boolean frontWall() throws Exception {
		int[] frontField = SquareMatrix.addVectors(this.pos, this.orient);
		return maze[frontField[0]][ frontField[1]];
	}
	public boolean walk() throws Exception {
		while (true) {
			if (SquareMatrix.compVectors(this.pos, this.end_pos)) {
				return true;
			}
			if (this.rightWall()) {
				if (!this.frontWall()) {
					this.walkForward();
				} else {
					this.turnLeft();
				}
			} else {
				turnRight();
				this.walkForward();
				}
			if (SquareMatrix.compVectors(this.pos, this.start_pos)) {
				return false;
			}
		}
        //return SquareMatrix.compVectors(this.pos, this.end_pos);
    }

	public static void main(String[] args) throws Exception {
		boolean[][] maze = Maze.generateStandardMaze(10, 10);
		StudentResult result = new StudentResult();
		Walker walker = new Walker(maze, result);
		System.out.println(walker.walk());
		Maze.draw(maze, result);
	}
}
