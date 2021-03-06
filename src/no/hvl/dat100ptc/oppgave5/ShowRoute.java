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

		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		int x1 = 0;
		int y1 = 0;

		int x2 = 0;
		int y2 = 0;

		for (int i = 0; i < latitudes.length; i++) {

			x1 = (int) (MARGIN + (longitudes[i] - minlon) * xstep());
			y1 = (int) (ybase - (latitudes[i] - minlat) * ystep());

			setColor(40, 255, 40);

			fillCircle(x1, y1, 5);

			if (i != 0) {
				drawLine(x1, y1, x2, y2);
			}

			x2 = x1;
			y2 = y1;
		}

		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		// TODO - START
		// kan be om input, kcal && totalKcal krever weight som formell parameter
		double weight = 80.0;

		String[] statisticsArray = { "Total time     : " + GPSUtils.formatTime(gpscomputer.totalTime()),
				"Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km",
				"Total elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m",
				"Max speed      : " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t",
				"Average speed  : " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t",
				"Enregy         : " + GPSUtils.formatDouble(gpscomputer.totalKcal(weight)) + " kcal" };

		for (int i = 0; i < statisticsArray.length; i++) {
			drawString(statisticsArray[i], MARGIN, (i + 1) * TEXTDISTANCE);
		}

		// TODO - SLUTT;
	}

}
