package com.fkomuniku.mapsuniku;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner a;
    TextView marquee;
    EditText b, c;
    RadioGroup rg;
    RadioButton rb1, rb2;
    ImageButton icon;
    public String[] gedung1 = {"Yayasan Sang Adipati Kuningan","Gedung Rektorat", "Gedung Student Center", "Mesjid UNIKU",
            "Perpustakaan UNIKU","Gedung KOPMA", "Pos Satpam", "Fakultas Ilmu Komputer", "Fakultas Kehutanan", "Fakultas Hukum",
            "Fakultas Keguruan dan Ilmu Pendidikan", "Fakultas Ekonomi"};
    public String[] gedung2 = {"Gedung Kampus 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        a = findViewById(R.id.sp_gedung);
        b = findViewById(R.id.x);
        c = findViewById(R.id.y);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        icon = findViewById(R.id.logo);
        marquee = findViewById(R.id.marqueeText);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gedung1);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gedung2);
        a.setAdapter(adapter1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    a.setAdapter(adapter1);
                } else if (checkedId == R.id.rb2) {
                    a.setAdapter(adapter2);
                }
            }
        });

        icon.startAnimation(animScale);
        startIconAnimasi();
        marqueeText();

        a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String p = a.getSelectedItem().toString();
                if(p == "Yayasan Sang Adipati Kuningan"){
                    b.setText("-6.97585144");
                    c.setText("108.5011774");
                }
                else if(p == "Gedung Rektorat"){
                    b.setText("-6.975341");
                    c.setText("108.500487");
                }
                else if(p == "Gedung Student Center"){
                    b.setText("-6.9746794");
                    c.setText("108.5005883");
                }
                else if(p == "Mesjid UNIKU"){
                    b.setText("-6.97515854");
                    c.setText("108.5011348");
                }
                else if(p == "Perpustakaan UNIKU"){
                    b.setText("-6.975921");
                    c.setText("108.500781");
                }
                else if(p == "Gedung KOPMA"){
                    b.setText("-6.976107");
                    c.setText("108.500977");
                }
                else if(p == "Pos Satpam"){
                    b.setText("-6.975635");
                    c.setText("108.501078");
                }
                else if(p == "Fakultas Ilmu Komputer"){
                    b.setText("-6.975718");
                    c.setText("108.500369");
                }
                else if(p == "Fakultas Kehutanan"){
                    b.setText("-6.9746444");
                    c.setText("108.4996093");
                }
                else if(p == "Fakultas Hukum"){
                    b.setText("-6.974358");
                    c.setText("108.500652");
                }
                else if(p == "Fakultas Keguruan dan Ilmu Pendidikan"){
                    b.setText("-6.97453354");
                    c.setText("108.5011677");
                }
                else if(p == "Fakultas Ekonomi"){
                    b.setText("-6.97409694");
                    c.setText("108.5003677");
                }
                else if(p == "Gedung Kampus 2"){
                    b.setText("-6.97577764");
                    c.setText("108.4774169");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void cari(View view) {
        Intent cari = new Intent(getApplicationContext(), Maps.class);
        cari.putExtra("a", a.getSelectedItem().toString());
        cari.putExtra("b", b.getText().toString());
        cari.putExtra("c", c.getText().toString());
        startActivity(cari);
    }

    private void startIconAnimasi() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(icon, "scaleY", 0.8f);
        scaleY.setDuration(200);
        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(icon, "scaleY", 1f);
        scaleYBack.setDuration(500);
        scaleYBack.setInterpolator(new BounceInterpolator());

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(1000);
        animatorSet.playSequentially(scaleY, scaleYBack);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.setStartDelay(1000);
                animatorSet.start();
            }
        });
        icon.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.aboutdialog);
                dialog.show();
            }
        });
    }

    private void marqueeText() {
        marquee.setText("Selamat datang di Aplikasi UNIKU MAPS, aplikasi ini dibuat untuk memenuhi salah satu tugas Mata Kuliah Cloud Computing");
        marquee.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Apakah Anda Mau Keluar?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Jika Yes
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Jika No
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
