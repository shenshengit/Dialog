package com.shenshenff.gametest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private static final String TAG = "MainActivity";
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    private AlertDialog.Builder mBuilder;

    private final String[] mItems = {"item0", "item1", "item2", "item3", "item4", "item5", "item6"};


    //进度条
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();

    }

    private void initViews() {
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);

    }

    private void initEvents() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }

    int s = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setIcon(R.mipmap.ic_launcher);
                mBuilder.setTitle("你确定要离开吗？");
                mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里添加点击确认后的逻辑
                        showDialog("你选择了确定");
                    }
                });
                mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里添加点击取消后的逻辑
                        showDialog("你选择了取消");
                    }
                });
                mBuilder.create().show();
                break;

            case R.id.button2:
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setIcon(R.mipmap.ic_launcher);
                mBuilder.setTitle("投票");
                mBuilder.setMessage("你认为什么样的内容能吸引您？");
                mBuilder.setPositiveButton("有趣味的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDialog("你选择了有趣味的");
                    }
                });
                mBuilder.setNeutralButton("有思想的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDialog("你选择了有思想的");
                    }
                });
                mBuilder.setNegativeButton("主题强的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDialog("你选择了主题强的");
                    }
                });
                mBuilder.create().show();
                break;
            case R.id.button3:
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle("列表选择框");
                mBuilder.setItems(mItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDialog("您选择的ID为" + which + "，" + mItems[which]);
                    }
                });
                mBuilder.create().show();
                break;
            case R.id.button4:
                mBuilder = new AlertDialog.Builder(MainActivity.this);

                mBuilder.setIcon(R.mipmap.ic_launcher);
                mBuilder.setTitle("选项选择");
                mBuilder.setSingleChoiceItems(mItems, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // mSingleChoiceID = which;
                        s = which;
                        showDialog("你选择的id为" + which + "," + mItems[which]);
                    }
                });
                mBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (s >= 0) {
                            showDialog("你选择的是" + s);
                        }
                    }
                });
                mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mBuilder.create().show();
                break;
            case R.id.button5:
                //点击进度条框按钮后，开启一个线程计算读取的进度，假设读取结束位100
                //Progress在小于100的时候一直在线程中做循环++ 直到读取结束后，停止线程
                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setIcon(R.mipmap.ic_launcher);
                mProgressDialog.setTitle("进度条窗口");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setMax(100);
                mProgressDialog.setButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里添加点击后的逻辑
                    }
                });
                mProgressDialog.setButton2("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里添加点击后的逻辑
                    }
                });
                mProgressDialog.show();
                new Thread(this).start();

                break;

            case R.id.button6:
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                final ArrayList list = new ArrayList();
                list.clear();
                mBuilder.setIcon(R.mipmap.ic_launcher);
                mBuilder.setTitle("列表多项选择框");
                mBuilder.setMultiChoiceItems(mItems, new boolean[]{false, false, false, false, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            list.add(which);
                            showDialog("你选择的id为" + which + "," + mItems[which]);
                        } else {
                            list.remove(which);
                        }
                    }
                });
                mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "";
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            str += mItems[(int) list.get(i)] + ",";
                        }
                        showDialog("你选择的是" + str);
                    }
                });
                mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mBuilder.create().show();
                break;

            case R.id.button7:
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(this);
                final View view = inflater.inflate(R.layout.dialog_layout, null);
                mBuilder.setIcon(R.mipmap.ic_launcher);
                mBuilder.setTitle("自定义布局");
                mBuilder.setView(view);
                mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText userName = (EditText) view.findViewById(R.id.edittext1);
                        EditText password = (EditText) view.findViewById(R.id.edittext2);
                        showDialog("姓名：" + userName.getText().toString() + ",密码：" + password.getText().toString());
                    }
                });
                mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mBuilder.create().show();
                break;

            case R.id.button8:
                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setTitle("读取ing");
                mProgressDialog.setMessage("正在读取中请稍后");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                break;
        }
    }

    //这个dialog用于实现onClick后监听的内容信息
    private void showDialog(String str) {
        new AlertDialog.Builder(MainActivity.this).setMessage(str).show();
    }

    //开启线程用于模拟进度
    @Override
    public void run() {
        int Progress = 0;
        while (Progress < 100) {
            try {
                Thread.sleep(100);
                Progress++;
                mProgressDialog.incrementProgressBy(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
