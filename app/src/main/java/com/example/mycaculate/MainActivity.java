package com.example.mycaculate;


import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    Button bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9, bt_pt;
    Button bt_mul, bt_div, bt_add, bt_sub, bt_tras, bt_squ;
    Button bt_clr, bt_del, bt_eq;
    EditText et_input;
    boolean clr_flag;    //判断et中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化对象
        setContentView(R.layout.activity_main);
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_pt = (Button) findViewById(R.id.bt_pt);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_sub = (Button) findViewById(R.id.bt_sub);
        bt_mul = (Button) findViewById(R.id.bt_mul);
        bt_div = (Button) findViewById(R.id.bt_div);
        bt_tras = (Button) findViewById(R.id.bt_tras);
        bt_squ = (Button) findViewById(R.id.bt_squ);
        bt_clr = (Button) findViewById(R.id.bt_clr);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_eq = (Button) findViewById(R.id.bt_eq);
        et_input = (EditText) findViewById(R.id.et_input);

        //设置按钮的点击事件
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_pt.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_sub.setOnClickListener(this);
        bt_mul.setOnClickListener(this);
        bt_div.setOnClickListener(this);
        bt_tras.setOnClickListener(this);
        bt_squ.setOnClickListener(this);
        bt_clr.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_eq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.bt_0:
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_pt:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break; //初始化数据
            case R.id.bt_add:
            case R.id.bt_sub:
            case R.id.bt_mul:
            case R.id.bt_div:

                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }//初始化数据
                if (str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷")) {
                    str = str.substring(0, str.indexOf(" "));
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;//输入运算符的格式为“ ”+“运算符”+“ ”
            case R.id.bt_clr:
                if (clr_flag)
                    clr_flag = false;
                str = "";
                et_input.setText("");
                break;//清空按钮
            case R.id.bt_del: //判断是否为空，然后在进行删除
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                } else if (str != null && !str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));//利用字符串长度-1的方法删除
                }
                break;

            case R.id.bt_eq: //单独运算最后结果

                getResult();
                break;
            case R.id.bt_tras:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                Tras();
                break;
            case R.id.bt_squ:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                Square();
                break;
        }
    }

    private void getResult() {
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals("")) return;
        //因为没有运算符所以不用运算
        if (!exp.contains(" ")) {
            return;
        }
        if (clr_flag) {
            clr_flag = false;
            return;
        }
        clr_flag = true;
        //截取运算符前面的字符串
        String s1 = exp.substring(0, exp.indexOf(" "));
        //截取的运算符
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        //截取运算符后面的字符串
        String s2 = exp.substring(exp.indexOf(" ") + 3);
        double cnt = 0;
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                cnt = d1 + d2;
            }
            if (op.equals("-")) {
                cnt = d1 - d2;
            }
            if (op.equals("×")) {
                cnt = d1 * d2;
            }
            if (op.equals("÷")) {
                if (d2 == 0) cnt = 0;//除数不能为0
                else cnt = d1 / d2;
            }





            if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {//有小数的情况
                int res = (int) cnt;
                et_input.setText(res + "");
            } else {
                et_input.setText(cnt + "");
            }
        }
        //s1不为空但s2为空
        else if (!s1.equals("") && s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            if (op.equals("+")) {
                cnt = d1;
            }
            if (op.equals("-")) {
                cnt = d1;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }


            if (!s1.contains(".")) {//有小数的情况
                int res = (int) cnt;

                et_input.setText(res + "");
            } else {
                et_input.setText(cnt + "");
            }
        }
        //s1是空但s2不是空
        else if (s1.equals("") && !s2.equals("")) {
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                cnt = d2;
            }
            if (op.equals("-")) {
                cnt = 0 - d2;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }
            if (!s2.contains(".")) {
                int res = (int) cnt;

                et_input.setText(res + "");
            } else {
                et_input.setText(cnt + "");
            }
        } else {
            et_input.setText("");
        }
    }

    public void Tras() {//正负数转化函数
        String exp = et_input.getText().toString();
        double answer = 0;
        if (exp.equals("")) {
            et_input.setText("");
        } else if (exp.equals(".")) {//单独的小数点不支持正负变换
            et_input.setText(exp);
        } else if (!exp.contains(" ")) {//整个字符串中不含“ ”，即只有一个操作数时
            answer = 0 - Double.parseDouble(exp);
            et_input.setText(answer + "");
        } else {
            String s = exp.substring(exp.indexOf(" ") + 3);
            if (s == null || s.equals("")) {
                et_input.setText(exp);
            } else {
                answer = 0 - Double.parseDouble(s);
                et_input.setText(exp.substring(0, exp.indexOf(" ") + 3) + answer + "");
            }//有两个操作数时对第二个操作数进行正负变换
        }
    }


    public void Square() {//平方函数
        String str = et_input.getText().toString();
        double answer;

        if (str.equals("")) {
            et_input.setText("");
        } else if (!str.contains(" ")) {//只有一个操作数时
            double x = Double.parseDouble(str);
            if (x > 2147483647) {
                Toast tip = Toast.makeText(getBaseContext(), "输入数据超出范围", Toast.LENGTH_SHORT);
                tip.show();
                et_input.setText(str);//Toast方法给出提示
            } else {
                if (str.contains(".")) {//小数的平方
                    double a = Double.parseDouble(str);
                    answer = a * a;
                    et_input.setText(answer + "");
                } else {
                    int y = Integer.parseInt(str);//正整数的平方
                    answer = y * y;
                    if (answer > 2147483647) {
                        Toast tip = Toast.makeText(getBaseContext(), "输入数据超出范围", Toast.LENGTH_SHORT);
                        tip.show();
                        et_input.setText(str);
                    } else {
                        et_input.setText(answer + "");
                    }
                }
            }

        } else {
            String s = str.substring(str.indexOf(" ") + 3);
            String o = str.substring(str.indexOf(" "),str.indexOf(" ") + 2);
            if (s == null || s.equals("")) {
                et_input.setText(str);
            }
            else {
                double x = Double.parseDouble(s);
                if (x > 2147483647) {
                    Toast tip = Toast.makeText(getBaseContext(), "输入数据超出范围", Toast.LENGTH_SHORT);
                    tip.show();
                    et_input.setText(str);
                } else {
                    if (s.contains(".")) {
                        double a = Double.parseDouble(s);
                        answer = a * a;
                        et_input.setText(answer + "");
                    } else {
                        int y = Integer.parseInt(s);
                        answer = y * y;
                        if (answer > 2147483647) {
                            Toast tip = Toast.makeText(getBaseContext(), "输入数据超出范围", Toast.LENGTH_SHORT);
                            tip.show();
                            et_input.setText(str);
                        } else {if(o.contains("-")){
                            et_input.setText(answer+ "");}//复数的平方为正数
                        else
                            et_input.setText(str.substring(0, str.indexOf(" ") + 3) + answer+ "");
                        }
                    }
                }
            }

        }


    }
}
