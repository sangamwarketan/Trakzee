Feature: HomePage Features

  @Start @Travel
  Scenario Outline: User Login in Application
    Then user enter "<Username>" and "<Password>"
    And Click on Login Button
    Then User Handled Alert popup
    And User on main page

  Scenario Outline: Check The Main Manus List
    Given User Get the list of menus "<Module>"

    Examples: 
      | Module                                        |
      | Dashboard, Settings, Reports, Chart, Tracking |

  Scenario Outline: Check The Navigations
    And Check the Navigation of Module "<Module>"
      | Module    | SubModule |
      | Dashboard |           |

    Examples: 
      | Module                                         | SubModule |
      | Dashboard, Settings, Reports, Charts, Tracking | Dashboard,     |

  @End
  Scenario: User Logout From Application
    And User click on UserIcon
    Then User click on Logout
