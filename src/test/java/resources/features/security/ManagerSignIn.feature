Feature: As a irrigation manager I want to sign in to access the application's functionality.

  Scenario: User enters wrong credentials.
    Given an user with email "z@z.com" and with password "password"
    When that the credentials are incorrect
    Then the user receives an error message.
