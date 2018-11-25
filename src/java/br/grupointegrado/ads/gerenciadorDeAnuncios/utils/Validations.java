package br.grupointegrado.ads.gerenciadorDeAnuncios.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author Gabriel
 */
public class Validations {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean validaString(String valor, int minimo, int maximo) {
        return valor != null && valor.length() >= minimo && valor.length() <= maximo;
    }

    public static boolean validaDouble(double valor, double minimo, double maximo) {
        return valor >= minimo && valor <= maximo;
    }

    public static boolean validaLong(long valor, long minimo, long maximo) {
        return valor >= minimo && valor <= maximo;
    }

    public static boolean validaData(Date data, Date minimo, Date maximo) {

        return data != null && (data.after(minimo) || data.equals(minimo)) && (data.before(maximo) || data.equals(maximo));
    }

    public static boolean validaEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
