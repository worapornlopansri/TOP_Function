export default async function (event, context, logger) {
  logger.info(`Invoking salesforcesdkjs function with payload ${JSON.stringify(event.data || {})}`);

  // Extract properties from payload
  const { name, accountNumber, industry, type, website } = event.data;

  // Validate the payload params
  if (!name) {
    throw new Error(`Please provide account name345`);
  }

  // Define a record using the RecordFoCreate type and providing the Developer Name
  const account = {
    type: "Account",
    fields: {
      Name: `${name}-${Date.now()}-Modified123`,
      AccountNumber: accountNumber,
      Industry: industry,
      Type: type,
      Website: website,
    },
  };

  try {
    // Insert the record using the SalesforceSDK DataApi and get the new Record Id from the result
    const { id: recordId } = await context.org.dataApi.create(account);

    // Query Accounts using the SalesforceSDK DataApi to verify that your new Account was created.
    const soql = `SELECT Fields(STANDARD) FROM Account WHERE Id = '${recordId}'`;
    const queryResults = await context.org.dataApi.query(soql);
    //return queryResults;
    return myData();
  } catch (err) {
    // Catch any DML errors and pass the throw an error with the message
    const errorMessage = `Failed to insert record. Root Cause: ${err.message}`;
    logger.error(errorMessage);
    throw new Error(errorMessage);
  }
}

function myData() { 
  return '23'; 
} 
// Test