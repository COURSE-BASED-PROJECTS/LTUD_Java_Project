package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author TVD
 */
public class TestBarChart {

    public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DÂN SỐ VIỆT NAM",
                "Năm", "Số người",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private static CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(68000000, "Số người", "1990");
        dataset.addValue(80000000, "Số người", "2000");
        dataset.addValue(88000000, "Số người", "2010");
        dataset.addValue(95000000, "Số người", "2020");
        return dataset;
    }

    public static void main(String[] args) {
           
        
       

    }

}