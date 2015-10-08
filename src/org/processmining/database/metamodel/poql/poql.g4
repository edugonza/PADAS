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

@lexer::members 
{
  @Override
  public void recover(RecognitionException ex) 
  {
    throw new RuntimeException(ex.getMessage()); 
  }
}

@parser::members {
  
  public POQLFunctions poql = new POQLFunctions();

  @Override
  public void notifyErrorListeners(Token offendingToken, String msg, RecognitionException ex)
  {
    throw new RuntimeException(msg); 
  }
  
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
 	
objects returns [List<Object> list, Class type]: OBJECTSOF OPEN_PARENTHESIS t1=things CLOSE_PARENTHESIS { $list = poql.objectsOf($t1.list,$t1.type); $type=SLEXMMObject.class; }
	| t2=allObjects{ $list = $t2.list; $type = $t2.type; }
	| t3=objects f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
 	
cases returns [List<Object> list, Class type] : CASESOF OPEN_PARENTHESIS t1=things CLOSE_PARENTHESIS { $list = poql.casesOf($t1.list,$t1.type); $type=SLEXMMCase.class; }
	| t2=allCases{ $list = $t2.list; $type = $t2.type; }
	| t3=cases f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
events returns [List<Object> list, Class type]: EVENTSOF OPEN_PARENTHESIS t1=things CLOSE_PARENTHESIS { $list = poql.eventsOf($t1.list,$t1.type); $type=SLEXMMEvent.class;}
	| t2=allEvents{ $list = $t2.list; $type = $t2.type; }
	| t3=events f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
classes returns [List<Object> list, Class type]: CLASSESOF OPEN_PARENTHESIS t1=things CLOSE_PARENTHESIS { $list = poql.classesOf($t1.list,$t1.type); $type=SLEXMMClass.class;}
	| t2=allClasses{ $list = $t2.list; $type = $t2.type; }
	| t3=classes f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	; 
	
versions returns [List<Object> list, Class type]: VERSIONSOF OPEN_PARENTHESIS t1=things CLOSE_PARENTHESIS { $list = poql.versionsOf($t1.list,$t1.type); $type=SLEXMMObjectVersion.class;}
	| t2=allVersions{ $list = $t2.list; $type = $t2.type; }
	| VERSIONS_RELATED_TO OPEN_PARENTHESIS t4=versions CLOSE_PARENTHESIS { $list = poql.versionsRelatedTo($t4.list,$t4.type); $type=SLEXMMObjectVersion.class; }
	| t3=versions f=filter_versions { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
activities returns [List<Object> list, Class type]: ACTIVITIESOF OPEN_PARENTHESIS t1=things CLOSE_PARENTHESIS { $list = poql.activitiesOf($t1.list,$t1.type); $type=SLEXMMActivity.class;}
	| t2=allActivities { $list = $t2.list; $type = $t2.type; }
	| t3=activities f=filter { $list = poql.filter($t3.list,$t3.type,$f.conditions); $type = $t3.type; }
	;
	
filter returns [FilterTree conditions]: WHERE f=filter_expression { $conditions = $f.tree; }
	;
	
filter_versions returns [FilterTree conditions]: WHERE f=filter_expression_versions { $conditions = $f.tree; }
	;

filter_expression returns [FilterTree tree]:
	  NOT f0=filter_expression { $tree = poql.createNotNode($f0.tree); }
	| OPEN_PARENTHESIS f1=filter_expression AND f2=filter_expression CLOSE_PARENTHESIS { $tree = poql.createAndNode($f1.tree,$f2.tree); }
	| OPEN_PARENTHESIS f3=filter_expression OR f4=filter_expression CLOSE_PARENTHESIS { $tree = poql.createOrNode($f3.tree,$f4.tree); }
	| f5=filter_terminal { $tree = $f5.tree; }
	;

filter_terminal returns [FilterTree tree]:
	  f5=id EQUAL STRING { $tree = poql.createEqualTerminalFilter($f5.name,$STRING.text,$f5.att); }
	| f6=id DIFFERENT STRING { $tree = poql.createDifferentTerminalFilter($f6.name,$STRING.text,$f6.att); }
	| f7=id EQUAL_OR_GREATER STRING { $tree = poql.createEqualOrGreaterTerminalFilter($f7.name,$STRING.text,$f7.att); }
	| f8=id EQUAL_OR_SMALLER STRING { $tree = poql.createEqualOrSmallerTerminalFilter($f8.name,$STRING.text,$f8.att); }
	| f9=id GREATER STRING { $tree = poql.createGreaterTerminalFilter($f9.name,$STRING.text,$f9.att); }
	| f10=id SMALLER STRING { $tree = poql.createSmallerTerminalFilter($f10.name,$STRING.text,$f10.att); }
	| f11=id CONTAINS STRING { $tree = poql.createContainsTerminalFilter($f11.name,$STRING.text,$f11.att); }
	; 

filter_expression_versions returns [FilterTree tree]:
	  NOT f0=filter_expression_versions { $tree = poql.createNotNode($f0.tree); }
	| OPEN_PARENTHESIS f1=filter_expression_versions AND f2=filter_expression_versions CLOSE_PARENTHESIS { $tree = poql.createAndNode($f1.tree,$f2.tree); }
	| OPEN_PARENTHESIS f3=filter_expression_versions OR f4=filter_expression_versions CLOSE_PARENTHESIS { $tree = poql.createOrNode($f3.tree,$f4.tree); }
	| f5=filter_terminal { $tree = $f5.tree; }
	| f6=filter_terminal_changed { $tree = $f6.tree; }
	;

filter_terminal_changed returns [FilterTree tree]: 
	  f12=id CHANGED (FROM f13=STRING)? (TO f14=STRING)? { $tree = poql.createChangedTerminalFilter($f12.name,$f13.text,$f14.text); }
	; 

id returns [String name, boolean att]: IDATT {$name = $IDATT.text; $att = true;}
	| IDNOATT {$name = $IDNOATT.text; $att = false;}
	; 

allObjects returns [List<Object> list, Class type]: ALLOBJECTS { $list = poql.getAllObjects(); $type=SLEXMMObject.class;};
allCases returns [List<Object> list, Class type]: ALLCASES { $list = poql.getAllCases(); $type=SLEXMMCase.class;};
allEvents returns [List<Object> list, Class type]: ALLEVENTS { $list = poql.getAllEvents(); $type=SLEXMMEvent.class;};
allClasses returns [List<Object> list, Class type]: ALLCLASSES { $list = poql.getAllClasses(); $type=SLEXMMClass.class;};
allVersions returns [List<Object> list, Class type]: ALLVERSIONS { $list = poql.getAllVersions(); $type=SLEXMMObjectVersion.class;};
allActivities returns [List<Object> list, Class type]: ALLACTIVITIES { $list = poql.getAllActivities(); $type=SLEXMMActivity.class;};

CASESOF: C A S E S O F ;
OBJECTSOF: O B J E C T S O F ;
EVENTSOF: E V E N T S O F ;
CLASSESOF: C L A S S E S O F ;
VERSIONSOF: V E R S I O N S O F ;
ACTIVITIESOF: A C T I V I T I E S O F ;
VERSIONS_RELATED_TO: V E R S I O N S R E L A T E D T O ;

ALLOBJECTS: A L L O B J E C T S ;
ALLCASES: A L L C A S E S ;
ALLEVENTS: A L L E V E N T S ;
ALLCLASSES: A L L C L A S S E S ;
ALLVERSIONS: A L L V E R S I O N S ;
ALLACTIVITIES: A L L A C T I V I T I E S ;

OPEN_PARENTHESIS: '(' ;
CLOSE_PARENTHESIS: ')' ;

WHERE: W H E R E ;

EQUAL: '==';
DIFFERENT: '<>';
EQUAL_OR_GREATER: '=>';
EQUAL_OR_SMALLER: '=<';
GREATER: '>';
SMALLER: '<';
CONTAINS: C O N T A I N S ;
AND: A N D;
OR: O R;
NOT: N O T ;
CHANGED: C H A N G E D ;
FROM: F R O M ;
TO: T O ;
STRING: '"' ~('\r' | '\n' | '"')* '"' { setText(getText().substring(1, getText().length() - 1)); };

IDATT : 'at.'IDNOATT { setText(getText().substring(3, getText().length())); };
IDNOATT : [a-z,0-9,_,A-Z]+ ;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');