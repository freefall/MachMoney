package com.machmoney.nessiebankmachmoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.reimaginebanking.api.java.NessieClient;
import com.reimaginebanking.api.java.NessieException;
import com.reimaginebanking.api.java.NessieResultsListener;
import com.reimaginebanking.api.java.models.Customer;
import com.reimaginebanking.api.java.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountPicker extends AppCompatActivity {

    ArrayList<Account> accounts = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_picker);

        final ListView accountListView = (ListView) findViewById(R.id.account_list);

        NessieClient nessieClient = NessieClient.getInstance();
        nessieClient.setAPIKey("dbd6d202a2b0fd7c92a1f998045ca9f2");
        nessieClient.getCustomerAccounts("560f0205f8d8770df0ef9b57", new NessieResultsListener() {
            @Override
            public void onSuccess(Object result, NessieException e) {
                if (e == null) {
                    //There is no error, do whatever you need here.
                    // Cast the result object to the type that you are requesting and you are good to go
                    accounts = (ArrayList<Account>) result;
                    AccountAdapter adapter = new AccountAdapter(AccountPicker.this, R.layout.list_item, accounts);
                    accountListView.setAdapter(adapter);
                    accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Account account = (Account)(parent.getItemAtPosition(position));
                            Log.d("ACCOUNT CLICKED", account.getNickname());
                            //research how to use zxing to create a qrcode
                            //create separate activity to take withdraw
                            //pass withdrawal amount in intentExtras
                            //start new activity which generates qr code (zxing) using amount
                            //in intent extras
                            //display generated qr code in imageview
                            //mix = "some_customer_id;amount"
                            //"mix" = "abc123;5.32";
                            //string[] pieces = mix.split(":")
                            //id = pieces[0]
                            //amount = Double.valueOf(pieces[1])
                        }
                    });
                    String text = accounts.toString();
                    Log.d("CustomerOutput", text);
                } else {
                    //There was an error. Handle it here
                    Log.e("Error", e.toString());
                }
            }
        });


    }


}
