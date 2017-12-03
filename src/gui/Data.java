package proto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

public class Data {

	TreeMap<Date, Integer> timeIndex = new TreeMap<>();
	ArrayList<Double> temp = new ArrayList<>();
	int morningBeg, morningEnd;
	int afternoonBeg, afternoonEnd;
	int eveningBeg, eveningEnd;
	int nightBeg, nightEnd;

	public Data(int year, int month, int day) throws Exception {
		String query = month + "-" + day + "-" + year;
		int posCount;
		int startInd = DataRetrieval.dateArrayList.indexOf(DataRetrieval.stringToSQLDate(query));
		int endInd = DataRetrieval.dateArrayList.lastIndexOf(DataRetrieval.stringToSQLDate(query));

		if (startInd == -1 || endInd == -1) {
			throw new Exception("No data for specified date");
		}

		posCount = startInd;
		for (Date el : DataRetrieval.timeArrayList.subList(startInd, endInd)) {
			timeIndex.put(el, posCount);
			posCount++;
		}

		nightBeg = startInd;
		nightEnd = timeIndex.get(timeIndex.ceilingKey(DataRetrieval.stringToSQLTime("05:59:59")));
		morningBeg = timeIndex.get(timeIndex.floorKey(DataRetrieval.stringToSQLTime("06:00:00")));
		morningEnd = timeIndex.get(timeIndex.ceilingKey(DataRetrieval.stringToSQLTime("11:59:59")));
		afternoonBeg = timeIndex.get(timeIndex.floorKey(DataRetrieval.stringToSQLTime("12:00:00")));
		afternoonEnd = timeIndex.get(timeIndex.ceilingKey(DataRetrieval.stringToSQLTime("15:59:59")));
		eveningBeg = timeIndex.get(timeIndex.floorKey(DataRetrieval.stringToSQLTime("16:00:00")));
		eveningEnd = endInd;
	}

	// Temps
	public double getAverTempC(TIMESOFDAY section) {
		switch (section) {
		case MORNING: {
			return ArrayAverage(DataRetrieval.temperatureArrayList, morningBeg, morningEnd);
		}
		case EVENING: {
			return ArrayAverage(DataRetrieval.temperatureArrayList, eveningBeg, eveningEnd);
		}
		case AFTERNOON: {
			return ArrayAverage(DataRetrieval.temperatureArrayList, afternoonBeg, afternoonEnd);
		}
		case NIGHT: {
			return ArrayAverage(DataRetrieval.temperatureArrayList, nightBeg, nightEnd);
		}
		default: {
			return ArrayAverage(DataRetrieval.temperatureArrayList, morningBeg, nightEnd);
		}
		}
	}

	public double getAverTempF(TIMESOFDAY section) {
		return (getAverTempC(section) * (9 / 5.0)) + 32;
	}

	public double getHiTempC() {
		temp = DataRetrieval.temperatureArrayList;
		temp.sort(new AscendingComparator());
		return temp.get(temp.size() - 1);
	}

	public double getLoTempC() {
		temp = DataRetrieval.temperatureArrayList;
		temp.sort(new AscendingComparator());
		return temp.get(0);
	}

	public double getHiTempF() {
		return getHiTempC() * (9 / 5.0) + 32;
	}

	public double getLoTempF() {
		return getLoTempC() * (9 / 5.0) + 32;
	}

	// Windspeed
	public double getAverWindspeedMph(TIMESOFDAY section) {
		return (getAverWindspeedMps(section) * 3600) / 1609.3;
	}

	public double getAverWindspeedMps(TIMESOFDAY section) {
		switch (section) {
		case MORNING: {
			return ArrayAverage(DataRetrieval.windArrayList, morningBeg, morningEnd);
		}
		case EVENING: {
			return ArrayAverage(DataRetrieval.windArrayList, eveningBeg, eveningEnd);
		}
		case AFTERNOON: {
			return ArrayAverage(DataRetrieval.windArrayList, afternoonBeg, afternoonEnd);
		}
		case NIGHT: {
			return ArrayAverage(DataRetrieval.windArrayList, nightBeg, nightEnd);
		}
		default: {
			return ArrayAverage(DataRetrieval.windArrayList, morningBeg, nightEnd);
		}
		}
	}

	public double getHiWindspeedMph() {
		temp = DataRetrieval.windArrayList;
		temp.sort(new AscendingComparator());
		return temp.get(temp.size() - 1);
	}

	public double getHiWindspeedMps() {
		temp = DataRetrieval.windArrayList;
		temp.sort(new AscendingComparator());
		return temp.get(0);
	}

	// Rainfall--
	public double getRainfallInches(TIMESOFDAY section) {
		return getRainfall(section) / 150.0;
	}

	public double getRainfallCentimeters(TIMESOFDAY section) {
		return getRainfall(section) / 45.0;
	}

	private double getRainfall(TIMESOFDAY section) {
		switch (section) {
		case MORNING: {
			return ArrayAverage(DataRetrieval.rainArrayList, morningBeg, morningEnd);
		}
		case EVENING: {
			return ArrayAverage(DataRetrieval.rainArrayList, eveningBeg, eveningEnd);
		}
		case AFTERNOON: {
			return ArrayAverage(DataRetrieval.rainArrayList, afternoonBeg, afternoonEnd);
		}
		case NIGHT: {
			return ArrayAverage(DataRetrieval.rainArrayList, nightBeg, nightEnd);
		}
		default: {
			return ArrayAverage(DataRetrieval.rainArrayList, morningBeg, nightEnd);
		}
		}
	}

	// Humidity
	public double getAverHumidity(TIMESOFDAY section) {
		switch (section) {
		case MORNING: {
			return ArrayAverage(DataRetrieval.humidityArrayList, morningBeg, morningEnd);
		}
		case EVENING: {
			return ArrayAverage(DataRetrieval.humidityArrayList, eveningBeg, eveningEnd);
		}
		case AFTERNOON: {
			return ArrayAverage(DataRetrieval.humidityArrayList, afternoonBeg, afternoonEnd);
		}
		case NIGHT: {
			return ArrayAverage(DataRetrieval.humidityArrayList, nightBeg, nightEnd);
		}
		default: {
			return ArrayAverage(DataRetrieval.humidityArrayList, morningBeg, nightEnd);
		}
		}
	}

	public double ArrayAverage(ArrayList<Double> in, int low, int high) {
		int sum = 0;
		int siz = 0;

		for (double el : in.subList(low, high)) {
			sum += el;
			siz++;
		}
		return sum / siz;
	}
}

class AscendingComparator implements Comparator<Double> {
	@Override
	public int compare(Double o1, Double o2) {
		return o1.compareTo(o2);
	}

}

class DescendingComparator implements Comparator<Double> {
	@Override
	public int compare(Double o1, Double o2) {
		return o1.compareTo(o2) * -1;
	}

}
