package com.android.demo;

import com.android.demo.view.MTextView;

import infzm.text.Html;
import infzm.text.method.LinkMovementMethod;
import infzm.text.util.TypefaceLoader;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity{

	private TextView tv1;
	private infzm.text.TextView tv2;
	private LinearLayout layout;
	private MTextView tv3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (infzm.text.TextView) findViewById(R.id.tv2);
		layout = (LinearLayout) findViewById(R.id.layout);
		tv3 =  (MTextView) findViewById(R.id.tv3);
		
		TypefaceLoader mTFLoader = TypefaceLoader.getInstance(this);
		Typeface mTypeface = mTFLoader.getTypefaceCache().get("方正兰亭黑");
		
		String string = "我卡卡卡的吗， 按开机动画啊， ；急哦我安康，， 啊看得开啊“罚款卡”， 建啊看到罚款的饭卡卡卡的九分裤爱的空房间爱看电视就发生的积分卡《阿斯顿发》阿斯顿发阿里看到就发觉带额外奥斯卡的法解释道房间啊司法考试的发";
		tv1.setText(string);
		
		CharSequence mSpanned = (CharSequence)string;
		
		
		tv2.getPaint().setTextSize(44);
		tv2.getPaint().setTypeface(mTypeface);
		tv2.setLineSpacing(0,1);
		tv2.setTextColor(Color.WHITE);
		tv2.getPaint().setAntiAlias(true);
		tv2.setText(Html.fromHtml("<p class=\"image\">" + string + "</p>"));
		tv2.setMovementMethod(LinkMovementMethod.getInstance());
		
		tv3.getPaint().setTypeface(mTypeface);
		tv3.setTextSize(15);
		tv3.setMText(string);
		
	}
}


