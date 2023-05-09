package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultv,solutiontv;
    Button btnac,btnpercent,btnback;
    Button btndivide,btnmultiply,btnplus,btnminus;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btndot,btnequals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultv = findViewById(R.id.result_tv);
        solutiontv = findViewById(R.id.solution_tv);

        assignId(btnac,R.id.btn_AC);
        assignId(btnpercent,R.id.btn_percentage);
        assignId(btnback,R.id.btn_back);
        assignId(btndivide,R.id.btn_divide);
        assignId(btnmultiply,R.id.btn_multiple);
        assignId(btnplus,R.id.btn_plus);
        assignId(btnminus,R.id.btn_minus);
        assignId(btn0,R.id.btn_zero);
        assignId(btn1,R.id.btn_one);
        assignId(btn2,R.id.btn_two);
        assignId(btn3,R.id.btn_three);
        assignId(btn4,R.id.btn_four);
        assignId(btn5,R.id.btn_five);
        assignId(btn6,R.id.btn_six);
        assignId(btn7,R.id.btn_seven);
        assignId(btn8,R.id.btn_eight);
        assignId(btn9,R.id.btn_nine);
        assignId(btndot,R.id.btn_dot);
        assignId(btnequals,R.id.btn_equal);


    }

    void assignId(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       Button button = (Button) v;
       String buttonText = button.getText().toString();
       String dataCalculate = solutiontv.getText().toString();

       if (buttonText.equals("AC")){
           solutiontv.setText("");
           resultv.setText("0");
           return;
       }
       if (buttonText.equals("=")){
           solutiontv.setText(resultv.getText());
           return;
       }
       if(buttonText.equals("back")){
           dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);
           return;
       }else{
           dataCalculate = dataCalculate + buttonText;

       }

       solutiontv.setText(dataCalculate);

       String finalResult = getResult(dataCalculate);

       if(!finalResult.equals("err")){
           resultv.setText(finalResult);
       }
    }

    String getResult(String data){
       try {
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initSafeStandardObjects();
          String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
          if (finalResult.endsWith(".0")){
              finalResult = finalResult.replace(".0","");
          }
            return finalResult;
       }catch (Exception e){
           return  "err";
       }
    }
}