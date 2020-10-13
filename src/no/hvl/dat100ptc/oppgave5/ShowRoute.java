package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// ybase- (int) (latitude for punktet, - minstelatituden) * ystep
		
		// TODO - START
		double[] latitudes = GPSUtils.getLatitudes(gpspoints);
		double[] longitudes = GPSUtils.getLongitudes(gpspoints);
		
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		int x = 0;
		int y = 0;
		
		double tempX = 0;
		double tempY = 0;
		
		System.out.println(ybase);
		System.out.println("maxlat" + maxlat);
		System.out.println("ystep" + ystep());
		
		for (int i = 0; i < latitudes.length; i++) {
			
			// her kan vi trolig droppe tempY og tempX, 
			// og heller caste direkte til int
			
			tempY = ybase - (latitudes[i] - minlat) * ystep();
			tempX = MARGIN + (longitudes[i] - minlon) * xstep();
			
			y = (int)tempY;
			x = (int)tempX;
			
			setColor(40,255,40);
			
			fillCircle(x, y, 5);
			//x++;
		}
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}

}
