package org.processmining.redologs.ui;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.InplaceEditorProvider;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.processmining.database.metamodel.MetaModel;
import org.processmining.database.metamodel.MetaModelPopulator;
import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.openslex.SLEXStorageImpl;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.EventAttributeColumn;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.ProgressHandler;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceIDPattern;
import org.processmining.redologs.oracle.OracleLogMinerExtractor;
import org.processmining.redologs.ui.components.AskNameDialog;
import org.processmining.redologs.ui.components.AskYesNoDialog;
import org.processmining.redologs.ui.components.CustomInternalFrame;
import org.processmining.redologs.ui.components.DataModelList;
import org.processmining.redologs.ui.components.DataModelTree;
import org.processmining.redologs.ui.components.HistogramPanel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JProgressBar;

import java.awt.Component;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;

public class FrameVisualAPITest extends CustomInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6031117937878835756L;
	
	private SLEXMMStorageMetaModel mmstrg;
	
	private Scene scene;
	private JComponent myView;
	
	private LayerWidget mainLayer;
	private LayerWidget interactionLayer;
	private LayerWidget connectionLayer;
	
	public FrameVisualAPITest(SLEXMMStorageMetaModel mmstrg) {
		super("MetaModel Inspector");
		this.mmstrg = mmstrg;
		
		//BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		setBounds(715, 30, 820, 670);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		scene = new Scene();
		myView = scene.createView();
		
		scene.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));
		scene.getActions().addAction(ActionFactory.createZoomAction());
		
		JScrollPane scrollPane = new JScrollPane(myView);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		getContentPane().add(scene.createSatelliteView(), BorderLayout.WEST);
		
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);
		
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);
		
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);
	}

	private class CreateProvider implements SelectProvider {

		@Override
		public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2) {
			return false;
		}

		@Override
		public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2) {
			return true;
		}

		@Override
		public void select(Widget relatedWidget, Point localLocation, boolean invertSelection) {
			Widget widget = new LabelWidget(scene,"Rename me");
			widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
			widget.setBorder(BorderFactory.createRoundedBorder(10, 10, Color.getHSBColor(50, 50, 155), Color.YELLOW));
			widget.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer, new SceneConnectProvider()));
			widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
			widget.getActions().addAction(ActionFactory.createInplaceEditorAction(new RenameEditor()));
			widget.getActions().addAction(ActionFactory.createSelectAction(new EatEventSelectProvider()));
			mainLayer.addChild(widget);
		}
		
	}
	
	private class EatEventSelectProvider implements SelectProvider {

		@Override
		public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2) {
			return false;
		}

		@Override
		public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2) {
			return true;
		}

		@Override
		public void select(Widget arg0, Point arg1, boolean arg2) {
		}
		
	}
	
	private class RenameEditor implements TextFieldInplaceEditor {

		@Override
		public String getText(Widget widget) {
			return ((LabelWidget) widget).getLabel();
		}

		@Override
		public boolean isEnabled(Widget widget) {
			return true;
		}

		@Override
		public void setText(Widget widget, String text) {
			((LabelWidget) widget).setLabel(text);
		}
		
	}
	
	private class SceneConnectProvider implements ConnectProvider {

		@Override
		public void createConnection(Widget sourceWidget, Widget targetWidget) {
			ConnectionWidget connection = new ConnectionWidget(scene);
			connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
			connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
			connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
			connection.setRouter(RouterFactory.createOrthogonalSearchRouter(mainLayer, connectionLayer));
			connectionLayer.addChild(connection);
		}

		@Override
		public boolean hasCustomTargetWidgetResolver(Scene arg0) {
			return false;
		}

		@Override
		public boolean isSourceWidget(Widget sourceWidget) {
			return sourceWidget != null && sourceWidget instanceof LabelWidget ? true : false;
		}

		@Override
		public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) {
			return sourceWidget != targetWidget && targetWidget instanceof LabelWidget ? ConnectorState.ACCEPT : ConnectorState.REJECT;
		}

		@Override
		public Widget resolveTargetWidget(Scene arg0, Point arg1) {
			return null;
		}
		
	}
}