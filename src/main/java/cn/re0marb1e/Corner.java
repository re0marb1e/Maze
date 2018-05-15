package cn.re0marb1e;

import java.util.ArrayList;

public class Corner {
	private int id;
	private ArrayList<Point> cornerRedPoints;
	private ArrayList<Point> cornerBluePoints;

	// Constructor
	public Corner(int id) {
		this.id = id;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Point> getCornerRedPoints() {
		return cornerRedPoints;
	}

	public ArrayList<Point> getCornerBluePoints() {
		return cornerBluePoints;
	}

	public ArrayList<Point> getCornerPoints() {
		ArrayList<Point> cornerPoints = new ArrayList<>();
		cornerPoints.addAll(cornerBluePoints);
		cornerPoints.addAll(cornerRedPoints);
		return cornerPoints;
	}

	public void setCornerPointsByPointsIDs(Point[] allPoints, int... cornerPointsIds) {
		cornerRedPoints = new ArrayList<Point>();
		cornerBluePoints = new ArrayList<Point>();
		for (int pointId : cornerPointsIds) {
			Point cornerPoint = allPoints[pointId];
			if (cornerPoint.getValue() == Point.VALUE_RED) {
				cornerRedPoints.add(cornerPoint);
			} else {
				cornerBluePoints.add(cornerPoint);
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CornerID:" + id + "\ncornerRedPoints:" + cornerRedPoints + "\ncornerBluePoints:" + ""
				+ cornerBluePoints;
	}

}
