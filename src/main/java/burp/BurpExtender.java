package burp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class BurpExtender implements IBurpExtender, IContextMenuFactory {
    // Extension Name
    public static final String EXTENSION_NAME = "Parameter Encode";

    // Extension Version
    public static final String VERSION = "1.0";

    // Context
    public static String STDOUT_REQ = "Requests Decoder";
    public static String STDOUT_RES = "Response Decoder";

    private IBurpExtenderCallbacks iBurpExtenderCallbacks;


    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.iBurpExtenderCallbacks = callbacks;
        this.iBurpExtenderCallbacks.setExtensionName(EXTENSION_NAME);
        this.iBurpExtenderCallbacks.registerContextMenuFactory(this);

        PrintWriter stdout = new PrintWriter(iBurpExtenderCallbacks.getStdout(), true);
        stdout.println("Activated version: " + VERSION);
    }

    // Create a menu to be displayed in the Burp Suite context menu
    public List<JMenuItem> createMenuItems(IContextMenuInvocation iContextMenuInvocation) {
        final IContextMenuInvocation in = iContextMenuInvocation;

        // Throw the currently selected request array to the execution method
        ExecAction execAction = new ExecAction(iBurpExtenderCallbacks, in.getSelectedMessages());

        // Create in context string and listeners
        List<JMenuItem> jMenuItemList = new LinkedList<>();
        JMenuItem jMenuItem_Req = new JMenuItem(STDOUT_REQ);
        jMenuItem_Req.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execAction.printReq();
            }
        });

        JMenuItem jMenuItem_Res = new JMenuItem(STDOUT_RES);
        jMenuItem_Res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execAction.printRes();
            }
        });

        // Add the List
        jMenuItemList.add(jMenuItem_Req);
        jMenuItemList.add(jMenuItem_Res);

        return jMenuItemList;
    }
}