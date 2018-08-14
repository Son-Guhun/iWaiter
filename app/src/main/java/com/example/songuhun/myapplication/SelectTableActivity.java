package com.example.songuhun.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.songuhun.objects.Table;
import com.example.songuhun.test.TestStuff;

import java.util.TreeMap;

public class SelectTableActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    static private TreeMap<String, Table> tablesMap = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        final EditText tableSelect = findViewById(R.id.editText_tableSelect);

        tableSelect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    String s = tableSelect.getText().toString();

                    if (tablesMap.containsKey(s)) {
                        selectTable(tablesMap.get(s));
                    }
                    else {
                        Table table = new Table(s);
                        tablesMap.put(s, table);
                        selectTable(table);
                    }

                    textView.setText("");
                    startActivityForResult(new Intent(SelectTableActivity.this, CustomerOrderActivity.class), REQUEST_CODE);
                };
                return  true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("finished"))
                        tablesMap.remove(getSelectedTable().getIdentifier());
                }
                break;
            }
        }
    }

    private static void selectTable(Table table){
        TestStuff.currentTable = table;
    }

    public static Table getSelectedTable(){
        return TestStuff.currentTable;
    }
}
