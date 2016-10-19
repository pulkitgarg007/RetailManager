Feature: Smoke verification of service

Scenario: Service running after deploy
  Given the service has been deployed
  When the root endpoint is queried
  Then the response should be successful
