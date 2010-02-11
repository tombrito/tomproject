package tomPack.swing.images;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @version 04/11/2009
 * @author 	Tom Brito
 */
public class Images {
	
	public static Icon getNo() {
		URL url = Images.class.getResource("no16.png"); //$NON-NLS-1$
		return new ImageIcon(url);
	}

	public static Icon getYes() {
		URL url = Images.class.getResource("yes16.png"); //$NON-NLS-1$
		return new ImageIcon(url);
	}

}
