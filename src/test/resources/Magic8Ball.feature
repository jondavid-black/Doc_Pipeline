Feature: Predict the future with a Magic 8 Ball

  Req-001 = Magic 8 ball shall allow the user to ask a question.
  Req-002 = Magic 8 ball shall provide the user a response.
  Req-003 = Magic 8 ball response shall conform to the standrd
            as defined on wikipedia 
            (https://en.wikipedia.org/wiki/Magic_8-Ball#Possible_answers)


  Scenario: Predict the college football national champion
    Given I have a magic eight ball
    When I ask will Mississippi State be the national champion
    And I shake the magic eight ball
    Then I get a response
    And the response is one of the standard answers

  Scenario: Come to my senses and ask a realistic question
    Given I have a magic eight ball
    When I ask will Mississippi State ever be the national champion
    And I shake the magic eight ball
    Then I get a response
    And the response is one of the standard answers