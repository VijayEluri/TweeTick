package com.riglabs.tweetick;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class TKChart extends View {
	ArrayList<String> times;
	ArrayList<String[]> legendList;
	ArrayList<ShapeDrawable> pieList;
	ArrayList<ShapeDrawable> legendRectList;
	ArrayList<TextView> labelList;
	
	static boolean isEmpty; 
	final int startX = 50;
	final int startY = 10;
	final int dia    = 200;
	final int width  = startX + dia;
	final int height = startY + dia;
	final int legendWidth = 150;
	final int legendHeight = 15;
	final int labelWidth   = 250;
	final int labelHeight  = legendHeight;
//	int startX;
//	int startY;
//	int dia;
//	int width;
//	int height;
	final int margin = 10;
	
	Context context;
	
	public TKChart(Context context) {
		super(context);
		isEmpty = false;
		setupChart(null, 0);
	}
	
	public TKChart(Context context, ArrayList<String[]> times) {
		super(context);
		this.context = context;
		isEmpty = false;
		
		legendList = new ArrayList<String[]>();
		labelList  = new ArrayList<TextView>();
	
//		startX = margin;
//		startY = margin;
//		width = getMeasuredWidth() - margin;
//		height = getBottom() - margin;
		
		
//		Log.i("h/w", width + "/" + height);
		
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
		
//			Log.i("totalTime", times.get(times.size() - 1)[0]);
			
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
//			String [] legend = new String[2];
			float fraction = Float.parseFloat(times.get(i)[0]) / totalTime;
			Integer color = 0xff74AC23 * (i + 1);

			final int startLegendX = 10;
			final int startLegendY = height + ((i + 1) * 20);
			final int endLegendX   = startLegendX + legendWidth;
			final int endLegendY   = startLegendY + legendHeight;

//			Building Legend
			TextView label = new TextView(this.context);
			String labelString	 = times.get(i)[1] + "(" + fraction * 100 + "%)";
//			CharSequence labelChars = labelString.subSequence(0,
//					labelString.length() - 1);
//			Log.i("label", labelString);
//			label.setText(labelString);
//			label.requestRectangleOnScreen(new Rect(startLegendX + 50, 
//					startLegendY, endLegendX + 175, endLegendY));
//			label.setFrame(startLegendX + 50, 
//					startLegendY, endLegendX + 175, endLegendY);
			label.setVisibility(VISIBLE);
			label.setTextColor(color);
			label.setWidth(250);
			label.setHeight(endLegendX - startLegendX);
			label.setText(labelString.subSequence(0, 
					labelString.length() - 1));
//			label.setBounds(startLegendX + 100, startLegendY,
//					endLegendX + 400, endLegendY);
			labelList.add(label);
//			legendList.add(legend);
			ShapeDrawable  legendRect = new ShapeDrawable();
			legendRect.getPaint().setColor(color);
			legendRect.setBounds(startLegendX, startLegendY, 
					endLegendX, endLegendY);
			legendRectList.add(legendRect);
			
			
//			Building Pie Chart
			float sweepAngle = fraction * 360f; 
			ShapeDrawable sd = new ShapeDrawable(
					new ArcShape(startAngle, sweepAngle));
			sd.getPaint().setColor(color);
			sd.setBounds(startX, startY, width, height);
			pieList.add(sd);
			
//			String legendValue = times.get(i)[1] + "(" + fraction * 100 + "%)"; 
//			Log.i("Type", legendValue);
			
			startAngle += sweepAngle;
		}
	}
	
	protected void onDraw(Canvas canvas) {
		if (isEmpty)
			return;
		
		final int startLegendX = 10;
		
		for (int i = 0; i < pieList.size(); i++) {
//			Log.i("drawing", "pie: " + i);
			ShapeDrawable pie = (ShapeDrawable) pieList.get(i);
//			ShapeDrawable legend = (ShapeDrawable) legendRectList.get(i);
			TextView label       = (TextView) labelList.get(i);
			pie.draw(canvas);
//			legend.draw(canvas);	
//			label.draw(canvas);
			String labelString = (String) label.getText();
			Paint paint = new Paint();
			paint.setColor(label.getCurrentTextColor());
			final int startLegendY = height + ((i + 1) * 15);
			
			canvas.drawText(labelString, startLegendX, startLegendY, paint);
		}
	}
	
	public ArrayList<TextView> getLabels() {
		return labelList;
	}
	
	public int getLabelWidth() {
		return labelWidth;
	}
	
	public int getLabelHeight() {
		return labelHeight;
	}
}
