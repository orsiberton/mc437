package br.com.jsflab.view.mb;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "configMB")
@SessionScoped
public class ConfigMB extends AbstractMB{

    private static final long serialVersionUID = -8168079468992950249L;

    private String linguaEscolhida = "pt";

    private Map<String, String> linguas;

    @PostConstruct
    public void inicializar() {

        gerarLinguas();
    }

    private void gerarLinguas() {

        linguas = new HashMap<String, String>();
        //TODO i18n
        linguas.put(getMessage("portugues"), "pt");
        linguas.put(getMessage("ingles"), "en");
    }

    public void alterarIdioma() {

        getViewRoot().setLocale(new Locale(linguaEscolhida));
        gerarLinguas();
    }

    public String getLinguaEscolhida() {

        return linguaEscolhida;
    }

    public void setLinguaEscolhida(String linguaEscolhida) {

        this.linguaEscolhida = linguaEscolhida;
    }

    public Map<String, String> getLinguas() {

        return linguas;
    }

    public void setLinguas(Map<String, String> linguas) {

        this.linguas = linguas;
    }
}
