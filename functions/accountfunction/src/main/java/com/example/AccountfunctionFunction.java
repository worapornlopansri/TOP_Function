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
 * Describe AccountfunctionFunction here.
 */
public class AccountfunctionFunction implements SalesforceFunction<FunctionInput, FunctionOutput> {
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountfunctionFunction.class);

  @Override
  public FunctionOutput apply(InvocationEvent<FunctionInput> event, Context context)
      throws Exception {

    List<RecordWithSubQueryResults> records =
        context.getOrg().get().getDataApi().query("SELECT Id, Name ,Location__Latitude__s,Location__Longitude__s FROM Account where  Id ='"+event.getData().getId()+"'"
        ).getRecords();

        List<Account> accounts = new ArrayList<>();
    for (Record record : records) {
      String id = record.getStringField("Id").get();
      String name = record.getStringField("Name").get();
      String Location__Latitude__s = "";
      String Location__Longitude__s = "";
      System.out.println(record.getStringField("Location__Latitude__s"));
      
      if(record.getStringField("Location__Latitude__s") != null && !record.getStringField("Location__Latitude__s").isEmpty()){
        Location__Latitude__s = record.getStringField("Location__Latitude__s").get();
      }
      if(record.getStringField("Location__Longitude__s") != null && !record.getStringField("Location__Longitude__s").isEmpty()){
        Location__Longitude__s = record.getStringField("Location__Longitude__s").get();
      }
      accounts.add(new Account(id, name ,Location__Latitude__s,Location__Longitude__s));
    }

    return new FunctionOutput(accounts);
  }
}


