package com.example.sqllite_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite_project.DBHelperFolder.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText name,field,age,desc;
    Button insert,Update,Delete,View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView Assign Id
        name=findViewById(R.id.Name);
        field=findViewById(R.id.Field);
        age=findViewById(R.id.Age);
        desc=findViewById(R.id.Desc);

        //Buttion Assign Id
        insert=findViewById(R.id.insert);
        Update=findViewById(R.id.Update);
        Delete=findViewById(R.id.Delete);
        View=findViewById(R.id.View);

        DBHelper dbHelper= new DBHelper(MainActivity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                dbHelper.InsertData(name.getText().toString(),field.getText().toString(),age.getText().toString(),desc.getText().toString());

                Toast.makeText(MainActivity.this, "New Data Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                dbHelper.UpdateData(name.getText().toString(),field.getText().toString(),age.getText().toString(),desc.getText().toString());
                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                dbHelper.Delete(name.getText().toString());
                Toast.makeText(MainActivity.this, " Data Deleted", Toast.LENGTH_SHORT).show();

            }
        });
       View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Cursor result = dbHelper.getData();

                while (result.moveToNext())
                {
                    Log.d("TAG", result.getString(0)+"");
                }
            }
        });




    }
}