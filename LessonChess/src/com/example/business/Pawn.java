package com.example.business;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.example.lessonchess.ChessActivity;
import com.example.lessonchess.R;

public class Pawn extends Piece{

	private int ressource;
	private int currentSquare;
	private int colorA;
	private Context context;
	private Piece[] currentBoard;

	public Pawn (int color, Context chessActivity){
		context = chessActivity;
		colorA = color;
		if (color == 0){
			ressource = R.drawable.new_white_pawn_tran_small;
		}else{
			ressource = R.drawable.new_black_pawn_tran_small;
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
		
		ArrayList<Integer> listSquare =  new ArrayList<Integer>();
		
		currentBoard = ((ChessActivity)context).getAlp();

		int currentLine = currentSquare/8;


		//White pawn
		if (this.colorA == 0){

			if (currentSquare-8 >= 0){
				if(currentBoard[currentSquare-8].getColor()==-1){
					listSquare.add(currentSquare-8);

					if (currentLine == 6){

						if(currentBoard[currentSquare-16].getColor()==-1){
							listSquare.add(currentSquare-16);
						}
					}
				}
			}

			//Capture of the pawn


			if(currentSquare-9 > 0){
				if ((currentBoard[currentSquare-9].getColor() != this.getColor()) 
						&& (currentBoard[currentSquare-9].getColor() != -1)){
					listSquare.add(currentSquare-9);
				}
			}


			if(currentSquare-7 > 0){
				if ((currentBoard[currentSquare-7].getColor() != this.getColor()) 
						&& (currentBoard[currentSquare-7].getColor() != -1)){
					listSquare.add(currentSquare-7);
				}
			}


		}


		//Black pawn
		if (this.colorA == 1){

			if(currentSquare+8 < 64){
				if(currentBoard[currentSquare+8].getColor()==-1){
					listSquare.add(currentSquare+8);
					if (currentLine == 1){
						if(currentBoard[currentSquare+16].getColor()==-1){
							listSquare.add(currentSquare+16);
						}
					}
				}
			}

			//Capture of the pawn
			if (currentSquare+9 < 64){
				if ((currentBoard[currentSquare+9].getColor() != this.getColor()) 
						&& (currentBoard[currentSquare+9].getColor() != -1)){
					listSquare.add(currentSquare+9);
				}
			}

			if (currentSquare+7 < 64){
				if ((currentBoard[currentSquare+7].getColor() != this.getColor()) 
						&& (currentBoard[currentSquare+7].getColor() != -1)){
					listSquare.add(currentSquare+7);
				}
			}
		}

		while(listSquare.lastIndexOf(currentSquare) != -1){
			listSquare.remove(listSquare.lastIndexOf(currentSquare));
		}

		return listSquare;

	}

}
