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

import java.net.URL;
import java.net.HttpURLConnection;

import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.StringBuilder;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Describe AribapendingFunction here.
 */
public class AribapendingFunction implements SalesforceFunction<FunctionInput, FunctionOutput> {
  private static final Logger LOGGER = LoggerFactory.getLogger(AribapendingFunction.class);

  @Override
  public FunctionOutput apply(InvocationEvent<FunctionInput> event, Context context)
      throws Exception {

    HttpURLConnection connection = null;

    String urlParameters = "grant_type=openapi_2lo";

    URL url = new URL("https://api.ariba.com/v2/oauth/token");
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
    connection.setRequestProperty("Authorization", "Basic MTQyM2ZjNjUtNzMzOS00OThjLTkwNGQtZThmODQ2ZTQwYjVjOmczdnQ0OE9KNHNZYmtyS05OVVVmWXg4YUFkSmNwd0g0");

    connection.setUseCaches(false);
    connection.setDoOutput(true);

    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.close();

    InputStream is = connection.getInputStream();
    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
      response.append(line);
      response.append("\r");
    }
    rd.close();

    connection.disconnect();

    JSONObject json = new JSONObject(response.toString());
    LOGGER.info(response.toString());

    HttpURLConnection connectionPending = null;
    String accessToken = json.getString("access_token");
    URL urlPending = new URL("https://openapi.ariba.com/api/approval/v1/prod/pendingApprovables?realm=thaioil-t&limit=10&user=prowumporn&passwordAdapter=PasswordAdapter1");
    connectionPending = (HttpURLConnection) urlPending.openConnection();
    connectionPending.setRequestMethod("GET");
    connectionPending.setRequestProperty("Content-Type", "application/json");
    connectionPending.setRequestProperty("apiKey", "0QcCPTXD91RM2zydv5gW9pr9ETHUrbZd");
    connectionPending.setRequestProperty("Authorization", "Bearer " + accessToken);

    connectionPending.setUseCaches(false);
    connectionPending.setDoOutput(true);

    InputStream isPending = connectionPending.getInputStream();
    BufferedReader rdPending = new BufferedReader(new InputStreamReader(isPending));
    StringBuilder responsePending = new StringBuilder();
    String linePending;
    while ((linePending = rdPending.readLine()) != null) {
      responsePending.append(linePending);
      responsePending.append("\r");
    }
    rdPending.close();

    connectionPending.disconnect();

    JSONArray arrayPending = new JSONArray(responsePending.toString());
    for (int i = 0; i < arrayPending.length(); i++) {
      JSONObject jsonPending = arrayPending.getJSONObject(i);

      LOGGER.info("uniqueName: " + jsonPending.getString("uniqueName"));
      LOGGER.info("documentType: " + jsonPending.getString("documentType"));
      LOGGER.info("description: " + jsonPending.getString("description"));
      LOGGER.info("assignedDate: " + jsonPending.getString("assignedDate"));
      LOGGER.info("approver: " + jsonPending.getString("approver"));
      LOGGER.info("email: " + jsonPending.getString("email"));
      LOGGER.info("fullURL: " + jsonPending.getString("fullURL"));
      LOGGER.info("attachments: " + jsonPending.getJSONArray("attachments").toString());
    }

    List<RecordWithSubQueryResults> records =
        context.getOrg().get().getDataApi().query("SELECT Id, Name FROM Account").getRecords();

    LOGGER.info("Function successfully queried {} account records!", records.size());

    List<Account> accounts = new ArrayList<>();
    for (Record record : records) {
      String id = record.getStringField("Id").get();
      String name = record.getStringField("Name").get();

      accounts.add(new Account(id, name));
    }

    return new FunctionOutput(accounts);
  }
}
