package com.example.its_demo;

import jp.co.seino.sis.util.ActivityUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.initTitleBar(R.layout.activity_main, R.string.app_name,0,this);
	}


}
