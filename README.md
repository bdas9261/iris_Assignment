WebservicesTest To Check USD Rates Against Multiple Currency API.
Used tools and frameworks
    1.RestAssured
    2.Serenity BDD
    3.Maven Repository.
    4.TestNg
    5.Json SchemaValidation.
    Main features
       1.Hybrid framework to test automation Web Api It is keyword-driven and data-driven framework. So, you can separate testing data in Excel sheet 
       with predefined schema and using some keywords inside Excel sheet you can validate the returned response body, headers and cookies.
       2.Supports both REST webservices testing.
       3.All classes and methods are implemented in Java with Maven repository to include all dependencies needed. REST-Assured is used to offer a 
       friendly DSL (Domain specific Languages) that describes a connection to an HTTP endpoint and expected results.
       4. Below Acceptance Creiteria are include as part of this framework.
             1.Api Call is sucessful and return valid price.
             2.Check the Status and Status Code Returned By the API Response.
             3.Fetch the USD Price Against the AED and Prices are in range between 3.6-3.7
             4.Verify that Api Response Time not Less Than 3 Second.
             5.Verify 162 Curriencies Pair returned By the API.
             6.Validated JSON Schema.
       
