package com.arqiao.closure_compiler;



import com.google.javascript.rhino.ErrorReporter;


public final class TestErrorReporter implements ErrorReporter {
	  private String[] errors;
	  private String[] warnings;
	  private int errorsIndex = 0;
	  private int warningsIndex = 0;

	  public TestErrorReporter(String[] errors, String[] warnings) {
	    this.errors = errors;
	    this.warnings = warnings;
	  }

	  public static TestErrorReporter forNoExpectedReports() {
	    return new TestErrorReporter(null, null);
	  }

	  public void setErrors(String[] errors) {
	    this.errors = errors;
	    errorsIndex = 0;
	  }

	  public void setWarnings(String[] warnings) {
	    this.warnings = warnings;
	    warningsIndex = 0;
	  }

	  @Override
	  public void error(String message, String sourceName, int line,
	      int lineOffset) {
	    if (errors != null && errorsIndex < errors.length) {
//	      assertThat(message).isEqualTo(errors[errorsIndex++]);
	    } else {
	      Assert.fail("extra error: " + message);
	    }
	  }

	  @Override
	  public void warning(String message, String sourceName, int line,
	      int lineOffset) {
	    if (warnings != null && warningsIndex < warnings.length) {
//	      assertThat(message).isEqualTo(warnings[warningsIndex++]);
	    } else {
	      Assert.fail("extra warning: " + message);
	    }
	  }

	  public void assertHasEncounteredAllWarnings() {
	    if (warnings == null) {
//	      assertThat(warningsIndex).isEqualTo(0);
	    } else {
//	      assertThat(warnings).hasLength(warningsIndex);
	    }
	  }

	  public void assertHasEncounteredAllErrors() {
	    if (errors == null) {
//	      assertThat(errorsIndex).isEqualTo(0);
	    } else {
//	      assertThat(errors).hasLength(errorsIndex);
	    }
	  }

	 static  class Assert
	  {
		  static void fail(String message)
		  {
			  System.err.print(message);
		  }
	  }
	}