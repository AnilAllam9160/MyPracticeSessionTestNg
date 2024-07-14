Feature: Search Trains On Particular Date

  Scenario: Login And Search With Valid Details

      Given The User is on IRCTC Search Trains Page
      When Login to IRCTC Application
      When Select From station
      When Select To station
      When Select Date of Journey
      When Select Category
      Then Click On Search