package phoneNumbers;

import exceptions.InvalidPhoneNumberException;

public class PhoneNumberUS {
	long number = 1l;

	public boolean isValidLong(String longo) {
		try {
			Long.parseLong(longo);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public PhoneNumberUS(String rawNum) throws InvalidPhoneNumberException {
		long littleYahtty = 0L;
		if (rawNum.length() < 10)
			throw new InvalidPhoneNumberException("Phone numbers have to be at least 10 digits long");
		if (isValidLong(rawNum)) {
			littleYahtty = Long.parseLong(rawNum);
		} else {
			throw new InvalidPhoneNumberException("Phone numbers must not Contain letters.");
		}

		if (littleYahtty < 1000000000L || littleYahtty > 9999999999L) {
			throw new InvalidPhoneNumberException("Phone number must Contain be between 1000000000 and 9999999999");
		}

		this.number = littleYahtty;
	}

	public String toString() {

		StringBuilder trueNumberFormat = new StringBuilder("");
		
		trueNumberFormat.append(this.number);
		
		trueNumberFormat.insert(0, '(');
		
		trueNumberFormat.insert(4, ") - ");
		
		trueNumberFormat.insert(11, " - ");
		
		return trueNumberFormat.toString();
		
	}
}
