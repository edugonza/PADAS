package org.processmining.redologs.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

public class DiagramComponent extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5543858873672796408L;
	private Scene scene;
	private JComponent myView;
	private LayerWidget mainLayer;
	private LayerWidget interactionLayer;
	private LayerWidget connectionLayer;

	public DiagramComponent() {
		super(new BorderLayout(0, 0));
		
		scene = new Scene();
		myView = scene.createView();
		
		scene.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));
		scene.getActions().addAction(ActionFactory.createZoomAction());
		
		JScrollPane scrollPane = new JScrollPane(myView);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.add(scene.createSatelliteView(), BorderLayout.WEST);
		
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
			//widget.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer, new SceneConnectProvider()));
			widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
			//widget.getActions().addAction(ActionFactory.createInplaceEditorAction(new RenameEditor()));
			//widget.getActions().addAction(ActionFactory.createSelectAction(new EatEventSelectProvider()));
			mainLayer.addChild(widget);
		}
		
	}
}
