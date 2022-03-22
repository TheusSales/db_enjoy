package com.enjoy.Tests;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import com.enjoy.Configs.DbConn;
import com.enjoy.Entities.Bebida;
import com.enjoy.Entities.Consumacao;
import com.enjoy.Entities.Consumidor;
import com.enjoy.Entities.Endereco;
import com.enjoy.Entities.EntityDefault;
import com.enjoy.Entities.Estabelecimento;
import com.enjoy.Entities.Enums.EstadosEnum;

public class InsertTest {

    static DbConn db;
    static Random random = new Random(Calendar.getInstance().getTimeInMillis());
    static TipoBebida tipo1;
    static TipoBebida tipo2;
    static int lastDay = 1;

    public static void main(String[] args) throws SQLException {
        startDatabase();

        popularDB();

        // Como não estava explicito nas exigencias se eram em relação a um só consumidor ou todos do estabelecimento,
        // nós optamos por fazer em relação à todos os consumidores do estabelecimento
        // (mas descomentando as linhas 101 e 102, ficará relativo a um só consumidor)!
        pegarBebidasFavoritas();
        calcularTotalMedio();
        pegarUltimaVisita();
        calcularFrequenciaVisitas(30);
        calcularFrequenciaVisitas(61);
        calcularFrequenciaVisitas(365);

        DbConn.getInstance().close();
    }

    private static void pegarUltimaVisita() {
        String sql = "select c.dt_consumacao, e.nm_estabelecimento from t_consumacao c, t_estabelecimento e" +
            " where e.id_estabelecimento = 1 order by dt_consumacao desc fetch first 1 rows only";
        var consEst = (Object[])EntityDefault.queryNative(sql).getSingleResult();
        Timestamp ultimaVisita = (Timestamp)consEst[0];
        String nomeEst = (String)consEst[1];
        System.out.println("Ultima visita ao " + nomeEst + ": " + ultimaVisita.toString());
    }

    private static void calcularFrequenciaVisitas(int intervalo) {
        String sql = "select sum(to_number(extract(day from c.dt_consumacao))) / " + intervalo + " media, e.nm_estabelecimento from t_consumacao c, t_estabelecimento e " +
            "where e.id_estabelecimento = (select id_estabelecimento from t_estabelecimento order by DBMS_RANDOM.RANDOM fetch first 1 rows only) " +
                "and (c.dt_consumacao between (sysdate -" + intervalo + ") and sysdate) group by e.nm_estabelecimento order by media desc";
        var res = EntityDefault.queryNative(sql).getResultList();
        if (res.size() > 0){
            var resArray = (Object[])res.get(0);
            var frequencia = ((BigDecimal)resArray[0]).floatValue();
            var nomeEst = resArray[1];
            System.out.println(String.format("Frequencia de visitas no %s: %.2f vezes a cada %d dias.", nomeEst, frequencia, intervalo));
        } else{
            System.out.println("Esse estabelecimento não recebeu nenhuma visita registrada no periodo de " + intervalo + " dias!");
        }
    }

    private static void calcularTotalMedio() {
        String sql = "select sum(total_consumacao) / count(*) from t_consumacao where" +
            " id_estabelecimento = (select id_estabelecimento from t_estabelecimento order by DBMS_RANDOM.RANDOM fetch first 1 rows only)";
        var totalMedio = EntityDefault.queryNative(sql).getSingleResult();
        System.out.println(String.format("Total médio gasto no estabelecimento: %.2f", totalMedio));
    }

    private static void pegarBebidasFavoritas() {
        System.out.println("****Bebidas favoritas ****");
        
        String sql = "select b.id_bebida, count(cb.id_bebida) countConsBeb from " +
                        "t_consumacaobebida cb, t_bebida b where cb.id_bebida = b.id_bebida and " +
                            "(select id_estabelecimento from t_consumacao where id_consumacao = cb.id_consumacao) =" +
                                " (select id_estabelecimento from t_estabelecimento order by DBMS_RANDOM.RANDOM fetch first 1 rows only) " +
                                    // "and (select t_consumacao.id_consumidor from t_consumacao " +
                                    //     "where t_consumacao.id_consumacao = cb.id_consumacao) = " + c.getId() + " " + 
                                            "group by b.id_bebida order by countConsBeb desc fetch first 4 rows only";
        var bebidas = EntityDefault.queryNative(sql).getResultList().stream().toArray();
        int classificacao = 1;
        for (var b : bebidas) {
            Object[] values = (Object[])b;
            Bebida bebida = (Bebida)EntityDefault.find(Bebida.class, ((BigDecimal)values[0]).intValue());
            System.out.println(classificacao + "- " + bebida.getNome());
            classificacao++;
        }
        System.out.println("********************************");
    }

    private static void popularDB() {
        criarTiposBebidas();
        criarBebidas();
        
        Estabelecimento e = new Estabelecimento("Irmãos Plens", "R. José Bonifácio, 655 - Nova, Guareí - SP, 18250-000");        
        Consumidor c = new Consumidor("987098710","Matheus","46486320877",Calendar.getInstance(),"matheush_sales@hotmail.com",
                new Endereco(18250000, EstadosEnum.SP, "QualquerUm", "Jofácio Bonisé", 556));
        Consumidor c2 = new Consumidor("987098710","Thiago","46486320877",Calendar.getInstance(),"thiago_guar@hotmail.com",
            new Endereco(18250000, EstadosEnum.SP, "QualquerUm", "Jofácio Bonisé 2", 5562));
        c.add(false);
        c2.add(false);
        e.add();

        gerarConsumacoesAleatorias(c, e);
        gerarConsumacoesAleatorias(c2, e);

        c.add();

        System.out.println("Dados adicionados com sucesso!");
    }

    private static void criarTiposBebidas() {
        tipo1 = new TipoBebida("Ale");
        tipo2 = new TipoBebida("Pilsen");
        tipo1.add();
        tipo2.add();
    }

    private static void criarBebidas() {
        for (int i = 0; i < 80; i++) {
            new Bebida("Bebida Aleatória " + i, random.nextInt(100) > 50 ? tipo1 : tipo2).add(false);
        }
        new Bebida("Bebida Aleatória ", random.nextInt(100) > 50 ? tipo1 : tipo2).add();
    }

    private static void gerarConsumacoesAleatorias(Consumidor consumidor, Estabelecimento e) {
        for (int i = 0; i < random.nextInt(2000) + 1; i++) {
            gerarConsumacaoAleatoria(consumidor, e);
        }
    }

    private static void gerarConsumacaoAleatoria(Consumidor consumidor, Estabelecimento e) {
        Consumidor c = consumidor;

        int day = random.nextInt(28);
        int month = random.nextInt(12);
        int year = random.nextInt(25) + 2000;
        Calendar d = getDate(day, month, year);
        if (random.nextInt(100) > 50) {
            d = Calendar.getInstance();
            d.add(Calendar.DAY_OF_MONTH, -lastDay);
        }
        Consumacao cons = new Consumacao(d, random.nextFloat() * 100, c, e);
        String sql = "select * from T_Bebida b order by DBMS_RANDOM.RANDOM fetch first " + (random.nextInt(24) + 1) + " rows only";
        for (var bebidas : EntityDefault.findNativeWhere(Bebida.class, sql)) {
            cons.addBebida((Bebida) bebidas);
        }
        c.addConsumacao(cons);
        c.add();

        lastDay++;
    }

    private static void startDatabase() throws SQLException {
        db = DbConn.getInstance();
    }
 
    private static Calendar getDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        try {
            cal.setTime(sdf.parse(String.format("%02d", day) + " " + String.format("%02d", month) + " " + String.format("%04d", year)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
