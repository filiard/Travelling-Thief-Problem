import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;

public class Chart extends ApplicationFrame {
    public Chart (String appTitle, String chartTitle){
        super(appTitle);
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(chartTitle, "X", "Y", createDataset(), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension( 640 , 480 ) );
        final XYPlot plot = xyLineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );

        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );

        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset(){
        final XYSeries seria = new XYSeries("Seria");
        seria.add(1,6);
        seria.add(10,4);
        seria.add(5,3);
//        for (int i=0; i<citiesList.size();i++) {
//            seria.add(citiesList.get(i).getX(), citiesList.get(i).y);
//        }
        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(seria);
        return collection;
    }
}
