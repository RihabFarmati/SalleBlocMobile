package com.example.sallebloc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.sallebloc.beans.Occupation;
import com.example.sallebloc.beans.Results;
import com.example.sallebloc.instance.Api;
import com.example.sallebloc.instance.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sallebloc.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private boolean occupe=false;
    private String idO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
                intentIntegrator.setPrompt("for flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();

            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    ///////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult.getContents() != null )
        //when the result is note null
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            //time yyyy-mm-dd
            Calendar cal=Calendar.getInstance();

            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            String formatted = format1.format(cal.getTime());
            //time HH:mm:ss
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
            String h = format2.format(cal.getTime());
            String string=intentResult.getContents();
            string=string.replace("\"", "");

            try {
                builder.setMessage("You are going to occupy "+string+",the current date is "+ formatted+" \n From "+crenaux(h));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //set position button
            String finalString = string;
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //dismiss dialog
                    dialogInterface.dismiss();
                    try {
                        createOccupation(finalString,formatted,crenaux(h));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });
            //show alert
            System.out.println("call the function create occupation");

            builder.show();
        }else{
            Toast.makeText(getApplicationContext(),"Oops you did not scan anything",Toast.LENGTH_SHORT).show();
        }


    }
    public String crenaux(String s) throws ParseException {
        String result=s;
        try {
            ///////////////////////seance 1 /////////////////////////////////////////////////////////
            String string11 = "8:30:00";
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string11);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);
            String string12 = "10:20:00";
            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string12);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);
            ///////////////////////seance 2 /////////////////////////////////////////////////////////
            String string21 = "10:30:00";
            Date time21 = new SimpleDateFormat("HH:mm:ss").parse(string21);
            Calendar calendar21 = Calendar.getInstance();
            calendar21.setTime(time21);
            calendar21.add(Calendar.DATE, 1);
            String string22 = "12:20:00";
            Date time22 = new SimpleDateFormat("HH:mm:ss").parse(string22);
            Calendar calendar22 = Calendar.getInstance();
            calendar22.setTime(time22);
            calendar22.add(Calendar.DATE, 1);
            ///////////////////////seance 3 /////////////////////////////////////////////////////////
            String string31 = "13:30:00";
            Date time31 = new SimpleDateFormat("HH:mm:ss").parse(string31);
            Calendar calendar31 = Calendar.getInstance();
            calendar31.setTime(time31);
            calendar31.add(Calendar.DATE, 1);
            String string32 = "15:20:00";
            Date time32 = new SimpleDateFormat("HH:mm:ss").parse(string32);
            Calendar calendar32 = Calendar.getInstance();
            calendar32.setTime(time32);
            calendar32.add(Calendar.DATE, 1);
            ///////////////////////seance 4 /////////////////////////////////////////////////////////
            String string41 = "15:30:00";
            Date time41 = new SimpleDateFormat("HH:mm:ss").parse(string41);
            Calendar calendar41 = Calendar.getInstance();
            calendar41.setTime(time41);
            calendar41.add(Calendar.DATE, 1);
            String string42 = "17:20:00";
            Date time42 = new SimpleDateFormat("HH:mm:ss").parse(string42);
            Calendar calendar42 = Calendar.getInstance();
            calendar42.setTime(time42);
            calendar42.add(Calendar.DATE, 1);
            //////////////////////////////////my time//////////////////////////////////////////:
            String someRandomTime = s;
            Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);
            Date x = calendar3.getTime();

            ////////////////////////////...Compare ///
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                result="08:30 To 10:20";
            }
            if (x.after(calendar21.getTime()) && x.before(calendar22.getTime())) {
                result="10:30 To 12:20";
            }
            if (x.after(calendar31.getTime()) && x.before(calendar32.getTime())) {
                result="13:30 To 15:20";
            }
            if (x.after(calendar41.getTime()) && x.before(calendar42.getTime())) {
                result="15:30 To 17:20";
            }
            ;;;;;;;;;;;//System.out.println(".,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"+x.after(calendar31.getTime()) +"   "+ x.before(calendar32.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    };

    void createOccupation(String name, String formatted,String h){
        Occupation occupation=new Occupation(formatted,h,name);
        System.out.println("in function create occupation");
        Api api= RetrofitClient.getInstance().getMyApi();
        Call<List<Results>> call=api.createOccupation(occupation);

        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                if(myheroList==null){
                    String d="You successfully occupied ";
                    notif(d);
                }else{
                    String[] oneHeroes = new String[myheroList.size()];

                    for (int i = 0; i < myheroList.size(); i++) {
                        oneHeroes[i] = myheroList.get(i).getId();

                        if(oneHeroes[i]!=""){
                            occupe=true;
                            idO=oneHeroes[0];
                            System.out.println("++++++++++++++++++++++++++++++++"+ oneHeroes[i]);
                            //Toast.makeText(QrCodeActivity.this, "deja occupe : "+idO, Toast.LENGTH_SHORT).show();
                            /////////////////////////Dialog alert
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            // Setting Alert Dialog Title
                            alertDialogBuilder.setTitle("the room is already occupied..!!!");
                            // Icon Of Alert Dialog
                            alertDialogBuilder.setIcon(R.mipmap.cancel_foreground);
                            // Setting Alert Dialog Message
                            alertDialogBuilder.setMessage("You want to free the room?");
                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Call<List<Results>> call2=api.deleteOccupation(idO);
                                    ////////////////////////////////////////////////////////
                                    call2.enqueue(new Callback<List<Results>>() {
                                        @Override
                                        public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                                            List<Results> myheroList = response.body();
                                            System.out.println("call the delete method");
                                        }
                                        @Override
                                        public void onFailure(Call<List<Results>> call, Throwable t) {
                                            // Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                                        }

                                    });
                                    //////////////////////////////////////////////////////////
                                }
                            });

                            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();


                        }else{
                            String d="You successfully occupied ";
                            notif(d);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });

    }
    String getTheIdO(String name, String formatted,String h){
        Occupation occupation=new Occupation(formatted,h,name);
        System.out.println("in function create occupation");
        Api api=RetrofitClient.getInstance().getMyApi();
        Call<List<Results>> call=api.createOccupation(occupation);

        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getId();
                    System.out.println("++++++++++++++++++++++++++++++++"+ oneHeroes[i]);

                    idO=oneHeroes[i];


                }
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
        return idO;
    }
    /////////////////////notification////////////////////////////////////////////
    public void notif(String donne) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel("id1", "channel 1",
                            NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Description");
            notificationChannel.setShowBadge(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{500, 1000, 500, 1000, 500});

            ////channel 2
            NotificationChannel notificationChannel2 =
                    new NotificationChannel("id2", "channel 2",
                            NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel2.setDescription("Description");
            notificationChannel2.setShowBadge(true);
            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(Color.BLUE);
            notificationChannel2.enableVibration(true);
            notificationChannel2.setVibrationPattern(new long[]{500, 1000, 500, 1000, 500});

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.createNotificationChannel(notificationChannel);
            nm.createNotificationChannel(notificationChannel2);


            ///notification

            Notification notification = new NotificationCompat.Builder(getApplicationContext(),"id1" )
                    .setSmallIcon(R.mipmap.education_foreground)

                    .setContentTitle("New Occupation")
                    .setContentText(donne)
                    .build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);

            NotificationChannel channel = nm.getNotificationChannel("id1");

        }
    }
}