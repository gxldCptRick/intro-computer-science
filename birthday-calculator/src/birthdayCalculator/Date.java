package birthdayCalculator;

import java.time.DateTimeException;

public class Date {
	private int day = 1, month = 1, year = 1;
	private boolean isInvalidDate = true, isLeapYear = true;
	private String errorMessage = "", invalidDay = "invalid day", monthString = "", yearString = "";

	/**
	 * @return returns the value of the current date.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            sets the day of the current date.
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return returns the value of the current date.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            sets the month of the current date.
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return returns the value of the current year.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            sets the year of the current date.
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * the base constructor for date. sets the date equal to January 1st, 0001.
	 */
	public Date() {
	}

	/**
	 * @param day
	 *            sets the value of day in the new Date.
	 * @param month
	 *            sets the value of month in the new Date.
	 * @param year
	 *            sets the value of year in the new Date.
	 * @throws DateTimeException
	 *             exception is thrown when given an invalid day, month or year.
	 */
	public Date(int day, int month, int year) throws DateTimeException {
		this.isInvalidDate = (year < 0);
		this.isLeapYear = (year > 1584 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0));
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			this.isInvalidDate = (day > 31 || day < 1);
			this.errorMessage += this.invalidDay;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			this.isInvalidDate = (day > 30 || day < 1);
			this.errorMessage += this.invalidDay;
			break;
		case 2:
			if (this.isLeapYear) {
				this.isInvalidDate = (day > 29 || day < 1);
			} else {
				this.isInvalidDate = (day > 28 || day < 1);
			}
			this.errorMessage += this.invalidDay;
			break;
		default:
			this.errorMessage += "Invalid Month";
		}
		if (this.isInvalidDate) {
			throw new DateTimeException(errorMessage);
		}

		this.day = day;
		this.year = year;
		this.yearString = year + "";
		this.month = month;
		switch (month) {
		case 1:
			this.monthString = "January";
			break;
			
		case 2:
			this.monthString = "Feburary";
			break;
			
		case 3:
			this.monthString = "March";
			break;
			
		case 4:
			this.monthString = "April";
			break;
		
		case 5:
			this.monthString = "May";
			break;
			
		case 6: 
			this.monthString = "June";
			break;
		
		case 7:
			this.monthString = "July";
			break;
			
		case 8:
			this.monthString = "August";
			break;
			
		case 9: 
			this.monthString = "September";
			break;
			
		case 10:
			this.monthString = "October";
			break;
		
		case 11:
			this.monthString = "November";
			break;
			
		case 12: 
			this.monthString = "December";
			break;
		}
		
		switch(yearString.length()) {
		
		case 1:
			yearString = "000" + yearString;
			break;
		case 2:
			yearString = "00" + yearString;
			break;
		case 3:
			yearString = "0" + yearString;
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() outputs date in month/ day/ year
	 */
	public String toString() {
		return (this.monthString + " " + this.day + ", " + this.yearString);
	}

	/**
	 * @param otherDate
	 *            another date that is being compared to the current date.
	 * @return returns whether or not that the current date is equal to the other
	 *         date in terms of exact date.
	 */
	public boolean isEqual(Date otherDate) {
		return (this.year == otherDate.year && this.month == otherDate.month && this.day == otherDate.day);
	}

}
