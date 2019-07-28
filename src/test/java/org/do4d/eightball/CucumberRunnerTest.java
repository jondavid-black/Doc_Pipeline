package org.do4d.eightball;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith (Cucumber.class)
@CucumberOptions (
//		plugin = { "junit:build/reports/cucumber/cucumber-junit.xml",
//                "json:build/reports/cucumber/cucumber.json",
//                "pretty:build/reports/cucumber/cucumber-pretty.txt",
//                "usage:build/reports/cucumber/cucumber-usage.json"
//                },
        features = { "src/test/resources" },
        glue = { "org/do4d/eightball" }
	)
public class CucumberRunnerTest {}
