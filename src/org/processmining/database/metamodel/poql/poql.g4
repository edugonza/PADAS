// Define a grammar

grammar poql;

@header {
  import java.util.List;
  import org.processmining.openslex.metamodel.SLEXMMCase;
  import org.processmining.openslex.metamodel.SLEXMMObject;
  import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
  import org.processmining.openslex.metamodel.SLEXMMEvent;
  import org.processmining.openslex.metamodel.SLEXMMActivity;
  import org.processmining.openslex.metamodel.SLEXMMCase;
  import org.processmining.openslex.metamodel.SLEXMMClass; 
}

@parser::members {
  
  public POQLFunctions poql = new POQLFunctions();
  
}

prog returns [List<Object> result, Class type]: t=things { $result = $t.list; $type = $t.type; }
;

things returns [List<Object> list, Class type]: t1=cases { $list = $t1.list; $type = $t1.type; }
	| t2=objects { $list = $t2.list; $type = $t2.type; }
	| t3=events { $list = $t3.list; $type = $t3.type; }
	| t4=classes { $list = $t4.list; $type = $t4.type; }
	| t5=versions { $list = $t5.list; $type = $t5.type; }
	| t6=activities { $list = $t6.list; $type = $t6.type; }
	;
 	
things_no_object returns [List<Object> list, Class type]: t1=cases { $list = $t1.list; $type = $t1.type; }
	| t2=events { $list = $t2.list; $type = $t2.type; }
	| t3=classes { $list = $t3.list; $type = $t3.type; }
	| t4=versions { $list = $t4.list; $type = $t4.type; }
	| t5=activities { $list = $t5.list; $type = $t5.type; }
	;
things_no_case returns [List<Object> list, Class type]: t1=objects { $list = $t1.list; $type = $t1.type; }
	| t2=events { $list = $t2.list; $type = $t2.type; }
	| t3=classes { $list = $t3.list; $type = $t3.type; }
	| t4=versions { $list = $t4.list; $type = $t4.type; }
	| t5=activities { $list = $t5.list; $type = $t5.type; }
	;
things_no_event returns [List<Object> list, Class type]: t1=cases { $list = $t1.list; $type = $t1.type; }
	| t2=objects { $list = $t2.list; $type = $t2.type; }
	| t3=classes { $list = $t3.list; $type = $t3.type; }
	| t4=versions { $list = $t4.list; $type = $t4.type; }
	| t5=activities { $list = $t5.list; $type = $t5.type; }
	;
things_no_class returns [List<Object> list, Class type]: t1=cases { $list = $t1.list; $type = $t1.type; }
	| t2=objects { $list = $t2.list; $type = $t2.type; }
	| t3=events { $list = $t3.list; $type = $t3.type; }
	| t4=versions { $list = $t4.list; $type = $t4.type; }
	| t5=activities { $list = $t5.list; $type = $t5.type; }
	;
things_no_version returns [List<Object> list, Class type]: t1=cases { $list = $t1.list; $type = $t1.type; }
	| t2=objects { $list = $t2.list; $type = $t2.type; }
	| t3=events { $list = $t3.list; $type = $t3.type; }
	| t4=classes { $list = $t4.list; $type = $t4.type; }
	| t5=activities { $list = $t5.list; $type = $t5.type; }
	;
things_no_activity returns [List<Object> list, Class type]: t1=cases { $list = $t1.list; $type = $t1.type; }
	| t2=objects { $list = $t2.list; $type = $t2.type; }
	| t3=events { $list = $t3.list; $type = $t3.type; }
	| t4=classes { $list = $t4.list; $type = $t4.type; }
	| t5=versions { $list = $t5.list; $type = $t5.type; }
	;
 	
objects returns [List<Object> list, Class type]: OBJECTSOF OPEN_FUNCTION t1=things_no_object CLOSE_FUNCTION { $list = poql.objectsOf($t1.list,$t1.type); $type=SLEXMMObject.class; }
	| t2=allObjects{ $list = $t2.list; $type = $t2.type; }
	| t3=objects f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
 	
cases returns [List<Object> list, Class type] : CASESOF OPEN_FUNCTION t1=things_no_case CLOSE_FUNCTION { $list = poql.casesOf($t1.list,$t1.type); $type=SLEXMMCase.class; }
	| t2=allCases{ $list = $t2.list; $type = $t2.type; }
	| t3=cases f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
events returns [List<Object> list, Class type]: EVENTSOF OPEN_FUNCTION t1=things_no_event CLOSE_FUNCTION { $list = poql.eventsOf($t1.list,$t1.type); $type=SLEXMMEvent.class;}
	| t2=allEvents{ $list = $t2.list; $type = $t2.type; }
	| t3=events f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
classes returns [List<Object> list, Class type]: CLASSESOF OPEN_FUNCTION t1=things_no_class CLOSE_FUNCTION { $list = poql.classesOf($t1.list,$t1.type); $type=SLEXMMClass.class;}
	| t2=allClasses{ $list = $t2.list; $type = $t2.type; }
	| t3=classes f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	; 
	
versions returns [List<Object> list, Class type]: VERSIONSOF OPEN_FUNCTION t1=things_no_version CLOSE_FUNCTION { $list = poql.versionsOf($t1.list,$t1.type); $type=SLEXMMObjectVersion.class;}
	| t2=allVersions{ $list = $t2.list; $type = $t2.type; }
	| t3=versions f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
activities returns [List<Object> list, Class type]: ACTIVITIESOF OPEN_FUNCTION t1=things_no_activity CLOSE_FUNCTION { $list = poql.activitiesOf($t1.list,$t1.type); $type=SLEXMMActivity.class;}
	| t2=allActivities { $list = $t2.list; $type = $t2.type; }
	| t3=activities f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
filter returns [FilterTree conditions]: WHERE f=filter_expression { $conditions = $f.tree; }
	;

filter_expression returns [FilterTree tree]: f1=filter_expression AND f2=filter_expression { $tree = poql.createAndNode($f1.tree,$f2.tree); }
	| f3=filter_expression OR f4=filter_expression { $tree = poql.createOrNode($f3.tree,$f4.tree); }
	| f5=id EQUAL STRING { $tree = poql.createEqualTerminalFilter($f5.name,$STRING.text,$f5.att); }
	| f6=id DIFFERENT STRING { $tree = poql.createDifferentTerminalFilter($f6.name,$STRING.text,$f6.att); }
	| f7=id EQUAL_OR_GREATER STRING { $tree = poql.createEqualOrGreaterTerminalFilter($f7.name,$STRING.text,$f7.att); }
	| f8=id EQUAL_OR_SMALLER STRING { $tree = poql.createEqualOrSmallerTerminalFilter($f8.name,$STRING.text,$f8.att); }
	| f9=id GREATER STRING { $tree = poql.createGreaterTerminalFilter($f9.name,$STRING.text,$f9.att); }
	| f10=id SMALLER STRING { $tree = poql.createSmallerTerminalFilter($f10.name,$STRING.text,$f10.att); }
	| f11=id CONTAINS STRING { $tree = poql.createContainsTerminalFilter($f11.name,$STRING.text,$f11.att); }
	;

id returns [String name, boolean att]: IDATT {$name = $IDATT.text; $att = true;}
	| IDNOATT {$name = $IDNOATT.text; $att = false;}
	; 

allObjects returns [List<Object> list, Class type]: 'allObjects' { $list = poql.getAllObjects(); $type=SLEXMMObject.class;};
allCases returns [List<Object> list, Class type]: 'allCases' { $list = poql.getAllCases(); $type=SLEXMMCase.class;};
allEvents returns [List<Object> list, Class type]: 'allEvents' { $list = poql.getAllEvents(); $type=SLEXMMEvent.class;};
allClasses returns [List<Object> list, Class type]: 'allClasses' { $list = poql.getAllClasses(); $type=SLEXMMClass.class;};
allVersions returns [List<Object> list, Class type]: 'allVersions' { $list = poql.getAllVersions(); $type=SLEXMMObjectVersion.class;};
allActivities returns [List<Object> list, Class type]: 'allActivities' { $list = poql.getAllActivities(); $type=SLEXMMActivity.class;};

CASESOF: 'casesOf' ;
OBJECTSOF: 'objectsOf' ;
EVENTSOF: 'eventsOf';
CLASSESOF: 'classesOf';
VERSIONSOF: 'versionsOf';
ACTIVITIESOF: 'activitiesOf';

OPEN_FUNCTION: '(' ;
CLOSE_FUNCTION: ')' ;

WHERE: 'where';

EQUAL: '==';
DIFFERENT: '<>';
EQUAL_OR_GREATER: '=>';
EQUAL_OR_SMALLER: '=<';
GREATER: '>';
SMALLER: '<';
CONTAINS: 'contains';
AND: 'AND';
OR: 'OR';
STRING: '"' ~('\r' | '\n' | '"')* '"' { setText(getText().substring(1, getText().length() - 1)); };

IDATT : 'at.'IDNOATT { setText(getText().substring(3, getText().length())); };
IDNOATT : [a-z,0-9,_,A-Z]+ ;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
