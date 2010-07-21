package com.random.randomproject;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.util.Log;
import android.view.View;


public class TKChart extends View {
	ArrayList<String> times;
	ArrayList<String[]> legendList;
	ArrayList<ShapeDrawable> pieList;
	ArrayList<ShapeDrawable> legendRectList;
	static boolean isEmpty; 
	final int startX = 50;
	final int startY = 10;
	final int dia    = 200;
	final int width  = startX + dia;
	final int height = startY + dia;
	
	public TKChart(Context context) {
		super(context);
		isEmpty = false;
		setupChart(null, 0);
	}
	
	public TKChart(Context context, ArrayList<String[]> times) {
		super(context);
		isEmpty = false;
		
		legendList = new ArrayList<String[]>();
		
		if (times == null) {
			isEmpty = true;
			return;
		}
		
		try {
			if (times.size() < 2) {
				isEmpty = true;
				return;
			}
			
			String[] lastElement = times.get(times.size() - 1);
			long totalTime = Long.parseLong(lastElement[0]);
			times.remove(lastElement);
		
			Log.i("totalTIme", times.get(times.size() - 1)[0]);
			
			pieList = new ArrayList<ShapeDrawable>();
			legendRectList = new ArrayList<ShapeDrawable>();
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
			String [] legend = new String[2];
			float fraction = Float.parseFloat(times.get(i)[0]) / totalTime;
			Integer color = 0xff74AC23 * (i + 1);

			final int startLegendX = 10;
			final int startLegendY = height + ((i + 1) * 20);
			final int endLegendX   = startLegendX + 50;
			final int endLegendY   = startLegendY + 10;

//			Building Legend
			legend[0] = color.toString();
			legend[1] = times.get(i)[1] + "(" + fraction * 100 + "%)"; 
			legendList.add(legend);
			ShapeDrawable  legendRect = new ShapeDrawable();
			legendRect.getPaint().setColor(color);
			legendRect.setBounds(startLegendX, startLegendY, 
					endLegendX, endLegendY);
			legendRectList.add(legendRect);
			
//			Building Piechart
			float sweepAngle = fraction * 360f; 
			ShapeDrawable sd = new ShapeDrawable(
					new ArcShape(startAngle, sweepAngle));
			sd.getPaint().setColor(color);
			sd.setBounds(startX, startY, width, height);
			pieList.add(sd);
			
			String legendValue = times.get(i)[1] + "(" + fraction * 100 + "%)"; 
			Log.i("Type", legendValue);
			
			startAngle += sweepAngle;
		}
	}
	
	protected void onDraw(Canvas canvas) {
		if (isEmpty)
			return;
		
		for (int i = 0; i < pieList.size(); i++) {
			Log.i("drawing", "pie: " + i);
			ShapeDrawable pie = (ShapeDrawable) pieList.get(i);
			ShapeDrawable legend = (ShapeDrawable) legendRectList.get(i);
			pie.draw(canvas);
			legend.draw(canvas);			
		}
	}
}
