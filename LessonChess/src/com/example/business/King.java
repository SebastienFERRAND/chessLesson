package com.example.business;

import java.util.ArrayList;

import android.content.Context;

import com.example.lessonchess.ChessActivity;
import com.example.lessonchess.R;

public class King  extends Piece{

	private int ressource;
	private int currentSquare;
	private int colorA;
	private Context context;

	private ArrayList<Integer> listSquare;
	private Piece[] currentBoard;

	public King (int color, Context chessActivity){
		context = chessActivity;
		colorA = color;
		if (color == 0){
			ressource = R.drawable.new_white_king_tran_small;
		}else{
			ressource = R.drawable.new_black_king_tran_small;
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

		ArrayList<Integer> forbidenSquare = new ArrayList<Integer>();



		for (int i = 0; i < 64; i++){
			if ((currentBoard[i] instanceof King)
					&& (this.getColor() != currentBoard[i].getColor())){

				for (int j = i-9; j <= i -7 ; j++){
					if (i > 0){
						forbidenSquare.add(j);
					}
				}

				for (int j = i-1; j <= i +1 ; j++){
					if ((i > 0)
							&& (i < 64)){
						forbidenSquare.add(j);
					}
				}

				for (int j = i+7; j <= i+9 ; j++){
					if (i < 64){
						forbidenSquare.add(j);
					}
				}
			}
		}

		for (int i = currentSquare-9; i <= currentSquare -7 ; i++){
			if ((i >= 0)
					&& (currentBoard[i].getColor() != this.getColor())
					&& (!(forbidenSquare.contains(i)))){
				listSquare.add(i);
			}
		}

		for (int i = currentSquare-1; i <= currentSquare +1 ; i++){
			if ((i >= 0)
					&& (i < 64)
					&& (currentBoard[i].getColor() != this.getColor())
					&& (!(forbidenSquare.contains(i)))){
				listSquare.add(i);
			}
		}

		for (int i = currentSquare+7; i <= currentSquare +9 ; i++){
			if ((i < 64)
					&& (currentBoard[i].getColor() != this.getColor())
					&& (!(forbidenSquare.contains(i)))){
				listSquare.add(i);
			}
		}
		
		if ((this.getColor() == 0)
				&&(this.getCurrentSquare() == 60)
				&&(currentBoard[61].getColor()==-1)
				&&(currentBoard[62].getColor()==-1)){
			listSquare.add(62);
		}
		
		if ((this.getColor() == 0)
				&&(this.getCurrentSquare() == 60)
				&&(currentBoard[59].getColor()==-1)
				&&(currentBoard[58].getColor()==-1)){
			listSquare.add(58);
		}
		
		if ((this.getColor() == 1)
				&&(this.getCurrentSquare() == 4)
				&&(currentBoard[5].getColor()==-1)
				&&(currentBoard[6].getColor()==-1)){
			listSquare.add(6);
		}
		
		if ((this.getColor() == 1)
				&&(this.getCurrentSquare() == 4)
				&&(currentBoard[3].getColor()==-1)
				&&(currentBoard[2].getColor()==-1)){
			listSquare.add(2);
		}

		while(listSquare.lastIndexOf(currentSquare) != -1){
			listSquare.remove(listSquare.lastIndexOf(currentSquare));
		}

		return listSquare;

	}
}
