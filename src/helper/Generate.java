package helper;

import java.util.Random;

public class Generate {
	public String generateRandomOtp(int length) {
		String characters = "0123456789";

		Random rand = new Random();

		String otp = "";

		for (int i = 0; i < length; i++) {
			otp += characters.charAt(rand.nextInt(characters.length()));
		}

		return otp;
	}
}
