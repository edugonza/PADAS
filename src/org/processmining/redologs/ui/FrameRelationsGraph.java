package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.GraphEdge;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class FrameRelationsGraph extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3381778662806389348L;
	private Graph<GraphNode, GraphEdge> graph;
	private VisualizationViewer<GraphNode, GraphEdge> vv;
	private GridBagConstraints gbc_vv;
	
	public FrameRelationsGraph() {
		super("Relations Graph");
		final JPanel graphPanel = new JPanel();
		graphPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.setBounds(404, 267, 225, 132);
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		final JPanel panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		final JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		JButton btnReload = new JButton("Reload");
		panel_1.add(btnReload);
		btnReload.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReload.setVerticalAlignment(SwingConstants.TOP);

		JButton btnRepaint = new JButton("Reset");
		btnRepaint.setVerticalAlignment(SwingConstants.TOP);
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vv.getRenderContext().getMultiLayerTransformer()
						.getTransformer(Layer.VIEW)
						.setScale(1.0, 1.0, vv.getCenter());
				vv.getRenderContext().getMultiLayerTransformer()
						.getTransformer(Layer.LAYOUT)
						.setScale(1.0, 1.0, vv.getCenter());
			}
		});
		panel_1.add(btnRepaint);
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				graphPanel.removeAll();

				Thread relationsThread = new Thread(new Runnable() {

					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						// explorer = new OracleRelationsExplorer();

						DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
						
						if (model != null) {
							String title = "Relations Graph - Data Model: "+model.getName();
							
							FrameRelationsGraph.this.setTitle(title);
							
							graph = OracleRelationsExplorer.generateRelationsGraphPKAndFK(model);

							vv = OracleRelationsExplorer.getViewer(graph,
								"Relations from Foreign and Primary Keys");

							gbc_vv = new GridBagConstraints();
							gbc_vv.fill = GridBagConstraints.BOTH;
							gbc_vv.gridx = 0;
							gbc_vv.gridy = 0;

							vv.setSize(graphPanel.getSize());

							GraphZoomScrollPane graphPanel2 = new GraphZoomScrollPane(
									vv);
							graphPanel.add(graphPanel2, gbc_vv);
							graphPanel.revalidate();
						}
						progressBar.setIndeterminate(false);
					}
				});

				relationsThread.start();
			}
		});
		this.getContentPane()
				.add(graphPanel, BorderLayout.CENTER);
		graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
	}
}
