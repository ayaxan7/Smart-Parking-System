import serial
import time
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

# Firebase setup
cred = credentials.Certificate("parking-lot.json")  # Replace with the path to your Firebase admin SDK JSON file
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://abcd.com/'  # Replace with your Firebase Realtime Database URL
})

# Initialize serial connection (adjust COM port and baud rate as necessary)
ser = serial.Serial('COM9', 9600)  # Replace 'COM9' with your Arduino's serial port

# References to the Firebase database nodes
ref_sensor1 = db.reference('sensor_data/sensor_1')
ref_sensor2 = db.reference('sensor_data/sensor_2')
ref_sensor3 = db.reference('sensor_data/sensor_3')
while True:
    try:
        if ser.in_waiting > 0:
            data = ser.readline().decode('utf-8').strip()  # Read the data from Arduino
            # Assuming data format: "sensor1_value,sensor2_value,sensor3_value"
            sensor_values = data.split(",")
            if len(sensor_values) == 3:
                sensor_1_value = int(sensor_values[0])  # Convert sensor values to integers
                sensor_2_value = int(sensor_values[1])
                sensor_3_value = int(sensor_values[2])

                print(f'Sensor 1: {sensor_1_value}, Sensor 2: {sensor_2_value}, Sensor 3: {sensor_3_value}')

                # Send data to Firebase, overwriting existing data
                ref_sensor1.set({
                    'value': sensor_1_value,
                })
                ref_sensor2.set({
                    'value': sensor_2_value,
                })
                ref_sensor3.set({
                    'value': sensor_3_value,
                })

                print('Data sent to Firebase')

            time.sleep(1)  # Wait for a second before sending the next data point
    except Exception as e:
        print(f'Error: {e}')
        time.sleep(5)  # Wait for a while before trying again in case of error
