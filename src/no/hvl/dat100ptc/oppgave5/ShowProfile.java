package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50; // margin on the sides

	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters

	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public int drawCircle(int x, int y, int r) {
		return 0;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT);
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start

		int x = MARGIN, y;

		// TODO - START
		// drawLine(int startX, int startY, int endX, int endY)

		double pauseSkala = 1 / Double.parseDouble(getText("Skaleringfaktor?"));
		int yTop = 0;
		int xOld = 0;
		int yTopOld = 0;

		setColor(40, 40, 255);

		int circleID = fillCircle(x, 2*MARGIN + MAXBARHEIGHT, 5);

		double timeDiff = 0;

		setColor(255, 40, 40);
		for (int i = 0; i < gpspoints.length; i++) {
			yTop = ybase - (int) gpspoints[i].getElevation() > 0 ? ybase - (int) gpspoints[i].getElevation() : 0;

			moveCircle(circleID, x, yTop);

			if (i != 0) {

				drawLine(xOld, yTopOld, x, yTop);

			}
			drawLine(x, ybase, x, yTop);
			xOld = x;
			yTopOld = yTop;
			x += 2;

			if (i != 0) {
				timeDiff = 60*(gpspoints[i].getTime() - gpspoints[i - 1].getTime());
				System.out.println(timeDiff);

				pause((int) (timeDiff * pauseSkala));
			}
			
		}

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

}
