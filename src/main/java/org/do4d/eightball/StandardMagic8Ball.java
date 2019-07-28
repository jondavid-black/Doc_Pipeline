package org.do4d.eightball;

import java.util.Random;

/**
 * This is a standard Magic 8 Ball using the 20 standard responses.
 * 
 * @author jd
 *
 */
public class StandardMagic8Ball implements Magic8Ball {

	private final Random rand = new Random();

	private final String[] possibleAnswers = new String[] {
			"It is certain.",
			"It is decidedly so.",
			"Without a doubt.",
			"Yes - definitely.",
			"You may rely on it.",
			"As I see it, yes.",
			"Most likely.",
			"Outlook good.",
			"Yes.",
			"Signs point to yes.",
			"Reply hazy, try again",
			"Ask again later.",
			"Better not tell you now.",
			"Cannot predict now.",
			"Concentrate and ask again.",
			"Don't count on it.",
			"My reply is no.",
			"My sources say no.",
			"Outlook not so good.",
			"Very doubtful." };
	
	private String response = possibleAnswers[0];

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.do4d.eightball.Magic8Ball#ask(java.lang.String)
	 */
	@Override
	public void ask(String question) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.do4d.eightball.Magic8Ball#shake()
	 */
	@Override
	public void shake() {
		int idx = rand.nextInt(possibleAnswers.length);
		response = possibleAnswers[idx];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.do4d.eightball.Magic8Ball#look()
	 */
	@Override
	public String look() {

		return response;

	}

}
