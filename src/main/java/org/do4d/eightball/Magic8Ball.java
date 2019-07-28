package org.do4d.eightball;

/**
 * A Magic 8-Ball interface that allows the user to ask a question, shake the Magic 8-Ball,
 * and get a response.
 * 
 * @author jd
 *
 */
public interface Magic8Ball {

	/** 
	 * Allows the user to ask a question of the Magic 8-Ball.
	 * 
	 * @param question any true of false question the user would like answered
	 */
	void ask(String question);

	/**
	 * Introduces the energy needed by the Magic 8-Ball to produce a response to the question.
	 */
	void shake();

	/**
	 * Provides the user the response to the question they asked.
	 * 
	 * @return response to the user's question
	 */
	String look();

}