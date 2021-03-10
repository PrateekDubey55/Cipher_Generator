package com.example.ccs_project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cButton;
    private Button aButton;
    private EditText msg;
    private EditText key1,key2;
    private TextView key2heading;
    private TextView key1heading;
    private TextView opMessage;
    private Switch cc,ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cButton=findViewById(R.id.cCipher);
        aButton=findViewById(R.id.affine);
        msg=findViewById(R.id.messageIp);
        key1=findViewById(R.id.keyIp);
        key2=findViewById(R.id.key2IP);
        opMessage=findViewById(R.id.outputMsg);
        cc=findViewById(R.id.ccSwitch);
        ac=findViewById(R.id.acSwitch);
        key2heading=findViewById(R.id.keydisplay2);
        key1heading=findViewById(R.id.keydisplay);

        cButton.setOnClickListener(this);
        aButton.setOnClickListener(this);
        cc.setOnClickListener(this);
        ac.setOnClickListener(this);
        cButton.setEnabled(false);
        aButton.setEnabled(false);
        key2heading.setVisibility(View.INVISIBLE);
        key2.setVisibility(View.INVISIBLE);
        key2.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int a=0,n=0,i=0,j=0,key=Integer.parseInt(key1.getText().toString());
        String alphabet1 = "abcdefghijklmnopqrstuvwxyz";
        String alphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char sp = ' ';
        String ipMsg;
        char alphabet01[] = alphabet1.toCharArray();
        char alphabet02[] = alphabet2.toCharArray();
        switch (v.getId()){
            case R.id.ccSwitch:
                if(cc.isChecked())
                {
                    cButton.setEnabled(true);
                }
                else {
                    cButton.setEnabled(false);
                }
                break;

            case R.id.acSwitch:

                if(ac.isChecked())
                {
                    key2heading.setVisibility(View.VISIBLE);
                    key2.setVisibility(View.VISIBLE);
                    key2.setEnabled(true);
                    key1.setWidth(500);
                    key1heading.setText("Enter Additive Key :");
                    aButton.setEnabled(true);
                }
                else {
                    aButton.setEnabled(false);
                    key2heading.setVisibility(View.INVISIBLE);
                    key2.setVisibility(View.INVISIBLE);
                    key2.setEnabled(false);
                    key1.setWidth(700);
                    key1heading.setText("Enter Key :");
                }
                break;

            case R.id.cCipher:
                ipMsg=msg.getText().toString();
                n=ipMsg.length();
                char msgCheck[] = ipMsg.toCharArray();
                int storeMsg[] = new int[n];
                char cipher[] = new char[n];
                for(i=0;i<ipMsg.length();i++){
                    for(j=0;j<alphabet1.length();j++){
                        if(msgCheck[i]>='a' && msgCheck[i]<='z')
                        {
                            if(msgCheck[i]==alphabet01[j]){
                                storeMsg[i] = j;
                                break;
                            }
                        }
                        else if(msgCheck[i]==' '){
                            storeMsg[i] = 0;
                        }
                        else
                        {
                            if(msgCheck[i]==alphabet02[j]){
                                storeMsg[i] = j;
                                break;
                            }
                        }

                    }
                }
                for(i=0;i<ipMsg.length();i++){
                    j = storeMsg[i];
                    if(msgCheck[i]>='a' && msgCheck[i]<='z') {
                        cipher[i] = alphabet01[(j + key) % alphabet1.length()];
                    }
                    else if(msgCheck[i]>='A' && msgCheck[i]<='Z')
                    {
                        cipher[i] = alphabet02[(j + key) % alphabet1.length()];
                    }
                    else
                    {
                        cipher[i] =sp;
                    }
                }
                opMessage.setText("Encrypted Message : "+new String(cipher));
                break;

            case R.id.affine:
                key=Integer.parseInt(key1.getText().toString());
                a=Integer.parseInt(key2.getText().toString());
                ipMsg=msg.getText().toString();
                n=ipMsg.length();
                char msgCheck1[] = ipMsg.toCharArray();
                int storeMsg1[] = new int[n];
                char storeTemp1[] = new char[n];
                char affine[] = new char[n];
                for(i=0;i<ipMsg.length();i++){
                    for(j=0;j<alphabet1.length();j++){
                        if(msgCheck1[i]>='a' && msgCheck1[i]<='z')
                        {
                            if(msgCheck1[i]==alphabet01[j]){
                                storeMsg1[i] = j;
                                break;
                            }
                        }
                        else
                        {
                            if(msgCheck1[i]==alphabet02[j]){
                                storeMsg1[i] = j;
                                break;
                            }
                        }
                    }
                }
                for(i=0;i<ipMsg.length();i++){
                    j = storeMsg1[i];
                    if(msgCheck1[i]>='a' && msgCheck1[i]<='z')
                    {
                        affine[i] = alphabet01[a*(j + key) % alphabet1.length()];
                    }
                    else
                    {
                        affine[i] = alphabet02[a*(j + key) % alphabet1.length()];
                    }
                }
                opMessage.setText("Encrypted Message : "+new String(affine));
                break;
        }
    }
}
