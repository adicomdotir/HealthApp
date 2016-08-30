package ir.adicom.app.healthapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    RadioButton radioButton;
    String[] r;
    TextView txtActi;
    EditText edtAge, edtWeight, edtHeight;
    ToggleButton toggleButton;
    TextView txtResult;
    int tag = 0;
    float act[] = {1.2f, 1.375f,1.55f,1.725f, 1.9f};
    // For BMI
    EditText edtWeightBMI,edtHeightBMI;
    Button btnBMI;
    TextView txtResultBMI, txt1, txt2, txt3, txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = getResources().getStringArray(R.array.array_activity);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("BMR");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("BMI");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("PAC");
        host.addTab(spec);

        for (int i = 0; i < host.getChildCount(); i++) {
            host.getChildAt(i).setBackgroundResource(R.color.colorPrimary);
        }

        for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#ffffff"));
        }

        txtActi = (TextView) findViewById(R.id.textView7);
        edtAge = (EditText) findViewById(R.id.editText3);
        edtWeight = (EditText) findViewById(R.id.editText2);
        edtHeight = (EditText) findViewById(R.id.editText);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        txtResult = (TextView) findViewById(R.id.textView6);

        RadioGroup radio = (RadioGroup) findViewById(R.id.radiogroup);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                int _tag= Integer.parseInt((String) radioButton.getTag());
                txtActi.setText("" + r[_tag]);
                tag = _tag;
            }
        });

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = toggleButton.isChecked();
                int age = Integer.parseInt(edtAge.getText().toString());
                int weight = Integer.parseInt(edtWeight.getText().toString());
                int height = Integer.parseInt(edtHeight.getText().toString());
                int r;
                if(b==false) {
                    r = (int) (13.7516 * weight + 5.0033 * height - 6.7550 * age + 66.4730);
                } else {
                    r = (int) (9.5634 * weight + 1.8496 * height - 4.6756 * age + 655.0955);
                }
                txtResult.setText("کالری مصرفی روزانه شما : " + (int)(r*act[tag])
                        + "\n برای افزایش یک کیلو در هفته " + ((int)(r*act[tag])+1100)
                        + "\nبرای کاهش یک کیلو در هفته "  + ((int)(r*act[tag])-1100));
            }
        });

        // Code for tab BMI
        btnBMI = (Button) findViewById(R.id.button2);
        edtHeightBMI = (EditText) findViewById(R.id.editText6);
        edtWeightBMI = (EditText) findViewById(R.id.editText5);
        txtResult = (TextView) findViewById(R.id.textView8);
        txt1 = (TextView) findViewById(R.id.textView13);
        txt2 = (TextView) findViewById(R.id.textView14);
        txt3 = (TextView) findViewById(R.id.textView15);
        txt4 = (TextView) findViewById(R.id.textView16);
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int w = Integer.parseInt(edtWeightBMI.getText().toString());
                int h = Integer.parseInt(edtHeightBMI.getText().toString());
                float bmi = (float) w/(float) (h*h);
                bmi *= 10000;
                txtResult.setText("شاخص : " + bmi + "\n" +
                    "بهترین وزن : " + (h-102));
                if (bmi<18.5) {
                    txt1.setTextColor(getResources().getColor(R.color.green));
                    txt2.setTextColor(Color.BLACK);
                    txt3.setTextColor(Color.BLACK);
                    txt4.setTextColor(Color.BLACK);
                } else if(bmi>=18.5 && bmi<25) {
                    txt2.setTextColor(getResources().getColor(R.color.green));
                    txt1.setTextColor(Color.BLACK);
                    txt3.setTextColor(Color.BLACK);
                    txt4.setTextColor(Color.BLACK);
                } else if(bmi>=25 && bmi<30) {
                    txt3.setTextColor(getResources().getColor(R.color.green));
                    txt2.setTextColor(Color.BLACK);
                    txt1.setTextColor(Color.BLACK);
                    txt4.setTextColor(Color.BLACK);
                } else {
                    txt4.setTextColor(getResources().getColor(R.color.green));
                    txt2.setTextColor(Color.BLACK);
                    txt3.setTextColor(Color.BLACK);
                    txt1.setTextColor(Color.BLACK);
                }
            }
        });

        PAC();
    }

    EditText edtAgePac,edtWeiPac,edtHeiPac,edtTimePac;
    RadioGroup radioGroupPac;
    Button btnPac;
    TextView txtResultPac;
    int indexRadioGroup=0;

    private void PAC() {
        txtResultPac = (TextView) findViewById(R.id.textView19);
        edtAgePac = (EditText) findViewById(R.id.editText31);
        edtWeiPac = (EditText) findViewById(R.id.editText21);
        edtHeiPac = (EditText) findViewById(R.id.editText11);
        edtTimePac = (EditText) findViewById(R.id.editText4);
        btnPac = (Button) findViewById(R.id.button3);
        radioGroupPac = (RadioGroup) findViewById(R.id.radiogroup2);

        radioGroupPac.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radio = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                indexRadioGroup = Integer.parseInt((String) radio.getTag());
            }
        });

        btnPac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int w = Integer.parseInt(edtWeiPac.getText().toString());
                int t = Integer.parseInt(edtTimePac.getText().toString());
                float res = 0;
                switch (indexRadioGroup) {
                    case 0:
                        res = ((float) t/60)*((float) w/73)*438;
                        // 438 - 73kg
                        break;
                    case 1:
                        res = ((float) t/60)*((float) w/73)*986;
                        // 986 - 73kg
                        break;
                    case 2:
                        res = ((float) t/60)*((float) w/73)*292;
                        // 292 - 73kg
                        break;
                    case 3:
                        res = ((float) t/60)*((float) w/73)*511;
                        // 511 - 73kg
                        break;
                }
                txtResultPac.setText("کالری مصرفی : " + (int) res);
            }
        });
    }
}
