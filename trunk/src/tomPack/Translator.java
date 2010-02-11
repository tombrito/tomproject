package tomPack;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import tomPack.externalization.Messages;

/**
 * @version 2009/11/08
 * @author Tom Brito
 */
public class Translator {

    // TODO Deve-se externalizar as strings? Ou depende apenas da JVM?
    // Se necessário: http://www.rgagnon.com/javadetails/JavaUIDefaults.txt

    public static void translate() {

	UIManager.put("OptionPane.yesButtonText", Messages.getString("Translator.1")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("OptionPane.cancelButtonText", Messages.getString("Translator.3")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("OptionPane.noButtonText", Messages.getString("Translator.5")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("OptionPane.okButtonText", Messages.getString("Translator.7")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.cancelButtonText", Messages.getString("Translator.9")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.cancelButtonToolTipText", //$NON-NLS-1$
		Messages.getString("Translator.11")); //$NON-NLS-1$

	UIManager.put("FileChooser.saveButtonText", Messages.getString("Translator.13")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.saveButtonToolTipText", //$NON-NLS-1$
		Messages.getString("Translator.15")); //$NON-NLS-1$

	UIManager.put("FileChooser.lookInLabelMnemonic", new Integer('E')); //$NON-NLS-1$
	UIManager.put("FileChooser.lookInLabelText", Messages.getString("Translator.18")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.saveInLabelText", Messages.getString("Translator.20")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.fileNameLabelMnemonic", new Integer('N')); // N //$NON-NLS-1$
	UIManager.put("FileChooser.fileNameLabelText", Messages.getString("Translator.23")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.filesOfTypeLabelMnemonic", new Integer('T')); // T //$NON-NLS-1$
	UIManager.put("FileChooser.filesOfTypeLabelText", Messages.getString("Translator.26")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.upFolderToolTipText", Messages.getString("Translator.28")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.upFolderAccessibleName", Messages.getString("Translator.30")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.homeFolderToolTipText", "Desktop"); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.homeFolderAccessibleName", "Desktop"); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.newFolderToolTipText", Messages.getString("Translator.36")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.newFolderAccessibleName", Messages.getString("Translator.38")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.listViewButtonToolTipText", Messages.getString("Translator.40")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.listViewButtonAccessibleName", Messages.getString("Translator.42")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.detailsViewButtonToolTipText", Messages.getString("Translator.44")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.detailsViewButtonAccessibleName", Messages.getString("Translator.46")); //$NON-NLS-1$ //$NON-NLS-2$

	UIManager.put("FileChooser.fileNameHeaderText", Messages.getString("Translator.48")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.fileSizeHeaderText", Messages.getString("Translator.50")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.fileTypeHeaderText", Messages.getString("Translator.52")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.fileDateHeaderText", Messages.getString("Translator.54")); //$NON-NLS-1$ //$NON-NLS-2$
	UIManager.put("FileChooser.fileAttrHeaderText", Messages.getString("Translator.56")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    public static void main(String[] args) {
	new JFileChooser().showOpenDialog(null);
    }

}
