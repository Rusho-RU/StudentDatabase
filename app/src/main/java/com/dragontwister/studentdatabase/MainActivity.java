package com.dragontwister.studentdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StudentDBHandler dbHandler;
    EditText editName, editSurname, editMarks;
    Button btnAddData, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new StudentDBHandler(this);

        editName = findViewById(R.id.textName);
        editSurname = findViewById(R.id.textSurname);
        editMarks = findViewById(R.id.textMarks);
        btnAddData = findViewById(R.id.buttonAdd);
        btnReset = findViewById(R.id.buttonReset);
        addData();
        resetData();
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
                        boolean isInserted = dbHandler.inserData(editName.getText().toString(),
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
}
