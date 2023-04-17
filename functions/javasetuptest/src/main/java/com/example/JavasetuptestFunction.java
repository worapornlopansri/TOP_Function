package com.example;

import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.InvocationEvent;
import com.salesforce.functions.jvm.sdk.SalesforceFunction;
import com.salesforce.functions.jvm.sdk.data.Record;
import com.salesforce.functions.jvm.sdk.data.RecordWithSubQueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe JavasetuptestFunction here.
 */
public class JavasetuptestFunction implements SalesforceFunction<FunctionInput, FunctionOutput> {
  private static final Logger LOGGER = LoggerFactory.getLogger(JavasetuptestFunction.class);

  @Override
  public FunctionOutput apply(InvocationEvent<FunctionInput> event, Context context)
      throws Exception {

    List<RecordWithSubQueryResults> records = context.getOrg().get().getDataApi().query(
        "SELECT DeveloperName, Requester_Manager__c, Requester_VP__c, DGVP__c FROM DG_Service_Approvers_Setting__mdt")
        .getRecords();

    LOGGER.info("Function successfully queried {} account records!", records.size());

    // List<Account> accounts = new ArrayList<>();
    // for (Record record : records) {
    // String id = record.getStringField("DeveloperName").get();
    // String name = record.getStringField("Requester_Manager__c").get();

    // accounts.add(new Account(id, name));
    // }

    List<Approver> approvers = new ArrayList<Approver>();
    for (Record record : records) {
      String developername = record.getStringField("DeveloperName").get();
      String requestermanager = record.getStringField("Requester_Manager__c").get();
      String requesterVP = record.getStringField("Requester_VP__c").get();
      String DGVP = record.getStringField("DGVP__c").get();

      Approver app = new Approver(developername, requestermanager, requesterVP, DGVP);

      approvers.add(app);

    }
    return new FunctionOutput(approvers);
  }
}
