package com.example.business;

import java.util.ArrayList;

import android.content.Context;

import com.example.lessonchess.ChessActivity;
import com.example.lessonchess.R;

public class Knight extends Piece {

	private int ressource;
	private int currentSquare;
	private int colorA;
	private Context context;

	private ArrayList<Integer> listSquare;
	private Piece[] currentBoard;

	public Knight (int color, Context chessActivity){
		context = chessActivity;
		colorA = color;
		if (color == 0){
			ressource = R.drawable.new_white_knight_tran_small;
		}else{
			ressource = R.drawable.new_black_knight_tran_small;
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
		int i;

		i = currentSquare - 17;
		if ((i >= 0)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}


		i = currentSquare - 15;
		if ((i >= 0)
			&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}


		i = currentSquare - 10;
		if ((i > 0)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}

		i = currentSquare - 6;
		if ((i >= 0)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}

		i = currentSquare +17;
		if ((i < 64)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}

		i = currentSquare + 15;
		if ((i < 64)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}

		i = currentSquare + 10;
		if ((i < 64)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}

		i = currentSquare + 6;
		if ((i < 64)
				&& (currentBoard[i].getColor() != this.getColor())){
			listSquare.add(i);
		}

		while(listSquare.lastIndexOf(currentSquare) != -1){
			listSquare.remove(listSquare.lastIndexOf(currentSquare));
		}

		return listSquare;

	}
}
