# Smart Parking System 


## Overview

The **Smart Parking System** is an Android application that provides real-time status updates on parking slots based on sensor data fetched from Firebase Realtime Database. The app displays whether each slot is occupied or available, using color-coded indicators (red for occupied, green for available).

### 🚧 First Prototype

This is the first prototype of the Smart Parking System. More features are in the pipeline and will be released in future updates, enhancing the system's functionality and user experience.

## Features

- **Real-Time Data:** Displays the current status of parking slots using data from Firebase.
- **Color-Coded UI:** Easy-to-understand visual cues with red boxes indicating occupied slots and green boxes indicating available slots.
- **Firebase Integration:** Seamless integration with Firebase Realtime Database for fetching parking slot data.
- **Responsive Design:** Ensures that the app looks good on all screen sizes and orientations.
- **Lightweight:** The app is optimized for performance and battery efficiency.

## Screenshots

<img src="WhatsApp%20Image%202024-08-02%20at%2001.54.44_75cf8ece.jpg" alt="Parking Status" width="300" />

## Tech Stack

- **Android (Java/Kotlin):** For developing the mobile application.
- **Firebase Realtime Database:** For storing and fetching the parking slot data.
- **XML:** For designing the user interface.

## Setup and Installation

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/yourusername/parking-system.git
    cd parking-system
    ```

2. **Open in Android Studio:**
   - Import the project into Android Studio.
   - Make sure all the dependencies are installed.

3. **Firebase Setup:**
   - Create a Firebase project in the [Firebase Console](https://console.firebase.google.com/).
   - Add your Android app to the Firebase project.
   - Download the `google-services.json` file and place it in the `app` directory of your project.
   - Enable Firebase Realtime Database in the Firebase Console and set up the database rules.

4. **Build and Run:**
   - Connect your Android device or start an emulator.
   - Build and run the app from Android Studio.

## Usage

- **View Parking Status:** Open the app to see the real-time status of parking slots.
- **Data Update:** The parking slot statuses update automatically based on the sensor data in the Firebase Realtime Database.

## Upcoming Features

- **User Authentication:** Implement user authentication to allow users to save their parking preferences.
- **Enhanced UI:** Improve the user interface for better usability and aesthetics.
- **Notification System:** Send push notifications to users about slot availability.






