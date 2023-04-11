/**
 * Describe Notificationbell here.
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
  logger.info(`Invoking Notificationbell with payload ${JSON.stringify(event.data || {})}`);

  const results = await context.org.dataApi.query('SELECT Application__c, Status__c, Subject__c FROM OPP_Notification__c');

  logger.info(JSON.stringify(results));

  return 'test new function';
}
