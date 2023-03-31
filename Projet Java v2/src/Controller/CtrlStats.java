/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author amout
 */
public class CtrlStats extends JFrame{
    
    public CtrlStats(String appTitle, String chartTitle, int nbReglee, int nbNonReglee){
        
        PieDataset dataset = createDataset(nbReglee, nbNonReglee);
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel); 
    }
    
    
    private PieDataset createDataset(int nbReglee, int nbNonReglee)
    {
        DefaultPieDataset result =  new DefaultPieDataset();
        result.setValue("Leçons réglées", nbReglee);
        result.setValue("Leçons non réglées", nbNonReglee);
        return result;
    }
    
    
    private JFreeChart createChart(PieDataset dataset, String title)
    {
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        
        PiePlot3D plot = (PiePlot3D) chart.getPlot(); 
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
}
