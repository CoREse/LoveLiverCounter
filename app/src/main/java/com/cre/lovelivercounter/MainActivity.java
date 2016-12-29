package com.cre.lovelivercounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    long Count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readCount();

        refreshCount();
    }

    public void refreshCount()
    {
        TextView CountView=(TextView)findViewById(R.id.text_count);
        CountView.setText(String.valueOf(Count));
    }

    public void plusCount(View V)
    {
        ++Count;
        refreshCount();
    }

    public void minusCount(View V)
    {
        if (Count>0) --Count;
        refreshCount();
    }

    public void resetCount(View V)
    {
        Count=0;
        refreshCount();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        writeCount();
    }

    void writeCount()
    {
        SharedPreferences MyPref=this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor MyEditor=MyPref.edit();
        MyEditor.putLong("Count", Count);
        MyEditor.commit();
    }

    void readCount()
    {
        SharedPreferences MyPref=this.getPreferences(Context.MODE_PRIVATE);
        Count=MyPref.getLong("Count",0);
    }
}
