package gad.maze;
public class Walker {
	public boolean[][] maze;
	public boolean[][] maze_visited;
	public double[] pos = {1,0};
	public double[] orient = {0,1};
	private SquareMatrix rightRot = new SquareMatrix(new double[][] {{0,1},{-1,0}});
	private SquareMatrix leftRot = new SquareMatrix(new double[][] {{0,-1},{1,0}});
	public Result result;
	public Walker(boolean[][] maze, Result result) {
		this.maze = maze;
		this.result = result;
	}


	public boolean walk() {
		boolean result_ready = false;
		boolean solvable = false;

		while (!result_ready) {
			double[] rightVect = this.rightRot.multiplyVector(this.orient);
			double[] rightWall = {this.pos[0]+rightVect[0], this.pos[1]+rightVect[1]};
			System.out.println("{"+this.orient[0]+","+this.orient[1]+"}");
			if (maze[(int)rightWall[0]][(int)rightWall[1]]) {
				double[] leftVect = this.leftRot.multiplyVector(this.orient);
				double[] leftWall = {this.pos[0]+leftVect[0], this.pos[1]+leftVect[1]};
				if (maze[(int)pos[0]+(int)orient[0]][(int)pos[1]+(int)orient[1]]) {
					this.orient = new double[] {(int)leftVect[0], (int)leftVect[1]};
				} else {
					this.pos[0] += this.orient[0]; this.pos[1] += this.orient[1];
					result.addLocation((int)this.pos[0], (int)this.pos[1]);
				}

			} else {
				this.orient = new double[] {(int)rightVect[0], (int)rightVect[1]};
				this.pos[0] += this.orient[0]; this.pos[1] += this.orient[1];
				result.addLocation((int)this.pos[0], (int)this.pos[1]);
			}
			if (this.pos[0] == 1 && this.pos[1] == 0) {
				solvable = false;
				result_ready = true;
			} else if (this.pos[0] == this.maze[0].length-1 && this.pos[1] == this.maze[1].length-2) {
				solvable = true;
				result_ready = true;
			}
		}
		return solvable;
	}
	public static void main(String[] args) {
		boolean[][] maze = Maze.generateStandardMaze(80, 80);
		StudentResult result = new StudentResult();
		Walker walker = new Walker(maze, result);
		System.out.println(walker.walk());
		Maze.draw(maze, result);
	}
}
