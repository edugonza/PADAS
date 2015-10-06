package org.processmining.database.metamodel.poql;

import org.antlr.v4.runtime.*;
import org.processmining.database.metamodel.poql.poqlParser.ProgContext;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;

public class POQLRunner 
{
//    public static void main( String[] args) throws Exception 
//    {

	public QueryResult executeQuery(SLEXMMStorageMetaModel slxmm, String query) {
	
		QueryResult qr = new QueryResult();
		
        ANTLRInputStream input = new ANTLRInputStream(query);

        poqlLexer lexer = new poqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        poqlParser parser = new poqlParser(tokens);
        parser.poql.setMetaModel(slxmm);
        ProgContext progC = parser.prog(); // begin parsing at rule 'prog'
        System.out.println(progC.toStringTree(parser)); // print LISP-style tree
        
        qr.result = progC.result;
        qr.type = progC.type;
        return qr;
	}
	
//    }
}
