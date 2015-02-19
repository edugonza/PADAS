package org.processmining.redologs.ui.components;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.util.PaintAlpha;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventResultSet;
import org.processmining.openslex.SLEXStorage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class HistogramPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7051681320906718981L;
	
	private TimeSeries tseries;
	private JFreeChart chart;
	private ChartPanel cpanel;
	private TimeSeriesCollection tseriesCol;
	private List<ChangeListener> listeners = new Vector<>();

	public HistogramPanel() {
		super();
		tseries = new TimeSeries("Events per time");
		tseriesCol = new TimeSeriesCollection();
		tseriesCol.addSeries(tseries);
		chart = ChartFactory.createXYBarChart("", "", true, "", tseriesCol);
		chart.removeLegend();
		//chart = ChartFactory.createTimeSeriesChart("", "", "", tseriesCol,false,false,false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {10, 0, 10};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		cpanel = new ChartPanel(chart);
		GridBagConstraints gbc_cpanel = new GridBagConstraints();
		gbc_cpanel.fill = GridBagConstraints.BOTH;
		gbc_cpanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_cpanel.gridx = 0;
		gbc_cpanel.gridy = 1;
		add(cpanel, gbc_cpanel);
		cpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		cpanel.setMinimumDrawHeight(30);
		cpanel.setMinimumDrawWidth(30);
		cpanel.setMaximumDrawHeight(4000);
		cpanel.setMaximumDrawWidth(4000);		
		
		setSize(400,150);
		
		Color bc = Color.DARK_GRAY;
		chart.getPlot().setBackgroundPaint(bc);
		chart.setBackgroundPaint(bc);
		
		XYPlot plot = (XYPlot) chart.getPlot();
		Color c = Color.LIGHT_GRAY;
		plot.getRangeAxis().setTickMarkPaint(c);
		plot.getRangeAxis().setAxisLinePaint(c);
		plot.getRangeAxis().setTickLabelPaint(c);
		plot.getRangeAxis().setLabelPaint(c);
		
		plot.getDomainAxis().setTickMarkPaint(c);
		plot.getDomainAxis().setAxisLinePaint(c);
		plot.getDomainAxis().setTickLabelPaint(c);
		plot.getDomainAxis().setLabelPaint(c);
				
		plot.getRenderer().setSeriesPaint(0, c);
		
		cpanel.setRangeZoomable(false);
		cpanel.setDomainZoomable(true);
		Color zoomColor = Color.GRAY;
		cpanel.setZoomFillPaint(new Color(zoomColor.getRed(), zoomColor.getGreen(), zoomColor.getBlue(), 128));
		
		plot.getDomainAxis().addChangeListener(new AxisChangeListener() {
			
			@Override
			public void axisChanged(AxisChangeEvent arg0) {
				notifyDateRangeChangeListeners();
			}
		});
	}
	
	public void setSize(int width, int height) {
		cpanel.setPreferredSize(new Dimension(width, height));
	}
	
	public void setData(SLEXEventCollection ec, SimpleDateFormat dateFormat) {
		tseries.clear();
		try {
			List<SLEXAttribute> orderAttributes = new Vector<>();
			//orderAttributes
			orderAttributes.add(ec.getStorage().findAttribute("COMMON", "ORDER"));
			SLEXEventResultSet erset = ec.getEventsResultSetOrderedBy(orderAttributes);
			SLEXEvent e = null;
			SLEXAttribute timeStampAttr = ec.getStorage().findAttribute("COMMON", "TIMESTAMP");
			//DateFormat.
			while ((e = erset.getNext()) != null) {
				Hashtable<SLEXAttribute, SLEXAttributeValue> attributes = e.getAttributeValues();
				SLEXAttributeValue timestampAttrVal = attributes.get(timeStampAttr);
				if (timestampAttrVal != null) {
					String val = timestampAttrVal.getValue();
					Date dateVal = dateFormat.parse(val);
					final Second sec = new Second(dateVal);
					
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					      // Here, we can safely update the GUI
					      // because we'll be called from the
					      // event dispatch thread
					    	TimeSeriesDataItem item = tseries.getDataItem(sec);
					    	if (item == null) {
								item = new TimeSeriesDataItem(sec , 1);
							} else {
								Number value = item.getValue();
								Number newVal = new Integer(value.intValue()+1);
								item.setValue(newVal);
							}
					    	tseries.addOrUpdate(item);
					    }
					});
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Date[] getSelectedRange() {
		Date[] dates = new Date[2];
		
		double domainLow = chart.getXYPlot().getDomainAxis().getLowerBound();
        double domainUp = chart.getXYPlot().getDomainAxis().getUpperBound();
        
        dates[0] = new Date((long) domainLow);
        dates[1] = new Date((long) domainUp);
        
        return dates;
	}

	public void addDateRangeChangeListener(ChangeListener changeListener) {
		listeners.add(changeListener);
	}
	
	public void removeDateRangeChangeListener(ChangeListener changeListener) {
		listeners.remove(changeListener);
	}
	
	private void notifyDateRangeChangeListeners() {
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l: listeners) {
			l.stateChanged(e);
		}
	}
}
