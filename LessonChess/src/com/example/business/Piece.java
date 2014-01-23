package com.example.business;

import java.util.ArrayList;

public class Piece {
	
	private int colorA = -1;
	private int currentSquare;
	private ArrayList<Integer> listSquare;
	public ArrayList<Integer> possibleMoves(int square){
		return null;
		
	}
	
	public int getColor(){
		return colorA;
	}

	public void setCurrentSquare(int position) {
		currentSquare = position;
	}
	
	public int getCurrentSquare() {
		return currentSquare;
	}

	public int getRessource() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ArrayList<Integer> getPossibleMoves(){
		listSquare =  new ArrayList<Integer>();
		return listSquare;
		
	}

}
