![343shots_so](https://github.com/user-attachments/assets/469b4db6-0c4c-49ea-b951-49dc2de81c19)

# ChatBolt

ChatBolt is an Android application that allows users to engage in real-time chat conversations. The app uses Firebase Firestore for data storage and Firebase Cloud Messaging for push notifications. Users can see recent conversations, start new chats, and sign out from the application.

## Features

- Real-time chat with other users.
- Firebase Firestore integration for storing chat data.
- Firebase Cloud Messaging for push notifications.
- User authentication and profile management.
- Display recent conversations with the ability to start new chats.


## Prerequisites

- Android Studio 4.0 or higher
- Android SDK
- Firebase project setup with Firestore and Cloud Messaging enabled

## Getting Started

Follow these instructions to set up and run the ChatBolt app on your local machine.

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/chatbolt.git
    cd chatbolt
    ```

2. **Open the project in Android Studio:**
    - Launch Android Studio.
    - Select `Open an existing project`.
    - Navigate to the cloned repository directory and select it.

3. **Set up Firebase:**
    - Go to the Firebase Console and create a new project.
    - Add an Android app to your Firebase project and download the `google-services.json` file.
    - Place the `google-services.json` file in the `app` directory of your project.
    - Enable Firestore and Cloud Messaging in the Firebase Console.

4. **Add your Firebase configuration:**
    - Open `app/build.gradle` and ensure the following dependencies are included:

    ```groovy
    implementation platform('com.google.firebase:firebase-bom:26.7.0')
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.google.firebase:firebase-messaging'
    ```

5. **Build and run the app:**
    - Connect your Android device or start an emulator.
    - Click on the `Run` button in Android Studio.

## Usage

1. **Sign In:**
    - Launch the ChatBolt app.
    - Sign in using your preferred method (e.g., email and password).

2. **View Conversations:**
    - The main screen displays a list of recent conversations.
    - Click on a conversation to open the chat screen.

3. **Start a New Chat:**
    - Click on the floating action button to start a new chat.
    - Select a user from the list to start a conversation.

4. **Sign Out:**
    - Click on the sign-out button to log out of the app.

## Code Overview

### MainActivity.java

- **Initialization:** Sets up views and Firebase instances.
- **Load User Details:** Loads the signed-in user's details from shared preferences.
- **Listen to Conversations:** Listens for real-time updates to the conversations collection in Firestore.
- **Get Token:** Retrieves the FCM token for push notifications.
- **Update Token:** Updates the FCM token in Firestore.
- **Sign Out:** Signs out the user and clears their data from shared preferences.

### Adapters and Models

- **RecentConversationsAdapter:** RecyclerView adapter to display the list of recent conversations.
- **ChatMessage:** Model class representing a chat message.
- **User:** Model class representing a user.


## Acknowledgements

- [Firebase](https://firebase.google.com/) for the backend services.
- Android developers and the community for resources and support.

---

Happy coding! 🚀
