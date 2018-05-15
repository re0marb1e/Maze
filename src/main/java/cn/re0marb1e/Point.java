package cn.re0marb1e;

import java.util.ArrayList;

public class Point {
	public final static int VALUE_RED = 0;
	public final static int VALUE_BLUE = 1;

	private int id;
	private int value;
	private ArrayList<Corner> pointCorners;

	// Constructor
	public Point(int id) {
		// TODO Auto-generated constructor stub
		pointCorners = new ArrayList<Corner>();
		this.id = id;
	}

	// Setter & Getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArrayList<Corner> getPointCorners() {
		return pointCorners;
	}

	public void setPointCorner(Corner pointCorner) {
		pointCorners.add(pointCorner);
	}

	/**
	 * @param oneCorner
	 * @return 
	 */
	public Corner getAnotherCorner(Corner oneCorner) {
		for (Corner corner : pointCorners) {
			if (!corner.equals(oneCorner)) {
				return corner;
			}
		}
		// 自循环
		return oneCorner;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (this.value == VALUE_RED) {
			return "Point[RED ; id:" + id + "; pointCorners" + getPointCornersIdList();
		} else {
			return "Point[BLUE; id:" + id + "; pointCorners" + getPointCornersIdList();
		}
	}

	public ArrayList<Integer> getPointCornersIdList() {
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (Corner corner : pointCorners) {
			arrList.add(corner.getId());
		}
		return arrList;
	}
}
