package com.example.business;

import java.util.ArrayList;

import android.content.Context;

import com.example.adapter.SquareAdapter;
import com.example.lessonchess.ChessActivity;
import com.example.lessonchess.R;

public class Bishop extends Piece{

	private int ressource;
	private int currentSquare;
	private int colorA;
	private Context context;
	
	private boolean blocked = false;
	private ArrayList<Integer> listSquare;
	private Piece[] currentBoard;

	public Bishop (int color, Context chessActivity){
		context = chessActivity;
		colorA = color;
		if (color == 0){
			ressource = R.drawable.new_white_bishop_tran_small;
		}else{
			ressource = R.drawable.new_black_bishop_tran_small;
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


		int currentLine = currentSquare/8;
		int currentColumn = currentSquare%8;
		
		listSquare =  new ArrayList<Integer>();

		currentBoard = ((ChessActivity)context).getAlp();


		int[][] tableTwoDim  = new int[8][8];
		int increment = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				tableTwoDim[i][j] = increment;
				increment++;
			}
		}



		blocked = false;
		int i = currentLine;
		int j = currentColumn;
		while((i<8)&&(j<8)){
			addSquare(tableTwoDim[i][j]);
			i++;
			j++;

		}

		blocked = false;
		i = currentLine;
		j = currentColumn;
		while((i<8)&&(j>=0)){
			addSquare(tableTwoDim[i][j]);
			i++;
			j--;

		}

		blocked = false;
		i = currentLine;
		j = currentColumn;
		while((i>=0)&&(j<8)){
			addSquare(tableTwoDim[i][j]);
			i--;
			j++;

		}

		blocked = false;
		i = currentLine;
		j = currentColumn;
		while((i>=0)&&(j>=0)){
			addSquare(tableTwoDim[i][j]);
			i--;
			j--;

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
