package org.processmining.database.metamodel.poql;

import org.antlr.v4.runtime.*;
import org.processmining.database.metamodel.poql.poqlParser.ProgContext;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;

public class POQLRunner {

	public QueryResult executeQuery(SLEXMMStorageMetaModel slxmm, String query) {

		System.out.println("Executing query: "+query);
		long start_time = System.currentTimeMillis();
		System.out.println("Start time: "+start_time);
		
		QueryResult qr = new QueryResult();
		
		try {
			
			ANTLRInputStream input = new ANTLRInputStream(query);

			poqlLexer lexer = new poqlLexer(input);

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			poqlParser parser = new poqlParser(tokens);
			parser.poql.setMetaModel(slxmm);
			parser.poql.setCheckerMode(true);
        
			ProgContext progC = parser.prog(); // begin parsing at rule 'prog'
			System.out.println(progC.toStringTree(parser)); // print LISP-style tree
			
			parser.reset();
			
			parser.poql.setCheckerMode(false);
	        progC = parser.prog();
        
			qr.result = progC.result;
			qr.type = progC.type;
        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        long end_time = System.currentTimeMillis();
        double total_time = (double) end_time - start_time;
        double total_time_secs = total_time / 1000.0;
        double total_time_mins = total_time_secs / 60.0;
        System.out.println("End time: "+end_time);
		System.out.println("Total time (millis): "+total_time);
		System.out.println("Total time (seconds): "+total_time_secs);
		System.out.println("Total time (minutes): "+total_time_mins);
		
        return qr;
	}
	
}
