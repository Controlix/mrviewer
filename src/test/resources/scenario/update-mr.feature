Feature: Update an existing MR

  Background: Some MR have already been created
    Given some MR have already been created

  Scenario: Comment an MR that I haven't looked at yet
    Given a MR that I haven't looked at yet
    When a user comments that MR
    Then that MR does not appear on top of the list of MR

  Scenario: Comment an MR that I have already looked at
    Given a MR that I have already looked at
    When a user comments that MR
    Then that MR appears on top of the list of MR
