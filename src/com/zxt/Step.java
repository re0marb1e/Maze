package com.zxt;

public class Step {
	private Corner corner;
	private int nextPointType;
	private int routeNum;

	public int getRouteNum() {
		return routeNum;
	}

	public void setRouteNum(int routeNum) {
		this.routeNum = routeNum;
	}

	public Step(Corner corner, int nextPointType) {
		this.corner = corner;
		this.nextPointType = nextPointType;
		if (this.nextPointType == Point.VALUE_RED) {
			routeNum = this.corner.getCornerRedPoints().size();
		} else{
			routeNum = this.corner.getCornerBluePoints().size();
		}
	}

	public Corner getCorner() {
		return corner;
	}

	public void setCorner(Corner corner) {
		this.corner = corner;
	}

	public int getNextPointType() {
		return nextPointType;
	}

	public void setNextPointType(int nextPointType) {
		this.nextPointType = nextPointType;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Step step = (Step) obj;
		if (this.getCorner() == step.getCorner() && this.getNextPointType() == step.getNextPointType())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		String str = corner.getId() + "" + nextPointType;
		return Integer.parseInt(str);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (routeNum > 1) {
			return "Step[" + corner.getId() + ";" + nextPointType + ";" + routeNum + "]";
		} else {
			return "Step[" + corner.getId() + ";" + nextPointType + "]";
		}
	}
}
