Feature:
  Background: Database connectivity established
    * Database connection established

  @DB
    #Add 1 data to the email_config table whose passcode is protected with sha2 method.
  Scenario:add data whose passcode is protected with sha2 method  into the database
    * I insert the new data to the email_config table
    * Verify that 1 added to the table
    * Database closed
    #US25
  @US25
    Scenario: data control into the patients table
      * Query prepared into the patients table
      * Verify result is returned
      * Database closed


