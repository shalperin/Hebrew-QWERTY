package com.blauhaus.android.qwerty;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener{

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboard = new Keyboard(this, R.xml.keys_layout);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    public MyInputMethodService() {
        super();
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {
        Log.d("SAM", "release " + i);
    }

    @Override
    public void onKey(int primaryCode, int[] ints) {
        Log.d("SAM", "on key");
        InputConnection inputConnection = getCurrentInputConnection();
        if (inputConnection != null) {
            switch(primaryCode) {
                case Keyboard.KEYCODE_DELETE :
                    CharSequence selectedText = inputConnection.getSelectedText(0);

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }
                case Keyboard.KEYCODE_SHIFT:
                    caps = !caps;
                    keyboard.setShifted(caps);
                    keyboardView.invalidateAllKeys();
                    break;
                case Keyboard.KEYCODE_DONE:
                    inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));

                    break;
                default :
                    char code = (char) primaryCode;
                    if(Character.isLetter(code) && caps){
                        code = Character.toUpperCase(code);
                    }
                    inputConnection.commitText(String.valueOf(code), 1);

            }
        }
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public boolean onKeyUp(int primaryCode, KeyEvent event) {
        Log.d("SAM", "keyup: " + primaryCode);
        InputConnection inputConnection = getCurrentInputConnection();
        String out = "";
        switch(primaryCode) {
            case KeyEvent.KEYCODE_DEL :
                CharSequence selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
                break;
            case KeyEvent.KEYCODE_A:
                out = "א";
                break;
            case KeyEvent.KEYCODE_B:
                out = "ב";
                break;
            case KeyEvent.KEYCODE_C:
                out = "צ";
                break;
            case KeyEvent.KEYCODE_D:
                out = "ד";
                break;
            case KeyEvent.KEYCODE_E:
                out = "ע";
                break;
            case KeyEvent.KEYCODE_F:
                out = "פ";
                break;
            case KeyEvent.KEYCODE_G:
                out = "ג";
                break;
            case KeyEvent.KEYCODE_H:
                out = "ה";
                break;
            case KeyEvent.KEYCODE_I:
                out = "י";
                break;
            case KeyEvent.KEYCODE_J:
                out = "ח";
                break;
            case KeyEvent.KEYCODE_K:
                out = "כ";
                break;
            case KeyEvent.KEYCODE_L:
                out = "ל";
                break;
            case KeyEvent.KEYCODE_M:
                out = "מ";
                break;
            case KeyEvent.KEYCODE_N:
                out = "נ";
                break;
            case KeyEvent.KEYCODE_O:
                out = "ו";
                break;
            case KeyEvent.KEYCODE_P:
                out = "פ";
                break;
            case KeyEvent.KEYCODE_Q:
                out = "ק";
                break;
            case KeyEvent.KEYCODE_R:
                out = "ר";
                break;
            case KeyEvent.KEYCODE_S:
                out = "ס";
                break;
            case KeyEvent.KEYCODE_T:
                out = "ת";
                break;
            case KeyEvent.KEYCODE_U:
                out = "ו";
                break;
            case KeyEvent.KEYCODE_V:
                out = "ו";
                break;
            case KeyEvent.KEYCODE_W:
                out = "ש";
                break;
            case KeyEvent.KEYCODE_X:
                out = "ח";
                break;
            case KeyEvent.KEYCODE_Y:
                out = "ט";
                break;
            case KeyEvent.KEYCODE_Z:
                out = "ז";
                break;


            default:
                out = "yo";
        }

        inputConnection.commitText(out, 1);
        return true;
    }
}

