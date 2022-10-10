#include <DHT.h>
#include <DHT_U.h>

#include <SoftwareSerial.h>
#include <Adafruit_ADS1X15.h>

//#include <Adafruit_ADS1X15.h>
//#include <DHT.h>;
//#include "SoftwareSerial.h"

String ssid = "Saba";
String password = "12345@saba";

SoftwareSerial esp(7, 6);// RX, TX

String data;

String msg = "GET /fahad_iot/upload_sensor_data.php";
String cmd;

#define DHTPIN 2     // what pin we're connected to
#define DHTTYPE DHT22   // DHT 22  (AM2302)
DHT dht(DHTPIN, DHTTYPE);

Adafruit_ADS1115 ads;  /* Use this for the 16-bit version */

float hum;  //Stores humidity value
float temp; //Stores temperature value

float phS = 0;
int moistS = 0;
int lightS = 0;

String Stphs;
String Sthum;
String Sttem;
String StmoistS;
String StlightS;

void setup(void)
{
  Serial.begin(9600);
  Serial.println("Initialization..");
  esp.begin(9600);
  dht.begin();
  Serial.println("Resetting Esp-01!");
  delay(2000);

  reset();
  connectWifi();

  ads.setGain(GAIN_TWOTHIRDS);  // 2/3x gain +/- 6.144V  1 bit = 3mV      0.1875mV (default)
  ads.begin();
}

void loop(void)
{
  hum = dht.readHumidity();
  temp = dht.readTemperature();

  Sthum = String(hum, 2);
  Sttem = String(temp, 2);
  //Print temp and humidity values to serial monitor
  Serial.print("Humidity: ");
  Serial.print(Sthum);
  Serial.print(" %, Temp: ");
  Serial.print(Sttem);
  Serial.println(" Celsius");

  int16_t adc0, adc1, adc2, adc3;
  float volts0, volts1, volts2, volts3;

  adc0 = ads.readADC_SingleEnded(0);
  adc1 = ads.readADC_SingleEnded(1);
  adc2 = ads.readADC_SingleEnded(2);
  adc3 = ads.readADC_SingleEnded(3);


  phS = map(adc0, 1, 25000, 14, 0);
  Stphs = String(phS);

  lightS = map(adc1, 1, 25000, 100, 1);
  StlightS = String(lightS);

  moistS = map(adc2, 3000, 25000, 100, 1);
  StmoistS = String(moistS);

  Serial.print("phs: ");
  Serial.print(Stphs);

  Serial.print(", lightS: ");
  Serial.print(StlightS);

  Serial.print(", moistS: ");
  Serial.println(StmoistS);

  updateDB();
  delay(60000);
}

void reset() {

  esp.println("AT+RST");
  delay(1000);
  if (esp.find("OK") ) Serial.println("Module Reset");

}

void connectWifi() {

  String wifi = "AT+CWJAP=\"" + ssid + "\",\"" + password + "\"";
  esp.println(wifi);

  delay(4000);

  if (esp.find("OK")) {
    Serial.println("Connected!");
  }
  else {
    connectWifi();
    Serial.println("Retried wifi Connection. .");
  }
}


void updateDB() {
  esp.print("AT+CIPSTART=\"TCP\",\"www.stpsea.com\",80\r\n");;//start a TCP connection.

  if ( esp.find("OK")) {

    Serial.println("TCP connection ready");

  } delay(1000);


  cmd = msg ;
  cmd += "?phS=";
  cmd += Stphs;
  cmd += "&humS=";
  cmd += Sthum;
  cmd += "&tempS=";
  cmd += Sttem;
  cmd += "&lightS=";
  cmd += StlightS;
  cmd += "&moistS=";
  cmd += StmoistS;
  cmd += " HTTP/1.1\r\nHost: www.stpsea.com\r\n\r\n";

  esp.print("AT+CIPSEND=");
  esp.println(cmd.length());
  Serial.print("Ready to Send: ");
  Serial.print(cmd.length());
  Serial.println(" Characters");

  if ( esp.find(">")) {

    Serial.print("Out API: ");
    Serial.println(cmd);
    esp.print(cmd);
  }
  delay(2000);
    if ( esp.find("SEND OK")) {
    Serial.println("Data Sent!");
    delay(1000);
    esp.println("AT+CIPCLOSE");
    Serial.println("");
  }else{
    Serial.println("Error Sending Data");
    }
}
