public with sharing class FunctionApex {
    public static void test() {
        functions.Function myFunction = functions.Function.get('TOP_Function.myfunction');
        // Pass a callback to Function.invoke to perform an asynchronous invocation.
        functions.FunctionInvocation invocation = myFunction.invoke('{"fields":["Id","Name"]}', new DemoCallback());
    }

    public class DemoCallback implements functions.FunctionCallback {
        public void handleResponse(functions.FunctionInvocation result) {
            // Handle result of function invocation
            String jsonResponse = result.getResponse();
            System.debug('Got response ' + jsonResponse);

            // Log error, if applicable
            if (result.getStatus() == functions.FunctionInvocationStatus.ERROR) {
                functions.FunctionInvocationError resultError = result.getError();
                if (resultError != null) {
                    System.debug('Error type: ' + resultError.getType());
                    System.debug('Error message: ' + resultError.getMessage());
                } else {
                    System.debug('Error: UNKNOWN');
                }
                return;
            }
            // Successful response, deserialize the response to an Apex object.
            JSONParser parser = JSON.createParser(jsonResponse);
            Response response = (Response)parser.readValueAs(Response.class);
            // Verify that the Function actually wrote to the org.
            Account account = [ SELECT Name FROM Account WHERE Id = :response.accountId ];
            System.debug('Found account with name ' + account.Name);
        }
    }

    // A plain old data class to which we can deserialize the Function's response.
    public class Response {
        public String accountId;
        public String contactId;
        public String opportunityId;
    }
}