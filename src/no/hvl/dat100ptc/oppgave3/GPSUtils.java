package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] tabell = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			tabell[i] = (gpspoints[i].getLatitude());
		}
		return tabell;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] tabell = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			tabell[i] = (gpspoints[i].getLongitude());
		}
		return tabell;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = toRadians(gpspoint1.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());

		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude2 = toRadians(gpspoint2.getLongitude());

		double deltaLat = latitude2 - latitude1;
		double deltaLongi = longitude2 - longitude1;

		double a = pow(sin(deltaLat / 2), 2) + cos(latitude1) * cos(latitude2) * pow(sin(deltaLongi / 2), 2);

		double c = 2 * atan2(sqrt(a), sqrt(1 - a));

		d = R * c;

		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		secs = gpspoint2.getTime() - gpspoint1.getTime();

		speed = ((distance(gpspoint1, gpspoint2)) / secs) * 3.6;

		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		int timer = secs / 3600;

		int minutter = (secs / 60) - (timer * 60);

		int sekunder = secs - timer * 3600 - minutter * 60;

		timestr = String.format("  %1$02d%2$s%3$02d%2$s%4$02d", timer, TIMESEP, minutter, sekunder);

		return timestr;

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		str = String.format("%10.2f", d);

		str =str.replace("," , ".");
		
		return str;

	}
}
