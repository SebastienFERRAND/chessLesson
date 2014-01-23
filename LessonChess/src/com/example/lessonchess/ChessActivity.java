package com.example.lessonchess;

import com.example.adapter.SquareAdapter;
import com.example.business.Bishop;
import com.example.business.King;
import com.example.business.Knight;
import com.example.business.Pawn;
import com.example.business.Piece;
import com.example.business.Queen;
import com.example.business.Rook;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;

public class ChessActivity extends Activity {

	private Piece[] alp = new Piece[64];
	private int whiteTurn = 0;
	private ImageView chessImage;
	private GridView chessboardGridView = null;
	private ChessActivity ca;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chess);

		setUpBoard();

		ca = this;

		chessImage = (ImageView) findViewById(R.id.imageViewChess);

		chessboardGridView = (GridView)findViewById(R.id.chessboard);
		chessboardGridView.setAdapter(new SquareAdapter(this, alp, whiteTurn));
		//		whiteTurn = 1;
	}

	public Piece[] getAlp(){
		return alp;
	}
	
	public void setAlp(Piece[] p){
		alp = p;
	}

	private void setUpBoard() {

		alp[56] = new Rook(0, this);
		alp[63] = new Rook(0, this);
		alp[58] = new Bishop(0, this);
		alp[61] = new Bishop(0, this);
		alp[59] = new Queen(0, this);
		alp[60] = new King(0, this);
		alp[57] = new Knight(0, this);
		alp[62] = new Knight(0, this);
		for (int i = 48; i<=55; i++){
			alp[i] = new Pawn(0, this);
		}

		alp[0] = new Rook(1, this);
		alp[7] = new Rook(1, this);
		alp[5] = new Bishop(1, this);
		alp[2] = new Bishop(1, this);
		alp[3] = new Queen(1, this);
		alp[4] = new King(1, this);
		alp[1] = new Knight(1, this);
		alp[6] = new Knight(1, this);
		for (int i = 8; i<=15; i++){
			alp[i] = new Pawn(1, this);
		}


		for (int i = 16; i<=47; i++){
			alp[i] = new Piece();
		}

	}

	public void setNewAdapter(Piece[] p){

		//		Bitmap bitmap = loadBitmapFromView(chessboardGridView);
		//		BitmapDrawable ob = new BitmapDrawable(bitmap);
		//		
		//		chessImage.setBackgroundDrawable(ob);
		//		chessImage.setVisibility(View.VISIBLE);

		alp = p;

		chessboardGridView.setAdapter(new SquareAdapter(ca, alp, whiteTurn));


		//		if (whiteTurn == 0){
		//			whiteTurn = 1;
		//		}else{
		//			whiteTurn = 0;
		//		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chess, menu);
		return true;
	}



}
