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
                int age = Integer.parseInt(edtAge.getText().toString());
                int weight = Integer.parseInt(edtWeight.getText().toString());
                int height = Integer.parseInt(edtHeight.getText().toString());
                int r = (int) (13.7516*weight + 5.0033*height - 6.7550*age + 66.4730);
                txtResult.setText("کالری مصرفی روزانه شما : " + (int)(r*act[tag])
                        + "\n برای افزایش یک کیلو در هفته " + ((int)(r*act[tag])+1100)
                        + "\nبرای کاهش یک کیلو در هفته "  + ((int)(r*act[tag])-1100));
            }
        });
    }
}
