package com.uffizio.utilities;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;

public class ClipboardData {
	public static String pasteTheCopiedContentFromClipboard() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String text = null;
		if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			try {
				text = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return text;
	}
}
