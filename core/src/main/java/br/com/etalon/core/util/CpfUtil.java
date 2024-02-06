package br.com.etalon.core.util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CpfUtil {

    public static String formatarCpfCnpj(String documento) throws ParseException {
        String documentoFormatado = documento.replace("-", "").replace(".", "").replace("/", "");
        if (documentoFormatado.length() == 11) {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(documentoFormatado);
        } else {
            MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(documentoFormatado);
        }
    }
}
