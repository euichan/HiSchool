package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class TutorialFirstActivity extends Activity implements View.OnClickListener{

    private TextView am;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_first_activity);


        setSpinner(R.id.spinner_hour, R.array.hour_array, android.R.layout.simple_spinner_dropdown_item);
        setSpinner(R.id.spinner_min, R.array.min_array, R.layout.spinner_item);

        getSpinner(R.id.spinner_hour).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSpinner(R.id.spinner_min).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        am = (TextView)findViewById(R.id.am);
        am.setOnClickListener(this);

        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    public void setSpinner(int objId, int optionLabelId, int listStyle)
    {
        setSpinner(objId, optionLabelId, -1, listStyle, null);
    }

    public void setSpinner(int objId, int optionLabelId,
                           int optionId, int listStyle, String defaultVal)
    {
        String[] optionLavala = getResources().getStringArray(optionLabelId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, optionLavala);

        if(listStyle > -1) {
            adapter.setDropDownViewResource(listStyle);

            Spinner obj = (Spinner)findViewById(objId);
            obj.setAdapter(adapter);
            if(defaultVal != null)
            {
                String[] optiona = getResources().getStringArray(optionId);
                int thei = 0;
                for(int  a=0;a<optiona.length; a++)
                {
                    if(defaultVal.equals(optiona[a])){
                        thei = a;
                        break;
                    }
                }
                obj.setSelection(adapter.getPosition(optionLavala[thei]));
            }else{
                obj.setSelection(adapter.getPosition(defaultVal));
            }
        }
    }

    private Spinner getSpinner(int objId)
    {
        return (Spinner)findViewById(objId);
    }

    private String getSpinnerVal(int objId)
    {
        return getSpinnerVal(objId, null);
    }

    private String getSpinnerVal(int objId, String[] optiona)
    {
        String rtn = "";
        Spinner sp = (Spinner)findViewById(objId);
        if(sp!=null){
            int selectedIndex = sp.getSelectedItemPosition();
            if(optiona == null){
                rtn = ""+selectedIndex;
            }else{
                if(optiona.length> selectedIndex){
                    rtn = optiona[selectedIndex];
                }
            }
        }
        return rtn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_next:
                Intent it = new Intent(TutorialFirstActivity.this, TutorialSecondActivity.class);
                startActivity(it);
                finish();
                break;
            case R.id.am:
                if(am.getText().toString().equals("AM"))
                    am.setText("PM");
                else
                    am.setText("AM");
                break;
        }
    }
}
