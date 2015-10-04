package com.machmoney.nessiebankmachmoney;

import com.reimaginebanking.api.java.models.Account;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by quinn_000 on 10/3/2015.
 */
public class AccountAdapter extends ArrayAdapter<Account>
{
    private static class ViewHolder {
        private TextView NicknameView;
        private TextView BalanceView;
    }

    public AccountAdapter(Context context, int layoutResourceId, ArrayList<Account> accounts) {
        super(context, layoutResourceId, accounts);


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.NicknameView = (TextView) convertView.findViewById(R.id.nickname_view);
            viewHolder.BalanceView = (TextView)convertView.findViewById(R.id.balance_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Account account = getItem(position);
        if (account != null) {
            viewHolder.NicknameView.setText(account.getNickname());
            viewHolder.BalanceView.setText("$" + account.getBalance());
        }

        return convertView;
    }
}