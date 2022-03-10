package com.example.demo.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@Service
public class PushMsgByFCMServiceImpl implements PushMsgByFCMService {

	private static FirebaseApp firebaseInstance;

	public synchronized FirebaseApp initFirebaseApp() throws IOException {

		if (firebaseInstance == null) {
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.getApplicationDefault()).build();

			FirebaseApp.initializeApp(options);
		} else {
			return firebaseInstance;
		}
		return firebaseInstance;
	}

	public boolean sendMessage(String token) {
		
		try {
			FirebaseApp firebaseApp = initFirebaseApp();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String registrationToken = "YOUR_REGISTRATION_TOKEN";

		Message message = Message.builder().putData("score", "850").putData("time", "2:45").setToken(registrationToken)
				.build();

		String response = null;
		try {
			response = FirebaseMessaging.getInstance().send(message);
			System.out.println("Successfully sent message: " + response);
		} catch (FirebaseMessagingException e) {
			System.out.println("Successfully sent message: " + response);
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
