QWERTY Hebrew is a keyboard layout that maps the phonetic sound of English characters to their Hebrew counterparts.  So 'A' would map to 'א' (Aleph), and 'B' would map to 'ב' (Beit).  The normal Israeli layout is pretty difficult for a native English speaker to pick up, with no relationship between the English and Hebrew letters.

MacOS, iOS, and Windows all have some support for Hebrew QWERTY.  On Android, I haven't found it that difficult to pick up the default on-screen soft Hebrew keyboard, but I'd like to be in QWERTY mode when I plug in a physical Bluetooth keyboard.

We are going to create a custom keyboard InputMethodService (IMS).  The basics of how to do this are covered here [1].  An additional useful reference is here [2].

The approach is to override the onKeyUp method of the IMS as described in [2] above.  This is one of the methods that is triggered by the physical keyboard.  We will do three things in this method:

Get a reference to the current InputConnection, which we will serve as our output.  When 'a' is pressed, we will write (actually 'commit') 'א' to the InputConnection.
Switch on the keycode pressed, and do the right mapping from English keys to phonetic Hebrew letters.  We also need to handle the other keys on the keyboard, like DELETE, and ENTER.
Commit the new letter to the InputConnection which, per-above, is the pipeline between the keyboard, and higher level Android functionality.

Here is a rough example:

```
// Method below implemented in: 

// public class MyInputMethodService extends InputMethodService



public boolean onKeyUp(int primaryCode, KeyEvent event) {

    Log.d("HQWERTY", "keyup: " + primaryCode);
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
         //... rest of case statements
     }
     inputConnection.commitText(out, 1);
     return true;

}
```


Don't forget to register this service in the project's manifest.  Full disclosure - I grafted this code onto the working on-screen soft-keyboard example in [1] above.  It would probably work as a standalone IMS, but I didn't test that.

 A proof of concept, although incomplete, is hosted here on GitHub.  I will probably go back in and try to fully implement something usable as time permits, but if you are inclined to work on it or use it as a basis for your project - feel free. [ All chastisement due for posting such an unfinished example is deserved!]

Feel free to reach out if you want to connect around this project: http://linkedin.com/in/sqh
