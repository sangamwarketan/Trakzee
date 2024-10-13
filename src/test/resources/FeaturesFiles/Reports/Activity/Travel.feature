Feature: Activity Travel Report

  @Start @Travel @skipBefore
  Scenario Outline: User Login in Application
    Then user enter "<Username>" and "<Password>"
    And Click on Login Button
    Then User Handled Alert popup
    And User on main page
    And "<Module>" Module "<SubModule>" Sub Module "<Screen>" Screen and Header name is "<HeaderName>"

    Examples: 
      | Module  | SubModule | Screen | HeaderName     |
      | Reports | Activity  | Travel | Travel Summary |

 
  Scenario: Navigation to Screen
    Given Navigate to Screen
    And Verify the User on Screen

    
     ############################## Filter ############################
  @End
  Scenario Outline: Verify the filter should be open First when we Open the Report
    Then Verify the Filter screen is open