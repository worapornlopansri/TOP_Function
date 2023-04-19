/**
 * Describe Inspecschedulerpt here.
 *
 * The exported method is the entry point for your code when the function is invoked.
 *
 * Following parameters are pre-configured and provided to your function on execution:
 * @param event: represents the data associated with the occurrence of an event, and
 *                 supporting metadata about the source of that occurrence.
 * @param context: represents the connection to Functions and your Salesforce org.
 * @param logger: logging handler used to capture application logs and trace specifically
 *                 to a given execution of a function.
 */
export default async function (event, context, logger) {
  logger.info(`Invoking Inspecschedulerpt with payload ${JSON.stringify(event.data || {})}`);

  // const results = await context.org.dataApi.query('SELECT Id, Name FROM Account');

  // logger.info(JSON.stringify(results));

  // return results;

  try {
    // Query Accounts using the SalesforceSDK DataApi to verify that our new Account was created.
    // Query Work_Clearance__c using the SalesforceSDK DataApi to verify that our new Account was created.
    const soql = `SELECT Work_Clearance_Status__c,Authorized_Signature__c, Area_Code__c , OPSMainWorkCenter__c, Authorized_Date_UI__c FROM Work_Clearance__c WHERE Authorized_Date_UI__c = THIS_YEAR AND (Work_Clearance_Status__c = 'Created' Or Work_Clearance_Status__c = 'In Progress')`;
    const queryResults = await context.org.dataApi.query(soql);
    return queryResults;
  } catch (err) {
    // Catch any DML errors and pass the throw an error with the message
    const errorMessage = `Failed to insert record. Root Cause: ${err.message}`;
    logger.error(errorMessage);
    throw new Error(errorMessage);
  }
}
