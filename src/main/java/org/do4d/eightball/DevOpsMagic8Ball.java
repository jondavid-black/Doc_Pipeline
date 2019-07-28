package org.do4d.eightball;

import java.util.Random;

/**
 * The DevOps Magic 8-Ball works just like a standard Magic 8-Ball but adds a
 * DevOps quote to the response.
 * 
 * @author jd
 *
 */
public class DevOpsMagic8Ball extends StandardMagic8Ball {
	
	private final Random rand = new Random();
	
	
	
	private final String[] devopsQuotes = new String[] {
		"Agile and DevOps are for harnessing integration, interaction, and innovation. " 
				+ "- Pearl Zhu",
		"DevOps and its resulting technical, architectural, and cultural practices " 
				+ "represent a convergence of many philosophical and management movements " 
				+ "(including): Lean, Theory of Constraints, Toyota production system, " 
				+ "resilience engineering, learning organizations, safety culture, Human " 
				+ "factors, high-trust management cultures, servant leadership, " 
				+ "organizational change management, and Agile methods. - Gene Kim",
		"Devs are from Venus, Ops are from Mars. - Steven Haines",
		"Develop systems, not software. - Ben Butler Cole",
		"You build it, you run it. - Werner Vogels",
		"The most powerful tool we have as developers is automation. - Scott Hanselman",
		"If you automate a mess, you get an automated mess. - Rod Michael",
		"Deployment celebrations should be about the value of the new features, not " 
				+ "joyful relief that nothing went horribly wrong. - Rebecca Parsons",
		"DevOps is not a goal, but a never-ending process of continual improvement. - Jez Humble",
		"The best TDD can do, is assure that code does what the programmer thinks it " 
				+ "should do. That is pretty good BTW. — James Grenning",
		"If you think it’s expensive to hire a professional, wait until you hire an " 
				+ "amateur. — Red Adair",
		"Programming is not a zero-sum game. Teaching something to a fellow programmer " 
				+ "doesn’t take it away from you. — John Carmack",
		"A phased approach to continuous delivery is not only preferable, it’s " 
				+ "infinitely more manageable. — Maurice Kherlakian",
		"Programmers don’t burn out on hard work, they burn out on change-with-the-wind " 
				+ "directives and not ‘shipping’. — Mark Berry",
		"The key in such a transition to continuous delivery is to expect things to get " 
				+ "worse before you’ll be able to make them better. — Matthias Marschall",
		"Any roles involved in a project that do not directly contribute toward the goal " 
				+ "of putting valuable software in the hands of users as quickly as " 
				+ "possible should be carefully considered. — Stein Inge Morisbak",
		"To successfully implement continuous delivery, you need to change the culture of " 
				+ "how an entire organization views software development efforts. — Tommy Tynjä",
		"If you adopt only one agile practice, let it be retrospectives. Everything else " 
				+ "will follow. — Woody Zuill",
		"The key to following the continuous delivery path is to continually question " 
				+ "your own assumptions about what’s possible. — Jeff Sussna",
		"It is not the strongest of the species that survive, nor the most intelligent, " 
				+ "but the one most responsive to change. — Charles Darwin",
		"Showing a strong success and visible benefits is key to getting others to agree to " 
				+ "try your way of doing things. — Frederic Rivain",
		"Maturity models don't work. - Nichole Forsgren"
	};
	
	private String quote = devopsQuotes[0];
	
	/**
	 * Randomly generates the next DevOps Magic 8-Ball response.
	 */
	public void shake() {
		super.shake();
		int idx = rand.nextInt(devopsQuotes.length);
		quote = devopsQuotes[idx];
	}
	
	/**
	 * Gives the DevOps Magic 8-Ball response consisting of the standard Magic 8-Ball
	 * response plus a DevOps quote separated by a colon.
	 * 
	 * @return the response to the questions followed by a colon and a DevOps quote
	 */
	public String look() {
		return super.look() + " : " + quote;
	}

}
