package com.example.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@SpringBootApplication
@RestController
public class VerifyGcpServerApplication {

	private static FirebaseApp firebaseInstance;

	public static synchronized FirebaseApp initFirebaseApp() throws IOException {

		if (firebaseInstance == null) {
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.getApplicationDefault()).build();

			FirebaseApp.initializeApp(options);
		} else {
			return firebaseInstance;
		}
		return firebaseInstance;
	}

	public static void main(String[] args) {
		SpringApplication.run(VerifyGcpServerApplication.class, args);
		System.out.println("start");
		try {
			initFirebaseApp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}

		System.out.println("end");
	}
}
