Feature: Common Report SCenarios

  #@Start @Common
  Scenario Outline: User Login in Application
    Then user enter "<Username>" and "<Password>"
    And Click on Login Button
    Then User Handled Alert popup
    And User on main page
    And "<Module>" Module "<SubModule>" Sub Module "<Screen>" Screen and Header name is "<HeaderName>"

    Examples: 
      | Module | SubModule | Screen | HeaderName |
      |        |           |        |            |

  Scenario Outline: Open "<Screen>" "<Module>"
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Then User On "<HeaderName>" page

    Examples: 
      | Module | Screen |
      |        |        |

  ############################## Filter ############################
  Scenario Outline: Verify the filter should be open First when we Open the Report
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Given We are on Already Summary Page of Report
      | Report Name    |
      | Travel Summary |
    Then Verify the Filter screen is open

  #Header Buttons
  @Common
  Scenario Outline: Verify the All button is present on Header tab of Summary Screen
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Then User On "<HeaderName>" page
    Given Filter Should be close
    When Check All button is present on Header tab of Report Summary Screen "<List>" "<Extra Icons>"

    Examples: 
      | List | Extra Icons |
      |      |             |

  #Bottom Buttons
  @Common
  Scenario Outline: Verify the All button is present on Bottom tab of Summary Screen
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Then User On "<HeaderName>" page
    Given Filter Should be close
    When Check All button is present on Bottom tab of Summary Screen "<List Of Buttons>" "<Other fields>"

    Examples: 
      | List Of Buttons | Other fields |
      |                 |              |

  #Column Names
  @Common
  Scenario Outline: Verify the All Coloumn name is present on Summary Report
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Then User On "<HeaderName>" page
    Given Filter Should be close
    And Setting set up should be Deleted of Report
    And Check All Columns name are Present on Summary Screen "<List>"

    Examples: 
      | List |
      |      |

  ############################## Filter ############################
  @Common
  Scenario Outline: Verify the filter should be open First when we Open the Report
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Given We are on Already Summary Page of Report
      | Report Name    |
      | Travel Summary |
    Then Verify the Filter screen is open

  @Common
  Scenario Outline: Verify all the Fields is Present on LHS of Filter screen
    And Click on "<Module>" Module
    Then Go to "<SubModule>" Sub Module
    And Click on "<Screen>"
    Given We are on Already Summary Page of Report
      | Report Name    |
      | Travel Summary |
    Then Verify the Filter screen is open
    And Check all the Fields is Present on LHS of Filter screen "<List>"

    Examples: 
      | List |
