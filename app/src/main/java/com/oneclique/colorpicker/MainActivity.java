package com.oneclique.colorpicker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mConstraintLayoutBase;

    private TextView mTextViewR1, mTextViewR2;
    private TextView mTextViewG1, mTextViewG2;
    private TextView mTextViewB1, mTextViewB2;

    private int r1 = 0, r2 = 0;
    private int g1 = 0, g2 = 0;
    private int b1 = 0, b2 = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mConstraintLayoutBase = findViewById(R.id.mConstraintLayoutBase);

        LinearLayout mLinearLayoutAdd = findViewById(R.id.mLinearLayoutAdd);
        LinearLayout mLinearLayoutMin = findViewById(R.id.mLinearLayoutMin);

        Button mButtonInfo = findViewById(R.id.mButtonInfo);
        Button mButtonReset = findViewById(R.id.mButtonReset);

        mTextViewR1 = findViewById(R.id.mTextViewR1);   mTextViewR2 = findViewById(R.id.mTextViewR2);
        mTextViewG1 = findViewById(R.id.mTextViewG1);   mTextViewG2 = findViewById(R.id.mTextViewG2);
        mTextViewB1 = findViewById(R.id.mTextViewB1);   mTextViewB2 = findViewById(R.id.mTextViewB2);

        Button mButtonAddR1 = findViewById(R.id.mButtonAddR1);
        Button mButtonAddR2 = findViewById(R.id.mButtonAddR2);
        Button mButtonAddG1 = findViewById(R.id.mButtonAddG1);
        Button mButtonAddG2 = findViewById(R.id.mButtonAddG2);
        Button mButtonAddB1 = findViewById(R.id.mButtonAddB1);
        Button mButtonAddB2 = findViewById(R.id.mButtonAddB2);

        Button mButtonMinR1 = findViewById(R.id.mButtonMinR1);
        Button mButtonMinR2 = findViewById(R.id.mButtonMinR2);
        Button mButtonMinG1 = findViewById(R.id.mButtonMinG1);
        Button mButtonMinG2 = findViewById(R.id.mButtonMinG2);
        Button mButtonMinB1 = findViewById(R.id.mButtonMinB1);
        Button mButtonMinB2 = findViewById(R.id.mButtonMinB2);

        backColor(mLinearLayoutAdd);    backColor(mLinearLayoutMin);

        HexColor hex_Color = new HexColor();

        mButtonAddR1.setOnTouchListener(hex_Color);
        mButtonAddR2.setOnTouchListener(hex_Color);
        mButtonAddG1.setOnTouchListener(hex_Color);
        mButtonAddG2.setOnTouchListener(hex_Color);
        mButtonAddB1.setOnTouchListener(hex_Color);
        mButtonAddB2.setOnTouchListener(hex_Color);

        mButtonMinR1.setOnTouchListener(hex_Color);
        mButtonMinR2.setOnTouchListener(hex_Color);
        mButtonMinG1.setOnTouchListener(hex_Color);
        mButtonMinG2.setOnTouchListener(hex_Color);
        mButtonMinB1.setOnTouchListener(hex_Color);
        mButtonMinB2.setOnTouchListener(hex_Color);

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1 = r2 = g1 = g2 = b1 = b2 = 0;
                mTextViewR1.setText("0");
                mTextViewR2.setText("0");
                mTextViewG1.setText("0");
                mTextViewG2.setText("0");
                mTextViewB1.setText("0");
                mTextViewB2.setText("0");
                mConstraintLayoutBase.setBackgroundColor(Color.parseColor("#000000"));
            }
        });

        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.info_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.myFaceBook:{
                                OpenLink(Uri.parse("https://www.facebook.com/CRUSHBAGAKITADMOALAM"));
                                break;
                            }
                            case R.id.myTwitter:{
                                OpenLink(Uri.parse("https://twitter.com/JPapares_23"));
                                break;
                            }
                            case R.id.myLinkedIn:{
                                OpenLink(Uri.parse("https://www.linkedin.com/in/john-patrick-papares-3a3432187/"));
                                break;
                            }
                            case R.id.myGithub:{
                                OpenLink(Uri.parse("https://github.com/johnpatrick23"));
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    private void OpenLink(Uri uri){
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private class HexColor implements View.OnTouchListener{

        private int MinMax(int i){
            if(i > 15){
                i = 0;
            }
            else if(i < 0){
                i = 15;
            }
            return i;
        }

        private void fillColor(int i, TextView mTextView){
            char s = Integer.toHexString(i).charAt(0);
            if(Character.isLetter(s)){
                s = Character.toTitleCase(s);
            }
            mTextView.setText(Character.toString(s));
            String hexColor = "#" + mTextViewR1.getText().toString() + mTextViewR2.getText().toString() + mTextViewG1.getText().toString()
                    + mTextViewG2.getText().toString() + mTextViewB1.getText().toString() + mTextViewB2.getText().toString();
            mConstraintLayoutBase.setBackgroundColor(Color.parseColor(hexColor));
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                switch (v.getId()){
                    //region add
                    case R.id.mButtonAddR1:{
                        r1++;
                        r1 = MinMax(r1);
                        fillColor(r1, mTextViewR1);
                        break;
                    }
                    case R.id.mButtonAddR2:{
                        r2++;
                        r2 = MinMax(r2);
                        fillColor(r2, mTextViewR2);
                        break;
                    }
                    case R.id.mButtonAddG1:{
                        g1++;
                        g1 = MinMax(g1);
                        fillColor(g1, mTextViewG1);
                        break;
                    }
                    case R.id.mButtonAddG2:{
                        g2++;
                        g2 = MinMax(g2);
                        fillColor(g2, mTextViewG2);
                        break;
                    }
                    case R.id.mButtonAddB1:{
                        b1++;
                        b1 = MinMax(b1);
                        fillColor(b1, mTextViewB1);
                        break;
                    }
                    case R.id.mButtonAddB2:{
                        b2++;
                        b2 = MinMax(b2);
                        fillColor(b2, mTextViewB2);
                        break;
                    }
                    //endregion
                    //region min
                    case R.id.mButtonMinR1:{
                        r1--;
                        r1 = MinMax(r1);
                        fillColor(r1, mTextViewR1);
                        break;
                    }
                    case R.id.mButtonMinR2:{
                        r2--;
                        r2 = MinMax(r2);
                        fillColor(r2, mTextViewR2);
                        break;
                    }
                    case R.id.mButtonMinG1:{
                        g1--;
                        g1 = MinMax(g1);
                        fillColor(g1, mTextViewG1);
                        break;
                    }
                    case R.id.mButtonMinG2:{
                        g2--;
                        g2 = MinMax(g2);
                        fillColor(g2, mTextViewG2);
                        break;
                    }
                    case R.id.mButtonMinB1:{
                        b1--;
                        b1 = MinMax(b1);
                        fillColor(b1, mTextViewB1);
                        break;
                    }
                    case R.id.mButtonMinB2:{
                        b2--;
                        b2 = MinMax(b2);
                        fillColor(b2, mTextViewB2);
                        break;
                    }
                    //endregion
                }
                (v).setBackgroundResource(R.drawable.button_bg_ontouch);
                return true;
            }
            (v).setBackgroundResource(R.drawable.button_bg_untouch);
            return false;
        }
    }

    private void backColor(LinearLayout linearLayout){
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
    }

}
