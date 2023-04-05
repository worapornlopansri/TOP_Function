export default async function (event, context, logger) {
  logger.info(`Invoking salesforcesdkjs function with payload ${JSON.stringify(event.data || {})}`);
  //test test 4/3/2023
  // Extract properties from payload
  const { name, accountNumber, industry, type, website } = event.data;
//test may
  // Validate the payload params
  if (!name) {
    throw new Error(`Please provide account name345`);
  }

  // Define a record using the RecordFoCreate type and providing the Developer Name
  const account = {
    type: "Account",
    fields: {
      id : id,
      Name: `${name}-${Date.now()}-Modified123`,
      AccountNumber: 'test1'
    },
  };

  try {
    // Insert the record using the SalesforceSDK DataApi and get the new Record Id from the result
    const UpdateAccount = await context.org.dataApi.update(account);

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