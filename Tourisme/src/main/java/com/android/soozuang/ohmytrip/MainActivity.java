package com.android.soozuang.ohmytrip;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout main_l1, main_l2;
    Animation down;
    Animation left;
    Animation right;
    Button btnSignIn, btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        // Load Animation
        down = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        left = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        right = AnimationUtils.loadAnimation(this, R.anim.righttoleft);

        main_l1 = (LinearLayout)findViewById(R.id.main_l1);
        main_l2 = (LinearLayout) findViewById(R.id.main_l2);
        main_l1.setAnimation(left);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        btnSignIn.setAnimation(down);
        btnSignUp.setAnimation(down);
        txtSlogan =(TextView)findViewById(R.id.txtSlogan);
        txtSlogan.setTextSize(getResources().getDimension(R.dimen.textsize));
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);

        /*btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        }); */

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(MainActivity.this, SignIn.class);
                startActivity(signUp);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signIn = new Intent(MainActivity.this, SignUp.class);
                startActivity(signIn);
            }
        });



    }
}
