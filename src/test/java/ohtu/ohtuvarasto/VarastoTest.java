package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoNegatiivistaTilavuutta() {
        Varasto negatiivinenVarasto = new Varasto(-2);
        assertEquals(0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void valmiiksiTaydenVarastonLisays() {
        Varasto olemassaoleva = new Varasto(2, 1);
        assertEquals(2, olemassaoleva.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void olemassaolevanVarastonNegatiivinenTilavuus() {
        Varasto olemassaoleva = new Varasto(-2, 2);
        assertEquals(0, olemassaoleva.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void olemassaolevanVarastonNegatiivineSaldo() {
        Varasto olemassaoleva = new Varasto (4, -2);
        assertEquals(0, olemassaoleva.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void olemassaolevanVarastonLiikataytto() {
        Varasto olemassaoleva = new Varasto(2, 4);
        assertEquals(2, olemassaoleva.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysSivuutetaan() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenSivuutetaan() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(8);

        // metodin tulisi palauttaa vain varaston saldon verran
        assertEquals(8, varasto.otaVarastosta(9), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisätäLiikaa() {
        varasto.lisaaVarastoon(11);

        // metodin tulisi palauttaa 10 varaston koon mukaisesti
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toimiikoToString() {
        varasto.lisaaVarastoon(4);
        varasto.otaVarastosta(2);
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto.toString());
    }

}