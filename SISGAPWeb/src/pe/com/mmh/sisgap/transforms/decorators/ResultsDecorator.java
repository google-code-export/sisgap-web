package pe.com.mmh.sisgap.transforms.decorators;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Base class for a series of ResultSet printers.
 * 
 * @version $Id: ResultsDecorator.java,v 1.2 2004/03/26 02:39:33 ian Exp $
 */

public abstract class ResultsDecorator {
	
	protected String tableDefination = "<table border=1>";
	
	ResultsDecoratorPrinter parent;

	ResultsDecorator(ResultsDecoratorPrinter wr) {
		this.parent = wr;
	}

	/** Print the name of this Decorator's output format */
	public abstract String getName();

	/** Print the contents of a ResultSet */
	public abstract void write(ResultSet rs,String...widths) throws IOException, SQLException;

	/** Print the results of an operation as a Count */
	public abstract void write(int rowCount) throws IOException;

	void println(String line) throws IOException {
		parent.println(line);
	}

	void println() throws IOException {
		parent.println();
	}

	void print(String lineSeg) throws IOException {
		parent.print(lineSeg);
	}

	public String getTableDefination() {
		return tableDefination;
	}

	public void setTableDefination(String tableDefination) {
		this.tableDefination = tableDefination;
	}
	
	
}