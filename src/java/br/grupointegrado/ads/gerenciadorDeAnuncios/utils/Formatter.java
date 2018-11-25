package br.grupointegrado.ads.gerenciadorDeAnuncios.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @jgpancheski
 */
public final class Formatter {

    public static Double stringParaDouble(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (Exception e) {
            return 0d;
        }
    }

    public static Long stringParaLong(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (Exception e) {
            return 0l;
        }
    }

    public static Integer stringParaInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Date stringParaData(String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static String dataParaString(Date data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(data);
        } catch (Exception ex) {
            return "";
        }
    }
}
