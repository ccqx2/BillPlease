package sg.edu.rp.c346.id21000243.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.controls.templates.ToggleRangeTemplate;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView tvTotalPay;
    TextView tvEachPay;
    EditText etAmount;
    EditText etPax;
    EditText etDiscount;
    ToggleButton tgSvs;
    ToggleButton tgGst;
    ToggleButton tgSplit;
    ToggleButton tgReset;
    RadioGroup rgCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalPay = findViewById(R.id.textViewTotal);
        tvEachPay = findViewById(R.id.textViewEach);
        etAmount = findViewById(R.id.editTextInputAmount);
        etPax = findViewById(R.id.editTextInputPax);
        etDiscount = findViewById(R.id.editTextInputDiscount);
        tgSvs = findViewById(R.id.toggleButtonSvs);
        tgGst = findViewById(R.id.toggleButtonGST);
        tgSplit = findViewById(R.id.toggleButtonSplit);
        tgReset = findViewById(R.id.toggleButtonSplit);
        rgCash = findViewById(R.id.radioGroupPayment);

        tgSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAmount.getText().toString().trim().length() != 0 && etPax.getText().toString().trim().length() != 0) {
                    double newAmount = 0.0;
                    if (!tgSvs.isChecked() && !tgGst.isChecked()) {
                        newAmount = Double.parseDouble(etAmount.getText().toString());
                    } else if (tgSvs.isChecked() && !tgGst.isChecked()) {
                        newAmount = Double.parseDouble(etAmount.getText().toString()) * 1.1;
                    } else if (!tgSvs.isChecked() && tgGst.isChecked()) {
                        newAmount = Double.parseDouble(etAmount.getText().toString()) * 1.07;
                    } else {
                        newAmount = Double.parseDouble(etAmount.getText().toString()) * 1.17;
                    }

                    if (etDiscount.getText().toString().trim().length() != 0) {
                        newAmount *= 1 - Double.parseDouble(etDiscount.getText().toString()) / 100;
                    }
                    tvTotalPay.setText("Total Bill: $" + String.format(".2f", newAmount));
                    int numPerson = Integer.parseInt(etPax.getText().toString());
                    if (numPerson != 1) {
                        tvEachPay.setText("Each Pays: $" + String.format(".2f", newAmount / numPerson));
                    } else {
                        tvEachPay.setText("Each Pays: $" + newAmount);
                    }
                }
            }
        });
        tgReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPax.setText("");
                tgSvs.setChecked(false);
                tgGst.setChecked(false);
                etDiscount.setText("");
            }
        });
    }
}