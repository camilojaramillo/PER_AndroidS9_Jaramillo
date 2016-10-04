package com.example.camilo.per_semana9_jaramillo;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import mensaje.Mensaje;

public class Semana9_Jaramillo extends AppCompatActivity {

    private SeekBar r,g,b;
    private Button enviar;
    private EditText x,y,tam;
    private int rojo = 0, verde = 0,azul = 0;
    private ImageView color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semana9__jaramillo);

        x = (EditText) findViewById(R.id.x);
        y = (EditText) findViewById(R.id.y);
        tam = (EditText) findViewById(R.id.tam);
        enviar = (Button)findViewById(R.id.enviar);
        r = (SeekBar)findViewById(R.id.r);
        g = (SeekBar)findViewById(R.id.g);
        b = (SeekBar)findViewById(R.id.b);
        color = (ImageView)findViewById(R.id.color);

        r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rojo = progress;
                color.setBackgroundColor(Color.argb(255, rojo, verde, azul));
                System.out.println(rojo);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                verde = progress;
                color.setBackgroundColor(Color.argb(255, rojo, verde, azul));
                System.out.println(verde);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                azul = progress;
                color.setBackgroundColor(Color.argb(255, rojo, verde, azul));
                System.out.println(azul);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String posX = x.getText().toString();
                String posY = y.getText().toString();
                String diam = tam.getText().toString();

                int xx = Integer.parseInt(posX);
                int yy = Integer.parseInt(posY);
                int ttam = Integer.parseInt(diam);

                Mensaje m = new Mensaje(xx, yy, ttam, rojo, verde, azul);
                new Comando().execute(m);
                System.out.println(m.getX()+m.getY()+m.getTam()+m.getR()+ m.getG()+m.getB());
            }
        });
    }

    private class Comando extends AsyncTask<Mensaje, Integer, String> {

        DatagramSocket ds;
        InetAddress ia;
        int puerto = 5000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            try{
                ia = InetAddress.getByName("10.0.2.2");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            try {
                ds = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        @Override

        protected String doInBackground(Mensaje... params) {


            Mensaje byt = params [0];
            String mes = byt.getX() + "-" + byt.getY() +"-" + byt.getTam()+"-" + byt.getR() +"-"+ byt.getG() +"-" + byt.getB();
            byte[] misBytes = serializar(byt);
            DatagramPacket dp = new DatagramPacket(misBytes, misBytes.length, ia, puerto);

            try {
                ds.send(dp);
                System.out.println("Envia");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private byte[] serializar(Object data) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            bytes = baos.toByteArray();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
