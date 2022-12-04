package burp;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class TransStr {
    private String inputstr;
    private JTextArea utf8_decode;
    private JTextArea utf8_encode;
    private JTextArea sjis_decode;
    private JTextArea sjis_encode;
    private JTextArea base64_decode;
    private JTextArea base64_encode;

    public TransStr(){
        this.utf8_decode = new JTextArea();
        this.utf8_decode.setText("UTF-8 URL Decode");

        this.utf8_encode = new JTextArea();
        this.utf8_encode.setText("UTF-8 URL Encode");

        this.sjis_decode = new JTextArea();
        this.sjis_decode.setText("Shift-JIS URL Decode");

        this.sjis_encode = new JTextArea();
        this.sjis_encode.setText("UTF-8 URL Decode");

        this.base64_decode = new JTextArea();
        this.base64_decode.setText("Base64 Decode");

        this.base64_encode = new JTextArea();
        this.base64_encode.setText("Base64 Encode");
    }

    // Encode, Decode to the respective character set
    public TransStr updateStr(String inputstr){
        if(inputstr.length() == 0) {
            return this;
        }
        try {
            this.utf8_decode.setText(URLDecoder.decode(inputstr, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            this.utf8_decode.setText("(Error)");
        }

        try {
            this.utf8_encode.setText(URLEncoder.encode(inputstr, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            this.utf8_decode.setText("(Error)");
        }

        try {
            this.sjis_decode.setText(URLDecoder.decode(inputstr, "Shift_JIS"));
        } catch (UnsupportedEncodingException e) {
            this.utf8_decode.setText("(Error)");
        }

        try {
            this.sjis_encode.setText(URLEncoder.encode(inputstr, "Shift_JIS"));
        } catch (UnsupportedEncodingException e) {
            this.utf8_decode.setText("(Error)");
        }

        try {
            byte[] base64_decode_byte = Base64.getDecoder().decode(inputstr);
            String str_utf8 = new String(base64_decode_byte, "UTF-8");
            String str_sjis = new String(base64_decode_byte, "MS932");      // MS932はShiftJISの上位互換
            String outTxt = (new StringBuilder("UTF-8\n")).append(str_utf8).append("\n\nShift-JIS\n").append(str_sjis).toString();
            this.base64_decode.setText(outTxt);
        } catch (Exception e) {
            this.base64_decode.setText("-");
        }

        try {
            String byte_utf8 = Base64.getEncoder().encodeToString(inputstr.getBytes("UTF-8"));
            String byte_sjis = Base64.getEncoder().encodeToString(inputstr.getBytes("MS932"));      // MS932はShiftJISの上位互換
            String outTxt = (new StringBuilder("UTF-8\n")).append(byte_utf8).append("\n\nShift-JIS\n").append(byte_sjis).toString();
            this.base64_encode.setText(outTxt);
        } catch (Exception e) {
            this.base64_encode.setText("(Error)");
        }

        return this;
    }

    public List<JTextArea> getTextAreaList(){
        List<JTextArea> textlist = new ArrayList<JTextArea>();
        JTextArea aa = new JTextArea();
        textlist.add(this.utf8_decode);
        textlist.add(this.utf8_encode);
        textlist.add(this.sjis_decode);
        textlist.add(this.sjis_encode);
        textlist.add(this.base64_decode);
        textlist.add(this.base64_encode);
        return textlist;
    }
}
