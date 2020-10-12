package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			if (gpspoints[i + 1].getElevation() > gpspoints[i].getElevation()) {

				elevation += gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			}
		}
		return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int time = 0;

		time += gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();

		return time;

	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START // OPPGAVE - START
		double[] snitt = new double[gpspoints.length - 1];

		double tid = 0;
		double avstand = 0;

		// TODO - START // OPPGAVE - START
		double[] hastighet = new double[gpspoints.length - 1];
		
		public double maxSpeed() {

		for (int i = 0; i < gpspoints.length - 1; i++) {
			hastighet[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return hastighet;
		}

		// TODO - SLUTT
		public double averageSpeed() {

		for (int i = 0; i < gpspoints.length - 1; i++) {
			tid = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			avstand = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			snitt[i] = avstand / tid;
		}
		return snitt;

		// TODO - SLUTT
	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO - START

		double speeds[] = speeds();
		for (int i = 0; i < speeds.length; i++) {
			if (speeds[i] > maxspeed) {
				maxspeed = speeds[i];
			}
		}
		return maxspeed;
		// TODO - SLUTT

	}

	public double averageSpeed() {

		double average = 0;

		average = totalDistance() / totalTime() * 3.6;

		return average;


		// TODO - SLUTT

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjÃ¸res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// TODO - START

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph <= 12) {
			met = 6.0;
		} else if (speedmph <= 14) {
			met = 8.0;
		} else if (speedmph <= 16) {
			met = 10.0;
		} else if (speedmph <= 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}
		kcal = met * weight * (secs / 3600.0);
		return kcal;

		// TODO - SLUTT

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START


		for (int i = 0; i < gpspoints.length - 1; i++) {
			totalkcal += kcal(weight, gpspoints[i + 1].getTime() - gpspoints[i].getTime(),
					GPSUtils.speed(gpspoints[i], gpspoints[i + 1]));
		}

		return totalkcal;

		// TODO - SLUTT

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.println("Total time     : " + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance : " + GPSUtils.formatDouble(totalDistance() / 1000) + " km");
		System.out.println("Total elevation: " + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max speed      : " + GPSUtils.formatDouble(maxSpeed()) + " km/t");
		System.out.println("Average speed  : " + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Enregy         : " + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");

		System.out.println("==============================================");

		// TODO - SLUTT

	}

}
