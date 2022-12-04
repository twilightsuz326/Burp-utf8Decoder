package burp;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.io.PrintWriter;

public class ExecAction{

    private IBurpExtenderCallbacks iBurpExtenderCallbacks;
    private IHttpRequestResponse[] httpRequestResponseArray;


    public ExecAction(IBurpExtenderCallbacks iBurpExtenderCallbacks, IHttpRequestResponse[] httpRequestResponseArray) {
        this.iBurpExtenderCallbacks = iBurpExtenderCallbacks;
        this.httpRequestResponseArray = httpRequestResponseArray;
    }

    // Encode/decode based on request  (Eventually the response will use the same method.)
    public void printReq(){
        JFrame frame = new JFrame("Parameter Encode");
        JPanel panel = new JPanel();

        // Create two horizontal grids
        panel.setLayout(new GridLayout(1, 2));

        // Create,Add left area
        JTextArea leftTextArea = new JTextArea();
        leftTextArea.setLineWrap(true);
        JScrollPane scroll_leftPanel = new JScrollPane(leftTextArea);
        leftTextArea.setText(new String(httpRequestResponseArray[0].getRequest()));
        panel.add(scroll_leftPanel);

        // Create,Add Right area
        JPanel rightPanel = new JPanel();
        panel.add(rightPanel);
        rightPanel.setLayout(new GridLayout(3, 2));

        // Create multiple text areas with the TransStr class
        TransStr transStr = new TransStr();
        for (JTextArea jt : transStr.getTextAreaList()){
            jt.setLineWrap(true);
            rightPanel.add(jt);
        }

        // Decode, Encode when the selection is changed
        leftTextArea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                // Get Selection
                int start = leftTextArea.getSelectionStart();
                int end = leftTextArea.getSelectionEnd();
                String selectedText = leftTextArea.getText().substring(start, end);

                // Execute updateStr method on selected String
                transStr.updateStr(selectedText);
            }
        });

        frame.add(panel);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    public void printRes(){
        // Unimplemented method. To be deleted.
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "まだ未実装です。");

        PrintWriter stdout = new PrintWriter(iBurpExtenderCallbacks.getStdout(), true);
        stdout.println("printRes");
        String res = new String(httpRequestResponseArray[0].getResponse());
        stdout.println(res);
    }

}
