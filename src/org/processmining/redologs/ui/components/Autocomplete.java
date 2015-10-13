package org.processmining.redologs.ui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import org.processmining.database.metamodel.poql.POQLFunctions;
import org.processmining.database.metamodel.poql.POQLRunner;
import org.processmining.database.metamodel.poql.QueryResult;
import org.processmining.database.metamodel.poql.SuggestionsResult;
import org.processmining.database.metamodel.poql.poqlParser;

public class Autocomplete implements DocumentListener {

  private static enum Mode {
    INSERT,
    COMPLETION
  };

  private JTextField textField;
  private List<String> keywords;
  private Mode mode = Mode.INSERT;
  //private int n = 0;
  //private int w = 0;
  
  private boolean shift = false;
  private int shift_count = 0;

  public Autocomplete(JTextField textField, List<String> keywords) {
    this.textField = textField;
    setKeywords(keywords);
  }

  public void setKeywords(List<String> keywords) {
	this.keywords = keywords;
	if (this.keywords != null) {
		Collections.sort(keywords);
	} else {
		this.keywords = new ArrayList<>(); 
	}
  }
  
  @Override
  public void changedUpdate(DocumentEvent ev) {
	  shift = false;
	  shift_count = 0;
  }

  @Override
  public void removeUpdate(DocumentEvent ev) {
	  shift = false;
	  shift_count = 0;
  }

  @Override
  public void insertUpdate(DocumentEvent ev) {
//    if (ev.getLength() != 1)
//      return;
//
//    int pos = ev.getOffset();
//    String content = null;
//    try {
//      content = textField.getText(0, pos + 1);
//    } catch (BadLocationException e) {
//      e.printStackTrace();
//    }

//    // Find where the word starts
//    int w;
//    for (w = pos; w >= 0; w--) {
//      if (!Character.isLetter(content.charAt(w))) {
//        break;
//      }
//    }
//
//    // Too few chars
//    if (pos - w < 2)
//      return;

    
    
//    String prefix = content.substring(w + 1).toLowerCase();
    
//    SuggestionsResult suggRes = null;
//    try {
//    	POQLRunner runner = new POQLRunner();
//    	suggRes = runner.executeQueryForSuggestions(content);
//    } catch (Exception e) {
//    	
//    }
//    
//    setKeywords(suggRes.suggestions);
//    
//    int w = suggRes.initOffendingToken-1;
//    this.w = w;
//    String prefix = content.substring(w + 1).toUpperCase();
//    
//    int n = Collections.binarySearch(keywords, prefix);
//    
//    if (n < 0 && -n <= keywords.size()) {
//      String match = keywords.get(-n - 1);
//      if (match.startsWith(prefix)) {
//        // A completion is found
//        String completion = match.substring(pos - w);
//        // We cannot modify Document from within notification,
//        // so we submit a task that does the change later
//        SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
//      }
//    } else {
//      // Nothing found
//      mode = Mode.INSERT;
//    }
//    
//    this.n = n;
	  
	  shift = false;
	  shift_count = 0;
	  
  }

  public class CommitAction extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = 5794543109646743416L;

    @Override
    public void actionPerformed(ActionEvent ev) {
     // if (mode == Mode.COMPLETION) {
        int pos = textField.getSelectionEnd();
        StringBuffer sb = new StringBuffer(textField.getText());
        sb.insert(pos, " ");
        textField.setText(sb.toString());
        textField.setCaretPosition(pos+1);
        //mode = Mode.INSERT;
        shift = false;
        
        System.out.println("COMMIT!!!!!");
      //} else {

      //}
    }
  }
  
  public class ShiftAction extends AbstractAction {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 5794543109646743416L;
	    
	    @Override
	    public void actionPerformed(ActionEvent ev) {
//	      if (mode == Mode.COMPLETION) {
//	        int pos = textField.getSelectionStart();
//	        int w = Autocomplete.this.w;
//	        String prefix = textField.getText().substring(w+1, pos).toUpperCase();
//	        
//	        n--;
//	        
//	        if (n < 0 && -n <= keywords.size()) {
//	            String match = keywords.get(-n - 1);
//	            if (!match.startsWith(prefix)) {
//	            	n = Collections.binarySearch(keywords, prefix);
//	            	match = keywords.get(-n - 1);
//	            }
//	            if (match.startsWith(prefix)) {
//	              // A completion is found
//	              StringBuffer sb = new StringBuffer(textField.getText().substring(0, pos));
//	              //sb.insert(pos, " ");
//	              textField.setText(sb.toString());
//	                
//	              String completion = match.substring(pos - w - 1);
//	              // We cannot modify Document from within notification,
//	              // so we submit a task that does the change later
//	              SwingUtilities.invokeLater(new CompletionTask(completion, pos));
//	            } else {
//	            	
//	            }
//	          } else {
//	            // Nothing found
//	            mode = Mode.INSERT;
//	          }
//
//	        System.out.println("SHIFT!!!!!");
//	      } else {
//
//	      }
	    	int pos = textField.getCaretPosition()-1;
	    	String content = null;
	        try {
	          content = textField.getText(0, pos + 1);
	        } catch (BadLocationException e) {
	          e.printStackTrace();
	        }
	        
	        SuggestionsResult suggRes = null;
	        try {
	        	POQLRunner runner = new POQLRunner();
	        	suggRes = runner.executeQueryForSuggestions(content);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	        setKeywords(suggRes.suggestions);
	        
	        int w = suggRes.initOffendingToken-1;
	        //this.w = w;
	        String prefix = content.substring(w + 1).toUpperCase();
	        
	        int n = Collections.binarySearch(keywords, prefix);
	        
	        if (shift) {
	        	n=n-shift_count;
	        	shift_count++;
	        } else {
	        	shift=true;
	        	shift_count=1;
	        }
	        
	        if (n < 0 && -n <= keywords.size()) {
	          String match = keywords.get(-n - 1);
	          if (match.startsWith(prefix)) {
	            // A completion is found
	            String completion = match.substring(pos - w);
	            // We cannot modify Document from within notification,
	            // so we submit a task that does the change later
	            SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
	          } else {
	        	shift=false;
	        	SwingUtilities.invokeLater(new HighlightTask(suggRes.initOffendingToken,suggRes.endOffendingToken));
	          }
	        } else {
	        	shift=false;
	        	SwingUtilities.invokeLater(new HighlightTask(suggRes.initOffendingToken,suggRes.endOffendingToken));
	          }
	        
	        //this.n = n;
	    	
	    }
	  }

  private class CompletionTask implements Runnable {
    private String completion;
    private int position;

    CompletionTask(String completion, int position) {
      this.completion = completion;
      this.position = position;
    }

    public void run() {
//      StringBuffer sb = new StringBuffer(textField.getText());
      int selectionStart = textField.getSelectionStart();
      int selectionEnd = textField.getSelectionEnd();
      StringBuffer sb = new StringBuffer(
    		  textField.getText().substring(0, selectionStart)+
    		  textField.getText().substring(selectionEnd));
      sb.insert(position, completion);
      textField.getDocument().removeDocumentListener(Autocomplete.this);
      textField.setText(sb.toString());
      textField.setCaretPosition(position + completion.length());
      textField.moveCaretPosition(position);
      //mode = Mode.COMPLETION;
      textField.getDocument().addDocumentListener(Autocomplete.this);
    }
  }
  
  private class HighlightTask implements Runnable {
	    private int start;
	    private int end;
	    private Color HILIT_COLOR = Color.pink;
	    private Highlighter hilit;
	    private Highlighter.HighlightPainter painter;

	    HighlightTask(int start, int end) {
	      this.start = start;
	      this.end = end;
	    }

	    public void run() {
//	      int selectionStart = textField.getSelectionStart();
//	      int selectionEnd = textField.getSelectionEnd();
	    	hilit = textField.getHighlighter();
	    	if (hilit == null) {
	    		hilit = new DefaultHighlighter();
	    	}
	    	painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
	      hilit.removeAllHighlights();
	      textField.setHighlighter(hilit);
	      try {
			hilit.addHighlight(start, end+1, painter);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	  }

}
