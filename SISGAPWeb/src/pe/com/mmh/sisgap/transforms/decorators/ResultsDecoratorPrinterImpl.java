package pe.com.mmh.sisgap.transforms.decorators;

import java.io.IOException;
import java.io.PrintWriter;

public class ResultsDecoratorPrinterImpl implements ResultsDecoratorPrinter{

	StringBuilder stringBuilder = new StringBuilder();
	
	public void print(String line) throws IOException {
		// TODO Auto-generated method stub
		stringBuilder.append(line);
	}

	public void println(String line) throws IOException {
		// TODO Auto-generated method stub
		stringBuilder.append(line);
		stringBuilder.append("\n");
	}

	public void println() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public PrintWriter getPrintWriter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return stringBuilder.toString();
	}

}
