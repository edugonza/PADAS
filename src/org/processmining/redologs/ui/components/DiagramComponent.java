package org.processmining.redologs.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.EditProvider;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.vmd.VMDFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;
import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMClass;
import org.processmining.openslex.metamodel.SLEXMMClassResultSet;
import org.processmining.openslex.metamodel.SLEXMMDataModel;
import org.processmining.openslex.metamodel.SLEXMMRelationship;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;

public class DiagramComponent extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5543858873672796408L;
	
	private VMDGraphScene scene;
	private JComponent myView;
	private SLEXMMDataModel dm;
	private SLEXMMStorageMetaModel mmstrg;
	
	private static int nodeID = 1;
    private static int edgeID = 1;
	
	public DiagramComponent() {
		super(new BorderLayout(0, 0));
		
		scene = new VMDGraphScene (VMDFactory.getNetBeans60Scheme ());
		
		myView = scene.createView();
		
		JScrollPane scrollPane = new JScrollPane(myView);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.add(scene.createSatelliteView(), BorderLayout.SOUTH);
		
		scene.getActions ().addAction (ActionFactory.createEditAction (new EditProvider() {
            public void edit (Widget widget) {
                scene.layoutScene ();
            }
        }));
		
	}

    static String createNode (VMDGraphScene scene, int x, int y, Image image, String name, String type, List<Image> glyphs) {
        String nodeID = "node" + DiagramComponent.nodeID ++;
        VMDNodeWidget widget = (VMDNodeWidget) scene.addNode (nodeID);
        widget.setPreferredLocation (new Point (x, y));
        widget.setNodeProperties (image, name, type, glyphs);
        scene.addPin (nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
        return nodeID;
    }

    static void createPin (VMDGraphScene scene, String nodeID, String pinID, Image image, String name, String type) {
        VMDPinWidget pinWidget = ((VMDPinWidget) scene.addPin (nodeID, pinID));
        pinWidget.setProperties (name, null);
    }
	
    static void createEdge (VMDGraphScene scene, String name, String sourceNodeID, String targetNodeID) {
        String edgeID = "edge" + DiagramComponent.edgeID ++;
        Widget edge = scene.addEdge (edgeID);
        edge.setToolTipText(name);
        scene.setEdgeSource (edgeID, sourceNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
        scene.setEdgeTarget (edgeID, targetNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
    }
    
	public void setDataModel(SLEXMMDataModel dm) {
		
		this.dm = dm;
		this.mmstrg = dm.getStorage();
		
		HashMap<String,SLEXMMClass> widgetIdToClassMap = new HashMap<>();
		HashMap<Integer,String> classToWidgetIdMap = new HashMap<>();
		
		SLEXMMClassResultSet crset = mmstrg.getClassesForDataModel(dm);
		SLEXMMClass c = null;
		
		while ((c = crset.getNext()) != null) {
			
			String nodeId = createNode (scene, 100, 100, null, c.getName()+" ("+c.getId()+")", "Class", null);
			
			widgetIdToClassMap.put(nodeId, c);
			classToWidgetIdMap.put(c.getId(),nodeId);
			
			List<SLEXMMAttribute> attrs = mmstrg.getAttributesForClass(c);
			for (SLEXMMAttribute at: attrs) {
				createPin(scene, nodeId, String.valueOf(at.getId()), null, at.getName(), "Attribute");
			}
			
		}
		
		crset = mmstrg.getClassesForDataModel(dm);
		c = null;
		
		while ((c = crset.getNext()) != null) {
			List<SLEXMMRelationship> rels = mmstrg.getRelationshipsForClass(c);
			
			for (SLEXMMRelationship rel: rels) {
				
				String source = classToWidgetIdMap.get(rel.getSourceClassId());
				String target = classToWidgetIdMap.get(rel.getTargetClassId());
				
				createEdge (scene, rel.getName()+" ("+rel.getId()+")", source, target);
				
			}
		}
		
		scene.layoutScene();
		
	}
	
//	private class CreateProvider implements SelectProvider {
//
//		@Override
//		public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2) {
//			return false;
//		}
//
//		@Override
//		public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2) {
//			return true;
//		}
//
//		@Override
//		public void select(Widget relatedWidget, Point localLocation, boolean invertSelection) {
//			Widget widget = new LabelWidget(scene,"Rename me");
//			widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
//			widget.setBorder(BorderFactory.createRoundedBorder(10, 10, Color.getHSBColor(50, 50, 155), Color.YELLOW));
//			//widget.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer, new SceneConnectProvider()));
//			widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
//			//widget.getActions().addAction(ActionFactory.createInplaceEditorAction(new RenameEditor()));
//			//widget.getActions().addAction(ActionFactory.createSelectAction(new EatEventSelectProvider()));
//			mainLayer.addChild(widget);
//		}
//		
//	}
}
