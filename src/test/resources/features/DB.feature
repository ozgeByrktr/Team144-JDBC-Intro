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

    @US36
    Scenario: Add 3 data to organisations table at the same time.
      * Add 3 data to organisations table at the same time
      * 3 Enter the data in bulk. Check that it is added to the table.
      * Database closed
      @US04
      Scenario Outline: Date control into the appointment table
        * prepare query List appointment table
        * Verify the list results <ogleden_once> <ogleden_sonra> obtained
        * Database closed
        Examples:
          | ogleden_once | ogleden_sonra |
          |118           |53             |



