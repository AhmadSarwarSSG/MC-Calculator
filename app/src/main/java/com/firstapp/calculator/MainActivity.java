package com.firstapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button zero;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button add;
    Button sub;
    Button mul;
    Button div;
    Button eql;
    Button dot;
    Button clear;
    Button Backspace;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zero=(Button) findViewById(R.id.zero);
        zero.setOnClickListener(this);
        one=(Button) findViewById(R.id.one);
        one.setOnClickListener(this);
        two=(Button) findViewById(R.id.two);
        two.setOnClickListener(this);
        three=(Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        four=(Button) findViewById(R.id.four);
        four.setOnClickListener(this);
        five=(Button) findViewById(R.id.five);
        five.setOnClickListener(this);
        six=(Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        seven=(Button) findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight=(Button) findViewById(R.id.eight);
        eight.setOnClickListener(this);
        nine=(Button) findViewById(R.id.nine);
        nine.setOnClickListener(this);

        add=(Button) findViewById(R.id.plus);
        add.setOnClickListener(this);
        sub=(Button) findViewById(R.id.minus);
        sub.setOnClickListener(this);
        mul=(Button) findViewById(R.id.Multiply);
        mul.setOnClickListener(this);
        div=(Button) findViewById(R.id.divide);
        div.setOnClickListener(this);
        eql=(Button) findViewById(R.id.equal);
        eql.setOnClickListener(this);
        dot=(Button) findViewById(R.id.dot);
        dot.setOnClickListener(this);

        clear=(Button) findViewById(R.id.Clear);
        clear.setOnClickListener(this);
        Backspace=(Button) findViewById(R.id.Backspace);
        Backspace.setOnClickListener(this);

        txt=(TextView) findViewById(R.id.textView);
    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zero:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+0);
                }
                else {
                    txt.setText(""+0);
                }
                break;
            case R.id.one:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+1);
                }
                else {
                    txt.setText(""+1);
                }
                break;
            case R.id.two:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+2);
                }
                break;
            case R.id.three:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+3);
                }
                break;
            case R.id.four:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+4);
                }
                break;
            case R.id.five:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+5);
                }
                break;
            case R.id.six:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+6);
                }
                break;
            case R.id.seven:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+7);
                }
                break;
            case R.id.eight:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+8);
                }
                break;
            case R.id.nine:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+9);
                }
                break;
            case R.id.plus:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+"+");
                }
                break;
            case R.id.minus:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+"-");
                }
                break;
            case R.id.Multiply:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+"*");
                }
                break;
            case R.id.divide:
                if(txt.getText().toString()!="0")
                {
                    txt.setText(""+txt.getText().toString()+"/");
                }
                break;
            case R.id.Clear:
                txt.setText("");
                break;
            case R.id.equal:
                txt.setText(""+eval(txt.getText().toString()));
                break;

        }
    }
}