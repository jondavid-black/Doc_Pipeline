package org.do4d.eightball;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


class Magic8BallTest {
	
	private final String[] allowedAnswers = new String[] {
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

	@Test
	void testStandardUsage() {
		Magic8Ball b = new StandardMagic8Ball();
		
		b.ask("Will this test pass?");
		b.shake();
		String answer = b.look();
		assertNotNull(answer);
		
	}

	@Test
	void testForgotToShake() {
		Magic8Ball b = new StandardMagic8Ball();
		String answer = b.look();
		b.ask("Do I have to shake this thing?");
		assertEquals(answer, b.look());
	}

	@Test
	void testShakingIsGenerallyRandom() {
		Magic8Ball b = new StandardMagic8Ball();
		int numChanged = 0;
		int numSame = 0;
		
		String last = b.look();
		for (int i = 0; i < 100; i++) {
			b.shake();
			String answer = b.look();
			if (last.equals(answer)) {
				numSame++;
			} else {
				numChanged++;
			}
		}
		assertTrue(numChanged > numSame);
	}
	
	@Test
	void testThatAllStandardAnswersArePresent() {
		Magic8Ball b = new StandardMagic8Ball();
		
		Map<String, Integer> answers = new HashMap<>();
		for (int i = 0; i < allowedAnswers.length; i++) {
			answers.put(allowedAnswers[i], 0);
		}

		for (int i = 0; i < 1000; i++) {
			b.shake();
			String answer = b.look();
			if (!answers.containsKey(answer)) {
				fail("Encountered unexpected answer: " + answer);
			} else {
				// increment the answer count
				int val = answers.get(answer);
				val++;
				answers.put(answer, val);
			}
		}
		
		for (String key : answers.keySet()) {
			assertTrue(answers.get(key) > 0, "Standard answer \"" + key + "\" not observed.");
		}
	}

}
