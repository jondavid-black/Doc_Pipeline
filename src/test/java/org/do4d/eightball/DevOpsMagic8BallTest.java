package org.do4d.eightball;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DevOpsMagic8BallTest {

	@Test
	void testStandardUsage() {
		Magic8Ball b = new DevOpsMagic8Ball();
		
		b.ask("Will this test pass?");
		b.shake();
		String answer = b.look();
		assertNotNull(answer);
		assertTrue(answer.indexOf(":") > 1);
	}

}
