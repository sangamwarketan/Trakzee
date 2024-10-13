Feature: Login Page Features
 
Scenario: Log in Application of "<Server>" Server
Given User is on login page
When We are Check All the Buttons and Input Field Are visible and Working
Then user enter "<Username>" and "<Password>"
And Click on Login Button
Then User Handled Alert popup
And User on main page

