/************************************************************************
 ����ͨѶ��ʾ����android������ͨѶ���룬�����ʾ��
 ����ͨѶ��������
 ׼ȷ��95%���ϣ���ʵһ���ǲ������ġ�
 �ӿڷǳ��򵥣���������ʾ����3���ӾͿ��������Ӧ����������ͨѶ����
 ��������ǿ�����������������ô���ţ��źŶ���׼ȷ��
 �����ı���Ϊ16���ƣ���ͨ������ɴ����κ��ַ�
 ���ܷǳ�ǿ��û�����в��˵�ƽ̨������ͨ���ڴ���Ż�����ʱ����벻�ٷ������ڴ棬��7*24Сʱ����
 ��֧���κ�ƽ̨��������ƽ̨android, iphone, windows, linux, arm, mipsel����ʾ��
 ����ɲ鿴��http://blog.csdn.net/softlgh
 ����: ҹ���� QQ:3116009971 �ʼ���3116009971@qq.com
 ************************************************************************/

package com.android.demo;

import voice.decoder.VoiceRecognition;
import voice.decoder.VoiceRecognitionListener;
import voice.encoder.VoicePlayer;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VoiceActivity extends Activity {
	
	public static final String TAG = "VoiceActivity";
	
    private final static int MSG_RECG_TEXT = 1;
    private final static int MSG_ERROR_PLAY_TEXT = 2;
    class MyHandler extends Handler
    {
        private TextView mRecognisedTextView;
        public MyHandler(TextView textView) {
            mRecognisedTextView = textView;
//            directiveTextView = _directiveTextView;
        }
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MSG_RECG_TEXT)
            {
                String s = (String)msg.obj;
                mRecognisedTextView.setText(s);
                Toast toast = Toast.makeText(VoiceActivity.this, s, Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(msg.what == MSG_ERROR_PLAY_TEXT)
            {
                String s = (String)msg.obj;
                Toast toast = Toast.makeText(VoiceActivity.this, s + " ������Ч��ʮ�������ַ���", Toast.LENGTH_SHORT);
                toast.show();
            }
            super.handleMessage(msg);
        }
    }

    VoicePlayer player;//����ͨѶ������
    VoiceRecognition recognition;//����ͨѶʶ����
	Handler handler;
    EditText toPlayText;
    ToneGenerator toneGenerator =new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //��������ͨѶ������
        player = new VoicePlayer();
        //��������ͨѶʶ����
        recognition = new VoiceRecognition();
        recognition.setListener(new VoiceRecognitionListener() {
            @Override
            public void onRecognitionStart() {
            	Log.i(TAG, "onRecognitionStart");
            }

            public void onRecognitionEnd(int _recogStatus, String _val) {
                if(_recogStatus == VoiceRecognition.Status_Success)
                {
                	Log.i(TAG, "onRecognitionEnd success");
                    handler.sendMessage(handler.obtainMessage(MSG_RECG_TEXT, _val));
                    toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
                }
                else
                {
                	Log.i(TAG, "onRecognitionEnd failure");
                    handler.sendMessage(handler.obtainMessage(MSG_RECG_TEXT, "errorCode:" + _recogStatus));
                }
            }
        });

        toPlayText = (EditText) this.findViewById(R.id.toPlayText);
        toPlayText.setText("12345");
        //��ʼ����
        Button playStart = (Button) this.findViewById(R.id.startPlay);
        playStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String toPlayString = toPlayText.getText().toString().trim();
                boolean validPlayString = toPlayString.length() > 0;
                if(validPlayString)
                {
                    for(int i = 0; i < toPlayString.length(); i ++)
                    {
                        char c = toPlayString.charAt(i);
                        if(!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')))
                        {
                            validPlayString = false;
                            break;
                        }
                    }
                }
                if(!validPlayString)
                {
                    handler.sendMessage(handler.obtainMessage(MSG_ERROR_PLAY_TEXT, toPlayString));
                    return;
                }
                player.play(toPlayString, 10, 1000);//����50�Σ����Ϊ1000ms
            }
        });

        //ֹͣ����
        Button playStop = (Button) this.findViewById(R.id.stopPlay);
        playStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                player.stop();
            }
        });

        final TextView recognisedTextView = (TextView) findViewById(R.id.regtext);
        handler = new MyHandler(recognisedTextView);
        //��ʼʶ��
        Button recognitionStart = (Button) this.findViewById(R.id.startReg);
        recognitionStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                recognition.start();
            }
        });

        //ֹͣʶ��
        Button recognitionStop = (Button) this.findViewById(R.id.stopReg);
        recognitionStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                recognisedTextView.setText("");
                recognition.stop();
            }
        });
        autoSetAudioVolumn();
    }

    //��������Ϊ60%
    public void autoSetAudioVolumn()
    {
        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_MUSIC );
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int)(max*0.6), 0);
    }
}

