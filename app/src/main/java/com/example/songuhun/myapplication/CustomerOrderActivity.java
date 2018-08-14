package com.example.songuhun.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.songuhun.adapters.OrderListAdapter;
import com.example.songuhun.objects.DishCategory;
import com.example.songuhun.adapters.DishMenuAdapter;
import com.example.songuhun.objects.Order;
import com.example.songuhun.test.TestStuff;
import com.example.songuhun.utils.Utilities;

import java.util.ArrayList;

public class CustomerOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        RecyclerView myList2 = findViewById(R.id.recycler1);
        myList2.setHasFixedSize(true);
        myList2.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<Order> orders = SelectTableActivity.getSelectedTable().getOrders();
        final OrderListAdapter orderListAdapter = new OrderListAdapter(orders);
        myList2.setAdapter(orderListAdapter);

        final DishMenuAdapter listAdapter = loadDishMenuExpandableList(orders, orderListAdapter);


        EditText editText = findViewById(R.id.editText_placeOrder);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int after) {
                listAdapter.filterData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @NonNull
    private DishMenuAdapter loadDishMenuExpandableList(final ArrayList<Order> orders, final OrderListAdapter orderListAdapter) {
        ExpandableListView myList = findViewById(R.id.expandableListView_orderMenu);
        ArrayList<DishCategory> parentList = TestStuff.dishMenu;

        final DishMenuAdapter listAdapter = new DishMenuAdapter(CustomerOrderActivity.this, parentList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DishMenuAdapter.DishView tv = (DishMenuAdapter.DishView) v;
                final EditText editText0 = new EditText(v.getContext());
                editText0.setTag("details");
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                        .setTitle("Pedido: "+((TextView) v).getText())
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setMessage("Observações:")
                        .setView(editText0)
                        .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                orders.add(new Order(tv.getDish(), editText0.getText().toString()));
                                orderListAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        myList.setAdapter(listAdapter);
        return listAdapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_orders, menu);

        Utilities.allowIconsInOverflowMenu(menu);

        menu.findItem(R.id.action_finish).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent returnData = new Intent();
                returnData.putExtra("finished", 0);
                setResult(Activity.RESULT_OK, returnData);


                finish();
                return true;
            }
        });
        return true;
    }
}
