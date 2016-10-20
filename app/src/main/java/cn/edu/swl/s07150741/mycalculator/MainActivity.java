package cn.edu.swl.s07150741.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calculatorButton;
    private EditText weightEdiText;
    private RadioButton manRadiobutton,womanRadiobutton;
    private TextView resultTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButton = (Button) findViewById(R.id.calculate);
        weightEdiText = (EditText) findViewById(R.id.weight);
        manRadiobutton = (RadioButton) findViewById(R.id.man);
        womanRadiobutton = (RadioButton) findViewById(R.id.woman);
        resultTextview = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
     calculatorButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(!weightEdiText.getText().toString().trim().equals("")){
                 if(manRadiobutton.isChecked()||womanRadiobutton.isChecked()){
                     Double weight = Double.parseDouble(weightEdiText.getText().toString());
                    StringBuffer sb = new StringBuffer();
                     sb.append("-------评估结果-----");
                     if(manRadiobutton.isChecked()){
                         sb.append("男性标准身高：");
                         double result = evaluateHeight(weight,"男");
                         sb.append((int)result+"厘米");
                     }else{
                         sb.append("女性标准身高：");
                         double result = evaluateHeight(weight,"女");
                         sb.append((int)result+"厘米");
                     }
                     resultTextview.setText(sb.toString());
                 }else{
                     showMessage("请选择性别！");
                 }
             }else{
               showMessage("请输入体重！");
             }
         }
     });
    }

    private void showMessage(String s) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(s);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private double evaluateHeight(Double weight, String sex) {
      double height;
        if (sex=="男"){
            height = 170 - (62 - weight) / 0.6;
        }else{
            height = 158 - (52 - weight) / 0.5;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case 1:
               finish();;
               break;
       }
        return super.onOptionsItemSelected(item);
    }
}
