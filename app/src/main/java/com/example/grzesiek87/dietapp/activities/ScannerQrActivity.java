package com.example.grzesiek87.dietapp.activities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.grzesiek87.dietapp.QrScanFood.QrFood;
import com.example.grzesiek87.dietapp.QrScanFood.QrFoodViewModel;
import com.example.grzesiek87.dietapp.R;


public class ScannerQrActivity extends AppCompatActivity {

    private static final String LOGTAG ="ScanYourQR";
    private Button button;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    QrFoodViewModel qrFoodViewModel;
    QrFood qrFood;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_qr);
        qrFoodViewModel = ViewModelProviders.of(this).get(QrFoodViewModel.class);
        button = (Button) findViewById(R.id.scanButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScannerQrActivity.this, QrCodeActivity.class);
                startActivityForResult(i,REQUEST_CODE_QR_SCAN);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Metoda ta zajmuję się zeskanowanym parametrem. Jeśli spełnia on specjalne warunki tj. na 4
     * pozycjach w tablicy znajduje się znak '|' następuje wpis do bazy danych.
     * @param requestCode gdy jest równy 101, nastąpiło poprawne skanowanie
     * @param resultCode rezultat
     * @param data dane zakodowane w kodzie QR.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(ScannerQrActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            int tablica[] = ScannerQrActivity.returnTable(result);
            if (tablica[1] != 0 && tablica[2] != 0 && tablica[3] != 0){
                String nazwa = result.substring(tablica[0] + 1, tablica[1]);
                String waga = result.substring(tablica[1] + 1, tablica[2]);
                String kalorie = result.substring(tablica[2] + 1, tablica[3]);
                qrFood = new QrFood();
                qrFood.name = nazwa;
                qrFood.weight = waga;
                qrFood.energy = kalorie;
                qrFoodViewModel.insert(qrFood);
            }
            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(ScannerQrActivity.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
    }

    /**
     *
     * @param result przesłany łańcuch znaków
     * @return Metoda zwraca tablicę, która inforamuje na których pozycjach przesłanego do metody
     * łańcucha znaków znajduje się znak '|'
     */
    public static int [] returnTable(String result) {
        int tab[] = new int[4];
        int i = 0;
        int j = 0;
        while (i < result.length()) {
            if (result.charAt(i) == '|') {
                tab[j] = i;
                j++;
                i++;
            }
            i++;
        }
        return tab;
    }

}
