package proto;

public class Data {

	private double[] temp;
	private double[] windspeed;
	private double[] rainfall;
	private double[] humidity;

	public Data(int year, int month, int day, TIMESOFDAY section) {
		String query = year + "/" + month + "/" + day;
		//Finds the query by searching time stamps
		//Retrieves the line at which the query starts
		
		//Gathers data from appropriate section
		switch(section){
		case MORNING:
		
		case AFTERNOON:
			
		case EVENING:
			
		case NIGHT:
		}
		
		//Sorts needed arrays?
	}

	//Temps
	public double getAverTempC() {
		//Palce holder
		return ArrayAverage(temp);
	}
	
	public double getAverTempF(){
		//Palce holder
		return (ArrayAverage(temp) * (9/5.0)) + 32;
	}
	
	public double getHiTempC(){
		//Palce holder
		return 0;
	}
	
	public double getLoTempC(){
		//Palce holder
		return 0;		
	}
	
	public double getHiTempF(){
		//Palce holder
		return 0;		
	}
	
	public double getLoTempF(){
		//Palce holder
		return 0;		
	}

	//Windspeed
	public double getAverWindspeedMph() {
		return (ArrayAverage(windspeed) * 3600) / 1609.3;
	}
	
	public double getAverWindspeedMps(){
		return ArrayAverage(windspeed);
	}
	
	public double getHiWindspeedMph(){
		return 0;		
	}
	
	public double getHiWindspeedMps(){
		return 0;		
	}

	//Rainfall--
	public double getRainfallInches() {
		return ArrayAverage(rainfall) / 100.0;
	}
	
	public double getRainfallCentimeters(){
		return ArrayAverage(rainfall) / 45.0;
	}

	//Humidity
	public double getAverHumidity() {
		return ArrayAverage(humidity);
	}
	
	public double ArrayAverage(double [] in){
		int sum = 0;
		for(int x = 0; x < in.length; x++){
			sum += in[x];
		}
		return sum / in.length;
	}

}
