import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ControleCalendario {
    public static LocalDate getDataAtual(){
        return java.time.LocalDate.now();
    }

    public static LocalDate formatarStringParaLocalDate(String data){
        DateTimeFormatter conversor = DateTimeFormatter.ofPattern("yyyy-MM-dd").withResolverStyle(ResolverStyle.STRICT);;
        LocalDate dataConvertida = LocalDate.parse(data, conversor);
        return dataConvertida;
    }

    public static String formatarLocalDateParaString(LocalDate data){
        /*
        LocalDate localDate = data;
        Date date = Date.valueOf(localDate);
        SimpleDateFormat conversor = new SimpleDateFormat("dd/MM/yyyy");
        String data = format.format(date);*/
        String dataFormatada = data.toString();
        dataFormatada = dataFormatada.replaceAll("-", "/");
        String[] elementosData = dataFormatada.split("/");
        dataFormatada = elementosData[2]+"/"+elementosData[1]+"/"+elementosData[0];

        return dataFormatada;
    }
    public static LocalDate somarDias(LocalDate data, int dias){
        return data.plusDays(10);
    }

    public static void main(String[] args) {
        System.out.println(formatarLocalDateParaString(getDataAtual()));
    }
}
