Feature: Add new MR

  Scenario: The first MR is created
    Given no MR have been created yet
    When a user creates a MR
    Then that MR appears on top of the list of MR

