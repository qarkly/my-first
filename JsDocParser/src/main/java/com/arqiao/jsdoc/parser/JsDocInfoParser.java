package com.arqiao.jsdoc.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.arqiao.jsdoc.parser.util.Assert;
import com.google.javascript.jscomp.parsing.JsDocToken;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;








public class JsDocInfoParser {
	  private final JsDocTokenStream stream;
	  private State state;
	  
	  
	  private enum State {
		    SEARCHING_ANNOTATION,
		    SEARCHING_NEWLINE,
		    NEXT_IS_ANNOTATION
		  }
	
	  /**
	   * Specific value indicating that the {@link #unreadToken} contains no token.
	   */
	  private static final JsDocToken NO_UNREAD_TOKEN = null;
	  /**
	   * One token buffer.
	   */
	  private JsDocToken unreadToken = NO_UNREAD_TOKEN;
	  
	  public JsDocInfoParser(JsDocTokenStream stream)
	  {
		  this.stream = stream;
	  }
	
	  /**
	   * Parses a {@link JSDocInfo} object. This parsing method reads all tokens
	   * returned by the {@link JsDocTokenStream#getJsDocToken()} method until the
	   * {@link JsDocToken#EOC} is returned.
	   *
	   * @return {@code true} if JSDoc information was correctly parsed,
	   *     {@code false} otherwise
	   */
	 public boolean parse() {
	    state = State.SEARCHING_ANNOTATION;
	    skipEOLs();

	    JsDocToken token = next();

//	    // Always record that we have a comment.
//	    if (jsdocBuilder.shouldParseDocumentation()) {
//	      ExtractionInfo blockInfo = extractBlockComment(token);
//	      token = blockInfo.token;
//	      if (!blockInfo.string.isEmpty()) {
//	        jsdocBuilder.recordBlockDescription(blockInfo.string);
//	      }
//	    } else {
//	      if (token != JsDocToken.ANNOTATION &&
//	          token != JsDocToken.EOC) {
//	        // Mark that there was a description, but don't bother marking
//	        // what it was.
//	        jsdocBuilder.recordBlockDescription("");
//	      }
//	    }

	    return parseHelperLoop(token);
	  }
	

	  private boolean parseHelperLoop(JsDocToken token) {
	    while (true) {
	      switch (token) {
	        case ANNOTATION:
	          if (state == State.SEARCHING_ANNOTATION) {
	            state = State.SEARCHING_NEWLINE;
	            token = parseAnnotation(token);
	          } else {
	            token = next();
	          }
	          break;

	        case EOC:
	          boolean success = true;
	          // TODO(johnlenz): It should be a parse error to have an @extends
	          // or similiar annotations in a file overview block.
//	          checkExtendedTypes(extendedTypes);
	         
	          return success;

	        case EOF:
	          // discard any accumulated information
//	          jsdocBuilder.build();
	          addParserWarning("msg.unexpected.eof");
//	          checkExtendedTypes(extendedTypes);
	          return false;

	        case EOL:
	          if (state == State.SEARCHING_NEWLINE) {
	            state = State.SEARCHING_ANNOTATION;
	          }
	          token = next();
	          break;

	        default:
	          if (token == JsDocToken.STAR && state == State.SEARCHING_ANNOTATION) {
	            token = next();
	          } else {
	            state = State.SEARCHING_NEWLINE;
	            token = eatTokensUntilEOL();
	          }
	          break;
	      }
	    }
	  }
	
	 private void checkExtendedTypes(List<String> extendedTypes) {
		// TODO Auto-generated method stub
		
	}

	private void addParserWarning(String string) {
		// TODO Auto-generated method stub
		
	}

	private JsDocToken parseAnnotation(JsDocToken token) {
		int lineno = stream.getLineno();
		int charno = stream.getLineno();
		
		String annotation  = stream.getString();
		
		if(!CheckAnnotation(annotation))
		{
			addParserWarning("");
		}
		
		skipEOLs();
		token = next();
		lineno = stream.getLineno();
		charno = stream.getCharno();
		
		boolean matchingRc = false;
		
		if (token == JsDocToken.LEFT_CURLY) {
            token = next();
            matchingRc = true;
        }
		
		if(token == JsDocToken.STRING)
		{
			
		}
		
		return null;
	}
	
	 private static final Pattern Annotation_Pattern = Pattern.compile("[a-zA-Z]+");
	
	  private boolean CheckAnnotation(String annotation) {
		if(Assert.isNUllOrEmpty(annotation)){
			return false;
		}
		if(!Annotation_Pattern.matcher(annotation).matches())
		{
			return false;
		}
		return true;
	}

	/**
	   * Eats tokens until {@link JsDocToken#EOL} included, and switches back the
	   * state to {@link State#SEARCHING_ANNOTATION}.
	   */
	  private JsDocToken eatTokensUntilEOL() {
	    return eatTokensUntilEOL(next());
	  }

	  /**
	   * Eats tokens until {@link JsDocToken#EOL} included, and switches back the
	   * state to {@link State#SEARCHING_ANNOTATION}.
	   */
	  private JsDocToken eatTokensUntilEOL(JsDocToken token) {
	    do {
	      if (token == JsDocToken.EOL || token == JsDocToken.EOC ||
	          token == JsDocToken.EOF) {
	        state = State.SEARCHING_ANNOTATION;
	        return token;
	      }
	      token = next();
	    } while (true);
	  }

	/**
	   * Skips all EOLs and all empty lines in the JSDoc. Call this method if you
	   * want the JSDoc entry to span multiple lines.
	   */
	  private void skipEOLs() {
	    while (match(JsDocToken.EOL)) {
	      next();
	      if (match(JsDocToken.STAR)) {
	        next();
	      }
	    }
	  }
	  
	  /**
	   * Tests whether the next symbol of the token stream matches the specific
	   * token.
	   */
	  private boolean match(JsDocToken token) {
	    unreadToken = next();
	    return unreadToken == token;
	  }
	  
	  /**
	   * Gets the next token of the token stream or the buffered token if a matching
	   * was previously made.
	   */
	  private JsDocToken next() {
	    if (unreadToken == NO_UNREAD_TOKEN) {
	      return stream.getJsDocToken();
	    } else {
	      return current();
	    }
	  }

	  /**
	   * Gets the current token, invalidating it in the process.
	   */
	  private JsDocToken current() {
	    JsDocToken t = unreadToken;
	    unreadToken = NO_UNREAD_TOKEN;
	    return t;
	  }

}
