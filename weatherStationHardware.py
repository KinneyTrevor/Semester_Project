# Code based on a great tutorial by Matt from raspberrypi-spy.co.uk 
# of how to use the MCP3008 IC chip with modifications by me, Bryce M
# for the specific type of weather sensors we are using

#DHT library from Adafruit for the temperature and humidity sensor

import spidev
import os
import Adafruit_DHT
from datetime import datetime

dht = Adafruit_DHT.AM2302

#Open SPI bus
spi = spidev.SpiDev()
spi.open(0,0)

# Function to read SPI data from MCP3008
# Channel must be an integer 0-7
def ReadChannel(channel):
	adc = spi.xfer2([1,(8+channel)<<4,0])
	data = ((adc[1]&3) << 8) + adc[2]
	return data
	
def GetRainfall():
	rainfall = ReadChannel(1)
	rRef = ReadChannel(2)
	# Calculates the resistance of the ETape water strip
	# Vout = (R2 * Vin) / (R1+R2)
	try:
		rainfall = (rainfall * 330) / (1023 - rainfall)
		rRef = (rRef * 330) / (1023 - rRef)
	except ZeroDivisionError:
		rainfall = 0
		rRef = 0
	return rainfall, rRef
	
def GetTempHumid():
	humid, temp = Adafruit_DHT.read(dht, 22)
	
	# If either reading returns null/none the value is assigned to be -1
	if humid is None:
		humid = -1
	if temp is None:
		temp = -1
		
	# Returns humidity in percentage and temp in Celsius
	 
	return humid, round(temp, 2)
	
def GetWindSpeed():
	speed = ReadChannel(0)
	# Determine voltage
	voltage = (speed * 3.3)/float(1023)
	# Use voltage to determine speed
	# voltage/meters per second ratio: 
	# 0.47V = 0 m/s -- 2V = 32.4 m/s 	
	speed = ((voltage - initial) * 16.2)
	#Disregard voltage if not necessary 
	#Returns speed in meters per second
	return round(speed, 2), round(voltage, 2)

def SpeedToMPH(speed):
	"""Converts meters per second windspeed to miles per hour"""
	
	# 1 mile = 1609.34 meters
	speed = (speed * 3600) / 1609.3
	return round(speed, 2)
	
def TempToFahrenheit(temp):
	if(temp == - 1):
		return -1
	temp = ((temp * 9) / 5) + 32
	return round(temp, 2)

def RainfallToInches(rainfall, rRef):
	rainfall = (rRef - rainfall) / float(100)
	rainfall = round(rainfall, 2)
	return rainfall
	
def RainfallToCentimeters(rainfall, rRef):
	rainfall = (rRef - rainfall) / float(45)
	rainfall = round(rainfall, 2)
	return rainfall
	
#def WriteDataOut(humid, temp, speed, rainfall, tempc, tempf):
	#CODE HERE
	
initial = .48
temps_fahren = []
temps_celsi = []
speeds = []
humidities = []
rainfall_inches = []
rainfall_centi = []

while True:	

	if datetime.now().minute % 10 == 0:
		humid = sum(humidities) / float(len(humidities))
		tempf = sum(temps_fahren) / float(len(temps_fahren))
		temp = sum(temps_celsi) / float(len(temps_celsi))
		speed = sum(speeds) / float(len(speeds))
		rainfall_in = sum(rainfall_inches) / float(len(rainfall_inches))
		rainfall_ce = sum(rainfall_centi) / float(len(rainfall_centi))
		
		#Write data to file
	
	if datetime.now().second % 10 == 0:	
		humid, temp = GetTempHumid()
		speed, voltage = GetWindSpeed()
		rainfall, rRef = GetRainfall()
	
		temps_fahren.append(TempToFahrenheit(temp))
		temps_celsi.append(temp)
		speeds.append(speed)
		humidities.append(humid)
		rainfall_inches.append(RainfallToInches(rainfall, rRef))
		rainfall_centi.append(RainfallToCentimeters(rainfall, rRef))
		
		print(temps_celsi)

