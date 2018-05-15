package cn.re0marb1e;

import java.util.*;

/**
 * @author LHxSS
 *
 */
public class Maze {
	public final static int POINT_NUM = 21;
	public final static int CORNER_NUM = 13;
	private Point[] allPoints;
	private Corner[] allCorners;
	private Step start;
	private Step end;
	private HashSet<Step> stepRecord;
	private ArrayList<Step> stepRoute;
	
	/**
	 * 
	 */
	public Maze() {
		stepRecord = new HashSet<>();
		stepRoute = new ArrayList<>();
	}

	public void initMaze() {
		initAllPoints();
		initAllCorners();
		setPointsToCorners();
		setCornersToPoints();
		// Display
		// showAllCorners();
		// showAllPoints();
		// 设置起点Corner
		setStart();
		// 设置终点Corner
		setEnd();
	}

	// 初始化 allPoints
	public void initAllPoints() {
		allPoints = new Point[Maze.POINT_NUM];
		// 设置所有Point的id值
		for (int i = 0; i < allPoints.length; i++) {
			allPoints[i] = new Point(i);
			if (i % 2 == 1) {
				allPoints[i].setValue(Point.VALUE_BLUE);
			} else {
				allPoints[i].setValue(Point.VALUE_RED);
			}
		}
	}

	// 初始化 allCorners
	public void initAllCorners() {
		allCorners = new Corner[Maze.CORNER_NUM];
		// 设置所有Corner 的id值
		for (int i = 0; i < allCorners.length; i++) {
			allCorners[i] = new Corner(i);
		}
	}

	// 设置所有Corner 的 cornerPoints;
	public void setPointsToCorners() {
		// 0 Corner
		allCorners[0].setCornerPointsByPointsIDs(allPoints, 1, 2);
		// 1 Corner
		allCorners[1].setCornerPointsByPointsIDs(allPoints, 0, 1, 3);
		// 2 Corner
		allCorners[2].setCornerPointsByPointsIDs(allPoints, 0, 5);
		// 3 Corner
		allCorners[3].setCornerPointsByPointsIDs(allPoints, 2, 6, 7, 8);
		// 4 Corner
		allCorners[4].setCornerPointsByPointsIDs(allPoints, 3, 4, 6, 7);
		// 5 Corner
		allCorners[5].setCornerPointsByPointsIDs(allPoints, 4, 5, 9, 11);
		// 6 Corner
		allCorners[6].setCornerPointsByPointsIDs(allPoints, 9, 10);
		// 7 Corner
		allCorners[7].setCornerPointsByPointsIDs(allPoints, 8, 11, 12, 15);
		// 8 Corner
		allCorners[8].setCornerPointsByPointsIDs(allPoints, 14, 15, 16, 17, 20);
		// 9 Corner
		allCorners[9].setCornerPointsByPointsIDs(allPoints, 12, 13, 14, 18);
		// 10 Corner
		allCorners[10].setCornerPointsByPointsIDs(allPoints, 10, 13);
		// 11 Corner
		allCorners[11].setCornerPointsByPointsIDs(allPoints, 17, 18, 19);
		// 12 Corner
		allCorners[12].setCornerPointsByPointsIDs(allPoints, 19, 20);
	}

	// 设置所有Point 的 pointCorners;
	public void setCornersToPoints() {
		for (Corner corner : allCorners) {
			for (Point point : corner.getCornerPoints()) {
				point.setPointCorner(corner);
			}
		}
	}

	// 设置起点 Corner
	public void setStart() {
		this.start = new Step(allCorners[0], Point.VALUE_BLUE);
		System.out.println("起点为：" + this.start);
	}

	// 设置终点 Corner
	public void setEnd() {
		this.end = new Step(allCorners[Maze.CORNER_NUM - 1], Point.VALUE_RED);
		System.out.println("终点为：" + this.end);
	}

	public Point getPointByID(int id) {
		return allPoints[id];
	}

	public HashSet<Step> getStepRecord() {
		return stepRecord;
	}

	public void setStepRecord(HashSet<Step> stepRecord) {
		this.stepRecord = stepRecord;
	}

	// Display

	// Show allPoints
	public void showAllPoints() {
		for (Point point : allPoints) {
			System.out.println(point + "\n");
		}
	}

	// Show allCorners
	public void showAllCorners() {
		for (Corner corner : allCorners) {
			System.out.println(corner + "\n");
		}
	}

	public void startMaze(Step current) {
		// while(flag)
		System.out.println(current);
		stepRoute.add(current);
		// 如果遍历到终点，则退出
		if (current.equals(end)) {
			System.out.println("到达终点");
			System.out.println("此路径为：");
			System.out.println(stepRoute);
			return;
		}
		// 如果遍历到先前点，则终止此遍历
		for (Step step : stepRecord) {
			if (current.equals(step)) {
				System.out.println("遍历到原先点");
				flush();
				return;
			}
		}
		stepRecord.add(current);
		// 如果NextPointType为红色
		if (current.getNextPointType() == Point.VALUE_RED) {
			// 下一个寻找blue
			for (Point point : current.getCorner().getCornerRedPoints()) {
				Step step = new Step(point.getAnotherCorner(current.getCorner()), Point.VALUE_BLUE);
				startMaze(step);
			}
		}
		// 如果NextPointType为蓝色
		if (current.getNextPointType() == Point.VALUE_BLUE) {
			// 下一个寻找red
			for (Point point : current.getCorner().getCornerBluePoints()) {
				Step step = new Step(point.getAnotherCorner(current.getCorner()), Point.VALUE_RED);
				startMaze(step);
			}
		}
	}

	public void flush() {
		Step lastBranchStep = null;
		for (Step step : stepRoute) {
			if (step.getRouteNum() > 1 && stepRoute.lastIndexOf(step) != stepRoute.size() - 1) {
				lastBranchStep = step;
			}
		}
		lastBranchStep.setRouteNum(lastBranchStep.getRouteNum() - 1);
		int lastBranchIdx = stepRoute.lastIndexOf(lastBranchStep);
		for (int i = stepRoute.size() - 1; i > lastBranchIdx; i--) {
			stepRoute.remove(i);
		}
	}

	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.initMaze();
		maze.startMaze(maze.start);
	}
}
