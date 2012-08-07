package pe.com.mmh.sisgap.transforms.decorators;

/*
 * 
 */

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Print ResultSet in HTML
 */
public class ResultsDecoratorHTML extends ResultsDecorator {

	public ResultsDecoratorHTML(ResultsDecoratorPrinter out) {
		super(out);
	}

	public void write(ResultSet rs,String...widths) throws IOException, SQLException {

		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		println(tableDefination);
		print("<tr>");
		for (int i = 1; i <= count; i++) {
			
			if((i-1)<=(widths.length-1)){
				print("<th width='"+widths[i-1]+"'>");
			}else{
				print("<th>");
			}
						
			print(md.getColumnLabel(i));
			print("</th>"); //JMC
		}
		println("</tr>");
		while (rs.next()) {
			print("<tr>");
			for (int i = 1; i <= count; i++) {
				print("<td>");
				print(rs.getString(i));
				print("</td>"); //JMC
			}
			println("</tr>");
		}
		println("</table>");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ResultSetDecorator#write(int)
	 */
	public void write(int updateCount) throws IOException {
		println("<p>RowCount: updateCount = <b>" + updateCount + "</p>");
	}

	/**
	 * Return a printable name for this decorator
	 * 
	 * @see ResultsDecorator#getName()
	 */
	public String getName() {
		return "HTML";
	}
}
