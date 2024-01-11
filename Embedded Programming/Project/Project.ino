// Libraries 
#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <DHT.h>

//Variables

// Variables Wifi
const char* ssid = "iPhone de Ximena";  // name's network Wi-Fi
const char* password = "ximena123";     // password's Wi-Fi

bool dataSent = false;  // Flag to send data
#define DHTPIN 13      //  DHT22 data pin
#define DHTTYPE DHT22  // Type of the sensor DHT

#define LED_PIN 12 // LED to indicate data transmission

#define LIGHT_SENSOR_PIN A3 // Sensor TEMT6000
#define POTENTIOMETER_PIN A2 // Level sensor simulation

DHT dht(DHTPIN, DHTTYPE);

unsigned long previousMillis = 0;
const long interval = 30000;  // Time interval in milliseconds

void setup() {
  Serial.begin(115200);
  dht.begin();
  connectToWiFi();
  pinMode(LED_PIN, OUTPUT);
}

void loop() {
  unsigned long currentMillis = millis();

  if (currentMillis - previousMillis >= interval) {
    previousMillis = currentMillis;

    float temperature = dht.readTemperature();  // Read temperature with sensor DHT22
    float humidity = dht.readHumidity();         // Read Humidity with sensor DHT22

    float light = analogRead(LIGHT_SENSOR_PIN); // Read light with sensor TEMT6000
    float level = analogRead(POTENTIOMETER_PIN); // Reading level with a sensor simulation with a potentiometer
    // Print the values on the serial port
    Serial.print("Temperature: ");
    Serial.print (temperature);
    Serial.println(" °C");
    Serial.print("Humidity: ");
    Serial.print (humidity);
    Serial.println(" %");
    Serial.print("Light: ");
    Serial.print(light);
    Serial.println(" %");
    Serial.print("Level: ");
    Serial.print(level);
    Serial.println(" %");

    if (WiFi.status() == WL_CONNECTED) {
      sendSensorData (temperature);  // Send temperature to backend
      sendHumidityData (humidity);   // Send humidity to backend
      sendLightData(light);          // Send light to backend
      sendLevelData(level);          // Send level to backend
    }
  }
}

// Void to check if the connection is available
void connectToWiFi() {
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("Successful connection");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}

// Void to send temperature sensor 
//data to the backend at the correct sensor ID
void sendSensorData(float temperature) {
  HTTPClient http;
  //Endpoint
  String serverEndpoint = "http://laundryapp.cleverapps.io/api/sensors/update/1";

  // Create a JSON object using ArduinoJson
  StaticJsonDocument<200> jsonDocument;
  jsonDocument["id"] = 1;  
  jsonDocument["name"] =  "Temperature";
  jsonDocument["measure"] = temperature;
  jsonDocument["sensorType"] = "TEMPERATURE";
  jsonDocument["status"] = true;

  String jsonString;
  serializeJson(jsonDocument, jsonString);

  http.begin(serverEndpoint);
  http.addHeader("Content-Type", "application/json");
  http.setAuthorization("user", "myPassword");

  // type request PUT
  int httpResponseCode = http.PUT(jsonString);
  // Server responses
  if (httpResponseCode > 0) {
    Serial.print("Server response: ");
    Serial.println(httpResponseCode);
    // LED to indicate data transmission
    digitalWrite(LED_PIN, HIGH);
    delay(1000);
    digitalWrite(LED_PIN, LOW); 
  } else {
    Serial.print("Error in the request:");
    Serial.println(httpResponseCode);
  }
  http.end();
}

// Void to send humidity sensor 
//data to the backend at the correct sensor ID
void sendHumidityData(float humidity) {
  HTTPClient http;

  String serverEndpoint = "http://laundryapp.cleverapps.io/api/sensors/update/2";

  // Crear un objeto JSON usando ArduinoJson
  StaticJsonDocument<200> jsonDocument;
  jsonDocument["id"] = 2;  
  jsonDocument["name"] =  "Humidity";
  jsonDocument["measure"] = humidity;
  jsonDocument["sensorType"] = "HUMIDITY";
  jsonDocument["status"] = false;

  String jsonString;
  serializeJson(jsonDocument, jsonString);

  http.begin(serverEndpoint);
  http.addHeader("Content-Type", "application/json");
  http.setAuthorization("user", "myPassword");

  int httpResponseCode = http.PUT(jsonString);

  if (httpResponseCode > 0) {
    Serial.print("Server response: ");
    Serial.println(httpResponseCode);
    digitalWrite(LED_PIN, HIGH);
    delay(1000);  
    digitalWrite(LED_PIN, LOW); 
  } else {
    Serial.print("Error in the request:");
    Serial.println(httpResponseCode);
  }
  http.end();
}

// Void to send light sensor 
//data to the backend at the correct sensor ID
void sendLightData(float light) {
  HTTPClient http;

  String serverEndpoint = "http://laundryapp.cleverapps.io/api/sensors/update/3";

  StaticJsonDocument<200> jsonDocument;
  jsonDocument["id"] = 3;  
  jsonDocument["name"] = "Light";
  jsonDocument["measure"] = light;
  jsonDocument["sensorType"] = "LIGHT"; 
  jsonDocument["status"] = false;

  String jsonString;
  serializeJson(jsonDocument, jsonString);

  http.begin(serverEndpoint);
  http.addHeader("Content-Type", "application/json");
  http.setAuthorization("user", "myPassword");

  int httpResponseCode = http.PUT(jsonString);

  if (httpResponseCode > 0) {
    Serial.print("Server response: ");
    Serial.println(httpResponseCode);

    digitalWrite(LED_PIN, HIGH);
    delay(1000);  
    digitalWrite(LED_PIN, LOW); 
  } else {
    Serial.print("Error in the request: ");
    Serial.println(httpResponseCode);
  }

  http.end();
}

// Void to send level sensor 
//data to the backend at the correct sensor ID
void sendLevelData(float level) {
  HTTPClient http;

  String serverEndpoint = "http://laundryapp.cleverapps.io/api/sensors/update/4";

  StaticJsonDocument<200> jsonDocument;
  jsonDocument["id"] = 4;  
  jsonDocument["name"] = "Level";
  jsonDocument["measure"] = level;
  jsonDocument["sensorType"] = "LEVEL";  
  jsonDocument["status"] = false;

  String jsonString;
  serializeJson(jsonDocument, jsonString);

  http.begin(serverEndpoint);
  http.addHeader("Content-Type", "application/json");
  http.setAuthorization("user", "myPassword");

  int httpResponseCode = http.PUT(jsonString);

  if (httpResponseCode > 0) {
    Serial.print("Server response: ");
    Serial.println(httpResponseCode);

    digitalWrite(LED_PIN, HIGH);
    delay(1000);  
    digitalWrite(LED_PIN, LOW); 
  } else {
    Serial.print("Error in the request: ");
    Serial.println(httpResponseCode);
  }

  http.end();
}
