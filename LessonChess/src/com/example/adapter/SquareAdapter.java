package com.example.adapter;

import com.example.business.King;
import com.example.business.Piece;
import com.example.lessonchess.ChessActivity;
import com.example.lessonchess.R;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SquareAdapter extends BaseAdapter{

	private Context mContext;
	private LayoutInflater mInflater;
	private Piece[] lp;

	private Piece currentPiece = null;

	private FrameLayout flcp;
	private ImageView imgvcp = null;
	private ImageView imgsquare = null;

	private int whiteTurn = 0;

	private boolean canPlay = false;

	private boolean waitABit = false;


	// CHESSBOARD
	// references to our images
	private Integer[] chessboardIds = {
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
			R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
	};

	static class ViewHolder {
		public ImageView square;
		public ImageView piece;
	}


	public SquareAdapter(Context c, Piece[] listPiece, int turn) {
		mContext = c;
		Context context = c.getApplicationContext();
		mInflater = LayoutInflater.from(context);
		lp = listPiece;
		whiteTurn = turn;
	}

	@Override
	public int getCount() {
		return chessboardIds.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {  // if it's not recycled, initialize some attributes

			rowView = mInflater.inflate(R.layout.square, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.square = (ImageView) rowView.findViewById(R.id.square_background);
			viewHolder.square.setImageResource(chessboardIds[position]);
			viewHolder.piece = (ImageView) rowView.findViewById(R.id.piece);
			viewHolder.piece.setImageResource(lp[position].getRessource());

			lp[position].setCurrentSquare(position);

			// Assign the touch listener to your view which you want to move
			viewHolder.piece.setOnTouchListener(new MyTouchListener());

			viewHolder.square.setOnDragListener(new MyDragListener());

			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();

		holder.piece.setTag(lp[position]);

		return rowView;
	}


	// This defines your touch listener
	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {



			if ((motionEvent.getAction() == MotionEvent.ACTION_DOWN) && (!waitABit)) {
				//				if (!waitABit){


				//				if ((motionEvent.getAction() == MotionEvent.ACTION_DOWN)){
				waitABit = true;
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);

				flcp = (FrameLayout) view.getParent();
				imgsquare = (ImageView) flcp.getChildAt(0);
				imgvcp = (ImageView) flcp.getChildAt(1);
				currentPiece = (Piece) view.getTag();

				return true;
				//				}else {
				//					waitABit = false;
				//					return false;
				//				}
			} else {
				return false;
			}

		}
	} 

	class MyDragListener implements OnDragListener {

		@Override
		public boolean onDrag(View v, DragEvent event) {

			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				//				Log.v("Test", "Entered start");
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				//				Log.v("Test", "Entered drag");
				//				Log.v("Test", "Entered drop");

				try{
					FrameLayout fl2 = (FrameLayout) v.getParent();
					ImageView imgv2 = (ImageView) fl2.getChildAt(1);
					Piece square = (Piece) imgv2.getTag();

					if(currentPiece.getPossibleMoves().contains(square.getCurrentSquare())){

						Piece destinationPiece = lp[square.getCurrentSquare()];
						lp[square.getCurrentSquare()] = currentPiece;
						lp[currentPiece.getCurrentSquare()] = new Piece();

						int initialSquare = currentPiece.getCurrentSquare();
						currentPiece.setCurrentSquare(square.getCurrentSquare());

						if((yourBeingCheckedDumbAss(currentPiece))
								||((destinationPiece instanceof King)
										&&(destinationPiece.getColor() != currentPiece.getColor()))
										||(whiteTurn != currentPiece.getColor())){
							canPlay = false;
						}else{
							canPlay = true;
						}

						lp[square.getCurrentSquare()] = destinationPiece;
						currentPiece.setCurrentSquare(initialSquare);
						lp[currentPiece.getCurrentSquare()] = currentPiece;

					}else{
						canPlay = false;
					}
				}catch(Exception e){
					Log.v("Test", "Drag : entered bug");
				}
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				try{
					FrameLayout fl3 = (FrameLayout) v.getParent();
					ImageView imgsquare3 = (ImageView) flcp.getChildAt(0);
					ImageView imgv3 = (ImageView) fl3.getChildAt(1);
					Piece square3 = (Piece) imgv3.getTag();

					if (canPlay){
						Piece empty = new Piece();
						empty.setCurrentSquare(currentPiece.getCurrentSquare());
						lp[currentPiece.getCurrentSquare()] = empty;
						imgvcp.setTag(empty);
						imgvcp.setImageResource(0);
						//					imgvcp.setOnTouchListener(new MyTouchListener());
						lp[square3.getCurrentSquare()] = currentPiece;
						currentPiece.setCurrentSquare(square3.getCurrentSquare());
						imgv3.setTag(currentPiece);
						imgv3.setImageResource(currentPiece.getRessource());
						//					imgv3.setOnTouchListener(new MyTouchListener());

						if (whiteTurn == 0){
							whiteTurn = 1;
						}else if (whiteTurn == 1){
							whiteTurn = 0;
						}

						((ChessActivity) mContext).setAlp(lp);

					}else{

					}
					imgvcp.setVisibility(View.VISIBLE);

					//				((ChessActivity) mContext).setNewAdapter(lp);

					waitABit = false;
				}catch(Exception e){
					Log.v("Test", "Drag : drop bug");
				}
				break;
			case DragEvent.ACTION_DRAG_ENDED:
			default:
				break;
			}
			return true;
		}

		public boolean movementValid(){

			return false;

		}
	}


	public boolean yourBeingCheckedDumbAss(Piece p){

		boolean isCheck = false;

		int positionOfMyKing = 0;


		for (int i = 0; i <= 63; i++){
			if ((lp[i].getColor()== p.getColor()
					&& (lp[i] instanceof King))){
				positionOfMyKing = i;
			}
		}

		for (int i = 0; i <= 63; i++){
			if ((lp[i].getColor()!= -1)
					&& (lp[i].getColor()!= p.getColor())){
				if(lp[i].getPossibleMoves().contains(positionOfMyKing)){
					isCheck = true;
				}
			}

		}

		return isCheck;

	}


}
