package org.do4d.eightball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is a simple command line driver for the Magic 8 Ball demonstration code.
 * 
 * @author jd
 *
 */
public class Driver {

	/**
	 * The main for the Driver.  The user is asked a question and provided the response
	 * from the magic 8 ball forever, or until the user enters a q.
	 * 
	 * @param args args are ignored by the application
	 * @throws IOException if something goes wrong reading from stdin
	 */
	public static void main(String[] args) throws IOException {
		Magic8Ball m8b = new DevOpsMagic8Ball();
		
		String question = "";
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.println("What is your question?  [enter q to quit]");
			question = in.readLine();
			if (question != null) {
				question = question.trim();
			} else {
				question = "q";
			}
			if (question.toLowerCase().equals("q")) {
				break;
			}
			m8b.ask(question);
			m8b.shake();
			System.out.println(m8b.look());
		}

	}

}
