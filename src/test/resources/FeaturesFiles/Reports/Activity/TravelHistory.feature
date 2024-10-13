Feature: Activity Travel History Report

  @Start @Travel
  Scenario Outline: User Login in Application
    Then user enter "<Username>" and "<Password>"
    And Click on Login Button
    Then User Handled Alert popup
    And User on main page
    And "<Module>" Module "<SubModule>" Sub Module "<Screen>" Screen and Header name is "<HeaderName>"

    Examples: 
      | Module  | SubModule | Screen         | HeaderName     |
      | Reports | Activity  | Travel History | Travel History |

  @End
  Scenario: Navigation to Screen
    Given Navigate to Screen
    And Verify the User on Screen
