package burp;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test {


    // TextAreaをフィールドとして定義します
    private Map<String, JTextArea> map;

    public Test(){
        // Swingフレームを作成する
        JFrame frame = new JFrame("Parameter Encode");

        // JPanelを作成します
        JPanel panel = new JPanel();

        // JPanelにGridLayoutを設定します
        panel.setLayout(new GridLayout(1, 2));

        // JPanelの左側にTextAreaを追加します
        JTextArea leftTextArea = new JTextArea();
        JScrollPane scroll_leftPanel = new JScrollPane(leftTextArea);
        leftTextArea.setText("testssss");
        panel.add(scroll_leftPanel);

        // JPanelの右側にJPanelを追加します
        JPanel rightPanel = new JPanel();
        panel.add(rightPanel);

        // 右側のJPanelにGridLayoutを設定します
        rightPanel.setLayout(new GridLayout(3, 2));

        // リストの数だけのテキストエリアを作成
        // A-Fの文字列のリストを作成する
        TransStr transStr = new TransStr();
        List<String> list = Arrays.asList("utf8_d", "utf_e", "sjis_d", "sjis_e", "base64_d", "base64_e");

        // リストをもとにHashMapを作成する
        /*
        map = new HashMap<>();
        for (String s : list) {
            JTextArea textArea = new JTextArea();
            map.put(s, new JTextArea(transStr.getMap().get(s)));
            rightPanel.add(textArea);
        }
         */

        for (JTextArea jt : transStr.getTextAreaList()){
            jt.setLineWrap(true);
            rightPanel.add(jt);
        }


        // JTextAreaクラスにcaretUpdate()イベントリスナーを追加する
        leftTextArea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                // テキストエリアの選択範囲を取得する
                int start = leftTextArea.getSelectionStart();
                int end = leftTextArea.getSelectionEnd();

                // 選択範囲の文字列を取得する
                String selectedText = leftTextArea.getText().substring(start, end);

                // 別のテキストボックスに表示する
                transStr.updateStr(selectedText);
            }
        });

        // JPanelをJFrameに追加します
        frame.add(panel);

        // フレームのサイズを設定する
        frame.setSize(700, 400);

        // フレームを表示する
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        Test main = new Test();
    }
}
