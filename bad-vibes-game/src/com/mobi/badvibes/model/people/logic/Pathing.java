package com.mobi.badvibes.model.people.logic;

import java.util.ArrayList;

import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;

/**
 * This class implements flood fill whenever needed.
 * Usually this recomputes when a person changes position from one cell to another.
 * @author Darren
 *
 */
public abstract class Pathing {
	private static int[][] PlatformGrid;
	public static Pathing Instance;
	private Person[][] people;
	public Pathing(Person[][] people, Point destination){
		Instance = this;
		
		if (stillTheSamePlaces(people)) return;
		
		this.people = people;
		initializeGrid(people);
		ArrayList<Point> doneCells = new ArrayList<Point>();
		ArrayList<Point> currentCells = new ArrayList<Point>();
		ArrayList<Point> futureCells = new ArrayList<Point>();
		
		/** Begin with the destination */
		currentCells.add(destination);
		int currentValue = 0;
		do {
			++currentValue;
			
			/* Start with the current cells, add one value to their cell. */
			for (Point c : currentCells) {
				// System.out.println("Currently handling " + current);
				if (people[c.y][c.x] == null)
					PlatformGrid[c.y][c.x] += currentValue;
				else
					PlatformGrid[c.y][c.x] = 'X' - '0' + 1;
				
				/* Get surrounding cells, and add as a future cell. */
				ArrayList<Point> surroundingPoints = getSurroundingPoints(c);
				for (Point addables : surroundingPoints) {
					if (currentCells.contains(addables) == false && doneCells.contains(addables) == false){
						if (futureCells.contains(addables) == false){
							futureCells.add(addables);
							// System.out.println("Found neighbors " + addables);
						}
					}
				}
				doneCells.add(c);
			}
			
			currentCells.clear();
			currentCells.addAll(futureCells);
			futureCells.clear();
			// debugDisplay(PlatformGrid);
		}while(currentCells.size() != 0);
		debugDisplay(PlatformGrid);
	}
	
	private boolean stillTheSamePlaces(Person[][] people) {
		if (this.people == null) return false;
		for (int y = 0; y < people.length; y++)
			for (int x = 0; x < people[0].length; x++)
				if (people[y][x] != this.people[y][x]) return false;
			
		
		return true;
	}

	public Point getBestChoice(Point currentPlace){
		// System.out.println("I'm currently at " + currentPlace + ". Please help me pick where to go!");
		ArrayList<Point> surroundingPoints = getSurroundingPoints(currentPlace);
		Point best = null;
		int score = 999;
//		System.out.println("I found " + surroundingPoints.size() + " number of choices to go to!");
		for (Point p : surroundingPoints) {
			if (PlatformGrid[p.y][p.x] < score){
//				System.out.println("I found a better choice: " + p);
				best = p;
				score = PlatformGrid[p.y][p.x];
			}
		}
		// System.out.println("I am " + ((best == null) ? "lost!" : " going to " + best));
		return best;
	}
	
	private ArrayList<Point> getSurroundingPoints(Point p){
		ArrayList<Point> result = new ArrayList<Point>();
		if (p == null) return result;
		if (p.x - 1 > 0)
			result.add(new Point(p.x - 1, p.y));
		if (p.x + 1 < World.GRID_WIDTH)
			result.add(new Point(p.x + 1, p.y));
		if (p.y - 1 > 0)
			result.add(new Point(p.x, p.y - 1));
		if (p.y + 1 < World.GRID_HEIGHT)
			result.add(new Point(p.x, p.y + 1));
		return result;
	}
	
	private  void initializeGrid(Person[][] people){
		PlatformGrid = new int[people.length][people[0].length];
		for (int y = 0; y < PlatformGrid.length; y++){
			for (int x = 0; x < PlatformGrid[0].length; x++){
				int val = (people[y][x] == null) ? 0 : -1;
				PlatformGrid[y][x] = val;
			}
		}
	}
	

	private void debugDisplay(int[][] platformGrid) {
		for (int y = 0; y < PlatformGrid.length; y++){
			for (int x = 0; x < PlatformGrid[0].length; x++){
				char val = (char)(PlatformGrid[y][x] + '0' - 1);
				System.out.print("[" + val + "]");
			}
			System.out.println();
		}
	}

}