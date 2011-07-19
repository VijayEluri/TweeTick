package com.riglabs.tweetick;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class TKHistogram extends View {
	ArrayList<String> times;
	ArrayList<String[]> legendList;
	ArrayList<ShapeDrawable> coloredBoxList;
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
	final int margin = 10;
	
	Context context;
	
	public TKHistogram(Context context) {
		super(context);
		isEmpty = false;
		setupChart(null, 0);
	}
	
	public TKHistogram(Context context, ArrayList<String[]> times) {
		super(context);
		this.context = context;
		isEmpty = false;
		
		legendList = new ArrayList<String[]>();
		labelList  = new ArrayList<TextView>();
			
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
			
			coloredBoxList = new ArrayList<ShapeDrawable>();
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
	
		final int boxWidth = 25;
		int startBoxX = startX + boxWidth;
		int endBoxX = startBoxX + boxWidth;
		int startBoxY = startY;

		for (int i = 0; i < times.size(); i++) {
			float fraction = Float.parseFloat(times.get(i)[0]) / totalTime;
			Integer color = 0xff74AC23 * (i + 1);

			final int startLegendX = 10;
			//final int startLegendY = height + ((i + 1) * 20);
			final int endLegendX   = startLegendX + legendWidth;
			//final int endLegendY   = startLegendY + legendHeight;

//			Building Legend
			TextView label = new TextView(this.context); 
			String labelString	 = times.get(i)[1] + ": " + fraction * 100;

			label.setVisibility(VISIBLE);
			label.setTextColor(color);
			label.setWidth(250);
			label.setHeight(endLegendX - startLegendX);
			label.setText(labelString.subSequence(0, 
					labelString.length() - 1));
			labelList.add(label);
//			ShapeDrawable  legendRect = new ShapeDrawable();
//			legendRect.getPaint().setColor(color);
//			legendRect.setBounds(startLegendX, startLegendY, 
//					endLegendX, endLegendY);
//			legendRectList.add(legendRect);
			
//			Draw histogram boxes
			ShapeDrawable sd = new ShapeDrawable(new RectShape());
			sd.getPaint().setColor(color);
			Float boxLength = dia * fraction;
			Log.i("boxLength", boxLength + "");
			startBoxY = height - boxLength.intValue();
			Log.i("endBoxX", endBoxX + "");
			Log.i("boxWidth", boxWidth + "");
			sd.setBounds(startBoxX, startBoxY, endBoxX, height);
			startBoxX += boxWidth;
			endBoxX += boxWidth;
			
			coloredBoxList.add(sd);
		}
	}
	
	private void drawAxes(Canvas canvas) {
		Paint axesColor = new Paint();
		axesColor.setColor(0xffffffff);
		float[] axesPt = new float[48];
		axesPt[0] = startX;
		axesPt[1] = height;
		axesPt[2] = startX;
		axesPt[3] = startY;
		axesPt[4] = startX;
		axesPt[5] = height;
		axesPt[6] = startX + width;
		axesPt[7] = height;
		
		int j = 0;
		for(int i = 8; i < 48; i += 4) {
			final int ticY = height - (++j * (height / 10));
			axesPt[i] = startX - 2;
			axesPt[i + 1] = axesPt[i + 3] = ticY;
			axesPt[i + 2] = startX + 2;
		}
		
		canvas.drawLines(axesPt, axesColor);
	}
	
	protected void onDraw(Canvas canvas) {
		if (isEmpty)
			return;
		
		drawAxes(canvas);
		
		final int startLegendX = 10;
		
		for (int i = 0; i < coloredBoxList.size(); i++) {
			ShapeDrawable box = (ShapeDrawable) coloredBoxList.get(i);
			TextView label       = (TextView) labelList.get(i);
			box.draw(canvas);
			String labelString = (String) label.getText();
			Paint paint = new Paint();
			paint.setColor(label.getCurrentTextColor());
			final int startLegendY = height + ((i + 1) * 15) + 25;
			
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
