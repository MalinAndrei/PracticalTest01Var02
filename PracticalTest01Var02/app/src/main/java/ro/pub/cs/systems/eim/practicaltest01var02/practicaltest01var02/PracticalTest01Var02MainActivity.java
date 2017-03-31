package ro.pub.cs.systems.eim.practicaltest01var02.practicaltest01var02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {

    private EditText firstEditText = null;
    private EditText secondEditText = null;
    private Button plusButton = null;
    private Button minusButton = null;
    private Button navigateToSecondaryActivityButton = null;
    private TextView displayText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        firstEditText = (EditText)findViewById(R.id.first_edit_text);
        secondEditText = (EditText)findViewById(R.id.second_edit_text);
        firstEditText.setText(String.valueOf(0));
        secondEditText.setText(String.valueOf(0));

        displayText = (TextView)findViewById(R.id.display_text_view);

        plusButton = (Button)findViewById(R.id.plus_button);
        minusButton = (Button)findViewById(R.id.minus_button);

        plusButton.setOnClickListener(buttonClickListener);
        minusButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("firstCount")) {
                firstEditText.setText(savedInstanceState.getString("firstCount"));
            } else {
                firstEditText.setText(String.valueOf(0));
            }
            if (savedInstanceState.containsKey("secondCount")) {
                secondEditText.setText(savedInstanceState.getString("secondCount"));
            } else {
                secondEditText.setText(String.valueOf(0));
            }
        } else {
            firstEditText.setText(String.valueOf(0));
            secondEditText.setText(String.valueOf(0));
        }

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.plus_button:
                    int firstNumber = Integer.parseInt(firstEditText.getText().toString());
                    int secondNumber = Integer.parseInt(secondEditText.getText().toString());

                    displayText.setText(String.valueOf(firstNumber)+"+"+String.valueOf(secondNumber)+"="+String.valueOf(firstNumber+secondNumber));
                    break;
                case R.id.minus_button:
                    firstNumber = Integer.parseInt(firstEditText.getText().toString());
                    secondNumber = Integer.parseInt(secondEditText.getText().toString());

                    displayText.setText(String.valueOf(firstNumber)+"-"+String.valueOf(secondNumber)+"="+String.valueOf(firstNumber-secondNumber));
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02SecondaryActivity.class);
                    displayText.setText("aa"+displayText.getText());
                    intent.putExtra("operation", displayText.getText());
                    startActivityForResult(intent, 1);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("firstCount", firstEditText.getText().toString());
        savedInstanceState.putString("secondCount", secondEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("firstCount")) {
            firstEditText.setText(savedInstanceState.getString("firstCount"));
        } else {
            firstEditText.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("secondCount")) {
            secondEditText.setText(savedInstanceState.getString("secondCount"));
        } else {
            secondEditText.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
