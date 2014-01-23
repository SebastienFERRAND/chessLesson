package com.example.business;

import java.util.ArrayList;

import android.content.Context;

import com.example.lessonchess.ChessActivity;
import com.example.lessonchess.R;


public class Rook extends Piece{

	private int ressource;
	private int currentSquare;
	private int colorA;
	private Context context;

	boolean blocked = false;
	private ArrayList<Integer> listSquare;
	private Piece[] currentBoard;

	public Rook (int color, Context chessActivity){
		context = chessActivity;
		colorA = color;
		if (color == 0){
			ressource = R.drawable.new_white_rook_tran_small;
		}else{
			ressource = R.drawable.new_black_rook_tran_small;
		}
	}

	public int getColor(){
		return colorA;
	}

	public int getRessource(){
		return ressource;
	}

	public void setCurrentSquare(int position) {
		currentSquare = position;
	}

	public int getCurrentSquare() {
		return currentSquare;
	}

	public ArrayList<Integer> getPossibleMoves(){

		listSquare =  new ArrayList<Integer>();

		currentBoard = ((ChessActivity)context).getAlp();

		int currentLine = currentSquare/8;
		int beginOfRow = currentLine * 8;
		int endOfRow = (currentLine+1) * 8;

		blocked = false;
		for (int i = currentSquare; i >= beginOfRow ; i--){
			addSquare(i);
		}

		blocked = false;
		for (int i = currentSquare; i < endOfRow ; i++){
			addSquare(i);
		}

		blocked = false;
		for (int i = currentSquare; i < 64; i=i+8 ){
			addSquare(i);
		}

		blocked = false;
		for (int i = currentSquare; i >= 0 ; i=i-8 ){
			addSquare(i);
		}

		while(listSquare.lastIndexOf(currentSquare) != -1){
			listSquare.remove(listSquare.lastIndexOf(currentSquare));
		}

		return listSquare;

	}

	public void addSquare(int i){

		if (!blocked){
			//if empty
			if(currentBoard[i].getColor() == -1){
				listSquare.add(i);

				//if different color
			}else if (currentBoard[i].getColor() != this.getColor()){
//				if (!(currentBoard[i] instanceof King)){
					listSquare.add(i);
//				}
				blocked = true;

				//if same color
			}else{
				if (i != currentSquare){
					blocked = true;
				}
			}
		}
	}

}
