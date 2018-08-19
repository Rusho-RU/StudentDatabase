package com.dragontwister.studentdatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StudentDBHandler dbHandler;
    EditText editName, editSurname, editMarks, editId;
    Button btnAddData, btnReset, btnViewAll, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new StudentDBHandler(this);

        editId = findViewById(R.id.editTextId);
        editName = findViewById(R.id.textName);
        editSurname = findViewById(R.id.textSurname);
        editMarks = findViewById(R.id.textMarks);
        btnAddData = findViewById(R.id.buttonAdd);
        btnReset = findViewById(R.id.buttonReset);
        btnViewAll = findViewById(R.id.buttonViewAll);
        btnUpdate = findViewById(R.id.buttonUpdate);
        addData();
        resetData();
        viewAll();
        updateData();
    }

    public void resetData(){
        btnReset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editName.getText().clear();
                        editSurname.getText().clear();
                        editMarks.getText().clear();
                    }
                }
        );
    }

    public void addData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbHandler.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());

                        if(isInserted)
                            Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Something went wrong! Data was not inserted!", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = dbHandler.getAllData();

                        if(result.getCount() == 0){
                            showMessage("ERROR!", "NO DATA FOUND!");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(result.moveToNext()){
                            buffer.append("Id: "+ result.getString(0) + "\n");
                            buffer.append("Name: "+ result.getString(1) + "\n");
                            buffer.append("Surname: "+ result.getString(2) + "\n");
                            buffer.append("Marks: "+ result.getString(3) + "\n\n");
                        }

                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void updateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = dbHandler.updateData(
                            editId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString()
                        );

                        if(isUpdated)
                            Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Something went wrong! Data was not Updated" +
                                            "!",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
