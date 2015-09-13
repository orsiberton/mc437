package br.com.jsflab.view.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@ManagedBean
@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable {

    protected static final int BUFFER_SIZE = 1024;
    protected static final String BUNDLE_NAME = "bundle";
    protected static final String EMPTY_STRING = "";

    @PostConstruct
    protected abstract void inicializar();

    public FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }

    public ServletContext getContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    public String getRealPath(String path) {
        return getContext().getRealPath(path);
    }

    public String getRealPath() {
        return getRealPath(EMPTY_STRING);
    }

    public UIViewRoot getViewRoot() {
        return getCurrentInstance().getViewRoot();
    }

    public ExternalContext getExternalContext() {
        return getCurrentInstance().getExternalContext();
    }

    private Flash getFlash() {
        return getExternalContext().getFlash();
    }

    public void setFlashObject(String key, Object value) {

        if (key == null || value == null) {
            //TODO i18n
            throw new IllegalArgumentException("Parâmetros key e value não podem ser nulos");
        }

        getFlash().put(key, value);
    }

    public Object getFlashObject(String key) {
        return getFlash().get(key);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(BUNDLE_NAME, getViewRoot().getLocale());
    }

    public String getMessage(String key) {
        if (key == null) {
            //TODO i18n
            throw new IllegalArgumentException("Key não pode ser nula");
        }
        return getResourceBundle().getString(key);
    }

    public String getMessage(String key, String... parameters) {

        String mensagem = getMessage(key);
        if (parameters == null) {
            //TODO i18n
            throw new IllegalArgumentException("Parameters não pode ser nulo");
        }

        internacionalizarParametros(parameters);
        mensagem = MessageFormat.format(mensagem, (Object[]) parameters);

        return mensagem;
    }

    private void internacionalizarParametros(String... arrayParametros) {

        if (arrayParametros == null) {
            return;
        }

        for (String parametro : arrayParametros) {

            String parametroInternacionalizado = getMessage(parametro);
            parametro = (parametroInternacionalizado == null) ? parametro : parametroInternacionalizado;
        }
    }

    protected String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
}