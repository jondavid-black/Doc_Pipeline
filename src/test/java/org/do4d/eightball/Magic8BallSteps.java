package org.do4d.eightball;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;


public class Magic8BallSteps {
	
	// TODO  This is not thread safe...change to picocontainer
	private Magic8Ball toy;
	private String lastResponse;
	
	@Given ("^I have a magic eight ball$")
	public void haveAMagicEightBall() throws Throwable {
	    toy = new StandardMagic8Ball();
	}

	@When ("^I ask will Mississippi State be the national champion$")
	public void askWillMississippiStateBeTheNationalChampion() throws Throwable {
		toy.ask("Will Mississippi State be the nation champions?");
		
	}
	
	@And ("^I shake the magic eight ball$")
	public void shakeTheMagicEightBall() throws Throwable {
		toy.shake();
	}

	@Then ("^I get a response$")
	public void getAResponse() throws Throwable {
		lastResponse = toy.look();
		assertNotNull(lastResponse);
		assertTrue(lastResponse.length() > 0);
	}

	@And ("^the response is one of the standard answers$")
	public void theResponseIsOneOfTheStandardAnswers() throws Throwable {
		List<String> allowedAnswers = Arrays.asList(new String[] {
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
				"Very doubtful." });
		assertTrue(allowedAnswers.contains(lastResponse));
	}
	
	@When ("^I ask will Mississippi State ever be the national champion$")
	public void askWillMississippiStateEverBeTheNationalChampion() throws Throwable {
		toy.ask("Will Mississippi State ever be the nation champions?");
		
	}

}
