package pe.com.mmh.sisgap.comun.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class ImprimeDemo implements Printable {

	static String valor1;
	static String valor2;
	
	public static void Demo(String valor_1, String valor_2) {
		valor1 = valor_1;
		valor2 = valor_2;
		
		System.out.println("Valor 1 : " + valor1);
		System.out.println("Valor 2 : " + valor2);
	}
	
	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) {
		// TODO Auto-generated method stub
		if (pageIndex != 0)
			return NO_SUCH_PAGE;
		
		Graphics2D g2 = (Graphics2D) g; //Courier New, SansSerif 
		g2.setFont(new Font("Courier New",Font.PLAIN, 14));
		g2.setPaint(Color.black);
		g2.drawString(valor1, 50, 50);
		
		g2.setFont(new Font("Courier",Font.PLAIN, 12));
		g2.drawString(valor2, 150, 150);

		Rectangle2D outline = new Rectangle2D.Double(
						pf.getImageableX(), 
						pf.getImageableY(), 
						pf.getImageableWidth(), 
						pf.getImageableHeight()
		);
		g2.draw(outline);
		
		return PAGE_EXISTS;
	}

	
	public static void Imprime(String valor_1, String valor_2){
		Demo(valor_1, valor_2);
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = pj.defaultPage();
		Paper paper = new Paper();
		double margin = 36;
		paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
		pf.setPaper(paper);
		
		pj.setPrintable(new ImprimeDemo(), pf);
		
		if (pj.printDialog()) {
			try {
				pj.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
