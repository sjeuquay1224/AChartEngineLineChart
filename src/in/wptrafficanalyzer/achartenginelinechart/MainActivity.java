package in.wptrafficanalyzer.achartenginelinechart;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private String[] mMonth = new String[] {
				"Jan", "Feb" , "Mar", "Apr", "May", "Jun",
				"Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
			};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Getting reference to the button btn_chart
        Button btnChart = (Button) findViewById(R.id.btn_chart);
        
        // Defining click event listener for the button btn_chart
        OnClickListener clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Draw the Income vs Expense Chart
				openChart();				
			}
		};
		
		// Setting event click listener for the button btn_chart of the MainActivity layout
		btnChart.setOnClickListener(clickListener);
        
    }
    
    private void openChart(){
    	int[] x = { 1,2,3,4,5,6,7,8 };
    	int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};
    	int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
    	
    	// Creating an  XYSeries for Income
    	XYSeries incomeSeries = new XYSeries("Income");
    	// Creating an  XYSeries for Income
    	XYSeries expenseSeries = new XYSeries("Expense");
    	// Adding data to Income and Expense Series
    	for(int i=0;i<x.length;i++){
    		incomeSeries.add(x[i], income[i]);
    		expenseSeries.add(x[i],expense[i]);
    	}
    	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Income Series to the dataset
    	dataset.addSeries(incomeSeries);
    	// Adding Expense Series to dataset
    	dataset.addSeries(expenseSeries);    	
    	
    	
    	// Creating XYSeriesRenderer to customize incomeSeries
    	XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
    	incomeRenderer.setColor(Color.WHITE);
    	incomeRenderer.setPointStyle(PointStyle.CIRCLE);
    	incomeRenderer.setFillPoints(true);
    	incomeRenderer.setLineWidth(2);
    	incomeRenderer.setDisplayChartValues(true);
    	
    	// Creating XYSeriesRenderer to customize expenseSeries
    	XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
    	expenseRenderer.setColor(Color.YELLOW);
    	expenseRenderer.setPointStyle(PointStyle.CIRCLE);
    	expenseRenderer.setFillPoints(true);
    	expenseRenderer.setLineWidth(2);
    	expenseRenderer.setDisplayChartValues(true);
    	
    	
    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
    	multiRenderer.setXLabels(0);
    	multiRenderer.setChartTitle("Income vs Expense Chart");
    	multiRenderer.setXTitle("Year 2012");
    	multiRenderer.setYTitle("Amount in Dollars");
    	multiRenderer.setZoomButtonsVisible(true);    	    	
    	for(int i=0;i<x.length;i++){
    		multiRenderer.addXTextLabel(i+1, mMonth[i]);    		
    	}    	
    	
    	// Adding incomeRenderer and expenseRenderer to multipleRenderer
    	// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
    	// should be same
    	multiRenderer.addSeriesRenderer(incomeRenderer);
    	multiRenderer.addSeriesRenderer(expenseRenderer);
    	
    	// Creating an intent to plot line chart using dataset and multipleRenderer
    	Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);
    	
    	// Start Activity
    	startActivity(intent);
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}