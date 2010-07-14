package com.random.randomproject;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.util.Log;
import android.view.View;


public class TKChart extends View {
	private ShapeDrawable pieChart;
	private ShapeDrawable [] pie;
	private static int centerX;
	private static int centerY;
	private static int N_ITEMS;
	ArrayList<String> times;
	ArrayList<ShapeDrawable> pieList;
	
	public TKChart(Context context) {
		super(context);
	
		centerX = 75;//getWidth() / 2;
		centerY = 75;//getHeight() / 2;
		
		setupChart(null, 0);
	}
	
	public TKChart(Context context, ArrayList<String[]> times) {
		super(context);
		
		try {
			String[] lastElement = times.get(times.size() - 1);
			long totalTime = Long.parseLong(lastElement[0]);
			times.remove(lastElement);
		
			Log.i("totalTIme", times.get(times.size() - 1)[0]);
		
			N_ITEMS = times.size();
			pieList = new ArrayList<ShapeDrawable>();
			setupChart(times, totalTime);
		}
		catch (Exception e) {
			Log.e("TKChart-C", e.toString());
		}
	}
	
	private void setupChart(ArrayList<String[]> times, long totalTime) {		
		if (times == null || totalTime == 0)
			return;
	
		float startAngle = 0.0f;
		for (int i = 0; i < times.size(); i++) {
			float sweepAngle = 
				Float.parseFloat(times.get(i)[0]) * 360.0f / totalTime; 
			ShapeDrawable sd = new ShapeDrawable(
					new ArcShape(startAngle, sweepAngle));
			sd.getPaint().setColor(0xff74AC23 * (i + 1));
			sd.setBounds(25, 25, 300, 300);
			pieList.add(sd);
			
			startAngle += sweepAngle;
		}
	}
	
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < pieList.size(); i++) {
			Log.i("drawing", "pie: " + i);
			ShapeDrawable sd = (ShapeDrawable) pieList.get(i);
			sd.draw(canvas);
		}
	}
}
