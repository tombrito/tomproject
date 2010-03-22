package tomPack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Cleanup;

/**
 * Development utilities.
 * 
 * @version 2009/12/05
 * @author Tom Brito
 */
public class TomUtils {

    /**
     * Returns <code>true</code> if any argument object is <code>null</code>.
     */
    public static boolean isNull(final Object... objects) {
	for (Object o : objects) {
	    if (o == null) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Throws a {@link NullPointerException} if any argument object is
     * <code>null</code>.
     * 
     * @param objects
     *            - Object to check.
     */
    public static void assertNotNull(Object... objects) {
	for (Object o : objects) {
	    if (o == null) {
		throw new NullPointerException();
	    }
	}
    }

    /**
     * Copia o conteúdo do buffer de entrada e grava no buffer de saída.
     * 
     * @param inputFileName
     * @param outputFileName
     * @return <code>true</code> se o conteúdo foi copiado com sucesso. Se
     *         ocorrer qualquer Exception, retorna <code>false</code>.
     */
    public static boolean copyFile(String inputFileName, String outputFileName) {

	boolean success = true;
	byte[] buffer;

	try {
	    @Cleanup
	    InputStream in = new FileInputStream(inputFileName);
	    @Cleanup
	    OutputStream out = new FileOutputStream(outputFileName);
	    buffer = new byte[in.available()];

	    in.read(buffer);
	    out.write(buffer);
	    out.flush();

	} catch (Exception e) {
	    e.printStackTrace();
	    success = false;
	}

	return success;

    }

    /**
     * Prefira usar {@link #sameValues(WEntity, WEntity)}.<br>
     * Retorna true se dois objetos forem iguais (.equals()) ou ambos nulos.
     * Também funciona com primitivos como parametro.
     * 
     * @see #sameValues(WEntity, WEntity)
     * @param o1
     * @param o2
     * @return
     * 
     */
    public static boolean equals(Object o1, Object o2) {
	return (o1 == null) ? (o2 == null) : o1.equals(o2);
    }

    /**
     * Retorna true se os parametros forem iguais. Mais formalmente, retorna
     * true se ambos forem nulos ou se e1.sameValue(e2) retornar true.
     * 
     * @param e1
     * @param e2
     * @return
     */
    public static boolean sameValues(TomEntity e1, TomEntity e2) {

	// if one is null, the other needs to be too.
	if ((e1 == null) || (e2 == null)) {
	    return ((e1 == null) && (e2 == null));
	}

	if (e1.getClass() != e2.getClass()) {
	    return false;
	}

	return e1.sameValues(e2);

    }

    /**
     * Search for one, and only one, mask in value. For it, this method use the
     * regex methods {@link Pattern} and {@link Matcher}.
     * 
     * @param mask
     *            - mask to search at value
     * @param value
     *            - value to search the mask
     * @return <code>true</code> if find one time the mask at the value.
     */
    public static boolean find(String mask, String value) {
	Pattern p = Pattern.compile(mask); // e.g. I0.0, Q0.0
	Matcher m = p.matcher(value);
	return m.find();
    }

    public static String readFile(String filename) throws IOException {
	@Cleanup
	BufferedReader reader = new BufferedReader(new FileReader(filename));
	String line = null;
	StringBuilder stringBuilder = new StringBuilder();
	String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	while ((line = reader.readLine()) != null) {
	    stringBuilder.append(line);
	    stringBuilder.append(lineSeparator);
	}
	return stringBuilder.toString();
    }

}
